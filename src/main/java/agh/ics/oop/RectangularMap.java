package agh.ics.oop;

import java.util.LinkedHashMap;

public class RectangularMap extends AbstractWorldMap {
    private final int width, height;

    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
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
