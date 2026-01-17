package cz.cvut.fel.omo.homeworks.observer;

import cz.cvut.fel.omo.homeworks.events.Event;

public interface Publisher {
    public void addSubscriber(Subscriber subscriber);
    public void notifySubscribers(Event event);
}
