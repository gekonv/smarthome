package cz.cvut.fel.omo.homeworks.reports;

import cz.cvut.fel.omo.homeworks.house.House;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public class ActivityAndUsageReport {
    private House house;

    /**
     *
     * @param house
     */
    public ActivityAndUsageReport(House house){
        this.house = house;
    }
    public void generateReport(){
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("reports/ActivityAndUsageReport.txt", "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        writer.println("Activities");
        writer.println("--------------------------------");
        for (ActionLog log : house.getActionLog()) {
            writer.println(log.toString());
        }
        writer.println();
        writer.println("Usage");
        writer.println("--------------------------------");
        for (Map.Entry<String, Map<String, Integer>> entry : house.getUsage().entrySet()) {
            String entity = entry.getKey();
            writer.println(entity + " used");
            Map<String, Integer> deviceMap = entry.getValue();
            for (Map.Entry<String, Integer> deviceEntry : deviceMap.entrySet()) {
                writer.println("    " + deviceEntry.getKey() + ", Times Used: " + deviceEntry.getValue());
            }
        }
        writer.close();
    }
}
