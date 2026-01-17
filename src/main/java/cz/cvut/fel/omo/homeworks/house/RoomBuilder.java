package cz.cvut.fel.omo.homeworks.house;

import cz.cvut.fel.omo.homeworks.devices.Device;

public class RoomBuilder {
    private Room room;
    private Sensor sensor;

    /**
     *
     * @param floor
     * @param name
     */
    public RoomBuilder(Floor floor, String name){
        this.room = new Room(floor, name);
    }

    public void addDevice(Device device){
        room.add(device);
    }

    public void addWindow(Window window){
        if(sensor != null){
            sensor.addSubscriber(window);
        }
        room.add(window);
    }

    public void addSensor(Sensor sensor){
        this.sensor = sensor;
        room.add(sensor);
    }

    public Room build(){
        return this.room;
    }
}
