package agh.ics.oop;

public class Animal {
    private MapDirection animalsDirection = MapDirection.NORTH;
    private Vector2d animalsPosition = new Vector2d(2, 2);

    public String toString() {
        return "Kierunek: " + animalsDirection + ", pozycja: " + animalsPosition;
    }

    boolean isAt(Vector2d position) {
        return position.equals(animalsPosition);
    }

    public void move(MoveDirection direction) {
        Vector2d movePosition = null;
        switch (direction) {
            case RIGHT:
                animalsDirection = animalsDirection.next();
                break;

            case LEFT:
                animalsDirection = animalsDirection.previous();
                break;

            case FORWARD:
                switch (animalsDirection) {
                    case EAST:
                        movePosition = new Vector2d(1, 0);
                        break;

                    case WEST:
                        movePosition = new Vector2d(-1, 0);
                        break;

                    case NORTH:
                        movePosition = new Vector2d(0, 1);
                        break;

                    case SOUTH:
                        movePosition = new Vector2d(0, -1);
                        break;
                }
                animalsPosition = animalsPosition.add(movePosition);
                break;

            case BACKWARD:
                switch (animalsDirection) {
                    case EAST:
                        movePosition = new Vector2d(-1, 0);
                        break;

                    case WEST:
                        movePosition = new Vector2d(1, 0);
                        break;

                    case NORTH:
                        movePosition = new Vector2d(0, -1);
                        break;

                    case SOUTH:
                        movePosition = new Vector2d(0, 1);
                        break;
                }
                animalsPosition = animalsPosition.add(movePosition);
                break;
        }
        if (movePosition != null && !(animalsPosition.follows(new Vector2d(0, 0)) && animalsPosition.precedes(new Vector2d(4, 4)))) {
            animalsPosition = animalsPosition.subtract(movePosition);
        }
    }

    public MapDirection getDirection() {
        return animalsDirection;
    }

    public Vector2d getPosition() {
        return animalsPosition;
    }
}
