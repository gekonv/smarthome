package cz.cvut.fel.omo.homeworks.devices;

public interface DeviceFactory {
    Device createFridge(String roomName);
    Device createFeeder(String roomName);
    Device createStove(String roomName);
    Device createTV(String roomName);
    Device createMicrowave(String roomName);
    Device createPc(String roomName);
    Device createWashingMachine(String roomName);
    Device createDrier(String roomName);
}
