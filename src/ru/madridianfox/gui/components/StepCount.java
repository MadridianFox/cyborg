package ru.madridianfox.gui.components;

import ru.madridianfox.world.AbstractWorld;
import ru.madridianfox.world.BotWorld;

import javax.swing.*;

public class StepCount extends JLabel implements SubscriberInterface{
    private int count=0;
    @Override
    public void update(AbstractWorld world) {
        count++;
        setText(String.format("Step: %d", count));
    }
}
