package tests.world;

import org.junit.Test;
import ru.madridianfox.ai.SimpleMind;
import ru.madridianfox.genome.Dna;
import ru.madridianfox.world.World;
import ru.madridianfox.world.things.Bot;
import ru.madridianfox.world.things.Rock;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

public class BotTests {
    @Test
    public void freeCellAround(){
        World world = new World(3,3);
        Bot bot = new Bot(new Dna(new ArrayList<>()), new SimpleMind(), new int[]{0,0,0});
        bot.setCell(world.cellByCoords(1,1));
        world.cellByCoords(0,0).setThing(new Rock());
        world.cellByCoords(1,0).setThing(new Rock());
        world.cellByCoords(2,0).setThing(new Rock());

        world.cellByCoords(0,1).setThing(new Rock());
        world.cellByCoords(2,1).setThing(new Rock());

        world.cellByCoords(0,2).setThing(new Rock());
        world.cellByCoords(2,2).setThing(new Rock());

        assertEquals(world.cellByCoords(1,2), bot.freeCellAround());
    }
}
