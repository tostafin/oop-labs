package agh.ics.oop;

import java.util.LinkedList;
import java.util.List;

public class SimulationEngine implements Runnable {
    private MoveDirection[] directions;
    private final IWorldMap mapInstance;
    Vector2d[] animalsPos;
    private final List<IPositionChangeObserver> observers = new LinkedList<>();
    private int moveDelay;

    public SimulationEngine(IWorldMap mapInstance, Vector2d[] initialPos) {
        this.mapInstance = mapInstance;
        this.animalsPos = initialPos;
        this.moveDelay = 0;
        for (Vector2d v : this.animalsPos) this.mapInstance.place(new Animal(this.mapInstance, v));
    }

    public void addObserver(IPositionChangeObserver observer) {
        this.observers.add(observer);
    }

    public void positionChanged(Vector2d oldPos, Vector2d newPos) {
        for (IPositionChangeObserver observer : this.observers) {
            observer.positionChanged(oldPos, newPos);
        }
    }

    public void changeMoveDelay(int newMoveDelay) {
        this.moveDelay = newMoveDelay;
    }

    public void setDirections(MoveDirection[] directions) {
        this.directions = directions;
    }


    @Override
    public void run() {
        int movesNo = this.directions.length;
        int animalsNo = this.animalsPos.length;
        for (int i = 0; i < movesNo; i++) {
            if (this.mapInstance.objectAt(this.animalsPos[i % animalsNo]) instanceof Animal) {
                Animal animal = (Animal) (this.mapInstance.objectAt(this.animalsPos[i % animalsNo]));
                Vector2d oldPos = animal.getPosition();
                animal.move(this.directions[i]);
                Vector2d newPos = animal.getPosition();
                this.animalsPos[i % animalsNo] = newPos;
                this.positionChanged(oldPos, newPos);
            }

            try {
                System.out.println("Thread started.");
                Thread.sleep(this.moveDelay);
            } catch (InterruptedException e) {
                System.out.println("Simulation disrupted by an exception: " + e);
            }

        }
    }
}
