package cz.cvut.fel.omo.homeworks.house;

import cz.cvut.fel.omo.homeworks.events.Event;
import cz.cvut.fel.omo.homeworks.events.Events;
import cz.cvut.fel.omo.homeworks.observer.Subscriber;
import cz.cvut.fel.omo.homeworks.reports.EventLog;

public class Window implements Component, Subscriber {
    private static int size = 0;
    private int id;
    private boolean blindsDown = false;
    private House house;

    /**
     *
     * @param house
     */
    public Window(House house) {
        this.id = this.size;
        this.size += 1;
        this.house = house;
    }

    public void setBlindsDown(boolean down) {
        this.blindsDown = down;
    }

    public boolean getBlindsDown() {
        return this.blindsDown;
    }

    public String getName(){
        return "Window " + this.id;
    }

    @Override
    public String getInformation() {
        return "            Window " + id + ": blinds are " + (blindsDown? "down" : "up");
    }

    @Override
    public void update(Event event) {
        if (event.getEvent().equals(Events.WINDY)) {
            house.logEvent(new EventLog(getName(), " rolling blinds up"));
            setBlindsDown(false);
        } else if (event.getEvent().equals(Events.SUNNY)) {
            house.logEvent(new EventLog(getName(), " rolling blinds down"));
            setBlindsDown(true);
        }
    }
}
