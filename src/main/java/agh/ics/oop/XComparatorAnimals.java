package agh.ics.oop;

import java.util.Comparator;

public class XComparatorAnimals implements Comparator<Animal> {
    @Override
    public int compare(Animal o1, Animal o2) {
        if (o1.getAnimalsPos().x < o2.getAnimalsPos().x) {
            return -1;
        }
        return 0;
    }
}
