package com.wesley;


import com.wesley.block.Block;
import com.wesley.block.BlockBlock;
import com.wesley.block.BlockList;
import com.wesley.block.BlockType;

import java.awt.*;
import java.util.ArrayList;

public class Component extends java.awt.Component {

    public static ArrayList<BlockBlock> blockArrayList = new ArrayList<BlockBlock>();

    public void paint(Graphics graphics) {

        Graphics2D g2d = (Graphics2D) graphics;
        g2d.setStroke(new BasicStroke(1.0F, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        
        graphics.setColor(Color.DARK_GRAY);
        if (MouseListener.selectMode) graphics.setColor(Color.DARK_GRAY.darker());
        graphics.fillRect(0, 0, getWidth(), getHeight());
        graphics.setColor(Color.DARK_GRAY.darker());
        if (MouseListener.selectMode) graphics.setColor(Color.DARK_GRAY.darker().darker());


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

        paintBlockMenu(graphics);

        paintConsole(graphics);

        repaint();
    }


    public void paintBlockMenu(Graphics graphics) {
        graphics.setColor(Color.DARK_GRAY.darker());
        graphics.fillRoundRect(getWidth()-150, 0, 150, getHeight(), 0, 0);
        int offsety = -80;
        for (BlockType type : BlockType.values()) {
            switch (type){
                case Draw -> graphics.setColor(Color.YELLOW);
                case Event -> graphics.setColor(Color.GREEN);
                case Trigger -> graphics.setColor(Color.BLUE);
                case Operation -> graphics.setColor(Color.RED);
                case Variable -> graphics.setColor(Color.CYAN);
                default -> graphics.setColor(Color.WHITE);
            }
            for (Block block : BlockList.blocks) {
                if (block.getType().equals(type)){
                    offsety += 95;

                    switch (type) {
                        case Draw -> graphics.setColor(Color.YELLOW);
                        case Event -> graphics.setColor(Color.GREEN);
                        case Trigger -> graphics.setColor(Color.BLUE);
                        case Operation -> graphics.setColor(Color.RED);
                        case Variable -> graphics.setColor(Color.CYAN);
                        default -> graphics.setColor(Color.WHITE);
                    }



                    graphics.fillRoundRect(getWidth()-150 + (150/4), offsety + 15, 70, 70, 16, 16);
                    graphics.setColor(Color.DARK_GRAY);
                    graphics.drawString(block.getName(), getWidth()-150 + (150/4), offsety + 45 + 11);
                }
            }
            offsety += 15;
        }
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
        graphics.setColor(Color.darkGray.darker());
        graphics.fillRect(0, getHeight() - 150, getWidth(),150);
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
