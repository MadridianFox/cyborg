package ru.madridianfox.gui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.*;

public class WorldCreateDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JSpinner width_input;
    private JSpinner height_input;
    private boolean is_ok = false;

    public WorldCreateDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        width_input.setValue(190);
        height_input.setValue(94);

        width_input.addChangeListener(e -> {
            int value = (int)width_input.getValue();
            if(value > 190){
                width_input.setValue(190);
            }else if(value < 1){
                width_input.setValue(1);
            }
        });

        height_input.addChangeListener(e -> {
            int value = (int)height_input.getValue();
            if(value > 94){
                height_input.setValue(94);
            }else if(value < 1){
                height_input.setValue(1);
            }
        });

        buttonOK.addActionListener(e -> onOK());

        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        pack();
    }

    private void onOK() {
        is_ok = true;
        dispose();
    }

    private void onCancel() {
        is_ok = false;
        dispose();
    }

    public int width(){
        return (int) width_input.getValue();
    }

    public int height(){
        return (int) height_input.getValue();
    }

    public boolean isOk() {
        return is_ok;
    }
}
