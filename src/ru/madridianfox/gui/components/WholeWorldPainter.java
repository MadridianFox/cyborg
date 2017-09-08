package ru.madridianfox.gui.components;

import ru.madridianfox.world.CellInterface;
import ru.madridianfox.world.things.Thing;
import ru.madridianfox.world.World;

import javax.swing.*;
import java.awt.*;

public class WholeWorldPainter extends JPanel implements SubscriberInterface{
    private static Color bkg = new Color(43, 43, 43);
    private static Color lines = new Color(85, 85, 85);

    private static int padding = 10;
    private static int cell_size = 9;
    private static int border = 1;

    private World world;

    private int cell_coord(int n){
        return padding + border + (cell_size + border) * n;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        int width = this.getWidth();
        int height = this.getHeight();
        this.clear(g, width, height);
        if(this.world != null){
            int field_width = this.cell_coord(this.world.width()+1) - border;
            int field_height = this.cell_coord(this.world.height()+1) - border;
            this.paintField(g, field_width, field_height);
            this.paintCells(g);
        }else{
            g.setColor(lines);
            g.drawString("No world for drawing provided.", width/2, height/2);
        }
    }

    private void clear(Graphics g, int width, int height){
        g.setColor(bkg);
        g.clearRect(0,0, width, height);
        g.fillRect(0,0, width, height);
    }

    private void paintField( Graphics g, int width, int height){
        int w = width - padding;
        int h = height - padding;
        g.setColor(lines);
        for(int x = padding; x <= w; x+=10){
            g.drawLine(x, padding, x, h);
        }
        for(int y = padding; y <= h; y+=10){
            g.drawLine(padding, y, w, y);
        }
    }

    private void paintCells(Graphics g){
        for(int i=0; i < this.world.cells.length; i++){
            CellInterface[] column = this.world.cells[i];
            for(int j=0; j < column.length; j++){
                Thing thing = column[j].thing();
                int x = cell_coord(i),
                    y = cell_coord(j);
                int[] color = thing.color();
                g.setColor(new Color(color[0],color[1],color[2]));
                g.fillRect(x, y, cell_size, cell_size);
            }
        }
    }

    @Override
    public void update(World world) {
        this.world = world;
        this.repaint();
    }
}
