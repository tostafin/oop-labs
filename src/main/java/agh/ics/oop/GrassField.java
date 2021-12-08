package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GrassField extends AbstractWorldMap {
    public final List<Grass> fields;
    private final MapBoundary mapBoundary = new MapBoundary();

    public GrassField(int n) {
        this.fields = new ArrayList<>();
        int maxLen = (int) Math.sqrt(n * 10);
        while (this.fields.size() < n) {
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
            if (!(duplicate)) {
                Grass grass = new Grass(new Vector2d(x, y));
                this.fields.add(grass);
                this.mapBoundary.addGrass(grass);
            }
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return super.canMoveTo(position) || objectAt(position) instanceof Grass;
    }

    @Override
    public boolean place(Animal animal) {
        boolean animalPlaced = super.place(animal);
        if (animalPlaced) {
            this.mapBoundary.addAnimal(animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        if (super.isOccupied(position)) return true;
        for (Grass g : this.fields) {
            if (g.getPosition().equals(position)) {
                return true;
            }
        }
        return false;
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

    @Override
    public Vector2d getLowerLeft() {
        return mapBoundary.getLowerLeft();
    }

    @Override
    public Vector2d getUpperRight() {
        return mapBoundary.getUpperRight();
    }
}
