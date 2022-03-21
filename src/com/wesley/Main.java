package com.wesley;

import com.wesley.actionListeners.*;
import com.wesley.block.*;

import javax.swing.*;
import java.awt.*;
import java.util.Random;


public class Main {

    public static Component component;
    public static int speed = 250;
    public static BlockDrawWindow blockDrawWindow;
    public static JFrame blockJFrame;

    public static void main(String[] args) {
        BlockList.initilizeBlocks();

        JFrame frame = new JFrame("Block Coding");
        frame.setSize(1011, 1011);
        // Menu bar buttons
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createFileMenu());
        menuBar.add(createEditMenu());
        frame.setJMenuBar(menuBar);


        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocation(screenSize.width/2 - 500, 50 - 20);
        Component render = new Component();
        render.setSize(1011, 1011);
        frame.add(render);

        MouseListener mouseListener = new MouseListener();
        frame.addMouseListener(mouseListener);
        frame.addKeyListener(mouseListener);
        frame.addMouseWheelListener(mouseListener);
        ConsoleManager.addToConsole("", null);
        Main.component = render;

        frame.setVisible(true);
        frame.paint(frame.getGraphics());
        frame.repaint();
        render.paint(frame.getGraphics());
        render.repaint();





    }

    private static JMenu createEditMenu() {
        JMenu fileMenu = new JMenu("Edit");

        JMenuItem speed_up = new JMenuItem("Speed Up");
        speed_up.addActionListener(new editSpeed());
        fileMenu.add(speed_up);

        JMenuItem slow_down = new JMenuItem("Slow down");
        slow_down.addActionListener(new editSlow());
        fileMenu.add(slow_down);

        return fileMenu;
    }

    private static JMenu createFileMenu() {
        JMenu fileMenu = new JMenu("File");

        JMenuItem newItem = new JMenuItem("New Board");
        newItem.addActionListener(new fileNew());
        fileMenu.add(newItem);

        JMenuItem openItem = new JMenuItem("Open Board (.is)");
        openItem.addActionListener(new fileOpen());
        fileMenu.add(openItem);

        JMenuItem saveItem = new JMenuItem("Save Board");
        fileMenu.add(saveItem);
        saveItem.addActionListener(new fileSave());

        return fileMenu;
    }

    public static void makeAllBlocks() {
        BlockBlock blockBlock = new BlockBlock();

        for (int i = 0; i < BlockList.blocks.size() - 1; i++) {
            Block block = BlockList.blocks.get(i);

            if (i == 0) block = BlockList.blocks.get(1);

            blockBlock.getBlocks().add(new Block(block.getActions(), block.getType(), new Point(50 + (i * 75), 290), block.getName(), block.getNumArguments(), block.isManualInput()));
        }


        Component.blockArrayList.add(blockBlock);
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

            blockBlock.getBlocks().add(new Block(block.getActions(), block.getType(), new Point(50 + (i * 75), 60), block.getName(), block.getNumArguments(), block.isManualInput()));
        }


        Component.blockArrayList.add(blockBlock);
    }
}
