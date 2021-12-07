package agh.ics.oop;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected Map<Vector2d, Animal> animals;
    protected abstract Vector2d getLowerLeft();
    protected abstract Vector2d getUpperRight();

    public AbstractWorldMap() {
        this.animals = new LinkedHashMap<>();
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !(this.isOccupied(position));
    }

    @Override
    public boolean place(Animal animal) {
        Vector2d animalPos = animal.getAnimalsPos();
        if (this.canMoveTo(animalPos)) {
            this.animals.put(animalPos, animal);
            animal.addObserver(this);
            return true;
        }
        throw new IllegalArgumentException(animalPos + " is already occupied.");
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

    @Override
    public String toString() {
        MapVisualizer animalsMap = new MapVisualizer(this);
        return animalsMap.draw(getLowerLeft(), getUpperRight());
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Animal animal = this.animals.get(oldPosition);
        this.animals.remove(oldPosition);
        this.animals.put(newPosition, animal);
    }
}
