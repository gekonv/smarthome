package cz.cvut.fel.omo.homeworks;

public class Main {
    public static void main(String[] args) {
        Simulation simulation = new Simulation( "json/conf.json");
        simulation.simulate(100);

    }
}