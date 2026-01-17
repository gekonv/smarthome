package cz.cvut.fel.omo.homeworks.devices.deviceStates;

import cz.cvut.fel.omo.homeworks.devices.Device;

public class IdleState extends DeviceState {
    /**
     *
     * @param device
     */
    public IdleState(Device device) {
        super(device);
        device.setConsumed(device.getIdleConsumption());
    }

    @Override
    public void turnOn() {
        device.setState(new ActiveState(device));
        device.reduceDurability();
    }

    @Override
    public void turnOff() {
        device.setState(new InactiveState(device));
    }

    @Override
    public void turnIdle() {
    }
}
