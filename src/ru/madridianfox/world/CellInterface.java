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
     * Получить соседнюю клетку в указанном направлении
     * @param dir направлене
     * @return клетка
     */
    CellInterface cellByDirection(Sides dir);

    /**
     * Получить цвет клетки
     * @return цвет ы ыиде массива из трёх int [255, 255, 255]
     */
    int[] color();

    /**
     * Получить мир в котором стоит клетка
     * @return
     */
    World world();
}
