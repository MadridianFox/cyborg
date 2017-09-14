package ru.madridianfox;

import ru.madridianfox.gui.WorldCreateDialog;
import ru.madridianfox.gui.components.ConwayWorldPainter;
import ru.madridianfox.gui.pages.MainPage;
import ru.madridianfox.gui.pages.StartPage;
import ru.madridianfox.world.AbstractWorld;
import ru.madridianfox.world.ConwayWorld;

import javax.swing.*;
import java.awt.*;

public class App {
    private JFrame window;
    private MainPage page;
    private JMenuBar menu;
    public static App instance;
    public AbstractWorld world;

    public App(){
        window = makeWindow();
        menu = new JMenuBar();
        StartPage page = new StartPage();
        window.setContentPane(page.mainPanel());
        makeMenu(menu);
        window.setJMenuBar(menu);
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        window.setMaximizedBounds(env.getMaximumWindowBounds());
        window.setExtendedState(window.getExtendedState() |JFrame.MAXIMIZED_BOTH);
    }

    private void makeMenu(JMenuBar menu) {
        JMenu file_menu = new JMenu("File");
        JMenuItem item_new = new JMenuItem("New");
        file_menu.add(item_new);
        item_new.addActionListener(e -> {
            if(world != null){
                world.stopSimulation();
            }
            WorldCreateDialog dialog = new WorldCreateDialog();
            dialog.setVisible(true);
            if(dialog.isOk()){
                world = new ConwayWorld(dialog.width(),dialog.height());
                page = new MainPage(world, new ConwayWorldPainter(world));
                window.setContentPane(page.mainPanel());
                world.addSubscriber(page.getStepCount1());
                world.startSimulation();
            }
        });

        JMenuItem item_exit = new JMenuItem("Exit");
        file_menu.add(item_exit);
        item_exit.addActionListener(e -> System.exit(0));

        menu.add(file_menu);
    }


    private JFrame makeWindow(){
        JFrame frame = new JFrame("CybOrg");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        return frame;
    }

    public static void main(String[] args) {
        App.instance = new App();
    }
}
