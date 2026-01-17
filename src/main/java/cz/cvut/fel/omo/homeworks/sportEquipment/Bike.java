package cz.cvut.fel.omo.homeworks.sportEquipment;

public class Bike extends SportEquipment{
    private int id;
    private static int cnt = 0;

    public Bike(){
        id = cnt;
        cnt++;
    }
}
