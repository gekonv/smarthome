package cz.cvut.fel.omo.homeworks.reports;

public class ActionLog {
    private String entity;
    private String action;
    private String msg;

    /**
     *
     * @param entity
     * @param action
     * @param msg
     */
    public ActionLog(String entity, String action, String msg) {
        this.entity = entity;
        this.action = action;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return entity + action + msg;
    }
}
