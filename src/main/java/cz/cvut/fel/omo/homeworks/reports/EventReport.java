package cz.cvut.fel.omo.homeworks.reports;

import cz.cvut.fel.omo.homeworks.house.House;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class EventReport {
    private House house;

    /**
     *
     * @param house
     */
    public EventReport(House house){
        this.house = house;
    }
    public void generateReport(){
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("reports/EventReport.txt", "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        writer.println("Events");
        writer.println("--------------------------------");
        for (EventLog log : house.getEventLog()) {
            writer.println(log.toString());
        }
        writer.close();
    }
}
