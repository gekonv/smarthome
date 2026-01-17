package cz.cvut.fel.omo.homeworks.house;

import cz.cvut.fel.omo.homeworks.events.Event;
import cz.cvut.fel.omo.homeworks.observer.Publisher;
import cz.cvut.fel.omo.homeworks.observer.Subscriber;
import cz.cvut.fel.omo.homeworks.reports.EventLog;

import java.util.ArrayList;
import java.util.List;

public class Sensor implements Component, Publisher {
    private List<Subscriber> subscribers = new ArrayList<Subscriber>();
    private static int count = 0;
    private House house;
    private int id;
    private int activated;

    /**
     *
     * @param house
     */
    public Sensor(House house) {
        id = count;
        count++;
        activated = 0;
        this.house = house;
    }

    public void detectEvent(Event event) {
        house.logEvent(new EventLog(getName(), " detected " + event.getEvent()));
        activated++;
        notifySubscribers(event);
    }

    public String getName() {
        return "Sensor " + id;
    }

    @Override
    public String getInformation() {
        return "            Sensor " + id;
    }

    @Override
    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void notifySubscribers(Event event) {
        for (Subscriber subscriber : subscribers) {
            subscriber.update(event);
        }
    }
}
