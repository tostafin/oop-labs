package agh.ics.oop;

public class OptionsParser {
    public MoveDirection[] parse(String[] stringDirections) {
        MoveDirection[] directionsTmp = new MoveDirection[stringDirections.length];
        int idx = 0;
        for (String s : stringDirections) {
            switch (s) {
                case "f":
                case "forward":
                    directionsTmp[idx++] = MoveDirection.FORWARD;
                    break;
                case "b":
                case "backward":
                    directionsTmp[idx++] = MoveDirection.BACKWARD;
                    break;
                case "l":
                case "left":
                    directionsTmp[idx++] = MoveDirection.LEFT;
                    break;
                case "r":
                case "right":
                    directionsTmp[idx++] = MoveDirection.RIGHT;
                    break;
                default:
//                    throw new IllegalArgumentException(s + " is not legal move specification");
            }
        }
        MoveDirection[] res = new MoveDirection[idx];
        System.arraycopy(directionsTmp, 0, res, 0, idx);
        return res;
    }
}
