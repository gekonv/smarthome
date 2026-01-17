package cz.cvut.fel.omo.homeworks.events;

import cz.cvut.fel.omo.homeworks.devices.Device;

public class BrokenEvent extends Event{
    private Device device;

    /**
     *
     * @param events
     * @param source
     * @param device
     */
    public BrokenEvent(Events events, String source, Device device) {
        super(events, source);
        this.device = device;
    }
    public Device getDevice() {
        return device;
    }
}
