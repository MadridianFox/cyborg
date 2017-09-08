package ru.madridianfox;

import ru.madridianfox.gui.pages.MainPage;
import ru.madridianfox.world.Sides;
import ru.madridianfox.world.World;
import ru.madridianfox.world.things.Bot;
import ru.madridianfox.world.things.Rock;

import javax.swing.*;
import java.util.Random;

public class App {
    private JFrame window;
    private MainPage page;
    public static App instance;
    public World world;

    public App(){
        this.window = this.makeWindow();
        this.world = makeWorld();
        this.page = new MainPage();
        this.window.setContentPane(this.page.mainPanel());
        this.window.pack();

        this.world.addSubscriber(this.page.worldPainter());
        this.world.start();
    }

    private World makeWorld(){
        World world = new World(100,50);
        Random random_int = new Random();
        for(int i=0; i<0; i++){
            int x = random_int.nextInt(100),
                y = random_int.nextInt(50);
            world.cellByCoords(x, y).setThing(new Rock());
        }
        for(int i=0; i<100; i++){
            int x = random_int.nextInt(100),
                    y = random_int.nextInt(50);
            world.addBot(new Bot(world.cellByCoords(x, y), Sides.North, 1000));
        }
        return world;
    }

    private JFrame makeWindow(){
        JFrame frame = new JFrame("CybOrg");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        return frame;
    }

    public static void main(String[] args) {
        App.instance = new App();
    }
}
