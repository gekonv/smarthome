package cz.cvut.fel.omo.homeworks.events;

import cz.cvut.fel.omo.homeworks.livingEntities.Humans.Child;

public class CryingEvent extends Event{
    private Child child;

    /**
     *
     * @param events
     * @param source
     * @param child
     */
    public CryingEvent(Events events, String source, Child child) {
        super(events, source);
        this.child = child;
    }
}
