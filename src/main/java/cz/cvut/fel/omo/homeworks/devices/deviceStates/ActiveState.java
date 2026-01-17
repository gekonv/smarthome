package cz.cvut.fel.omo.homeworks.devices.deviceStates;

import cz.cvut.fel.omo.homeworks.devices.Device;

public class ActiveState extends DeviceState {
    /**
     *
     * @param device
     */
    public ActiveState(Device device) {
        super(device);
        device.setConsumed(device.getActiveConsumption());
    }

    @Override
    public void turnOn() {
    }

    @Override
    public void turnOff() {
        device.setState(new InactiveState(device));
    }

    @Override
    public void turnIdle() {
        device.setState(new IdleState(device));
        device.reduceDurability();
    }
}
