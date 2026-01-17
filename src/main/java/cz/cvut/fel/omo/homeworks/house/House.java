package cz.cvut.fel.omo.homeworks.house;

import cz.cvut.fel.omo.homeworks.reports.ActionLog;
import cz.cvut.fel.omo.homeworks.reports.EventLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class House extends Composite implements Component{
    private int ID;
    private List<ActionLog> actionLog;
    private List<EventLog> eventLog;
    private Map<String, Map<String, Integer>> usage;

    /**
     *
     * @param id
     */
    public House(int id) {
        this.ID = id;
        this.actionLog = new ArrayList<>();
        this.eventLog = new ArrayList<>();
        this.usage = new HashMap<>();
    }

    public Room getRoomByName(String roomName) {
        return getChildren().stream()
                .map(floor -> (Floor) floor)
                .flatMap(floor -> floor.getChildren().stream())
                .map(room -> (Room) room)
                .filter(room -> room.getName().equals(roomName))
                .findFirst()
                .orElse(null);
    }

    public void logAction(ActionLog actionLog) {
        this.actionLog.add(actionLog);
    }

    public List<ActionLog> getActionLog() {
        return actionLog;
    }

    public void logEvent(EventLog eventLog) {
        this.eventLog.add(eventLog);
    }

    public List<EventLog> getEventLog() {
        return eventLog;
    }

    public Map<String, Map<String, Integer>> getUsage() {
        return usage;
    }

    public void logUsage(String entityName, String deviceName) {
        this.usage.putIfAbsent(entityName, new HashMap<>());
        Map<String, Integer> deviceMap = this.usage.get(entityName);
        deviceMap.put(deviceName, deviceMap.getOrDefault(deviceName, 0) + 1);
    }

    @Override
    public String getInformation() {
        return "House ID: " + ID;
    }
}
