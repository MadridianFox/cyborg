package ru.madridianfox.world.things;

import ru.madridianfox.world.CellInterface;

public abstract class Thing {
    public static final Thing noThing = new NoThing();
    /**
     * @return цвет объекта
     */
    public abstract int[] color();

    /**
     * Попытаться съесть объект
     * @return количество енергии полученной при поедании
     */
    public int eat(){
        return 0;
    }

    /**
     * Поставить объект в клетку
     * @param cell
     */
    public void setCell(CellInterface cell){
        cell.setThing(this);
    }
}
