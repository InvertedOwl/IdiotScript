package com.wesley.actionListeners;

import com.wesley.ConsoleManager;
import com.wesley.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class editSlow implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        Main.speed += 150;
        ConsoleManager.addToConsole("New wait time between calls is " + Main.speed);
    }
}
