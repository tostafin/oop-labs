package agh.ics.oop;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RectangularMap extends AbstractWorldMap {
    private int width, height;
//    private List<Animal> animals;
    private Map<Vector2d, Animal> animals;

    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
//        this.animals = new ArrayList<>();
        this.animals = new LinkedHashMap<>();

    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return super.canMoveTo(position) && position.follows(new Vector2d(0, 0)) && position.precedes(new Vector2d(this.width, this.height));
    }

    public Vector2d getLowerLeft() {
        return new Vector2d(0, 0);
    }

    public Vector2d getUpperRight() {
        return new Vector2d(this.width, this.height);
    }
}
