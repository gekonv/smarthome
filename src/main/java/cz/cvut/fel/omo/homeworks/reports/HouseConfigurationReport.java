package cz.cvut.fel.omo.homeworks.reports;

import cz.cvut.fel.omo.homeworks.devices.Device;
import cz.cvut.fel.omo.homeworks.house.*;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class HouseConfigurationReport {
    private House house;

    /**
     *
     * @param house
     */
    public HouseConfigurationReport(House house){
        this.house = house;
    }
    public void generateReport(){
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("reports/HouseConfigurationReport.txt", "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        writer.println(house.getInformation());
        for (Component floorComponent : house.getChildren()) {
            Floor floor = (Floor) floorComponent;
            writer.println(floor.getInformation());
            for (Component roomComponent : floor.getChildren()) {
                Room room = (Room) roomComponent;
                writer.println(room.getInformation());
                for (Device device : room.getDevices()) {
                    writer.println(device.getInformation());
                }
                for(Window window : room.getWindows()) {
                    writer.println(window.getInformation());
                }
                writer.println(room.getSensor().getInformation());
            }
        }
        writer.close();
    }
}
