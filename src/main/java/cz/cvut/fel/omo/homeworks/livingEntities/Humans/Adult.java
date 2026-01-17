package cz.cvut.fel.omo.homeworks.livingEntities.Humans;

import cz.cvut.fel.omo.homeworks.devices.Device;
import cz.cvut.fel.omo.homeworks.devices.DeviceAPI;
import cz.cvut.fel.omo.homeworks.devices.deviceStates.IdleState;
import cz.cvut.fel.omo.homeworks.events.BrokenEvent;
import cz.cvut.fel.omo.homeworks.events.CryingEvent;
import cz.cvut.fel.omo.homeworks.events.Event;
import cz.cvut.fel.omo.homeworks.house.House;
import cz.cvut.fel.omo.homeworks.house.Room;
import cz.cvut.fel.omo.homeworks.observer.Subscriber;
import cz.cvut.fel.omo.homeworks.reports.EventLog;

public class Adult extends Person implements Subscriber{
    /**
     *
     * @param name
     * @param room
     * @param house
     */
    public Adult(String name, Room room, House house) {
        super(name, room, house);
    }
    @Override
    public String toString() {
        return "Adult " + getName();
    }

    @Override
    public void update(Event event) {
        if(event instanceof CryingEvent){
            getHouse().logEvent(new EventLog(getName(), " is making " + event.getSource() + " not cry"));
        } else if (event instanceof BrokenEvent) {
            getHouse().logEvent(new EventLog(getName(), " is fixing " + event.getSource()));
            Device device = ((BrokenEvent) event).getDevice();
            DeviceAPI deviceAPI = new DeviceAPI(device);
            deviceAPI.getManual();
            device.setState(new IdleState(device));
            device.setDurability(10);
            getHouse().logEvent(new EventLog(getName(), " fixed " + event.getSource()));
        }
    }

}
