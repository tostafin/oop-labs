package agh.ics.oop;

public class World {

    public static void run(Direction[] dirs) {
        for (Direction d : dirs) {
            if (d != null) {
                switch (d) {
                    case f:
                        System.out.println("Zwierzak idzie do przodu");
                        break;

                    case b:
                        System.out.println("Zwierzak idzie do tyłu");
                        break;

                    case r:
                        System.out.println("Zwierzak skręca w prawo");
                        break;

                    case l:
                        System.out.println("Zwierzak skręca w lewo");
                        break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Direction[] directions = new Direction[args.length];
        for (int i = 0; i < args.length; i++) {
            switch(args[i]) {
                case "f":
                    directions[i] = Direction.f;
                    break;

                case "b":
                    directions[i] = Direction.b;
                    break;

                case "r":
                    directions[i] = Direction.r;
                    break;

                case "l":
                    directions[i] = Direction.l;
                    break;
            }
        }
        System.out.println("Start");
        run(directions);
        System.out.println("Stop");
    }
}
