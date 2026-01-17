package cz.cvut.fel.omo.homeworks.house;

import cz.cvut.fel.omo.homeworks.devices.Device;
import cz.cvut.fel.omo.homeworks.livingEntities.Animals.Pet;
import cz.cvut.fel.omo.homeworks.livingEntities.Humans.Person;
import cz.cvut.fel.omo.homeworks.sportEquipment.SportEquipment;

import java.util.ArrayList;
import java.util.List;

public class Room extends Composite implements Component{
    private List<Person> people;
    private List<Pet> pets;
    private Floor floor;
    private String name;
    private Sensor sensor;

    /**
     *
     * @param floor
     * @param name
     */
    public Room(Floor floor, String name) {
        people = new ArrayList<>();
        pets = new ArrayList<>();
        this.floor = floor;
        this.name = name;
    }

    public void addPerson(Person person){
        people.add(person);
    }

    public void removePerson(Person person){
        people.remove(person);
    }

    public List<Person> getPeople(){
        return people;
    }

    public void addPet(Pet pet){
        pets.add(pet);
    }

    public void removePet(Pet pet){
        pets.remove(pet);
    }

    public List<Pet> getPets(){
        return pets;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getInformation() {
        return "        Room: " + name + "\n"
                + "            People " + people + "\n"
                + "            Pets " + pets;
    }

    public List<Device> getDevices() {
        List<Device> devices = new ArrayList<>();
        for (Component component : getChildren()) {
            if (component instanceof Device) {
                devices.add((Device) component);
            }
        }
        return devices;
    }

    public List<SportEquipment> getSportEquipments() {
        List<SportEquipment> sportEquipments = new ArrayList<>();
        for (Component component : getChildren()) {
            if (component instanceof SportEquipment) {
                sportEquipments.add((SportEquipment) component);
            }
        }
        return sportEquipments;
    }

    public List<Window> getWindows() {
        List<Window> windows = new ArrayList<>();
        for (Component component : getChildren()) {
            if (component instanceof Window) {
                windows.add((Window) component);
            }
        }
        return windows;
    }

    public Sensor getSensor() {
        for (Component component : getChildren()) {
            if (component instanceof Sensor) {
                return (Sensor) component;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return name;
    }
}
