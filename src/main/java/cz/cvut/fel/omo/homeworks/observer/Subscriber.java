package cz.cvut.fel.omo.homeworks.observer;

import cz.cvut.fel.omo.homeworks.events.Event;

public interface Subscriber {
    void update(Event event);
}
