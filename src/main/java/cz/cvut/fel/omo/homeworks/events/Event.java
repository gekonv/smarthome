package cz.cvut.fel.omo.homeworks.events;

public class Event {
    private String source;
    private Events event;

    /**
     *
     * @param events
     * @param source
     */
    public Event(Events events, String source) {
        this.source = source;
        event = events;
    }
    public String getSource() {
        return source;
    }
    public Events getEvent() {
        return event;
    }
}
