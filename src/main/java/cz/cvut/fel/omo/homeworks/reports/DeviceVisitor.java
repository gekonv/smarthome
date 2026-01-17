package cz.cvut.fel.omo.homeworks.reports;

import cz.cvut.fel.omo.homeworks.devices.Device;

public interface DeviceVisitor {
    void visit(Device device);
}
