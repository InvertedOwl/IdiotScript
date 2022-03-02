package com.wesley;

import com.wesley.block.Block;
import com.wesley.block.BlockAction;
import com.wesley.block.BlockBlock;
import com.wesley.block.BlockType;

import javax.swing.*;
import java.awt.*;


public class Main {


    public static void main(String[] args) {
        JFrame frame = new JFrame("Block Coding");
        frame.setSize(1011, 1011);
        frame.setVisible(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocation(screenSize.width/2 - 500, 50 - 20);
        Component render = new Component();
        render.setSize(1011, 1011);
        frame.add(render);
        render.paint(frame.getGraphics());
        render.repaint();
        frame.addMouseListener(new MouseListener());



        Block block = new Block(new BlockAction() {
            @Override
            public void trigger(Object... params) {

            }
        }, BlockType.Event, new Point(50, 50));

        Component.blockArrayList.add(new BlockBlock(block));

        Block block2 = new Block(new BlockAction() {
            @Override
            public void trigger(Object... params) {

            }
        }, BlockType.Event, new Point(50, 50));

        Component.blockArrayList.add(new BlockBlock(block2));

        Block block3 = new Block(new BlockAction() {
            @Override
            public void trigger(Object... params) {

            }
        }, BlockType.Operation, new Point(50, 50));

        Component.blockArrayList.add(new BlockBlock(block3));

        Block block4 = new Block(new BlockAction() {
            @Override
            public void trigger(Object... params) {

            }
        }, BlockType.Variable, new Point(50, 50));

        Component.blockArrayList.add(new BlockBlock(block4));
    }
}
