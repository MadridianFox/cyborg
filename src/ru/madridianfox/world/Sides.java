package ru.madridianfox.world;

import ru.madridianfox.ai.ann.WebInterface;

import java.util.Random;

public abstract class Sides {
    public static final Sides North = new NorthSide();
    public static final Sides West = new WestSide();
    public static final Sides South = new SouthSide();
    public static final Sides East = new EastSide();

    /**
     * Получить направление повернув против часовой стрелки
     * @return новое направление
     */
    public abstract Sides left();

    /**
     * Получить напоравление повернув по часовой стрелке
     * @return новое направление
     */
    public abstract Sides right();

    /**
     * Получить координаты в направлении которых мы смотрим
     * @param x координата x
     * @param y координата y
     * @return координаты
     */
    public abstract int[] vectorFrom(int x, int y);

    public Sides byTurn(Turn turn){
        switch (turn){
            case Left: return this.left();
            case Right: return this.right();
        }
        return this;
    }

    public static Sides random() {
        int rand = new Random().nextInt(4);
        switch (rand){
            case 0: return North;
            case 1: return West;
            case 2: return South;
            case 3: return East;
        }
        return North;
    }

    public static class NorthSide extends Sides {

        @Override
        public Sides left() {
            return West;
        }

        @Override
        public Sides right() {
            return East;
        }

        @Override
        public int[] vectorFrom(int x, int y) {
            return new int[]{x, y-1};
        }
    }
    public static class WestSide extends Sides {

        @Override
        public Sides left() {
            return South;
        }

        @Override
        public Sides right() {
            return North;
        }

        @Override
        public int[] vectorFrom(int x, int y) {
            return new int[]{x-1, y};
        }
    }
    public static class SouthSide extends Sides {

        @Override
        public Sides left() {
            return East;
        }

        @Override
        public Sides right() {
            return West;
        }

        @Override
        public int[] vectorFrom(int x, int y) {
            return new int[]{x, y+1};
        }
    }
    public static class EastSide extends Sides {

        @Override
        public Sides left() {
            return North;
        }

        @Override
        public Sides right() {
            return South;
        }

        @Override
        public int[] vectorFrom(int x, int y) {
            return new int[]{x+1, y};
        }
    }
}
