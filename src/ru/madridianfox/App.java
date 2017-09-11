package ru.madridianfox;

import ru.madridianfox.genome.Dna;
import ru.madridianfox.genome.Gene;
import ru.madridianfox.genome.NeuronGene;
import ru.madridianfox.gui.pages.MainPage;
import ru.madridianfox.gui.pages.StartPage;
import ru.madridianfox.world.Sides;
import ru.madridianfox.world.World;
import ru.madridianfox.world.things.Bot;
import ru.madridianfox.world.things.Rock;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class App {
    private JFrame window;
    private MainPage page;
    private JMenuBar menu;
    public static App instance;
    public World world;

    public App(){
        window = makeWindow();
        menu = new JMenuBar();
        StartPage page = new StartPage();
        window.setContentPane(page.mainPanel());
        makeMenu(menu);
        window.setJMenuBar(menu);
        window.pack();
    }

    private void makeMenu(JMenuBar menu) {
        JMenu file_menu = new JMenu("File");
        JMenuItem item_new = new JMenuItem("New");
        file_menu.add(item_new);
        item_new.addActionListener(e -> {
            if(world != null){
                world.stop();
            }
            world = makeWorld();
            page = new MainPage();
            window.setContentPane(page.mainPanel());
            world.addSubscriber(page.worldPainter());
            world.addSubscriber(page.getStepCount1());
            world.start();
            window.pack();
        });

        JMenuItem item_exit = new JMenuItem("Exit");
        file_menu.add(item_exit);
        item_exit.addActionListener(e -> System.exit(0));

        menu.add(file_menu);
    }

    private World makeWorld(){
        World world = new World(100,50);
        Random random = new Random();
        for(int bot_number=0; bot_number<100; bot_number++){
            List<Gene> genes = new ArrayList<>();
            List<String> middle_keys = new ArrayList<>();
            for(int middle_neuron_number=0; middle_neuron_number<8; middle_neuron_number++){
                Map<String,Float> weights = new HashMap<>();
                for(String input_key: Bot.input_names){
                    weights.put(input_key, (float) Math.random() * 2.f - 1.f);
                }
                String middle_key = "mdl-"+middle_neuron_number;
                genes.add(new NeuronGene(middle_key,weights));
                middle_keys.add(middle_key);
            }
            for(String output_key: Bot.output_names){
                Map<String,Float> weights = new HashMap<>();
                for(String middle_key: middle_keys){
                    weights.put(middle_key, (float) Math.random() * 2.f - 1.f);
                }
                genes.add(new NeuronGene(output_key,weights));
            }
            Dna dna = new Dna(genes);
            Bot bot = dna.makeOrganizm();
            world.addBot(bot);
            bot.setCell(world.cellByCoords(random.nextInt(100), random.nextInt(50)));
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
