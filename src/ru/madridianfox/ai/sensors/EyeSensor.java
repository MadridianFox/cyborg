package ru.madridianfox.ai.sensors;

import ru.madridianfox.world.CellInterface;
import ru.madridianfox.world.Turn;
import ru.madridianfox.world.things.Bot;

public class EyeSensor extends Sensor {
    private int chanell;
    private Turn[] turns;
    private Bot bot;

    public EyeSensor(int chanell, Turn[] turns, Bot bot){
        this.chanell = chanell;
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
        return cell.color()[this.chanell];
    }
}
