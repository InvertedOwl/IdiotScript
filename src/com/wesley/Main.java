package com.wesley;

import com.wesley.block.*;

import javax.swing.*;
import java.awt.*;
import java.util.Random;


public class Main {


    public static void main(String[] args) {
        BlockList.initilizeBlocks();

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
        ConsoleManager.addToConsole("");

        makeRandomBlocks(6);
        makeRandomBlocks(3);

    }

    public static void makeRandomBlocks(int num) {
        BlockBlock blockBlock = new BlockBlock();
        Random random = new Random();

        for (int i = 0; i < num; i++) {
            Block block = BlockList.blocks.get(random.nextInt(BlockList.blocks.size()));
            while (block.getType() == BlockType.Event) {
                block = BlockList.blocks.get(random.nextInt(BlockList.blocks.size()));
            }

            if (i == 0) block = BlockList.blocks.get(1);

            blockBlock.getBlocks().add(new Block(block.getActions(), block.getType(), new Point(50 + (i * 75), 60), block.getName()));
        }


        Component.blockArrayList.add(blockBlock);
    }
}
