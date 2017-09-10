package ru.madridianfox.gui.components;

import ru.madridianfox.world.World;

import javax.swing.*;

public class StepCount extends JLabel implements SubscriberInterface{
    private int count=0;
    @Override
    public void update(World world) {
        count++;
        setText(String.format("Step: %d", count));
    }
}
