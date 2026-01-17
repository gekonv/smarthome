package cz.cvut.fel.omo.homeworks.sportEquipment;

public class SportEquipment{
    private boolean inUse;
    public SportEquipment(){
        inUse = false;
    }

    public boolean isInUse(){
        return inUse;
    }

    public void setInUse(boolean inUse){
        this.inUse = inUse;
    }
}
