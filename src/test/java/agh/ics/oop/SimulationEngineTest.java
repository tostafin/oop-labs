package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimulationEngineTest {

    @Test
    void runTest() {
        MoveDirection[] directions1 = new OptionsParser().parse(new String[]{"f", "f", "b", "l", "r", "l", "f", "f", "b", "f", "f", "b"});
        IWorldMap map1 = new RectangularMap(7, 7);
        Vector2d[] positions1 = {new Vector2d(0, 0), new Vector2d(6, 3), new Vector2d(5, 7)};
        IEngine engine1 = new SimulationEngine(directions1, map1, positions1);
        engine1.run();
        Vector2d[] expectedResults1 = {new Vector2d(0, 1), new Vector2d(7, 4), new Vector2d(7, 6)};
        Vector2d[] actualResults1 = engine1.getAnimalsPos();
        assertArrayEquals(expectedResults1, actualResults1);

        MoveDirection[] directions2 = new OptionsParser().parse(new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"});
        IWorldMap map2 = new RectangularMap(10, 5);
        Vector2d[] positions2 = {new Vector2d(2, 2), new Vector2d(3, 4)};
        IEngine engine2 = new SimulationEngine(directions2, map2, positions2);
        engine2.run();
        Vector2d[] expectedResults2 = {new Vector2d(2, 0), new Vector2d(3, 5)};
        Vector2d[] actualResults2 = engine2.getAnimalsPos();
        assertArrayEquals(expectedResults2, actualResults2);
    }
}