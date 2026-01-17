package cz.cvut.fel.omo.homeworks.devices.deviceStates;

import cz.cvut.fel.omo.homeworks.devices.Device;

public class InactiveState extends DeviceState {
    /**
     *
     * @param device
     */
    public InactiveState(Device device) {
        super(device);
        device.setConsumed(device.getInactiveConsumption());
    }

    @Override
    public void turnOn() {
        device.setState(new ActiveState(device));
        device.reduceDurability();
    }

    @Override
    public void turnOff() {
    }

    @Override
    public void turnIdle() {
        device.setState(new IdleState(device));
        device.reduceDurability();
    }
}
