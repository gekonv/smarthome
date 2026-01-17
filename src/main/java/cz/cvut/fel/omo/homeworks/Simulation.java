package cz.cvut.fel.omo.homeworks;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.cvut.fel.omo.homeworks.devices.Device;
import cz.cvut.fel.omo.homeworks.devices.DeviceAPI;
import cz.cvut.fel.omo.homeworks.devices.DeviceFactory;
import cz.cvut.fel.omo.homeworks.devices.DeviceFactoryImpl;
import cz.cvut.fel.omo.homeworks.events.Event;
import cz.cvut.fel.omo.homeworks.events.Events;
import cz.cvut.fel.omo.homeworks.house.*;
import cz.cvut.fel.omo.homeworks.livingEntities.Animals.AnimalType;
import cz.cvut.fel.omo.homeworks.livingEntities.Animals.Pet;
import cz.cvut.fel.omo.homeworks.livingEntities.Humans.Adult;
import cz.cvut.fel.omo.homeworks.livingEntities.Humans.Child;
import cz.cvut.fel.omo.homeworks.livingEntities.Humans.Person;
import cz.cvut.fel.omo.homeworks.reports.*;
import cz.cvut.fel.omo.homeworks.sportEquipment.Bike;
import cz.cvut.fel.omo.homeworks.sportEquipment.Skateboard;
import cz.cvut.fel.omo.homeworks.sportEquipment.SportEquipment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;

public class Simulation {
    private List<Room> rooms;
    private List<Device> devices;
    private List<Person> people;
    private List<Pet> pets;
    private List<Child> children;
    private List<Sensor> sensors;
    private List<SportEquipment> sportEquipments;
    private House house;
    private String PATH;
    ObjectMapper mapper;
    JsonNode json;
    private static final Logger logger = Logger.getLogger(Simulation.class.getName());

    public Simulation(String path) {
        this.PATH = path;
        generate();
    }

    public void simulate(int actions) {
        for(int i = 0; i < actions; i++){
            randomAction();
            randomEvent();
            randomSport();
        }
        createRerports();
    }

    private void randomSport() {
        Person person = people.get(ThreadLocalRandom.current().nextInt(0, people.size()));
        SportEquipment equipment = sportEquipments.get(ThreadLocalRandom.current().nextInt(0, sportEquipments.size()));
        person.sport(equipment);

    }

    private void randomEvent() {
        int num = ThreadLocalRandom.current().nextInt(0, Events.values().length);
        Sensor sensor = sensors.get(ThreadLocalRandom.current().nextInt(0, sensors.size()));

        switch (num) {
            case 0:
                Child child = children.get(ThreadLocalRandom.current().nextInt(0, children.size()));
                child.cry();
                break;
            case 1:
                sensor.detectEvent(new Event(Events.WINDY, ""));
                break;
            case 2:
                sensor.detectEvent(new Event(Events.SUNNY, ""));
                break;
            case 3:
                Device device = devices.get(ThreadLocalRandom.current().nextInt(0, devices.size()));
                house.logEvent(new EventLog(device.toString(), " broke"));
                device.wreck();
                break;
        }
    }

    private void randomAction() {
        Person person = people.get(ThreadLocalRandom.current().nextInt(0, people.size()));
        Device device = devices.get(ThreadLocalRandom.current().nextInt(0, devices.size()));

        int num = ThreadLocalRandom.current().nextInt(0, 3);
        switch (num) {
            case 0:
                person.useDevice(device);
                break;
            case 1:
                person.turnOff(device);
                break;
            case 2:
                person.turnIdle(device);
                break;
        }
    }

    private void createRerports() {
        HouseConfigurationReport h = new HouseConfigurationReport(house);
        ActivityAndUsageReport a = new ActivityAndUsageReport(house);
        EventReport e = new EventReport(house);
        ConsumptionReport c = new ConsumptionReport(house);
        for (Device device : devices) {
            DeviceAPI deviceAPI = new DeviceAPI(device);
            deviceAPI.accept(c);
        }
        h.generateReport();
        a.generateReport();
        e.generateReport();
        c.generateReport();
    }

    private void generate() {
        this.rooms = new ArrayList<>();
        this.devices = new ArrayList<>();
        this.people = new ArrayList<>();
        this.pets = new ArrayList<>();
        this.children = new ArrayList<>();
        this.sensors = new ArrayList<>();
        this.sportEquipments = new ArrayList<>();
        getJson();
        getHouseAndFloors();
        getRoomAndDevices();
        getPeople();
        getPets();
        getEquipment();
    }

