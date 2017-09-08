package ru.madridianfox.world.things;

public class ColoredThing extends Thing {
    private int[] color;

    public ColoredThing(int[] color){
        this.color = color;
    }

    @Override
    public int[] color() {
        return this.color;
    }
}
