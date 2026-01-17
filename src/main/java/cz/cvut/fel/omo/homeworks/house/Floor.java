package cz.cvut.fel.omo.homeworks.house;

public class Floor extends Composite implements Component{
    private House house;
    private int level;

    /**
     *
     * @param house
     * @param level
     */
    public Floor(House house, int level) {
        this.house = house;
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public String getInformation() {
        return "    Floor level: " + level;
    }
}
