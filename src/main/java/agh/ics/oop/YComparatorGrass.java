package agh.ics.oop;

import java.util.Comparator;

public class YComparatorGrass implements Comparator<Grass> {
    @Override
    public int compare(Grass o1, Grass o2) {
        Vector2d pos1 = o1.getPosition();
        Vector2d pos2 = o2.getPosition();
        if (pos1.y == pos2.y) {
            if (pos1.x == pos2.x) return 0;
            else return pos1.x - pos2.x;
        }
        return pos1.y - pos2.y;
    }
}

