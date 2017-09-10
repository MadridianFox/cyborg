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
            "erll","erlll","errr",

            "egnl", "egn", "egnr",
            "egl",         "egr",
            "egll","eglll","egrr",

            "ebnl", "ebn", "ebnr",
            "ebl",         "ebr",
            "ebll","eblll","ebrr",
    };
    public static final String[] output_names = new String[]{
            "mv", "tr", "tl", "et", "bn"
    };

    private CellInterface cell;
    private int energy = 100;
    private Sides dir = Sides.North;
    private Dna dna;
    private int[] color;
    private MindInterface mind;
    private Map<String, Sensor> sensors;

    public Bot(Dna dna, MindInterface mind, int[] color){
        this.dna = dna;
        this.color = color;
        this.mind = mind;
        dir = Sides.random();
        this.sensors = new HashMap<>();
        // red channel
        this.sensors.put("ernl", new EyeSensor(0, new Turn[]{Turn.NoTurn, Turn.Left},this));
        this.sensors.put("ern", new EyeSensor(0, new Turn[]{Turn.NoTurn},this));
        this.sensors.put("ernr", new EyeSensor(0, new Turn[]{Turn.NoTurn, Turn.Right},this));

        this.sensors.put("erl", new EyeSensor(0, new Turn[]{Turn.Left},this));
        this.sensors.put("err", new EyeSensor(0, new Turn[]{Turn.Right},this));
        
        this.sensors.put("erll", new EyeSensor(0, new Turn[]{Turn.Left, Turn.Left},this));
        this.sensors.put("erlll", new EyeSensor(0, new Turn[]{Turn.Left, Turn.Left, Turn.Left},this));
        this.sensors.put("errr", new EyeSensor(0, new Turn[]{Turn.Right, Turn.Right},this));
        // green channel
        this.sensors.put("egnl", new EyeSensor(1, new Turn[]{Turn.NoTurn, Turn.Left},this));
        this.sensors.put("egn", new EyeSensor(1, new Turn[]{Turn.NoTurn},this));
        this.sensors.put("egnr", new EyeSensor(1, new Turn[]{Turn.NoTurn, Turn.Right},this));

        this.sensors.put("egl", new EyeSensor(1, new Turn[]{Turn.Left},this));
        this.sensors.put("egr", new EyeSensor(1, new Turn[]{Turn.Right},this));

        this.sensors.put("egll", new EyeSensor(1, new Turn[]{Turn.Left, Turn.Left},this));
        this.sensors.put("eglll", new EyeSensor(1, new Turn[]{Turn.Left, Turn.Left, Turn.Left},this));
        this.sensors.put("egrr", new EyeSensor(1, new Turn[]{Turn.Right, Turn.Right},this));
        // blue channel
        this.sensors.put("ebnl", new EyeSensor(2, new Turn[]{Turn.NoTurn, Turn.Left},this));
        this.sensors.put("ebn", new EyeSensor(2, new Turn[]{Turn.NoTurn},this));
        this.sensors.put("ebnr", new EyeSensor(2, new Turn[]{Turn.NoTurn, Turn.Right},this));

        this.sensors.put("ebl", new EyeSensor(2, new Turn[]{Turn.Left},this));
        this.sensors.put("ebr", new EyeSensor(2, new Turn[]{Turn.Right},this));

        this.sensors.put("ebll", new EyeSensor(2, new Turn[]{Turn.Left, Turn.Left},this));
        this.sensors.put("eblll", new EyeSensor(2, new Turn[]{Turn.Left, Turn.Left, Turn.Left},this));
        this.sensors.put("ebrr", new EyeSensor(2, new Turn[]{Turn.Right, Turn.Right},this));
    }

    private void doAction(String action){
        switch (action){
            case "mv" : this.actionMove(); break;
            case "tr" : this.actionTurn(Turn.Right); break;
            case "tl" : this.actionTurn(Turn.Left); break;
            case "et" : this.actionEat(); break;
            case "bn" : this.actionBorn(); break;
        }
    }

    private void actionBorn() {
        energy = energy/3;
        Dna new_dna = dna.replicate();
        Bot bot = new_dna.makeOrganizm();
        cell.world().addBot(bot);
        CellInterface free_cell = freeCellAround();
        if(free_cell == null){
            die();
            bot.setCell(cell);
        }else{
            bot.setCell(free_cell);
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
        energy--;
        if(energy==0){
            die();
        }else{
            this.doAction(this.mind.decide(this.sensors));
        }
    }

    public CellInterface relativeCell(Turn[] turns) {
        Sides dir = this.dir;
        CellInterface cell = this.cell;
        for(Turn turn: turns){
            dir = dir.byTurn(turn);
            cell = cell.cellByDirection(dir);
        }
        return cell;
    }

    @Override
    public DrawableThing drawable(){
        return new DrawableBot(color,dir);
    }
}
