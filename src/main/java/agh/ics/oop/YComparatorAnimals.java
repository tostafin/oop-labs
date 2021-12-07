package agh.ics.oop;

import java.util.Comparator;

public class YComparatorAnimals implements Comparator<Animal> {
    @Override
    public int compare(Animal o1, Animal o2) {
        Vector2d pos1 = o1.getAnimalsPos();
        Vector2d pos2 = o2.getAnimalsPos();
        if (pos1.y == pos2.y) {
            if (pos1.x == pos2.x) return 0;
            else return pos1.x - pos2.x;
        }
        return pos1.y - pos2.y;
    }
}
