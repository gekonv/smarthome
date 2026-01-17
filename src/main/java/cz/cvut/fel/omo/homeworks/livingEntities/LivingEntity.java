package cz.cvut.fel.omo.homeworks.livingEntities;

import cz.cvut.fel.omo.homeworks.devices.Device;
import cz.cvut.fel.omo.homeworks.devices.DeviceAPI;
import cz.cvut.fel.omo.homeworks.house.House;
import cz.cvut.fel.omo.homeworks.house.Room;
import cz.cvut.fel.omo.homeworks.livingEntities.Animals.Pet;
import cz.cvut.fel.omo.homeworks.livingEntities.Humans.Person;
import cz.cvut.fel.omo.homeworks.reports.ActionLog;

public class LivingEntity {
    private Room room;
    private String name;
    private House house;

    /**
     *
     * @param name
     * @param room
     * @param house
     */
    public LivingEntity(String name, Room room, House house) {
        this.room = room;
        this.name = name;
        this.house = house;
    }

    public void addItem(Device device) {
        DeviceAPI deviceAPI = new DeviceAPI(device);
        deviceAPI.addItem();
    }

    public void useDevice(Device device) {
        deviceInRoom(device);
        house.logAction(new ActionLog(this.getName(), " used ", device.toString()));
        house.logUsage(this.getName(), device.toString());
        DeviceAPI deviceAPI = new DeviceAPI(device);
        deviceAPI.use();
    }

    public void turnOff(Device device){
        deviceInRoom(device);
        house.logAction(new ActionLog(this.getName(), " turned off ", device.toString()));
        house.logUsage(this.getName(), device.toString());
        DeviceAPI deviceAPI = new DeviceAPI(device);
        deviceAPI.turnOff();
    }

    public void turnIdle(Device device){
        deviceInRoom(device);
        house.logAction(new ActionLog(this.getName(), " turned idle ", device.toString()));
        house.logUsage(this.getName(), device.toString());
        DeviceAPI deviceAPI = new DeviceAPI(device);
        deviceAPI.turnIdle();
    }

    private void deviceInRoom(Device device) {
        if (!room.getDevices().contains(device)) {
            house.logAction(new ActionLog(this.getName(), " wanted to use " , device.toString() + ", but its not in this room "));
            moveToRoom(house.getRoomByName(device.getRoom()));
        }
    }

    public void moveToRoom(Room destination) {
        if (destination == null) {
            System.out.println("destination room does not exist");
            return;
        }
        house.logAction(new ActionLog(this.getName(), " moving to ", destination.toString()));
        if(this instanceof Person){
            this.room.removePerson((Person)this);
            destination.addPerson((Person)this);
        } else {
            this.room.removePet((Pet)this);
            destination.addPet((Pet)this);
        }
        this.room = destination;
    }

    public House getHouse() {
        return house;
    }

    public String getName() {
        return name;
    }
}
