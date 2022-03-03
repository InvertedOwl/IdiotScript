package com.wesley;


import com.wesley.block.Block;
import com.wesley.block.BlockBlock;

import java.awt.*;
import java.util.ArrayList;

public class Component extends java.awt.Component {

    public static ArrayList<BlockBlock> blockArrayList = new ArrayList<BlockBlock>();

    public void paint(Graphics graphics) {
        graphics.setColor(Color.DARK_GRAY);
        graphics.fillRect(0, 0, getWidth(), getHeight());
        graphics.setColor(Color.DARK_GRAY.darker());
        graphics.fillRect(0, getHeight() - 200, getWidth(),200);

        if (MouseListener.heldBlockBlock != null){
            for (int i = 0; i < MouseListener.heldBlockBlock.getBlocks().size(); i++) {
                Block block = MouseListener.heldBlockBlock.getBlocks().get(i);
                Point point = getMousePosition();
                point.setLocation(point.x + (i * 75), point.y);

                block.setPosition(point);
            }

        }

        paintBlocks(graphics);

        repaint();
    }

    public void paintBlocks(Graphics graphics) {
        for (BlockBlock blockBlock : blockArrayList ) {
            for (Block block : blockBlock.getBlocks()) {
                switch (block.getType()){
                    case Draw -> graphics.setColor(Color.YELLOW);
                    case Event -> graphics.setColor(Color.GREEN);
                    case Trigger -> graphics.setColor(Color.BLUE);
                    case Operation -> graphics.setColor(Color.RED);
                    case Variable -> graphics.setColor(Color.CYAN);
                    default -> graphics.setColor(Color.WHITE);
                }

                graphics.fillRoundRect((int) block.getPosition().getX() - (70/2), (int) block.getPosition().getY() - (70/2), 70, 70, 16, 16);
            }
        }

    }
}
