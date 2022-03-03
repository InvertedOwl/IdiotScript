package com.wesley;

import com.wesley.block.Block;
import com.wesley.block.BlockAction;
import com.wesley.block.BlockBlock;
import com.wesley.block.BlockType;

import javax.swing.*;
import java.awt.*;
import java.util.Random;


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
        MouseListener mouseListener = new MouseListener();
        frame.addMouseListener(mouseListener);
        frame.addKeyListener(mouseListener);

        makeRandomBlocks(6);
        makeRandomBlocks(3);

    }

    public static void makeRandomBlocks(int num) {
        BlockBlock blockBlock = new BlockBlock();


        for (int i = 0; i < num; i++) {
            Random random = new Random();
            BlockType type = BlockType.values()[random.nextInt(BlockType.values().length)];
            BlockAction action = new BlockAction() {
                @Override
                public void trigger(Object... params) {

                }
            };


            if (i == 0) type = BlockType.Event;
            if (i == 1) action = new BlockAction() {
                @Override
                public void trigger(Object... params) {
                    System.out.println("I was triggered!");
                }
            };

            blockBlock.getBlocks().add(new Block(action, type, new Point(50 + (i * 75), 60)));
        }


        Component.blockArrayList.add(blockBlock);
    }
}
