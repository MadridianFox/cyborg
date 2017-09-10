package ru.madridianfox.gui.pages;

import ru.madridianfox.App;
import ru.madridianfox.gui.components.StepCount;
import ru.madridianfox.gui.components.WholeWorldPainter;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainPage implements PageInterface {
    private JPanel mainPanel;
    private WholeWorldPainter wholeWorldPainter;
    private JButton btn_stop;
    private JButton btn_x1;
    private JButton btn_x10;
    private JButton btn_x100;
    private StepCount stepCount1;

    public MainPage() {
        btn_stop.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                App.instance.world.stopSimulation();
            }
        });
        btn_x1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                App.instance.world.simulationSpeed(1);
            }
        });
        btn_x10.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                App.instance.world.simulationSpeed(10);
            }
        });
        btn_x100.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                App.instance.world.simulationSpeed(100);
            }
        });
    }

    @Override
    public JPanel mainPanel() {
        return mainPanel;
    }

    public WholeWorldPainter worldPainter(){
        return this.wholeWorldPainter;
    }
    public StepCount getStepCount1() {
        return stepCount1;
    }
}
