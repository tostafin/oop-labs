package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnimalTest {
    IWorldMap map = new RectangularMap(4, 4);
    Vector2d initialPosition = new Vector2d(2, 2);
    Animal myAnimal = new Animal(map, initialPosition);

    @Test
    public void testAnimalsDirection() {
        myAnimal.move(MoveDirection.FORWARD);
        assertEquals(myAnimal.getAnimalsDir(), MapDirection.NORTH);

        myAnimal.move(MoveDirection.RIGHT);
        assertEquals(myAnimal.getAnimalsDir(), MapDirection.EAST);

        myAnimal.move(MoveDirection.BACKWARD);
        assertEquals(myAnimal.getAnimalsDir(), MapDirection.EAST);

        myAnimal.move(MoveDirection.LEFT);
        assertEquals(myAnimal.getAnimalsDir(), MapDirection.NORTH);

        myAnimal.move(MoveDirection.LEFT);
        assertEquals(myAnimal.getAnimalsDir(), MapDirection.WEST);
    }

    @Test
    public void testAnimalsPosition() {
        myAnimal.move(MoveDirection.FORWARD);
        assertEquals(myAnimal.getPosition(), new Vector2d(2, 3));

        myAnimal.move(MoveDirection.FORWARD);
        assertEquals(myAnimal.getPosition(), new Vector2d(2, 4));

        myAnimal.move(MoveDirection.FORWARD);
        assertEquals(myAnimal.getPosition(), new Vector2d(2, 4));

        myAnimal.move(MoveDirection.BACKWARD);
        assertEquals(myAnimal.getPosition(), new Vector2d(2, 3));

        myAnimal.move(MoveDirection.RIGHT);
        assertEquals(myAnimal.getPosition(), new Vector2d(2, 3));

        myAnimal.move(MoveDirection.LEFT);
        assertEquals(myAnimal.getPosition(), new Vector2d(2, 3));

        myAnimal.move(MoveDirection.LEFT);
        assertEquals(myAnimal.getPosition(), new Vector2d(2, 3));

        myAnimal.move(MoveDirection.BACKWARD);
        assertEquals(myAnimal.getPosition(), new Vector2d(3, 3));
    }

    @Test
    public void testOutOfMapExit() {
        for (int i = 0; i < 3; i++) myAnimal.move(MoveDirection.FORWARD);
        assertEquals(myAnimal.getPosition(), new Vector2d(2, 4));

        myAnimal.move(MoveDirection.RIGHT);
        for (int i = 0; i < 3; i++) myAnimal.move(MoveDirection.FORWARD);
        assertEquals(myAnimal.getPosition(), new Vector2d(4, 4));

        myAnimal.move(MoveDirection.LEFT);
        for (int i = 0; i < 5; i++) myAnimal.move(MoveDirection.BACKWARD);
        assertEquals(myAnimal.getPosition(), new Vector2d(4, 0));

        myAnimal.move(MoveDirection.LEFT);
        for (int i = 0; i < 5; i++) myAnimal.move(MoveDirection.FORWARD);
        assertEquals(myAnimal.getPosition(), new Vector2d(0, 0));
    }

    @Test
    public void testParser() {
        String[] D1 = {"r", "forward", "right", "left", "l", "backward", "f", "b"};
        OptionsParser tmp1 = new OptionsParser();
        MoveDirection[] A = tmp1.parse(D1);
        assertEquals(A[0], MoveDirection.RIGHT);
        assertEquals(A[1], MoveDirection.FORWARD);
        assertEquals(A[2], MoveDirection.RIGHT);
        assertEquals(A[3], MoveDirection.LEFT);
        assertEquals(A[4], MoveDirection.LEFT);
        assertEquals(A[5], MoveDirection.BACKWARD);
        assertEquals(A[6], MoveDirection.FORWARD);
        assertEquals(A[7], MoveDirection.BACKWARD);
        String[] D2 = {"r", "forward", "right", "left", "l", "backward", "f", "jghghgfhgfhgfhgf"};
        OptionsParser tmp2 = new OptionsParser();
        assertThrows(IllegalArgumentException.class,
                ()->{
                    MoveDirection[] B = tmp2.parse(D2);
                });
    }
}
