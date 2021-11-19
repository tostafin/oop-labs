package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GrassField implements IWorldMap {
    private int n;
    private Vector2d grassPos;
    private List<Animal> animals;
    private List<Grass> fields;

    public GrassField(int n) {
        this.n = n;
        this.fields = new ArrayList<>();
        int maxLen = (int) Math.sqrt(this.n * 10);
        int x = ThreadLocalRandom.current().nextInt(0, maxLen);
        int y = ThreadLocalRandom.current().nextInt(0, maxLen);
        while (objectAt(new Vector2d(x, y)) instanceof Grass && isOccupied(new Vector2d(x, y))) {
            x = ThreadLocalRandom.current().nextInt(0, maxLen);
            y = ThreadLocalRandom.current().nextInt(0, maxLen);
        }
        this.fields.add(new Grass(new Vector2d(x, y)));
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return objectAt(position) instanceof Animal && !(this.isOccupied(position));
    }

    @Override
    public boolean place(Animal animal) {
        Vector2d grassPos = animal.getAnimalsPos();
        if (!(this.isOccupied(grassPos))) {
            this.animals.add(animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for (Grass g : this.fields) {
            if (g.getPosition().equals(position)) {
                return true;
            }
        }

        for (Animal a : this.animals) {
            if (a.getAnimalsPos().equals(position)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (Grass g : this.fields) {
            if (g.getPosition().equals(position)) return g;
        }

        for (Animal a : this.animals) {
            if (a.getAnimalsPos().equals(position)) return a;
        }

        return null;
    }

    public String toString() {
        MapVisualizer animalsMap = new MapVisualizer(this);
        int xMax = Integer.MIN_VALUE, yMax = Integer.MIN_VALUE;
        int xMin = Integer.MAX_VALUE, yMin = Integer.MAX_VALUE;
        for (Grass g : this.fields) {
            xMax = Math.max(xMax, g.getPosition().x);
            yMax = Math.max(yMax, g.getPosition().y);
            xMin = Math.min(xMin, g.getPosition().x);
            yMin = Math.min(yMin, g.getPosition().y);
        }

        for (Animal a : this.animals) {
            xMax = Math.max(xMax, a.getAnimalsPos().x);
            yMax = Math.max(yMax, a.getAnimalsPos().y);
            xMin = Math.min(xMin, a.getAnimalsPos().x);
            yMin = Math.min(yMin, a.getAnimalsPos().y);
        }

        Vector2d vector1 = new Vector2d(xMin, yMin);
        Vector2d vector2 = new Vector2d(xMax, yMax);
        return animalsMap.draw(vector1, vector2);
    }
}
