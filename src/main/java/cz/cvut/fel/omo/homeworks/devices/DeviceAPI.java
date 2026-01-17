package cz.cvut.fel.omo.homeworks.devices;

import cz.cvut.fel.omo.homeworks.devices.deviceStates.BrokenState;
import cz.cvut.fel.omo.homeworks.reports.DeviceVisitor;

public class DeviceAPI {
    private Device device;

    /**
     *
     * @param device
     */
    public DeviceAPI(Device device) {
        this.device = device;
    }

    public void use() {
        if(isBroken()) {
            return;
        }
        this.device.use();
    }

    public void turnOff() {
        if(isBroken()) {
            return;
        }
        this.device.turnOff();
    }

    public void turnIdle() {
        if(isBroken()) {
            return;
        }
        this.device.turnIdle();
    }

    private boolean isBroken() {
        if (this.device.getState() instanceof BrokenState) {
            return true;
        }
        return false;
    }

    public void addItem() {
        this.device.addItem();
    }

    public String getManual() {
        return this.device.getManual();
    }

    public void accept(DeviceVisitor visitor) {
        visitor.visit(this.device);
    }
}