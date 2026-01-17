package cz.cvut.fel.omo.homeworks.livingEntities.Humans;

import cz.cvut.fel.omo.homeworks.events.CryingEvent;
import cz.cvut.fel.omo.homeworks.events.Event;
import cz.cvut.fel.omo.homeworks.events.Events;
import cz.cvut.fel.omo.homeworks.house.House;
import cz.cvut.fel.omo.homeworks.house.Room;
import cz.cvut.fel.omo.homeworks.observer.Publisher;
import cz.cvut.fel.omo.homeworks.observer.Subscriber;
import cz.cvut.fel.omo.homeworks.reports.EventLog;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Child extends Person implements Publisher {
    private List<Subscriber> subscribers = new ArrayList<Subscriber>();

    /**
     *
     * @param name
     * @param room
     * @param house
     */
    public Child(String name, Room room, House house) {
        super(name, room, house);
    }

    public void cry(){
        getHouse().logEvent(new EventLog(getName(), " is crying"));
        notifySubscribers(new CryingEvent(Events.CHILD_CRYING, getName(), this));
    }

    @Override
    public String toString() {
        return "Child " + getName();
    }

    @Override
    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void notifySubscribers(Event event) {
        subscribers.get(ThreadLocalRandom.current().nextInt(0, subscribers.size())).update(event);
    }
}
