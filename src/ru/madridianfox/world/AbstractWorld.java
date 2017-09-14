package ru.madridianfox.world;

import ru.madridianfox.gui.components.SubscriberInterface;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractWorld {
    private int width;
    private int height;
    private boolean go = false;
    private boolean stop = false;
    private int cooldown = 1;
    private List<SubscriberInterface> subscribers = new ArrayList<SubscriberInterface>();

    protected AbstractWorld(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int width(){
        return width;
    }

    public int height(){
        return height;
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

    public void startSimulation(){
        Thread thread = new Thread(() -> {
            try{
                while(!stop){
                    Thread.sleep(cooldown);
                    if(go){
                        this.updateWorld();
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }

        });
        thread.start();
    }

    public void stopSimulation(){
        stop = true;
    }

    public void setSimulationSpeed(int steps_per_second){
        cooldown = 1000 / steps_per_second;
        go = true;
        if(stop){
            stop = false;
            startSimulation();
        }
    }

    protected int getRealCoord(int coord, int size){
        if(coord < 0) return size-1;
        if(coord >= size) return 0;
        return coord;
    }

    protected int getRealI(int i){
        return getRealCoord(i, width);
    }

    protected int getRealJ(int j){
        return getRealCoord(j, height);
    }

    protected abstract void updateWorld();
}
