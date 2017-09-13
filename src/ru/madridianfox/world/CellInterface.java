package ru.madridianfox.world;

import ru.madridianfox.world.things.Thing;

public interface CellInterface {
    /**
     * Получить вещь лежащую на клетке
     * @return вещь
     */
    Thing thing();

    /**
     * Попытаться положить вещь на клетку
     * @param thing вещь, которую зхотим положить
     * @return флаг успешности попытки
     */
    boolean setThing(Thing thing);

    /**
     * Получить мир в котором стоит клетка
     * @return мир
     */
    World world();

    /**
     * Получить енергию солнца от клетки
     * @return количество енергии производимое клеткой
     */
    int sunEnergy();

    /**
     * Получить цыет клетки
     * @return массив целочисленных каналов r,g,b
     */
    int[] color();

    /**
     * Получить соседнюю клетку в указанном направлении
     * @param dir направлене
     * @return клетка
     */
    CellInterface cellByDirection(Sides dir);

    /**
     * Получить соседнюю клетку по относительным координатам
     * @param i смещение по x
     * @param j смезение по y
     * @return клетка
     */
    CellInterface relativeCell(int i, int j);
}
