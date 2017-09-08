package ru.madridianfox.world.things;

import ru.madridianfox.ai.Mind;
import ru.madridianfox.ai.sensors.EyeSensor;
import ru.madridianfox.ai.sensors.Sensor;
import ru.madridianfox.world.CellInterface;
import ru.madridianfox.world.Sides;
import ru.madridianfox.world.Turn;

import java.util.HashMap;
import java.util.Map;

public class Bot extends Thing {

    private CellInterface cell;
    private int energy;
    private Sides dir;
    private int[] color;
    private Mind mind;
    private Map<String, Sensor> sensors;

    public Bot(CellInterface cell, Sides dir, int energy){
        this.setCell(cell);
        this.dir = dir;
        this.energy = energy;
        this.color = new int[]{55, 177, 88};
        this.mind = new Mind();
        this.sensors = new HashMap<String, Sensor>();
        this.sensors.put("eye_r", new EyeSensor(0, new Turn[]{Turn.NoTurn},this));
        this.sensors.put("eye_g", new EyeSensor(1, new Turn[]{Turn.NoTurn},this));
        this.sensors.put("eye_b", new EyeSensor(2, new Turn[]{Turn.NoTurn},this));
    }

    private void doAction(String action){
        switch (action){
            case "mv" : this.actionMove(); break;
            case "tr" : this.actionTurn(Turn.Right); break;
            case "tl" : this.actionTurn(Turn.Left); break;
            case "et" : this.actionEat(); break;
        }
    }

    private void actionEat() {
        Thing thing = this.cell.cellByDirection(this.dir).thing();
        this.energy += thing.eat();
        System.out.println(String.format("I am killer! My energy is %d", this.energy));
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
        this.cell.world().kill(this);
        this.cell.setThing(new Rock());
        return this.energy;
    }

    @Override
    public void setCell(CellInterface cell){
        super.setCell(cell);
        this.cell = cell;
    }


    public void update(){
        this.doAction(this.mind.decide(this.sensors));
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
}
