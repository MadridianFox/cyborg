package tests.ai;

import org.junit.Test;
import ru.madridianfox.ai.sensors.Sensor;

import static org.junit.Assert.assertEquals;

public class SensorsTest {
    class TestSensor extends Sensor{
        private int max_value;
        private int value;
        public TestSensor(int max_value, int value){
            this.max_value = max_value;
            this.value = value;
        }
        @Override
        protected int maxValue() {
            return this.max_value;
        }

        @Override
        protected int value() {
            return this.value;
        }
    }
    @Test
    public void signalNormalization(){
        int v = 10, m = 100;
        Sensor sensor = new TestSensor(m, v);
        float out = (float) v / (float) m;
        assertEquals(out, sensor.normalizedValue(), 0.001f);
    }

//    @Test
//    public void signalFromEye(){
//        int[][] coords = new int[][]{
//                {0,0}, {1,0}, {2,0},
//                {0,1},        {2,1},
//                {0,2}, {1,2}, {2,2},
//        };
//
//        int[][] colors = new int[][]{
//            {255,0,0}, {255,128,0}, {255,255,0},
//            {0,255,0},              {128,125,255},
//            {0,0,255}, {255,0,255}, {255,255,255}
//        };
//
//        Turn[][] turns = new Turn[][]{
//                {Turn.NoTurn, Turn.Left},           {Turn.NoTurn},        {Turn.NoTurn, Turn.Right},
//                {Turn.Left},                                              {Turn.Right},
//                {Turn.Left, Turn.Left}, {Turn.Left, Turn.Left, Turn.Left},{Turn.Right, Turn.Right}
//        };
//
//        BotWorld world = new BotWorld(3, 3);
//        Bot bot = new Bot(world.cellByCoords(1,1), Sides.North, 1000);
//        for(int i=0; i < coords.length; i++){
//            int x = coords[i][0],
//                y = coords[i][1];
//            int[] color = colors[i];
//            world.cellByCoords(x, y).setThing(new ColoredThing(color));
//        }
//
//        for(int i=0; i < coords.length; i++){
//            int x = coords[i][0],
//                y = coords[i][1];
//            Turn[] turn = turns[i];
//            int[] color = colors[i];
//            // цикл по каналам r,g,b
//            for(int channel=0; channel<3; channel++){
//                Sensor sensor = new EyeSensor(channel, turn, bot);
//                float sensor_value = sensor.normalizedValue();
//                float out = (float) color[channel] / 255.0f;
//                assertEquals(String.format("On cell %d:%d in channel %d value is %f", x, y, channel, sensor_value), out, sensor_value, 0.001f);
//            }
//        }
//    }
}
