package agh.ics.oop;

import javax.swing.text.html.Option;

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
            switch (args[i]) {
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
//        run(directions);
//        Vector2d position1 = new Vector2d(1, 2);
//        System.out.println(position1);
//        Vector2d position2 = new Vector2d(-2, 1);
//        System.out.println(position2);
//        System.out.println(position1.add(position2));
//        MapDirection dir1 = MapDirection.EAST;
//        System.out.println(dir1);
//        System.out.println(dir1.next());
//        System.out.println(dir1.previous());
//        System.out.println(dir1.toUnitVector());
//        System.out.println("Stop");
        Animal myAnimal = new Animal();
        System.out.println(myAnimal);
//        zwierzatko.move(MoveDirection.RIGHT);
//        zwierzatko.move(MoveDirection.FORWARD);
//        zwierzatko.move(MoveDirection.FORWARD);
//        zwierzatko.move(MoveDirection.FORWARD);
        String[] D = {"r", "f", "f", "forward"};
        OptionsParser tmp = new OptionsParser();
        MoveDirection[] A = tmp.parse(D);
        for (MoveDirection d : A) myAnimal.move(d);
        System.out.println(myAnimal);
        /*
        Do 10.
        1) Tablica dwuwymiarowa typu boolean
        2) Trzymanie zwierząt w liście
        Którą opcję wybrać zależy od tego, ile jest zwierząt.
        */
    }
}
