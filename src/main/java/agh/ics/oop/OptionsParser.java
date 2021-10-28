package agh.ics.oop;

public class OptionsParser {
    public MoveDirection[] parse(String[] stringDirections) {
        MoveDirection[] directionsTmp = new MoveDirection[stringDirections.length];
        int idx = 0;
        for (String s : stringDirections) {
            if (s.equals("f") || s.equals("forward")) directionsTmp[idx++] = MoveDirection.FORWARD;
            if (s.equals("b") || s.equals("backward")) directionsTmp[idx++] = MoveDirection.BACKWARD;
            if (s.equals("l") || s.equals("left")) directionsTmp[idx++] = MoveDirection.LEFT;
            if (s.equals("r") || s.equals("right")) directionsTmp[idx++] = MoveDirection.RIGHT;
        }
        MoveDirection[] res = new MoveDirection[idx];
        System.arraycopy(directionsTmp, 0, res, 0, idx);
        return res;
    }
}
