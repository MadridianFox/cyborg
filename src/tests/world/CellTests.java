package tests.world;

import org.junit.Test;
import ru.madridianfox.world.Cell;
import ru.madridianfox.world.CellInterface;
import ru.madridianfox.world.World;
import ru.madridianfox.world.things.ColoredThing;
import ru.madridianfox.world.things.Rock;
import ru.madridianfox.world.things.Thing;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class CellTests {
    @Test
    public void thingOperations(){
        CellInterface cell = new Cell(new World(1,1),0,0);
        Thing rock = new Rock();
        cell.setThing(rock);
        assertEquals(rock, cell.thing());
    }
    @Test
    public void cellColor(){
        CellInterface cell = new Cell(new World(1,1),0,0);
        Thing thing = new ColoredThing(new int[]{128,255,0});
        assertArrayEquals(new int[]{43,43,43}, cell.color());
        cell.setThing(thing);
        assertArrayEquals(new int[]{128,255,0}, cell.color());
    }
}
