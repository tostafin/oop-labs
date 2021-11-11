package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimulationEngineTest {
    MoveDirection[] directions = new OptionsParser().parse(new String[]{"f f b l r l f f b f f b"});
    IWorldMap map = new RectangularMap(7, 7);
    Vector2d[] positions = {new Vector2d(0, 0), new Vector2d(6, 3), new Vector2d(5, 7)};
    IEngine engine = new SimulationEngine(directions, map, positions);


    @Test
    void runTest() {
        engine.run();
        Vector2d[] expectedResults = engine.getAnimalsPos();
        assertEquals(positions[0], expectedResults[0]);
        assertEquals(positions[1], expectedResults[1]);
        assertEquals(positions[2], expectedResults[2]);
    }
}