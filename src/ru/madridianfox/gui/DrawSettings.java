package ru.madridianfox.gui;

import java.awt.*;

public class DrawSettings {
    private Color bkg;
    private Color lines;

    private int padding;
    private int cell_size;

    private int border;
    public boolean energy_mode = true;

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

    public int[] heatmapColor(int value){
        float normalized = (float) (1 - 1 / (1+Math.exp((value-100)/25)));
        int aR = 0;   int aG = 0; int aB=255;  // RGB for our 1st color (blue in this case).
        int bR = 255; int bG = 0; int bB=0;    // RGB for our 2nd color (red in this case).

        int red   = (int) ((float)(bR - aR) * normalized + aR);      // Evaluated as -255*value + 255.
        int green = (int) ((float)(bG - aG) * normalized + aG);      // Evaluates as 0.
        int blue  = (int) ((float)(bB - aB) * normalized + aB);      // Evaluates as 255*value + 0.
        return new int[]{red,green,blue};
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
