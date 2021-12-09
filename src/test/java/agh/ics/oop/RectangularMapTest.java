package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {
    int width = 7;
    int height = 7;
    IWorldMap map = new RectangularMap(width, height);
    Animal Gregory = new Animal(map, new Vector2d(2, 2));
    Animal Johannes = new Animal(map, new Vector2d(4, 7));
    Animal Margaret = new Animal(map, new Vector2d(3, 1));

    @Test
    void canMoveToTest() {
        map.place(Gregory);
        map.place(Johannes);
        map.place(Margaret);
        assertFalse(map.canMoveTo(new Vector2d(2, 2)));
        assertTrue(map.canMoveTo(new Vector2d(3, 2)));
        assertFalse(map.canMoveTo(new Vector2d(4, 7)));
        assertTrue(map.canMoveTo(new Vector2d(5, 2)));
        assertTrue(map.canMoveTo(new Vector2d(6, 2)));
        assertFalse(map.canMoveTo(new Vector2d(3, 1)));
    }

    @Test
    void placeTest() {
        Animal Carlson = new Animal(map, new Vector2d(4, 7));
        assertTrue(map.place(Gregory));
        assertTrue(map.place(Johannes));
        assertTrue(map.place(Margaret));
        assertThrows(IllegalArgumentException.class, ()-> map.place(Carlson));
    }

    @Test
    void isOccupiedTest() {
        map.place(Gregory);
        map.place(Johannes);
        map.place(Margaret);
        assertTrue(map.isOccupied(Gregory.getPosition()));
        assertTrue(map.isOccupied(Johannes.getPosition()));
        assertTrue(map.isOccupied(Margaret.getPosition()));
        assertFalse(map.isOccupied(new Vector2d(2, 3)));
        assertFalse(map.isOccupied(new Vector2d(7, 4)));
        assertFalse(map.isOccupied(new Vector2d(2, 3)));
    }

    @Test
    void objectAtTest() {
        map.place(Gregory);
        map.place(Johannes);
        map.place(Margaret);
        assertEquals(map.objectAt(Gregory.getPosition()), Gregory);
        assertEquals(map.objectAt(Johannes.getPosition()), Johannes);
        assertEquals(map.objectAt(Margaret.getPosition()), Margaret);
    }
}