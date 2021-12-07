package agh.ics.oop;

public class SimulationEngine implements IEngine {
    private final MoveDirection[] moves;
    private final IWorldMap mapInstance;
    Vector2d[] animalsPos;

    public SimulationEngine(MoveDirection[] moves, IWorldMap mapInstance, Vector2d[] initialPos) {
        this.moves = moves;
        this.mapInstance = mapInstance;
        this.animalsPos = initialPos;
        for (Vector2d v : this.animalsPos) this.mapInstance.place(new Animal(this.mapInstance, v));
    }

    @Override
    public void run() {
        int movesNo = this.moves.length;
        int animalsNo = this.animalsPos.length;
        for (int i = 0; i < movesNo; i++) {
            if (this.mapInstance.objectAt(this.animalsPos[i % animalsNo]) instanceof Animal) {
                Animal animal = (Animal) (this.mapInstance.objectAt(this.animalsPos[i % animalsNo]));
                animal.move(this.moves[i]);
                this.animalsPos[i % animalsNo] = animal.getAnimalsPos();
            }

        }
    }
}
