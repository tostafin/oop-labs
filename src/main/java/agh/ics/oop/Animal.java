package agh.ics.oop;

import java.util.LinkedList;
import java.util.List;

public class Animal implements IMapElement {
//    private MapDirection animalsDirection = MapDirection.NORTH;
//    private Vector2d animalsPosition = new Vector2d(2, 2);
//
//    public String toString() {
//        return "Kierunek: " + animalsDirection + ", pozycja: " + animalsPosition;
//    }

//    boolean isAt(Vector2d position) {
//        return position.equals(animalsPos);
//    }

//    public void move(MoveDirection direction) {
//        Vector2d movePosition = null;
//        switch (direction) {
//            case RIGHT:
//                animalsDirection = animalsDirection.next();
//                break;
//
//            case LEFT:
//                animalsDirection = animalsDirection.previous();
//                break;
//
//            case FORWARD:
//                switch (animalsDirection) {
//                    case EAST:
//                        movePosition = new Vector2d(1, 0);
//                        break;
//
//                    case WEST:
//                        movePosition = new Vector2d(-1, 0);
//                        break;
//
//                    case NORTH:
//                        movePosition = new Vector2d(0, 1);
//                        break;
//
//                    case SOUTH:
//                        movePosition = new Vector2d(0, -1);
//                        break;
//                }
//                animalsPosition = animalsPosition.add(movePosition);
//                break;
//
//            case BACKWARD:
//                switch (animalsDirection) {
//                    case EAST:
//                        movePosition = new Vector2d(-1, 0);
//                        break;
//
//                    case WEST:
//                        movePosition = new Vector2d(1, 0);
//                        break;
//
//                    case NORTH:
//                        movePosition = new Vector2d(0, -1);
//                        break;
//
//                    case SOUTH:
//                        movePosition = new Vector2d(0, 1);
//                        break;
//                }
//                animalsPosition = animalsPosition.add(movePosition);
//                break;
//        }
//        if (movePosition != null && !(animalsPosition.follows(new Vector2d(0, 0)) && animalsPosition.precedes(new Vector2d(4, 4)))) {
//            animalsPosition = animalsPosition.subtract(movePosition);
//        }
//    }
//

    private final IWorldMap map;
    private Vector2d animalsPos;
    private MapDirection animalsDir = MapDirection.NORTH;
    private final List<IPositionChangeObserver> observers;

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        this.animalsPos = initialPosition;
        this.observers = new LinkedList<>();
    }

    public MapDirection getAnimalsDir() {
        return animalsDir;
    }

    @Override
    public Vector2d getPosition() {
        return animalsPos;
    }

    public IWorldMap getMap() {
        return map;
    }

    public String toString() {
        switch (animalsDir) {
            case NORTH:
                return "N";

            case EAST:
                return "E";

            case SOUTH:
                return "S";

            case WEST:
                return "W";
        }
        return null;
    }

    public Vector2d changeToUnitVector(MapDirection dir) {
        switch (dir) {
            case EAST:
                return new Vector2d(1, 0);

            case WEST:
                return new Vector2d(-1, 0);

            case NORTH:
                return new Vector2d(0, 1);

            case SOUTH:
                return new Vector2d(0, -1);
        }
        return null;
    }

    public void move(MoveDirection direction) {
        Vector2d oldPos = this.animalsPos;
        switch (direction) {
            case RIGHT:
                this.animalsDir = this.animalsDir.next();
                break;

            case LEFT:
                this.animalsDir = this.animalsDir.previous();
                break;

            case FORWARD:
                if (this.map.canMoveTo(this.animalsPos.add(changeToUnitVector(this.animalsDir)))) {
                    this.animalsPos = this.animalsPos.add(changeToUnitVector(this.animalsDir));
                    positionChanged(oldPos, this.animalsPos);
                }
                break;


            case BACKWARD:
                if (this.map.canMoveTo(this.animalsPos.subtract(changeToUnitVector(this.animalsDir)))) {
                    this.animalsPos = this.animalsPos.subtract(changeToUnitVector(this.animalsDir));
                    positionChanged(oldPos, this.animalsPos);
                }
                break;
        }
    }

    public void addObserver(IPositionChangeObserver observer) {
        this.observers.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer) {
        this.observers.remove(observer);
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        for (IPositionChangeObserver obs : this.observers) obs.positionChanged(oldPosition, newPosition);
    }

    @Override
    public String getSource() {
        switch (this.animalsDir) {
            case NORTH:
                return "src/main/resources/up.png";
            case SOUTH:
                return "src/main/resources/down.png";
            case WEST:
                return "src/main/resources/left.png";
            case EAST:
                return "src/main/resources/right.png";
        }
        return null;
    }
}
