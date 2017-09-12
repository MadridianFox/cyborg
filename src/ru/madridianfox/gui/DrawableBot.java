package ru.madridianfox.gui;

import ru.madridianfox.world.Sides;

import java.awt.*;

public class DrawableBot extends DrawableSimple{
    private Sides dir;
    private int energy;
    public DrawableBot(int[] color, Sides dir, int energy) {
        super(color);
        this.dir = dir;
        this.energy = energy;
    }
    @Override
    public void draw(Graphics g, DrawSettings settings, int i, int j) {
        if(settings.energy_mode){
            setColor(settings.heatmapColor(energy));
        }
        super.draw(g, settings, i, j);
        int x = settings.toPixels(i),
            y = settings.toPixels(j);
        g.setColor(Color.black);
        if(dir == Sides.North){
            g.fillRect(x+2, y+1, 2, 2);
            g.fillRect(x+5, y+1, 2, 2);
        }
        if(dir == Sides.South){
            g.fillRect(x+2, y+6, 2, 2);
            g.fillRect(x+5, y+6, 2, 2);
        }
        if(dir == Sides.West){
            g.fillRect(x+1, y+2, 2, 2);
            g.fillRect(x+1, y+5, 2, 2);
        }
        if(dir == Sides.East){
            g.fillRect(x+6, y+2, 2, 2);
            g.fillRect(x+6, y+5, 2, 2);
        }
    }
}
