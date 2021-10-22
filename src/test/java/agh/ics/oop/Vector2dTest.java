package agh.ics.oop;

import org.junit.jupiter.api.Test;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {
    public int randomPositiveOrZero() {
        return ThreadLocalRandom.current().nextInt(0, 1<<15 + 1);
    }

    public int randomNegative() {
        return ThreadLocalRandom.current().nextInt(-(1<<15), 0);
    }

    public int randomComponent() {
        return ThreadLocalRandom.current().nextInt(0, 4);
    }

    @Test
    public void testEquals() {
        // Corner cases
        // Testy z użyciem pseudolosowych liczb
        // Równe składowe
        // (+, +)
        int x = randomPositiveOrZero();
        int y = randomPositiveOrZero();
        assertTrue(new Vector2d(x, y).equals(new Vector2d(x, y)));

        // (-, -)
        x = randomNegative();
        y = randomNegative();
        assertTrue(new Vector2d(x, y).equals(new Vector2d(x, y)));

        // (-, +)
        x = randomNegative();
        y = randomPositiveOrZero();
        assertTrue(new Vector2d(x, y).equals(new Vector2d(x, y)));

        // (+, -)
        x = randomPositiveOrZero();
        y = randomNegative();
        assertTrue(new Vector2d(x, y).equals(new Vector2d(x, y)));


        // Różne składowe
        // (+, +)
        int x1 = randomPositiveOrZero();
        int y1 = randomPositiveOrZero();
        int x2 = randomPositiveOrZero();
        int y2 = randomPositiveOrZero();
        while (x1 == x2 && y1 == y2) {
            int c = randomComponent();
            switch(c) {
                case 0:
                    x1 = randomPositiveOrZero();
                    break;

                case 1:
                    y1 = randomPositiveOrZero();
                    break;

                case 2:
                    x2 = randomPositiveOrZero();
                    break;

                case 3:
                    y2 = randomPositiveOrZero();
                    break;
            }
        }
        assertFalse(new Vector2d(x1, y1).equals(new Vector2d(x2, y2)));

        // (-, -)
        x1 = randomNegative();
        y1 = randomNegative();
        x2 = randomNegative();
        y2 = randomNegative();
        while (x1 == x2 && y1 == y2) {
            int c = randomComponent();
            switch(c) {
                case 0:
                    x1 = randomNegative();
                    break;

                case 1:
                    y1 = randomNegative();
                    break;

                case 2:
                    x2 = randomNegative();
                    break;

                case 3:
                    y2 = randomNegative();
                    break;
            }
        }
        assertFalse(new Vector2d(x1, y1).equals(new Vector2d(x2, y2)));

        // (-, +)
        x1 = randomNegative();
        y1 = randomPositiveOrZero();
        x2 = randomNegative();
        y2 = randomPositiveOrZero();
        while (x1 == x2 && y1 == y2) {
            int c = randomComponent();
            switch(c) {
                case 0:
                    x1 = randomNegative();
                    break;

                case 1:
                    y1 = randomPositiveOrZero();
                    break;

                case 2:
                    x2 = randomNegative();
                    break;

                case 3:
                    y2 = randomPositiveOrZero();
                    break;
            }
        }
        assertFalse(new Vector2d(x1, y1).equals(new Vector2d(x2, y2)));

        // (+, -)
        x1 = randomPositiveOrZero();
        y1 = randomNegative();
        x2 = randomPositiveOrZero();
        y2 = randomNegative();
        while (x1 == x2 && y1 == y2) {
            int c = randomComponent();
            switch(c) {
                case 0:
                    x1 = randomPositiveOrZero();
                    break;

                case 1:
                    y1 = randomNegative();
                    break;

                case 2:
                    x2 = randomPositiveOrZero();
                    break;

                case 3:
                    y2 = randomNegative();
                    break;
            }
        }
        assertFalse(new Vector2d(x1, y1).equals(new Vector2d(x2, y2)));
    }
}
