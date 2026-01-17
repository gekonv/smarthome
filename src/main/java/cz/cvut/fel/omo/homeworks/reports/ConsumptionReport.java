package cz.cvut.fel.omo.homeworks.reports;

import cz.cvut.fel.omo.homeworks.devices.Device;
import cz.cvut.fel.omo.homeworks.house.House;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class ConsumptionReport implements DeviceVisitor{
    private final Map<String, Integer> consumptionReport;
    private House house;

    /**
     *
     * @param house
     */
    public ConsumptionReport(House house){
        this.consumptionReport = new HashMap<>();
        this.house = house;
    }
    public void generateReport(){
        int total = 0;
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("reports/ConsumptionReport.txt", "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        writer.println("Consumption Report:");
        for (Map.Entry<String, Integer> entry : consumptionReport.entrySet()) {
            writer.println(entry.getKey() + ": " + entry.getValue() + " kWh");
            total += entry.getValue();
        }
        writer.println();
        writer.println("Total consumption: " + total + " kWh");
        writer.println("Cost: 2,68 Kč/kWh");
        String cost = String.format("%.2f", total*2.68);
        writer.println("You have to pay " + cost + "Kč");
        writer.close();
    }

    @Override
    public void visit(Device device) {
        consumptionReport.put(device.toString(), device.getConsumed());
    }
}
