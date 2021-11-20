package agh.ics.oop;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

public class GrassField extends AbstractWorldMap {
    private int n;
    private List<Animal> animals;
    private List<Grass> fields;

    public GrassField(int n) {
        this.n = n;
        this.animals = new ArrayList<>();
        this.fields = new ArrayList<>();
        int maxLen = (int) Math.sqrt(this.n * 10);
        while (this.fields.size() < this.n) {
            int x = ThreadLocalRandom.current().nextInt(0, maxLen);
            int y = ThreadLocalRandom.current().nextInt(0, maxLen);
            Vector2d pos = new Vector2d(x, y);
            boolean duplicate = false;
            for (Grass g : this.fields) {
                if (g.getPosition().equals(pos)) {
                    duplicate = true;
                    break;
                }
            }
            if (!(duplicate)) this.fields.add(new Grass(new Vector2d(x, y)));
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return super.canMoveTo(position) || objectAt(position) instanceof Grass;
    }

    @Override
    public Object objectAt(Vector2d position) {
        Object animalCheck = super.objectAt(position);
        if (animalCheck != null) return animalCheck;
        for (Grass g : this.fields) {
            if (g.getPosition().equals(position)) return g;
        }
        return null;
    }

    public Vector2d getLowerLeft() {
        int xMin = Integer.MAX_VALUE, yMin = Integer.MAX_VALUE;
        for (Grass g : this.fields) {
            xMin = Math.min(xMin, g.getPosition().x);
            yMin = Math.min(yMin, g.getPosition().y);
        }

        for (Animal a : this.animals) {
            xMin = Math.min(xMin, a.getAnimalsPos().x);
            yMin = Math.min(yMin, a.getAnimalsPos().y);
        }

        return new Vector2d(xMin, yMin);
    }

    public Vector2d getUpperRight() {
        int xMax = Integer.MIN_VALUE, yMax = Integer.MIN_VALUE;
        for (Grass g : this.fields) {
            xMax = Math.max(xMax, g.getPosition().x);
            yMax = Math.max(yMax, g.getPosition().y);
        }
        for (Animal a : this.animals) {
            xMax = Math.max(xMax, a.getAnimalsPos().x);
            yMax = Math.max(yMax, a.getAnimalsPos().y);
        }

        return new Vector2d(xMax, yMax);
    }

    public String toString() {
        return super.toString();
    }
}