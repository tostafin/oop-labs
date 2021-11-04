package agh.ics.oop;

public class RectangularMap implements IWorldMap {
    int width, height;
    RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
    }
    boolean[][] animalsMap = new boolean[height][width];

    @Override
    public boolean canMoveTo(Vector2d position) {
        if (this.isOccupied(position)) return false;

        return position.precedes(new Vector2d(0, 0)) && position.follows(new Vector2d(4, 4));
    }

    @Override
    public boolean place(Animal animal) {
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animalsMap[position.x][position.y];
    }

    @Override
    public Object objectAt(Vector2d position) {
        return null;
    }
}
