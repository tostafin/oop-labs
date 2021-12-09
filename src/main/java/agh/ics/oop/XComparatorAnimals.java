package agh.ics.oop;

import java.util.Comparator;

public class XComparatorAnimals implements Comparator<Animal> {
    @Override
    public int compare(Animal o1, Animal o2) {
        Vector2d pos1 = o1.getPosition();
        Vector2d pos2 = o2.getPosition();
        if (pos1.x == pos2.x) {
            if (pos1.y == pos2.y) return 0;
            else return pos1.y - pos2.y;
        }
        return pos1.x - pos2.x;
    }
}
