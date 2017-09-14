package ru.madridianfox.world.things;

import ru.madridianfox.world.CellInterface;

public class Food extends Thing {
    private int[] color = new int[]{255, 255, 0};
    private CellInterface cell;
    public Food(CellInterface cell){
        this.cell = cell;
    }
    @Override
    public int[] color() {
        return color;
    }

    @Override
    public int eat(){
        cell.setThing(Thing.noThing);
        return 10;
    }
}
