package ru.madridianfox.world;

import ru.madridianfox.world.things.Thing;

public class Cell implements CellInterface{
    private Thing thing = Thing.noThing;
    private World world;
    private int x,y;
    private int energy;

    public Cell(World world, int x, int y){
        this.world = world;
        this.x = x;
        this.y = y;
        int e = 2 - (y * 4/world.height());
        energy = e >= 0 ? e : 0;
    }
    @Override
    public Thing thing() {
        return thing;
    }

    @Override
    public boolean setThing(Thing thing) {
        this.thing = thing;
        return true;
    }

    @Override
    public CellInterface cellByDirection(Sides dir) {
        int[] coords = dir.vectorFrom(x, y);
        return world.cellByCoords(coords[0], coords[1]);
    }

    @Override
    public World world() {
        return world;
    }

    @Override
    public int sunEnergy() {
        return energy;
    }

    @Override
    public int[] color() {
        return new int[]{43+energy*40,43+energy*40,43};
    }

    @Override
    public CellInterface relativeCell(int i, int j) {
        return world.cellByCoords(x+i, y+j);
    }
}
