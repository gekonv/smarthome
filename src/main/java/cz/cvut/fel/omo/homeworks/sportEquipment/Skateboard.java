package cz.cvut.fel.omo.homeworks.sportEquipment;

public class Skateboard extends SportEquipment{
    private int id;
    private static int cnt = 0;
    public Skateboard(){
        id = cnt;
        cnt++;
    }
}
