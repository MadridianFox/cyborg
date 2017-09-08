package ru.madridianfox.world;

import ru.madridianfox.gui.components.SubscriberInterface;
import ru.madridianfox.world.things.Bot;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class World {
    private int width = 100;
    private int height = 50;

    public CellInterface[][] cells;
    private Thread thread;
    private boolean go = false;
    private int steps_by_second = 1000;
    private List<SubscriberInterface> subscribers = new ArrayList<SubscriberInterface>();
    private List<Bot> bots = new CopyOnWriteArrayList<>();

    public World(int width, int height){
        this.width = width;
        this.height = height;
        this.cells = new CellInterface[this.width][this.height];
        for(int i=0; i < this.width; i++){
            for(int j=0; j < this.height; j++){
                this.cells[i][j] = new Cell(this, i, j);
            }
        }
    }

    public void addBot(Bot bot){
        this.bots.add(bot);
    }

    public int width(){
        return this.width;
    }

    public int height(){
        return this.height;
    }

    public void addSubscriber(SubscriberInterface subscriber){
        this.subscribers.add(subscriber);
        subscriber.update(this);
    }

    public void delSubscriber(SubscriberInterface subscriber){
        this.subscribers.remove(subscriber);
    }

    public void notifySubscribers(){
        for(SubscriberInterface subscriber: this.subscribers){
            subscriber.update(this);
        }
    }

    public void start(){
        this.thread = new Thread(() -> {
            try{
                while(true){
                    Thread.sleep(1000 / steps_by_second);
                    if(go){
                        this.updateWorld();
                    }
                }
            }catch(Exception e){
                System.out.println(e.getMessage());
            }

        });
        try{
            this.thread.start();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void updateWorld(){
        for(Bot bot: this.bots){
            bot.update();
        }
        this.notifySubscribers();
    }

    public void stopSimulation(){
        this.go = false;
    }

    public void simulationSpeed(int steps_by_second){
        this.steps_by_second = steps_by_second;
        this.go = true;
    }

    /**
     * Получить клетку по координатам.
     * @param x координата x
     * @param y координата y
     * @return клетка поля
     */
    public CellInterface cellByCoords(int x, int y) {
        int cycled_x = x,
            cycled_y = y;
        if(x >= this.width) cycled_x = 0;
        if(x < 0) cycled_x = this.width-1;
        if(y < 0) cycled_y = this.height-1;
        if(y >= this.height) cycled_y = 0;
        return this.cells[cycled_x][cycled_y];
    }

    public void kill(Bot bot) {
        this.bots.remove(bot);
    }
}
