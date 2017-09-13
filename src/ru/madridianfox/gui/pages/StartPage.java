package ru.madridianfox.gui.pages;

import javax.swing.*;
import java.awt.*;

public class StartPage implements PageInterface{
    private JPanel main_panel;

    public StartPage() {
        main_panel = new JPanel();
        main_panel.setLayout(new FlowLayout());
        main_panel.add(new JLabel("Создайте новый мир через меню"));
    }

    @Override
    public JPanel mainPanel() {
        return main_panel;
    }
}
