package agh.ics.oop;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver {
    Comparator xComp = new XComparatorAnimals();
    SortedSet<Animal> xSorted = new TreeSet<Animal>(xComp);

    Comparator yComp = new XComparatorAnimals();
    SortedSet<Animal> ySorted = new TreeSet<Animal>(yComp);

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {

    }
}
