package cz.cvut.fel.omo.homeworks.livingEntities.Humans;

import cz.cvut.fel.omo.homeworks.house.House;
import cz.cvut.fel.omo.homeworks.house.Room;
import cz.cvut.fel.omo.homeworks.livingEntities.LivingEntity;
import cz.cvut.fel.omo.homeworks.reports.ActionLog;
import cz.cvut.fel.omo.homeworks.sportEquipment.Bike;
import cz.cvut.fel.omo.homeworks.sportEquipment.SportEquipment;

public class Person extends LivingEntity {
    /**
     *
     * @param name
     * @param room
     * @param house
     */
    public Person(String name, Room room, House house) {
        super(name, room, house);
    }

    public void sport(SportEquipment equipment) {
        if(equipment.isInUse()){
            getHouse().logAction(new ActionLog(getName(), " cant ride(someone is using) ", ((equipment instanceof Bike)? "bike":"skateboard" )));
            return;
        }
        getHouse().logAction(new ActionLog(getName(), " riding ", ((equipment instanceof Bike)? "bike":"skateboard" )));
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + getName() + '\'' +

                '}';
    }
}
