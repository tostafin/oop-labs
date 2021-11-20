package agh.ics.oop;

public class Grass {
    private Vector2d position;
    public Grass(Vector2d pos) {
        this.position = pos;
    }

    public Vector2d getPosition() {
        return this.position;
    }

    public String toString() {
        return "*";
    }
}