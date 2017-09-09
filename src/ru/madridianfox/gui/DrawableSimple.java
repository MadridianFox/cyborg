package ru.madridianfox.gui;

import java.awt.*;

public class DrawableSimple implements DrawableThing{
    private int[] color;
    public DrawableSimple(int[] color){
        this.color = color;
    }
    @Override
    public void draw(Graphics g, DrawSettings settings, int i, int j) {
        int x = settings.toPixels(i),
                y = settings.toPixels(j);
        g.setColor(new Color(color[0],color[1],color[2]));
        g.fillRect(x, y, settings.getCell_size(), settings.getCell_size());
    }
}
