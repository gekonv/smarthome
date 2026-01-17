package cz.cvut.fel.omo.homeworks.house;

import java.util.ArrayList;
import java.util.List;

public abstract class Composite implements Component {
    private final List<Component> children = new ArrayList<>();

    public void add(Component item){
        children.add(item);
    }

    public void remove(Component item){
        children.remove(item);
    }   

    public List<Component> getChildren(){
        return children;
    }
}
