package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {
    IWorldMap grassFields = new GrassField(10);
    Animal Gregory = new Animal(grassFields, new Vector2d(2, 2));
    Animal Johannes = new Animal(grassFields, new Vector2d(4, 7));
    Animal Margaret = new Animal(grassFields, new Vector2d(3, 1));

    @Test
    void canMoveToTest() {
        grassFields.place(Gregory);
        grassFields.place(Johannes);
        grassFields.place(Margaret);
        assertFalse(grassFields.canMoveTo(new Vector2d(2, 2)));
        assertTrue(grassFields.canMoveTo(new Vector2d(3, 2)));
        assertFalse(grassFields.canMoveTo(new Vector2d(4, 7)));
        assertTrue(grassFields.canMoveTo(new Vector2d(5, 2)));
        assertTrue(grassFields.canMoveTo(new Vector2d(6, 2)));
        assertFalse(grassFields.canMoveTo(new Vector2d(3, 1)));
    }

    @Test
    void placeTest() {
        Animal Carlson = new Animal(grassFields, new Vector2d(4, 7));
        assertTrue(grassFields.place(Gregory));
        assertTrue(grassFields.place(Johannes));
        assertTrue(grassFields.place(Margaret));
        assertFalse(grassFields.place(Carlson));
    }

    @Test
    void isOccupiedTest() {
        grassFields.place(Gregory);
        grassFields.place(Johannes);
        grassFields.place(Margaret);
        assertTrue(grassFields.isOccupied(Gregory.getAnimalsPos()));
        assertTrue(grassFields.isOccupied(Johannes.getAnimalsPos()));
        assertTrue(grassFields.isOccupied(Margaret.getAnimalsPos()));
        assertFalse(grassFields.isOccupied(new Vector2d(2, 3)));
        assertFalse(grassFields.isOccupied(new Vector2d(7, 4)));
        assertFalse(grassFields.isOccupied(new Vector2d(2, 3)));
    }

    @Test
    void objectAtTest() {
        grassFields.place(Gregory);
        grassFields.place(Johannes);
        grassFields.place(Margaret);
        assertEquals(grassFields.objectAt(Gregory.getAnimalsPos()), Gregory);
        assertEquals(grassFields.objectAt(Johannes.getAnimalsPos()), Johannes);
        assertEquals(grassFields.objectAt(Margaret.getAnimalsPos()), Margaret);
    }
}