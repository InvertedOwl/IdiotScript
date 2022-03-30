package com.wesley;


import com.wesley.block.Block;
import com.wesley.block.BlockBlock;
import com.wesley.block.BlockList;
import com.wesley.block.BlockType;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Component extends java.awt.Component {

    public static ArrayList<BlockBlock> blockArrayList = new ArrayList<BlockBlock>();
    public static ArrayList<Block> menuBlocks = new ArrayList<>();
    public static Graphics2D G2D;
    public static Point mainPoint = new Point(-100, -100);
    public static ArrayList<Block> argumentHighlight = new ArrayList<>();
    public static final Color comment = Color.lightGray;
    private Point offset = new Point(0, 0);

    public Component () {


        for (BlockType type : BlockType.values()) {
            for (Block block : BlockList.blocks) {
                if (block.getType().equals(type)){

                    menuBlocks.add(block);
                }
            }
        }
    }

    public void paint(Graphics graphics) {

        Graphics2D g2d = (Graphics2D) graphics;
        Component.G2D = g2d;
        g2d.setStroke(new BasicStroke(1.0F, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.setFont(new Font(Main.font.getName(), Font.BOLD, 15));

        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);


        g2d.setColor(Color.DARK_GRAY);
        if (MouseListener.selectMode) g2d.setColor(Color.DARK_GRAY.darker());
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setColor(Color.DARK_GRAY.darker());
        if (MouseListener.selectMode) g2d.setColor(Color.DARK_GRAY.darker().darker());

        try {
            argumentHighlight = new ArrayList<>();
            if (getMousePosition() != null) {
                Block boundBlock = MouseListener.boundsWith(new Point((int) ((getMousePosition().x - offset.x) * MouseListener.scale), (int) ((getMousePosition().y - offset.y) * MouseListener.scale)));
                if (boundBlock != null) {
                    for (Object o : boundBlock.getArguments()) {
                        if (o instanceof Block) {
                            argumentHighlight.add((Block) o);
                        }
                    }
                }
            }
        } catch (NullPointerException e) {
            System.out.printf("Was null");
        }



        if (MouseListener.heldBlockBlock != null){
            for (int i = 0; i < MouseListener.heldBlockBlock.getBlocks().size(); i++) {
                Block block = MouseListener.heldBlockBlock.getBlocks().get(i);
                Point point = getMousePosition();

                if (point != null) {
                    int width = 75;
//                    if (i > 0) {
//                        Block blockBefore = MouseListener.heldBlockBlock.getBlocks().get(i - 1);
//                        if (blockBefore.getType().equals(BlockType.Comment)) {
//                            FontMetrics metrics = g2d.getFontMetrics();
//                            width = metrics.stringWidth(blockBefore.getArguments().toString().replace("[", "").replace("]",""));
//                            width += 5;
//                            if (width < 75) width = 75;
//                        }
//                    }

                    if (i > 0) {
                        Block blockBefore = MouseListener.heldBlockBlock.getBlocks().get(i - 1);
                        point.setLocation(((point.x + ((blockBefore.getPosition().x - point.x + width)))), point.y / MouseListener.scale - offset.y);
                        block.setPosition(point);
                    } else {
                        point.setLocation(((point.x + (i * width * MouseListener.scale)) / MouseListener.scale) - offset.x, point.y / MouseListener.scale - offset.y);
                        block.setPosition(point);
                    }

                }

            }

        }

        if (MouseListener.dragBoard && getMousePosition() != null) {
            offset.x = (int) ((getMousePosition().x + MouseListener.xOffset - MouseListener.mouseXStrt + 8));
            offset.y = (int) ((getMousePosition().y + MouseListener.yOffset - MouseListener.mouseYStrt + 54)
            );
        } else if (getMousePosition() != null){
            MouseListener.xOffset = (int) (offset.x );
            MouseListener.yOffset = (int) (offset.y );
        }




        paintChecker(g2d);
        for (BlockBlock blockBlock : blockArrayList) {
            for (Block block : blockBlock.getBlocks()) {
                if (block.getType().equals(BlockType.Logic) && block.getNumArguments() > 0) {
                    for (Object blockArgs : block.getArguments()){
                        if (blockArgs instanceof Block) {
                            Block blockArg = (Block) blockArgs;
                            if (blockArg.getType().equals(BlockType.Trigger)) {
                                Line2D line2D = new Line2D.Double((int) ((block.getPosition().x + offset.x)), (int) ((block.getPosition().y + offset.y)),
                                        (int) ((blockArg.getPosition().x + offset.x)), (int) ((blockArg.getPosition().y + offset.y)));

                                g2d.setStroke(new BasicStroke(12f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND));
                                g2d.setColor(new Color(255, 0, 0, 125));
                                g2d.draw(line2D);
                            }
                        }
                    }
                }
            }
        }

        paintBlocks(g2d);

        AffineTransform at = new AffineTransform();
        at.scale(1, 1);
        g2d.setTransform(at);
        g2d.setColor(new Color(255, 0, 0, 125));
        g2d.fillRect(0, 0, 85, 85);



        paintBlockMenu(g2d);
        paintConsole(g2d);


        repaint();
    }


    public void paintBlockMenu(Graphics2D g2d) {
        g2d.setColor(Color.DARK_GRAY.darker());
        g2d.fillRoundRect(getWidth()-150, 0, 150, getHeight(), 0, 0);
        int offsety = -80;
        for (BlockType type : BlockType.values()) {
            switch (type){
                case Draw -> g2d.setColor(Color.YELLOW);
                case Event -> g2d.setColor(Color.GREEN);
                case Trigger -> g2d.setColor(new Color(3, 190, 252));
                case Operation -> g2d.setColor(Color.RED);
                case Variable -> g2d.setColor(Color.CYAN);
                case Comment -> g2d.setColor(comment);
                case Logic -> g2d.setColor(new Color(255,105,180));
                default -> g2d.setColor(Color.WHITE);
            }
            for (Block block : BlockList.blocks) {
                if (block.getType().equals(type)){
                    offsety += 95;

                    switch (type) {
                        case Draw -> g2d.setColor(Color.YELLOW);
                        case Event -> g2d.setColor(Color.GREEN);
                        case Trigger -> g2d.setColor(new Color(3, 190, 252));
                        case Operation -> g2d.setColor(Color.RED);
                        case Variable -> g2d.setColor(Color.CYAN);
                        case Logic -> g2d.setColor(new Color(255,105,180));
                        case Comment -> g2d.setColor(comment);
                        default -> g2d.setColor(Color.WHITE);
                    }


                    block.setPosition(new Point(getWidth()-150 + (150/4), offsety + 45 + 11));
                    g2d.fillRoundRect(getWidth()-150 + (150/4), block.getPosition().y - 45 + MouseListener.scroll, 70, 70, 16, 16);
                    g2d.setColor(Color.DARK_GRAY);
                    g2d.drawString(block.getName(), getWidth()-150 + (150/4), block.getPosition().y - 6 + MouseListener.scroll);
                }
            }
            offsety += 15;
        }


    }

    public void paintChecker(Graphics2D g2d) {
        AffineTransform at = new AffineTransform();
        at.scale(MouseListener.scale, MouseListener.scale);
        g2d.setTransform(at);

        g2d.setColor(Color.GRAY.darker());
        int boardSize = getWidth() / 5;


        for ( int row = 0; row < boardSize; row++ )
        {
            for ( int col = row % 2; col < boardSize; col += 2 )
            {
                g2d.fillRect( row * 170 + Main.component.offset.x, col * 170 + Main.component.offset.y, 170, 170);
            }
        }
    }

    public void paintBlocks(Graphics2D g2d) {


        for (int i = 0; i < blockArrayList.size(); i++) {


            BlockBlock blockBlock = blockArrayList.get(i);
            g2d.setColor(Color.DARK_GRAY.darker());
            if (MouseListener.selectMode) g2d.setColor(Color.DARK_GRAY.darker().darker());
            if (blockBlock.getBlocks().size() > 0)
            g2d.fillRoundRect((int) blockBlock.getBlocks().get(0).getPosition().getX() - (70/2) - 6 + offset.x, (int) (blockBlock.getBlocks().get(0).getPosition().getY() - (70/2)) - 6 + offset.y, (blockBlock.getBlocks().size() * 75) + 12 - 5, 70 + 12, 16, 16);

            for (Block block : blockBlock.getBlocks()) {
                String comment = block.getArguments().toString().replace("[", "").replace("]","");


                if (block.isActive() || argumentHighlight.contains(block)) {
                    g2d.setColor(Color.WHITE);
                    g2d.fillRoundRect((int) block.getPosition().getX() - (70/2) - 5 + offset.x, (int) block.getPosition().getY() - (70/2) - 5 + offset.y, 70 + 10, 70 + 10, 16, 16);
                }
                if (block.getType().equals(BlockType.Comment)) {
                    int width = 70;
                    if (block.getType().equals(BlockType.Comment)) {
                        FontMetrics metrics = g2d.getFontMetrics();
                        width = metrics.stringWidth(comment);
                        if (width < 70) width = 70;
                    }
                    g2d.setColor(Color.DARK_GRAY.darker());
                    g2d.fillRoundRect((int) block.getPosition().getX() - (70/2) - 5 + offset.x, (int) block.getPosition().getY() - (70/2) - 5 + offset.y, width + 10, 70 + 10, 16, 16);
                }





                switch (block.getType()){
                    case Draw -> g2d.setColor(Color.YELLOW);
                    case Event -> g2d.setColor(Color.GREEN);
                    case Trigger -> g2d.setColor(new Color(3, 190, 252));
                    case Operation -> g2d.setColor(Color.RED);
                    case Variable -> g2d.setColor(Color.CYAN);
                    case Logic -> g2d.setColor(new Color(255,105,180));
                    case Comment -> g2d.setColor(Component.comment);

                    default -> g2d.setColor(Color.WHITE);
                }




                if (!block.getType().equals(BlockType.Comment)) {
                    g2d.fillRoundRect((int) (block.getPosition().getX() - (70 / 2)) + offset.x, (int) (block.getPosition().getY() - (70 / 2)) + offset.y, 70, 70, 16, 16);
                } else {
                    FontMetrics metrics = g2d.getFontMetrics();
                    int width = metrics.stringWidth(comment);
                    if (width < 70) width = 70;
                    g2d.fillRoundRect((int) (block.getPosition().getX() - (70 / 2)) + offset.x, (int) (block.getPosition().getY() - (70 / 2)) + offset.y, width, 70, 16, 16);
                }
                g2d.setColor(Color.DARK_GRAY);

                if (!block.getType().equals(BlockType.Comment)) {
                    g2d.drawString(block.getName(), (int) (block.getPosition().getX() - (70 / 2)) + offset.x, (int) ((int) (block.getPosition().getY() - (70 / 2) + 4.5 + (70 / 2))) + offset.y);
                } else {
                    g2d.drawString(comment, (int) (block.getPosition().getX() - (70 / 2)) + offset.x, (int) ((int) (block.getPosition().getY() - (70 / 2) + 4.5 + (70 / 2))) + offset.y);
                }
            }
        }

    }

    public void paintConsole(Graphics2D g2d) {
        g2d.setColor(Color.darkGray.darker());
        g2d.fillRect(0, getHeight() - 150, getWidth(),150);
        int offset = 50;
        g2d.setFont(new Font("", Font.PLAIN, 24));
        for (int i = ConsoleManager.list.size() - 1; i > 0; i--) {
            if (offset < (24 * 10)) {
                g2d.setColor(Color.WHITE);
                g2d.drawString(ConsoleManager.list.get(i), 0, 1011 - offset);
                offset += 24;
            } else {
                return;
            }
        }
    }
}
