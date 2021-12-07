package agh.ics.oop;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver {
    Comparator<Animal> xCompAnimal = new XComparatorAnimals();
    SortedSet<Animal> xAnimals = new TreeSet<>(xCompAnimal);
    Comparator<Animal> yCompAnimal = new YComparatorAnimals();
    SortedSet<Animal> yAnimals = new TreeSet<>(yCompAnimal);

    Comparator<Grass> xCompGrass = new XComparatorGrass();
    SortedSet<Grass> xGrass = new TreeSet<>(xCompGrass);
    Comparator<Grass> yCompGrass = new YComparatorGrass();
    SortedSet<Grass> yGrass = new TreeSet<>(yCompGrass);

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Animal animal = null;
        for (Animal a : xAnimals) {
            if (a.getAnimalsPos() == oldPosition) animal = a;
        }
        if (animal != null) {
            xAnimals.remove(animal);
            yAnimals.remove(animal);
            xAnimals.add(new Animal(animal.getMap(), newPosition));
            yAnimals.add(new Animal(animal.getMap(), newPosition));
        }
    }

    public void addAnimal(Animal animal) {
        xAnimals.add(animal);
        yAnimals.add(animal);
    }

    public void addGrass(Grass grass) {
        xGrass.add(grass);
        yGrass.add(grass);
    }

    public Vector2d getLowerLeft() {
        int xMin = Math.min(xAnimals.first().getAnimalsPos().x, xGrass.first().getPosition().x);
        int yMin = Math.min(yAnimals.first().getAnimalsPos().y, yGrass.first().getPosition().y);
        return new Vector2d(xMin, yMin);
    }

    public Vector2d getUpperRight() {
        int xMax = Math.max(xAnimals.last().getAnimalsPos().x, xGrass.last().getPosition().x);
        int yMax = Math.max(yAnimals.last().getAnimalsPos().y, yGrass.last().getPosition().y);
        return new Vector2d(xMax, yMax);
    }
}
