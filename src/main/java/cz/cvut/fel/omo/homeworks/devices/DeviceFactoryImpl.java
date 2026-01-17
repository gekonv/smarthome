package cz.cvut.fel.omo.homeworks.devices;

public class DeviceFactoryImpl implements DeviceFactory {
    private static int fridgeCnt = 0;
    private static int feederCnt = 0;
    private static int stoveCnt = 0;
    private static int tvCnt = 0;
    private static int microCnt = 0;
    private static int pcCnt = 0;
    private static int washCnt = 0;
    private static int drierCnt = 0;

    @Override
    public Device createFridge(String roomName) {
        int cnt = fridgeCnt++;
        DeviceType.FRIDGE.getAndIncrement();
        return new Device(DeviceType.FRIDGE, cnt, 30, 20, 10, roomName);
    }

    @Override
    public Device createFeeder(String roomName) {
        int cnt = feederCnt++;
        return new Device(DeviceType.FEEDER, cnt, 20, 5, 10, roomName);
    }

    @Override
    public Device createStove(String roomName) {
        int cnt = stoveCnt++;
        return new Device(DeviceType.STOVE, cnt, 25, 10, 5, roomName);
    }

    @Override
    public Device createTV(String roomName) {
        int cnt = tvCnt++;
        return new Device(DeviceType.TV, cnt, 15, 10, 0, roomName);
    }

    @Override
    public Device createMicrowave(String roomName) {
        int cnt = microCnt++;
        return new Device(DeviceType.MICROWAVE, cnt, 5, 1, 0, roomName);
    }

    @Override
    public Device createPc(String roomName) {
        int cnt = pcCnt++;
        return new Device(DeviceType.PC, cnt, 5, 5, 0, roomName);
    }

    @Override
    public Device createWashingMachine(String roomName) {
        int cnt = washCnt++;
        return new Device(DeviceType.WASHING_MACHINE, cnt, 30, 0, 0, roomName);
    }

    @Override
    public Device createDrier(String roomName) {
        int cnt = drierCnt++;
        return new Device(DeviceType.DRIER, cnt, 20, 10, 0, roomName);
    }
}
