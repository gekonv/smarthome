package cz.cvut.fel.omo.homeworks.devices;

import cz.cvut.fel.omo.homeworks.devices.deviceStates.BrokenState;
import cz.cvut.fel.omo.homeworks.devices.deviceStates.DeviceState;
import cz.cvut.fel.omo.homeworks.devices.deviceStates.InactiveState;
import cz.cvut.fel.omo.homeworks.events.Event;
import cz.cvut.fel.omo.homeworks.house.Component;
import cz.cvut.fel.omo.homeworks.observer.Publisher;
import cz.cvut.fel.omo.homeworks.observer.Subscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class Device implements Component, Publisher {
    private DeviceType type;
    private int count;
    private int activeConsumption;
    private int idleConsumption;
    private int inactiveConsumption;
    private int consumed;
    private DeviceState state;
    private int durability;
    private int items;
    private String room;
    private String manual;
    private List<Subscriber> subscribers = new ArrayList<Subscriber>();

    /**
     *
     * @param type
     * @param count
     * @param activeConsumption
     * @param idleConsumption
     * @param inactiveConsumption
     * @param roomName
     */
    public Device(DeviceType type, int count, int activeConsumption, int idleConsumption, int inactiveConsumption, String roomName) {
        this.type = type;
        this.count = count;
        this.activeConsumption = activeConsumption;
        this.idleConsumption = idleConsumption;
        this.inactiveConsumption = inactiveConsumption;
        this.consumed = 0;
        this.state = new InactiveState(this);
        this.durability = 10;
        this.items = 5;
        this.room = roomName;
    }

    public void use() {
        this.state.turnOn();
    }

    public void turnOff(){
        this.state.turnOff();
    }

    public void turnIdle(){
        this.state.turnIdle();
    }

    public void setState(DeviceState state) {
        this.state = state;
    }

    public DeviceState getState() {
        return state;
    }

    public String getRoom() {
        return room;
    }

    public DeviceType getType() {
        return type;
    }

    public int getCount() {
        return count;
    }

    public void addItem() {
        items++;
    }

    public String getManual(){
        if(this.manual == null){
            generateManual();
        }
        return manual;
    }

    private void generateManual() {
        this.manual = "Fix it with a hammer";
    }

    public void wreck(){
        setDurability(0);
        setState(new BrokenState(this));
    }

    public void reduceDurability() {
        if (this.durability > 0) {
            this.durability--;
        } else {
            setState(new BrokenState(this));
        }
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public int getConsumed(){
        return consumed;
    }

    public void setConsumed(int consumed) {
        this.consumed += consumed;
    }

    public int getActiveConsumption(){
        return activeConsumption;
    }

    public int getIdleConsumption(){
        return idleConsumption;
    }

    public int getInactiveConsumption(){
        return inactiveConsumption;
    }

    @Override
    public String getInformation() {
        return "            Device: " + type + " " + count;
    }

    @Override
    public String toString() {
        return type + " " + count;
    }

    @Override
    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void notifySubscribers(Event event) {
        subscribers.get(ThreadLocalRandom.current().nextInt(0, subscribers.size())).update(event);
    }
}
