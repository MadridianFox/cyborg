package ru.madridianfox.world;

import ru.madridianfox.world.things.Thing;

public class Cell implements CellInterface{
    private Thing thing = Thing.noThing;
    private World world;
    private int x,y;

    public Cell(World world, int x, int y){
        this.world = world;
        this.x = x;
        this.y = y;
    }
    @Override
    public Thing thing() {
        return this.thing;
    }

    @Override
    public boolean setThing(Thing thing) {
        this.thing = thing;
        return true;
    }

    @Override
    public CellInterface cellByDirection(Sides dir) {
        int[] coords = dir.vectorFrom(this.x, this.y);
        return this.world.cellByCoords(coords[0], coords[1]);
    }

    @Override
    public int[] color() {
        return this.thing.color();
    }

    @Override
    public World world() {
        return this.world;
    }
}
