package ru.madridianfox.gui.components;

import ru.madridianfox.gui.DrawSettings;
import ru.madridianfox.gui.DrawableSimple;
import ru.madridianfox.gui.DrawableThing;
import ru.madridianfox.world.CellInterface;
import ru.madridianfox.world.things.Thing;
import ru.madridianfox.world.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class WholeWorldPainter extends JPanel{

    private DrawSettings settings = new DrawSettings(10,9,1, new Color(43, 43, 43), new Color(85, 85, 85));
    private World world;
    private int selected_cell_x;
    private int selected_cell_y;
    private boolean cell_under_mouse = false;

    public WholeWorldPainter(World world) {
        this.world = world;
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                int x = settings.toIndex(e.getX()),
                    y = settings.toIndex(e.getY());
                if(x > 0 && x < WholeWorldPainter.this.world.width() && y > 0 && y < WholeWorldPainter.this.world.height()){
                    selected_cell_x = x;
                    selected_cell_y = y;
                    cell_under_mouse = true;
                }else{
                    cell_under_mouse = false;
                }
            }
        });
        Timer timer = new Timer(1000 / 20, e -> repaint());
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        this.clear(g, width, height);
        if(this.world != null){
            int field_width = settings.fieldSizePixels(world.width());
            int field_height = settings.fieldSizePixels(world.height());
            paintField(g, field_width, field_height);
            paintCells(g);
        }else{
            g.setColor(settings.getLines());
            g.drawString("No world for drawing provided.", width/2, height/2);
        }
    }

    private void clear(Graphics g, int width, int height){
        g.setColor(settings.getBkg());
        g.clearRect(0,0, width, height);
        g.fillRect(0,0, width, height);
    }

    private void paintField(Graphics g, int width, int height){
        g.setColor(settings.getLines());
        for(int x = settings.getPadding(); x <= width; x+=10){
            g.drawLine(x, settings.getPadding(), x, height);
        }
        for(int y = settings.getPadding(); y <= height; y+=10){
            g.drawLine(settings.getPadding(), y, width, y);
        }
        if(cell_under_mouse){
            g.setColor(Color.green);
            g.drawRect(settings.toPixels(selected_cell_x)-settings.getBorder(),settings.toPixels(selected_cell_y) - settings.getBorder(), 10,10);
        }
    }

    private void paintCells(Graphics g){
        for(int i=0; i < this.world.cells.length; i++){
            CellInterface[] column = this.world.cells[i];
            for(int j=0; j < column.length; j++){
                Thing thing = column[j].thing();
                DrawableThing drawableThing;
                if(thing != Thing.noThing){
                    drawableThing = thing.drawable();
                }else{
                    drawableThing = new DrawableSimple(column[j].color());
                }
                drawableThing.draw(g, settings, i, j);
            }
        }
    }

    public void setToggleEnergy() {
        settings.energy_mode = !settings.energy_mode;
    }
}
