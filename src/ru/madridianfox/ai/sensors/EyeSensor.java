package ru.madridianfox.ai.sensors;

import ru.madridianfox.world.CellInterface;
import ru.madridianfox.world.Turn;
import ru.madridianfox.world.things.Bot;

public class EyeSensor extends Sensor {
    private Turn[] turns;
    private Bot bot;

    public EyeSensor(Turn[] turns, Bot bot){
        this.turns = turns;
        this.bot = bot;
    }
    @Override
    protected int maxValue() {
        return 255;
    }

    @Override
    protected int value() {
        CellInterface cell = bot.relativeCell(this.turns);
        int[] color = cell.color();
        return (color[0]+color[1]+color[2])/255;
    }
}
