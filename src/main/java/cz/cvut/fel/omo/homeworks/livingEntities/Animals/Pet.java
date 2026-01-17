package cz.cvut.fel.omo.homeworks.livingEntities.Animals;

import cz.cvut.fel.omo.homeworks.house.House;
import cz.cvut.fel.omo.homeworks.house.Room;
import cz.cvut.fel.omo.homeworks.livingEntities.LivingEntity;

public class Pet extends LivingEntity {
    private AnimalType animalType;
    private int hungerLevel;

    /**
     *
     * @param animalType
     * @param room
     * @param petName
     * @param house
     */
    public Pet(AnimalType animalType, Room room, String petName, House house) {
        super(petName, room, house);
        this.animalType = animalType;
        this.hungerLevel = 5;
    }

    public void setHungerLevel(){
        hungerLevel += 1;
    }

    @Override
    public String toString() {
        return animalType + " " + getName();
    }
}
