package cz.cvut.fel.omo.homeworks.reports;

public class EventLog {
    private String entity;
    private String msg;

    /**
     *
     * @param entity
     * @param msg
     */
    public EventLog(String entity, String msg) {
        this.entity = entity;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return entity + msg;
    }
}
