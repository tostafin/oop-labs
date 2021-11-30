package agh.ics.oop;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
//    protected List<Animal> animals;
    protected Map<Vector2d, Animal> animals;
    protected abstract Vector2d getLowerLeft();
    protected abstract Vector2d getUpperRight();

    public AbstractWorldMap() {
//        this.animals = new ArrayList<>();
        this.animals = new LinkedHashMap<>();
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !(this.isOccupied(position));
    }

    @Override
    public boolean place(Animal animal) {
        if (this.canMoveTo(animal.getAnimalsPos())) {
            this.animals.put(animal.getAnimalsPos(), animal);
            animal.addObserver(this);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return this.animals.containsKey(position);
    }

    @Override
    public Object objectAt(Vector2d position) {
        if (this.animals.containsKey(position)) return this.animals.get(position);
        return null;
    }

    public String toString() {
        MapVisualizer animalsMap = new MapVisualizer(this);
        return animalsMap.draw(getLowerLeft(), getUpperRight());
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Animal animal = this.animals.get(oldPosition);
        this.animals.remove(oldPosition);
        this.animals.put(newPosition, animal); // TODO may not work because animal is removed on line 52
    }
}
