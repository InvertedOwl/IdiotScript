package com.wesley.actionListeners;

import com.wesley.Component;
import com.wesley.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class fileNew implements ActionListener {

    public void actionPerformed(ActionEvent e) {
        int result = JOptionPane.showConfirmDialog(Main.component, "Are you sure you want to do this? It will delete any unsaved data.", "", JOptionPane.YES_NO_OPTION, JOptionPane.DEFAULT_OPTION, null);
        if (result == 0) {
            Component.blockArrayList = new ArrayList<>();
        }
    }
}
