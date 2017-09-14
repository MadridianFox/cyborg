package ru.madridianfox.gui.components;

import ru.madridianfox.world.AbstractWorld;
import ru.madridianfox.world.ConwayWorld;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ConwayWorldPainter extends WorldPainter {
    public ConwayWorldPainter(AbstractWorld world) {
        super(world);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ConwayWorld c_world = (ConwayWorld) world;
                if(cell_under_mouse){
                    if(c_world.cells[selected_cell_x][selected_cell_y][0] == 1){
                        c_world.cells[selected_cell_x][selected_cell_y][0] = 0;
                    }else{
                        c_world.cells[selected_cell_x][selected_cell_y][0] = 1;
                    }
                }
            }
        });
    }
    @Override
    protected void paintCells(Graphics g){
        ConwayWorld world = (ConwayWorld) this.world;
        g.setColor(Color.WHITE);
        for(int i=0; i < world.cells.length; i++){
            for(int j=0; j < world.cells[i].length; j++){
                int x = settings.toPixels(i),
                        y = settings.toPixels(j);
                if(world.cells[i][j][0] == 1){
                    g.fillRect(x,y, settings.getCell_size(), settings.getCell_size());
                }
            }
        }
    }
}
