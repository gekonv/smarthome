package cz.cvut.fel.omo.homeworks.devices.deviceStates;

import cz.cvut.fel.omo.homeworks.devices.Device;

public abstract class DeviceState {
    Device device;

    /**
     *
     * @param device
     */
    public DeviceState(Device device) {
        this.device = device;
    }
    public abstract void turnOn();
    public abstract void turnOff();
    public abstract void turnIdle();
    public void fixDevice(){
        device.setState(new IdleState(device));
        device.setDurability(10);
    }
    public void breakDevice(){
        device.setState(new BrokenState(device));
    }
}
