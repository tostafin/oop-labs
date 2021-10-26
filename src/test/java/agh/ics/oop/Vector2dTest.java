package agh.ics.oop;

import org.junit.jupiter.api.Test;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {
    @Test
    public void testEquals() {
        // Testy z użyciem ustalonych liczb
        assertTrue(new Vector2d(10, 71).equals(new Vector2d(10, 71)));  // Równe składowe
        assertFalse(new Vector2d(-6, -7).equals(new Vector2d(-6, -8)));  // Różne składowe

        // Testy z użyciem pseudolosowych liczb
        int a = ThreadLocalRandom.current().nextInt(-(1<<15), 1<<15 + 1);
        int b = ThreadLocalRandom.current().nextInt(-(1<<15), 1<<15 + 1);
        assertTrue(new Vector2d(a, b).equals(new Vector2d(a, b)));  // Równe składowe
        int c = ThreadLocalRandom.current().nextInt(-(1<<15), 1<<15 + 1);
        int d = ThreadLocalRandom.current().nextInt(-(1<<15), 1<<15 + 1);
        while (a == c && b == d) d = ThreadLocalRandom.current().nextInt(-(1<<15), 1<<15 + 1);
        assertFalse(new Vector2d(a, b).equals(new Vector2d(c, d)));  // Różne składowe

    }
    @Test
    public void testToString() {
        // Testy z użyciem ustalonych liczb
        assertEquals("(2, -1)", new Vector2d(2, -1).toString());
        assertNotEquals("(2, -1)", new Vector2d(-2, -1).toString());

        // Testy z użyciem pseudolosowych liczb
        int a = ThreadLocalRandom.current().nextInt(-(1<<15), 1<<15 + 1);
        int b = ThreadLocalRandom.current().nextInt(-(1<<15), 1<<15 + 1);
        String resString = "(" + a + ", " + b + ")";
        assertEquals(new Vector2d(a, b).toString(), resString);
        int c;
        do {
            c = ThreadLocalRandom.current().nextInt(-(1<<15), 1<<15 + 1);
        } while (c == b);
        assertNotEquals(new Vector2d(a, c).toString(), resString);
    }
    @Test
    public void testPrecedes() {
        // Testy z użyciem ustalonych liczb
        assertTrue(new Vector2d(2, 5).precedes(new Vector2d(3, 5)));
        assertTrue(new Vector2d(2, 5).precedes(new Vector2d(2, 7)));
        assertFalse(new Vector2d(2, 5).precedes(new Vector2d(3, 4)));
        assertFalse(new Vector2d(2, 5).precedes(new Vector2d(1, 6)));

        // Testy z użyciem pseudolosowych liczb
        int a = ThreadLocalRandom.current().nextInt(-(1<<15), 1<<15 + 1);
        int b = ThreadLocalRandom.current().nextInt(-(1<<15), 1<<15 + 1);
        int c = ThreadLocalRandom.current().nextInt(a, 1<<15 + 1);
        int d = ThreadLocalRandom.current().nextInt(b, 1<<15 + 1);
        assertTrue(new Vector2d(a, b).precedes(new Vector2d(c, d)));
        c = ThreadLocalRandom.current().nextInt(-(1<<15), a + 1);
        d = ThreadLocalRandom.current().nextInt(-(1<<15), b + 1);
        assertFalse(new Vector2d(a, b).precedes(new Vector2d(c, d)));
    }
    @Test
    public void testFollows() {
        // Testy z użyciem ustalonych liczb
        assertTrue(new Vector2d(4, 6).follows(new Vector2d(4, 5)));
        assertTrue(new Vector2d(7, 11).follows(new Vector2d(5, 10)));
        assertFalse(new Vector2d(1, 2).follows(new Vector2d(1, 3)));
        assertFalse(new Vector2d(123, 456).follows(new Vector2d(456, 789)));

        // Testy z użyciem pseudolosowych liczb
        int a = ThreadLocalRandom.current().nextInt(-(1<<15), 1<<15 + 1);
        int b = ThreadLocalRandom.current().nextInt(-(1<<15), 1<<15 + 1);
        int c = ThreadLocalRandom.current().nextInt(-(1<<15), a + 1);
        int d = ThreadLocalRandom.current().nextInt(-(1<<15), b + 1);
        assertTrue(new Vector2d(a, b).follows(new Vector2d(c, d)));
        c = ThreadLocalRandom.current().nextInt(-(1<<15), a + 1);
        d = ThreadLocalRandom.current().nextInt(-(1<<15), b + 1);
        assertFalse(new Vector2d(a, b).precedes(new Vector2d(c, d)));
    }
    @Test
    public void testUpperRight() {
        // Testy z użyciem ustalonych liczb
        assertEquals(new Vector2d(0, 1).upperRight(new Vector2d(1, 0)), new Vector2d(1, 1));
        assertEquals(new Vector2d(3, 6).upperRight(new Vector2d(2, 4)), new Vector2d(3, 6));
        assertNotEquals(new Vector2d(45, 66).upperRight(new Vector2d(55, 77)), new Vector2d(45, 77));
        assertNotEquals(new Vector2d(90, 66).upperRight(new Vector2d(80, 70)), new Vector2d(80, 70));

        // Testy z użyciem pseudolosowych liczb
        int a = ThreadLocalRandom.current().nextInt(-(1<<15), 1<<15 + 1);
        int b = ThreadLocalRandom.current().nextInt(-(1<<15), 1<<15 + 1);
        int c = ThreadLocalRandom.current().nextInt(-(1<<15), 1<<15 + 1);
        int d = ThreadLocalRandom.current().nextInt(-(1<<15), 1<<15 + 1);
        int e = Math.max(a, c);
        int f = Math.max(b, d);
        assertEquals(new Vector2d(a, b).upperRight(new Vector2d(c, d)), new Vector2d(e, f));
        int g = Math.min(a, c) - 1;
        int h = Math.min(b, d) - 1;
        assertNotEquals(new Vector2d(a, b).upperRight(new Vector2d(c, d)), new Vector2d(g, h));
    }
    @Test
    public void testLowerLeft() {
        // Testy z użyciem ustalonych liczb
        assertEquals(new Vector2d(0, 1).lowerLeft(new Vector2d(1, 0)), new Vector2d(0, 0));
        assertEquals(new Vector2d(45, 67).lowerLeft(new Vector2d(33, 22)), new Vector2d(33, 22));
        assertNotEquals(new Vector2d(35, 66).lowerLeft(new Vector2d(55, 77)), new Vector2d(45, 66));
        assertNotEquals(new Vector2d(20, 30).lowerLeft(new Vector2d(40, 10)), new Vector2d(20, 30));

        // Testy z użyciem pseudolosowych liczb
        int a = ThreadLocalRandom.current().nextInt(-(1<<15), 1<<15 + 1);
        int b = ThreadLocalRandom.current().nextInt(-(1<<15), 1<<15 + 1);
        int c = ThreadLocalRandom.current().nextInt(-(1<<15), 1<<15 + 1);
        int d = ThreadLocalRandom.current().nextInt(-(1<<15), 1<<15 + 1);
        int e = Math.min(a, c);
        int f = Math.min(b, d);
        assertEquals(new Vector2d(a, b).lowerLeft(new Vector2d(c, d)), new Vector2d(e, f));
        int g = Math.max(a, c) + 1;
        int h = Math.max(b, d) + 1;
        assertNotEquals(new Vector2d(a, b).lowerLeft(new Vector2d(c, d)), new Vector2d(g, h));
    }
    @Test
    public void testAdd() {
        assertEquals(new Vector2d(2, 6).add(new Vector2d(4, 9)), new Vector2d(6, 15));
        assertEquals(new Vector2d(12, 65).add(new Vector2d(44, 35)), new Vector2d(56, 100));
        assertNotEquals(new Vector2d(-5, -2).add(new Vector2d(4, 3)), new Vector2d(-1, 0));
        assertNotEquals(new Vector2d(12, 13).add(new Vector2d(0, 3)), new Vector2d(12, 15));
    }
}
