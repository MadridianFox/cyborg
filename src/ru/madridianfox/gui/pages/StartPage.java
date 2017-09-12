package ru.madridianfox.gui.pages;

import javax.swing.*;
import java.awt.*;

public class StartPage implements PageInterface{
    private JPanel main_panel;

    public StartPage() {
        main_panel = new JPanel();
        main_panel.setLayout(new BorderLayout());
        main_panel.add(new JLabel("Создаёте новый мир  через меню"), BorderLayout.CENTER);
    }

    @Override
    public JPanel mainPanel() {
        return main_panel;
    }
}
