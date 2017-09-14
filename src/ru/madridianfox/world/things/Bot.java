package ru.madridianfox.world.things;

import ru.madridianfox.ai.MindInterface;
import ru.madridianfox.ai.sensors.EyeSensor;
import ru.madridianfox.ai.sensors.Sensor;
import ru.madridianfox.genome.Dna;
import ru.madridianfox.gui.DrawableBot;
import ru.madridianfox.gui.DrawableThing;
import ru.madridianfox.world.CellInterface;
import ru.madridianfox.world.Sides;
import ru.madridianfox.world.Turn;

import java.util.HashMap;
import java.util.Map;

public class Bot extends Thing {
    
    public static final String[] input_names = new String[]{
            "ernl", "ern", "ernr",
            "erl",         "err",
            "erll","erlll","errr"
    };
    public static final String[] output_names = new String[]{
            "mv", "tr", "tl", "et", "bn"
    };

    private CellInterface cell;
    private int energy = 10;
    private Sides dir = Sides.North;
    private Dna dna;
    private int[] color;
    private MindInterface mind;
    private Map<String, Sensor> sensors;
    private int age = 0;

    public Bot(Dna dna, MindInterface mind, int[] color){
        this.dna = dna;
        this.color = color;
        this.mind = mind;
        dir = Sides.random();
        this.sensors = new HashMap<>();
        // red channel
        this.sensors.put("ernl", new EyeSensor(new Turn[]{Turn.NoTurn, Turn.Left},this));
        this.sensors.put("ern", new EyeSensor(new Turn[]{Turn.NoTurn},this));
        this.sensors.put("ernr", new EyeSensor(new Turn[]{Turn.NoTurn, Turn.Right},this));

        this.sensors.put("erl", new EyeSensor(new Turn[]{Turn.Left},this));
        this.sensors.put("err", new EyeSensor(new Turn[]{Turn.Right},this));
        
        this.sensors.put("erll", new EyeSensor(new Turn[]{Turn.Left, Turn.Left},this));
        this.sensors.put("erlll", new EyeSensor(new Turn[]{Turn.Left, Turn.Left, Turn.Left},this));
        this.sensors.put("errr", new EyeSensor(new Turn[]{Turn.Right, Turn.Right},this));
    }

    private void doAction(String action){
        actionSynt();
        if(Math.random()<0.01){
            actionBorn();
        }else{
            switch (action){
                case "mv" : this.actionMove(); break;
                case "tr" : this.actionTurn(Turn.Right); break;
                case "tl" : this.actionTurn(Turn.Left); break;
                case "et" : this.actionEat(); break;
                case "bn" : this.actionBorn(); break;
            }
        }
    }

    private void actionSynt() {
        energy += cell.sunEnergy();
    }

    private void actionBorn() {
        if(energy >= 20) {
            CellInterface free_cell = freeCellAround();
            if (free_cell != null) {
                Dna new_dna = dna.replicate();
                Bot bot = new_dna.makeOrganizm();
                cell.world().addBot(bot);
                bot.setCell(free_cell);
            }
        }
    }

    public CellInterface freeCellAround(){
        CellInterface free_cell = null;
        for(int i=-1; i<2; i++){
            if(free_cell == null){
                for(int j=-1; j<2; j++){
                    if(i==0 && j==0) continue;
                    CellInterface maybe_free_cell = cell.relativeCell(i,j);
                    if(maybe_free_cell.thing() == Thing.noThing){
                        free_cell = maybe_free_cell;
                        break;
                    }
                }
            }
        }
        return free_cell;
    }

    private void actionEat() {
        energy--;
        Thing thing = this.cell.cellByDirection(this.dir).thing();
        this.energy += thing.eat();
    }

    private void actionTurn(Turn turn) {
        switch (turn){
            case Left: this.dir = this.dir.left(); break;
            case Right: this.dir = this.dir.right(); break;
        }
    }

    private void actionMove() {
        energy--;
        CellInterface new_cell = this.cell.cellByDirection(this.dir);
        if(new_cell.thing() == Thing.noThing){
            this.cell.setThing(Thing.noThing);
            this.setCell(new_cell);
        }
    }

    @Override
    public int[] color() {
        return this.color;
    }

    @Override
    public int eat() {
        die();
        return this.energy;
    }

    private void die(){
        this.cell.world().kill(this);
        this.cell.setThing(Thing.noThing);
    }

    @Override
    public void setCell(CellInterface cell){
        super.setCell(cell);
        this.cell = cell;
    }


    public void update(){
        age++;
        if(energy == 0 || age > 50){
            die();
        }else{
            this.doAction(this.mind.decide(this.sensors));
        }
    }

    public CellInterface relativeCell(Turn[] turns) {
        Sides dir = this.dir;
        CellInterface cell = this.cell;
        try{
            for(Turn turn: turns){
                dir = dir.byTurn(turn);
                cell = cell.cellByDirection(dir);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return cell;
    }

    @Override
    public DrawableThing drawable(){
        return new DrawableBot(color,dir, energy);
    }
}
