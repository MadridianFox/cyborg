package ru.madridianfox.world.things;

import ru.madridianfox.world.CellInterface;

public class Rock extends Thing {
    private CellInterface cell;
    @Override
    public int[] color() {
        return new int[]{255, 255, 255};
    }

    @Override
    public void setCell(CellInterface cell) {
        this.cell = cell;
        super.setCell(cell);
    }
}
