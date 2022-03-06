package com.wesley;


import com.wesley.block.Block;
import com.wesley.block.BlockBlock;

import java.awt.*;
import java.util.ArrayList;

public class Component extends java.awt.Component {

    public static ArrayList<BlockBlock> blockArrayList = new ArrayList<BlockBlock>();

    public void paint(Graphics graphics) {
        graphics.setColor(Color.DARK_GRAY);
        if (MouseListener.selectMode) graphics.setColor(Color.DARK_GRAY.darker());
        graphics.fillRect(0, 0, getWidth(), getHeight());
        graphics.setColor(Color.DARK_GRAY.darker());
        if (MouseListener.selectMode) graphics.setColor(Color.DARK_GRAY.darker().darker());

        graphics.fillRect(0, getHeight() - 200, getWidth(),200);

        if (MouseListener.heldBlockBlock != null){
            for (int i = 0; i < MouseListener.heldBlockBlock.getBlocks().size(); i++) {
                Block block = MouseListener.heldBlockBlock.getBlocks().get(i);
                Point point = getMousePosition();

                if (point != null) {
                    point.setLocation(point.x + (i * 75), point.y);
                    block.setPosition(point);

                }

            }

        }

        paintBlocks(graphics);

        paintConsole(graphics);

        repaint();
    }

    public void paintBlocks(Graphics graphics) {
        for (BlockBlock blockBlock : blockArrayList ) {
            graphics.setColor(Color.DARK_GRAY.darker());
            if (MouseListener.selectMode) graphics.setColor(Color.DARK_GRAY.darker().darker());
            graphics.fillRoundRect((int) blockBlock.getBlocks().get(0).getPosition().getX() - (70/2) - 6, (int) (blockBlock.getBlocks().get(0).getPosition().getY() - (70/2)) - 6, (blockBlock.getBlocks().size() * 75) + 12 - 5, 70 + 12, 16, 16);

            for (Block block : blockBlock.getBlocks()) {

                if (block.isActive()) {
                    graphics.setColor(Color.WHITE);
                    graphics.fillRoundRect((int) block.getPosition().getX() - (70/2) - 5, (int) block.getPosition().getY() - (70/2) - 5, 70 + 10, 70 + 10, 16, 16);

                }



                switch (block.getType()){
                    case Draw -> graphics.setColor(Color.YELLOW);
                    case Event -> graphics.setColor(Color.GREEN);
                    case Trigger -> graphics.setColor(Color.BLUE);
                    case Operation -> graphics.setColor(Color.RED);
                    case Variable -> graphics.setColor(Color.CYAN);
                    default -> graphics.setColor(Color.WHITE);
                }



                graphics.fillRoundRect((int) block.getPosition().getX() - (70/2), (int) block.getPosition().getY() - (70/2), 70, 70, 16, 16);
                graphics.setColor(Color.DARK_GRAY);
                graphics.drawString(block.getName(), (int) block.getPosition().getX() - (70/2), (int) ((int) block.getPosition().getY() - (70/2) + 4.5 + (70/2)));
            }
        }
    }

    public void paintConsole(Graphics graphics) {
        int offset = 50;
        graphics.setFont(new Font("", Font.PLAIN, 24));
        for (int i = ConsoleManager.list.size() - 1; i > 0; i--) {
            if (offset < (24 * 10)) {
                graphics.setColor(Color.WHITE);
                graphics.drawString(ConsoleManager.list.get(i), 0, 1011 - offset);
                offset += 24;
            } else {
                return;
            }
        }
    }
}
