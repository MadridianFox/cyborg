package ru.madridianfox.gui;

import java.awt.*;

public class DrawSettings {
    private Color bkg;
    private Color lines;

    private int padding;
    private int cell_size;

    private int border;

    public DrawSettings(int padding, int cell_size, int border, Color bkg, Color lines){
        this.padding = padding;
        this.cell_size = cell_size;
        this.border = border;
        this.bkg = bkg;
        this.lines = lines;
    }

    public Color getBkg() {
        return bkg;
    }

    public Color getLines() {
        return lines;
    }

    public int getPadding() {
        return padding;
    }

    public int getCell_size() {
        return cell_size;
    }

    public int getBorder() {
        return border;
    }

    /**
     * Преобразовать индекс клетки в расстояние от края хослста в пикселях
     * @param n индекс клетки
     * @return количество пикселей от края холста
     */
    public int toPixels(int n){
        return padding + border + (cell_size + border) * n;
    }

    /**
     * Преобразовать размер поля в клетках в размер поля в пикселях
     * @param size количество клеток
     * @return количество пикселей от края поля
     */
    public int fieldSizePixels(int size){
        return (cell_size + border) * (size+1);
    }
}
