package ru.madridianfox.gui.pages;

import ru.madridianfox.App;
import ru.madridianfox.gui.components.StepCount;
import ru.madridianfox.gui.components.WholeWorldPainter;
import ru.madridianfox.world.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainPage implements PageInterface {
    private JPanel mainPanel;
    private WholeWorldPainter wholeWorldPainter;
    private JButton btn_stop;
    private JButton btn_x1;
    private JButton btn_x10;
    private JButton btn_x100;
    private JButton btn_toggle_energy;
    private StepCount stepCount1;

    public MainPage(World world) {
        btn_stop = new JButton("Stop");
        btn_x1 = new JButton("x1");
        btn_x10 = new JButton("x10");
        btn_x100 = new JButton("x100");
        btn_toggle_energy = new JButton("Energy");
        stepCount1 = new StepCount();

        JPanel frame_for_buttons = new JPanel();
        FlowLayout flow_layout = new FlowLayout();
        flow_layout.setAlignment(FlowLayout.LEFT);
        frame_for_buttons.setLayout(flow_layout);
        frame_for_buttons.add(btn_stop);
        frame_for_buttons.add(btn_x1);
        frame_for_buttons.add(btn_x10);
        frame_for_buttons.add(btn_x100);
        frame_for_buttons.add(btn_toggle_energy);
        frame_for_buttons.add(stepCount1);

        wholeWorldPainter = new WholeWorldPainter(world);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(wholeWorldPainter,BorderLayout.CENTER);
        mainPanel.add(frame_for_buttons,BorderLayout.SOUTH);
        //mainPanel.setSize(1000,500);

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
        btn_toggle_energy.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                wholeWorldPainter.setToggleEnergy();
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
