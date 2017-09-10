package tests.world;

import org.junit.Test;
import ru.madridianfox.ai.SimpleMind;
import ru.madridianfox.genome.Dna;
import ru.madridianfox.gui.components.SubscriberInterface;
import ru.madridianfox.world.CellInterface;
import ru.madridianfox.world.Sides;
import ru.madridianfox.world.World;
import ru.madridianfox.world.things.Bot;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WorldTests {
    @Test
    public void constructorTest(){
        World world = new World(3,2);
        assertEquals(3, world.width());
        assertEquals(2, world.height());
        for(CellInterface[] column: world.cells){
            for(CellInterface cell: column){
                assertTrue(cell != null);
            }
        }
    }

    class TestSubscriber implements SubscriberInterface{
        public World world;
        public int count_updates = 0;
        @Override
        public void update(World world) {
            this.world = world;
            this.count_updates++;
        }
    }
    @Test
    public void subscriberOperations(){
        World world = new World(3,2);
        TestSubscriber subscriber = new TestSubscriber();
        world.addSubscriber(subscriber);
        assertEquals(1, subscriber.count_updates);
        assertTrue(subscriber.world != null);

        world.notifySubscribers();
        assertEquals(2, subscriber.count_updates);

        world.delSubscriber(subscriber);
        world.notifySubscribers();
        assertEquals(2, subscriber.count_updates);
    }

    class TestBot extends Bot {
        public int count_updates=0;
        public TestBot(CellInterface cell, Sides dir, int energy) {
            super(new Dna(new ArrayList<>()),new SimpleMind(),new int[]{0,0,0});
        }
        @Override
        public void update(){
            this.count_updates++;
        }
    }
    @Test
    public void worldUpdating(){
        World world = new World(3,2);
        TestBot bot = new TestBot(world.cellByCoords(0,0), Sides.North, 1000);
        assertEquals(0, bot.count_updates);

        world.updateWorld();
        assertEquals(1, bot.count_updates);
    }
}
