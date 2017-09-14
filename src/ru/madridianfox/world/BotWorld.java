package ru.madridianfox.world;

import ru.madridianfox.gui.components.SubscriberInterface;
import ru.madridianfox.world.things.Bot;
import ru.madridianfox.world.things.Food;
import ru.madridianfox.world.things.Thing;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class BotWorld extends AbstractWorld{
    public CellInterface[][] cells;

    private List<Bot> bots = new CopyOnWriteArrayList<>();

    public BotWorld(int width, int height){
        super(width, height);
        cells = new CellInterface[width][height];
        for(int i=0; i < width; i++){
            for(int j=0; j < height; j++){
                cells[i][j] = new Cell(this, i, j);
            }
        }
    }

    public void addBot(Bot bot){
        this.bots.add(bot);
    }

    @Override
    public void updateWorld(){
        Random random = new Random();
        int food_count=0;
        for(CellInterface[] cell_column:cells){
            for(CellInterface cell: cell_column){
                if(cell.thing() instanceof Food){
                    food_count++;
                }
            }
        }
        if(food_count<90){
            for(int i=0; i<10; i++){
                int x = random.nextInt(width()),
                        y = random.nextInt(height());
                CellInterface cell = cellByCoords(x,y);
                if(cell.thing() == Thing.noThing){
                    cell.setThing(new Food(cell));
                }
            }
        }
        for(Bot bot: this.bots){
            bot.update();
        }
        this.notifySubscribers();
    }

    /**
     * Получить клетку по координатам.
     * @param x координата x
     * @param y координата y
     * @return клетка поля
     */
    public CellInterface cellByCoords(int x, int y) {
        return cells[getRealI(x)][getRealJ(y)];
    }

    public void kill(Bot bot) {
        this.bots.remove(bot);
    }
}
