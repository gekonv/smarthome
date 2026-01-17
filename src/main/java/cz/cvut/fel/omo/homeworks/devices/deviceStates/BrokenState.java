package cz.cvut.fel.omo.homeworks.devices.deviceStates;

import cz.cvut.fel.omo.homeworks.devices.Device;
import cz.cvut.fel.omo.homeworks.events.BrokenEvent;
import cz.cvut.fel.omo.homeworks.events.Events;

public class BrokenState extends DeviceState {
    /**
     *
     * @param device
     */
    public BrokenState(Device device) {
        super(device);
        device.notifySubscribers(new BrokenEvent(Events.BROKEN_DEVICE , device.toString(), device));
    }

    @Override
    public void turnOn() {
    }

    @Override
    public void turnOff() {
    }

    @Override
    public void turnIdle() {
    }
}