    private void getJson() {
        this.mapper = new ObjectMapper();
        try {
            this.json = mapper.readTree(new File(PATH));
        } catch (IOException e) {
            logger.warning("Couldnt get JSON file with error msg: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void getHouseAndFloors() {
        //getting a house
        JsonNode houseNode = json.get("house");
        this.house = new House(houseNode.get("id").asInt());
        //getting floors
        JsonNode floorsNode = json.get("floors");
        for(JsonNode floorNode : floorsNode){
            Floor floor = new Floor(this.house, floorNode.get("level").asInt());
            this.house.add(floor);
        }
    }

    private void getRoomAndDevices() {
        JsonNode roomsNode = json.get("rooms");
        for(JsonNode roomNode : roomsNode){
            //finds the floor for the room
            List<Component> c = house.getChildren();
            for(Component f : c){
                Floor floor = (Floor) f;
                if(floor.getLevel() == roomNode.get("floor").asInt()){
                    RoomBuilder roomBuilder = new RoomBuilder(floor, roomNode.get("name").asText());
                    //creates and adds devices to rooms
                    addDevicesToRoom(roomBuilder, roomNode);
                    //adds a sensor
                    Sensor sensor = new Sensor(house);
                    this.sensors.add(sensor);
                    roomBuilder.addSensor(sensor);
                    //creates and adds windows to rooms then subsribes to sensor
                    addWindowsToRoom(roomBuilder, roomNode);
                    Room room = roomBuilder.build();
                    floor.add(room);
                    this.rooms.add(room);
                }
            }
        }
    }

    private void addDevicesToRoom(RoomBuilder roomBuilder, JsonNode roomNode) {
        JsonNode devicesNode = roomNode.get("devices");
        JsonNode room = roomNode.get("name");
        if(devicesNode != null){
            for(JsonNode deviceNode : devicesNode) {
                Device device = createDevice(deviceNode.asText(), room.asText());
                if(device != null){
                    roomBuilder.addDevice(device);
                    this.devices.add(device);
                }
            }
        }
    }

    private void addWindowsToRoom(RoomBuilder roomBuilder, JsonNode roomNode) {
        JsonNode windowsNode = roomNode.get("windows");
        if(windowsNode != null){
            for(int i = 0; i < windowsNode.asInt(); i++) {
                roomBuilder.addWindow(new Window(house));
            }
        }
    }

    private Device createDevice(String deviceType, String roomName){
        DeviceFactory deviceFactory = new DeviceFactoryImpl();
            switch (deviceType){
                case "FEEDER":
                    return deviceFactory.createFeeder(roomName);
                case "FRIDGE":
                    return deviceFactory.createFridge(roomName);
                case "STOVE":
                    return deviceFactory.createStove(roomName);
                case "TV":
                    return deviceFactory.createTV(roomName);
                case "MICROWAVE":
                    return deviceFactory.createMicrowave(roomName);
                case "PC":
                    return deviceFactory.createPc(roomName);
                case "WASHING_MACHINE":
                    return deviceFactory.createWashingMachine(roomName);
                case "DRIER":
                    return deviceFactory.createDrier(roomName);
                default:
                    logger.warning("Unknown device type: " + deviceType);
                    return null;
            }
    }

    private void getPeople() {
        JsonNode peopleNode = json.get("people");
        List<Adult> adults = new ArrayList<>();
        for(JsonNode personNode : peopleNode){
            Room room = house.getRoomByName(personNode.get("room").asText());
            if(room != null){
                Person person;
                if(personNode.get("adult").asText().equals("yes")){
                    person = new Adult(personNode.get("name").asText(), room, this.house);
                    adults.add((Adult) person);
                } else {
                    person = new Child(personNode.get("name").asText(), room, this.house);
                    children.add((Child) person);
                }
                room.addPerson(person);
                this.people.add(person);
            } else{
                logger.warning("room " + personNode.get("room").asText() + " doesnt exist for person: " + personNode.get("name").asText());
            }
        }
        for (Child child : children) {
            for (Adult adult : adults) {
                child.addSubscriber(adult);
            }
        }
        for(Device device : this.devices) {
            for(Adult adult : adults){
                device.addSubscriber(adult);
            }
        }
    }

    private void getPets() {
        JsonNode petsNode = json.get("pets");
        for(JsonNode petNode : petsNode){
            Room room = house.getRoomByName(petNode.get("room").asText());
            if(room != null){
                Pet pet = new Pet(AnimalType.valueOf(petNode.get("type").asText()), room, petNode.get("name").asText(), this.house);
                room.addPet(pet);
                this.pets.add(pet);
            } else{
                logger.warning("room " + petsNode.get("room").asText() + " doesnt exist for pet: " + petNode.get("name").asText());
            }
        }
    }

    private void getEquipment() {
        JsonNode uquipNode = json.get("equipment");
        for(JsonNode uqNode : uquipNode){
            if(uqNode.get("type").asText().equals("bike")){
                sportEquipments.add(new Bike());
            }
            else if(uqNode.get("type").asText().equals("skateboard")){
                sportEquipments.add(new Skateboard());
            }
            else{
                logger.warning("Unknown equipment type: " + uqNode.get("type").asText());
            }
        }
    }
}
