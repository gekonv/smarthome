package cz.cvut.fel.omo.homeworks.devices;

public enum DeviceType {
    FRIDGE,
    FEEDER,
    STOVE,
    MICROWAVE,
    PC,
    WASHING_MACHINE,
    DRIER,
    TV;

    private int count;

    private DeviceType() {
    this.count = 0;}

    public int getAndIncrement() {
    return this.count++;
    }
}
