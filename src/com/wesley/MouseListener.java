package com.wesley;

import com.wesley.block.Block;
import com.wesley.block.BlockAction;
import com.wesley.block.BlockBlock;
import com.wesley.block.BlockType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class MouseListener implements java.awt.event.MouseListener, KeyListener, MouseWheelListener {
    public static BlockBlock heldBlockBlock;
    public static boolean selectMode;
    public static Block selectBlock;
    public static int scroll;
    private Point mouse;
    public static boolean dragBoard;
    public static float scale = 1;
    public static int xOffset = 0;
    public static int yOffset = 0;
    public static int mouseXStrt;
    public static int mouseYStrt;

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        if (e.getButton() == MouseEvent.BUTTON2){
            mouseXStrt = e.getX();
            mouseYStrt = e.getY();
            dragBoard = true;
        }

        for (Block block : Component.menuBlocks){
            if (inBounds(new Point((int) block.getPosition().getX(), (int) block.getPosition().getY() + scroll), e.getPoint())){
                BlockBlock blockBlock = new BlockBlock();
                Block block1 = new Block(block.getActions(), block.getType(), block.getPosition(), block.getName(), block.getNumArguments(), block.isManualInput());
                blockBlock.getBlocks().add(block1);
                Component.blockArrayList.add(blockBlock);
                heldBlockBlock = blockBlock;
            }
        }
        Point mouse = new Point((int) (Main.component.getMousePosition().x / scale) - MouseListener.xOffset, (int) (Main.component.getMousePosition().y / scale) - MouseListener.yOffset);
        this.mouse = mouse;


        if (e.getButton() != 3 && !selectMode) {
            for (int i = 0; i < Component.blockArrayList.size(); i++) {
                BlockBlock blockBlock = Component.blockArrayList.get(i);
                for (int j = 0; j < blockBlock.getBlocks().size(); j++) {
                    Block block = blockBlock.getBlocks().get(j);

                    if (inBounds(block.getPosition(), mouse)) {
                        if (j == 0) {
                            heldBlockBlock = blockBlock;
                        } else {
                            BlockBlock newBlockBlock = new BlockBlock();


                            for (int k = j; k < blockBlock.getBlocks().size(); k++) {
                                newBlockBlock.getBlocks().add(blockBlock.getBlocks().get(k));
                            }

                            for (int k = j; k < blockBlock.getBlocks().size(); k++) {
                                blockBlock.getBlocks().remove(blockBlock.getBlocks().get(k));
                                k--;
                            }
                            Component.blockArrayList.add(newBlockBlock);

                            heldBlockBlock = newBlockBlock;

                        }
                    }

                }
            }
        } else if (selectMode) {
            Block block = boundsWith(mouse);

            if (block != null) {
                ArrayList<Object> blockArrayList = selectBlock.getArguments();
                if (blockArrayList.size() == selectBlock.getNumArguments()) {
                    blockArrayList = new ArrayList<>();
                }
                blockArrayList.add(block);
                selectBlock.setArguments(blockArrayList);
                if (selectBlock.getNumArguments() == selectBlock.getArguments().size()) {
                    selectMode = false;
                }
            }
        } else {

            Block block = boundsWith(mouse);
            if (block != null && !block.isManualInput() && block.getNumArguments() > 0) {
                selectMode = true;
                selectBlock = block;
            } else if (block != null && block.getNumArguments() > 0){
                String value = JOptionPane.showInputDialog(Main.component,
                        "Value: ", null);

                ArrayList<Object> objects = new ArrayList<>();
                objects.add(value);
                System.out.println("Arguments of " +  block.getName() + " set to " + objects);
                block.setArguments(objects);
            }
        }
    }

    public static Block boundsWith(Point e){
        for (int i = 0; i < Component.blockArrayList.size(); i++) {
            BlockBlock blockBlock = Component.blockArrayList.get(i);
            for (int j = 0; j < blockBlock.getBlocks().size(); j++) {
                Block block = blockBlock.getBlocks().get(j);

                if (inBounds(block.getPosition(), e)) {
                    return block;
                }
            }
        }
        return null;
    }

    public static boolean inBounds(Point block, Point mouse) {
        if (mouse.getY() > block.getY() - 40 && mouse.getX() > block.getX() - 40 - 20 && mouse.getX() < block.getX() + 70 - 20 && mouse.getY() < block.getY() + 70 ) return true;
        return false;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON2){
            dragBoard = false;
        }

        if (e.getX() < 105 && e.getY() < 105){
            Component.blockArrayList.remove(heldBlockBlock);
            heldBlockBlock = null;
            return;
        }


        for (int j = 0; j < Component.blockArrayList.size(); j++) {
            BlockBlock blockBlock = Component.blockArrayList.get(j);
            if (blockBlock.getBlocks().size() == 0) return;
            Block iterBlock = blockBlock.getBlocks().get(blockBlock.getBlocks().size() - 1);
            if (heldBlockBlock != null && !heldBlockBlock.getBlocks().contains(iterBlock)) {
                if (heldBlockBlock.getBlocks().get(0).getPosition().distance(iterBlock.getPosition().x, iterBlock.getPosition().y) <= 85 && heldBlockBlock.getBlocks().get(0).getPosition().distance(iterBlock.getPosition().x, iterBlock.getPosition().y) != 0) {
                    heldBlockBlock.getBlocks().get(0).setPosition((Point) iterBlock.getPosition().clone());
                    heldBlockBlock.getBlocks().get(0).getPosition().x = heldBlockBlock.getBlocks().get(0).getPosition().x + 75;


                    if (!blockBlock.equals(heldBlockBlock)) {
                        for (int i = 0; i < heldBlockBlock.getBlocks().size(); i++) {
                            blockBlock.getBlocks().add(heldBlockBlock.getBlocks().get(i));
                        }
                    }
                    Component.blockArrayList.remove(heldBlockBlock);
                }
            }
        }
        heldBlockBlock = null;

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == ' '){
            VariableManager.clearVariables();
            ConsoleManager.addToConsole("");
            ConsoleManager.addToConsole("Starting..");
            for (BlockBlock b : Component.blockArrayList) {
                for (Block block : b.getBlocks()) {
                    if (block.getType().equals(BlockType.Event)) {
                        Thread thread = new Thread(b);
                        thread.start();
                    }
                }
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (e.getX() > Main.component.getWidth() - 150 && !dragBoard) {
            scroll += e.getPreciseWheelRotation() * -50;
            if (scroll > 0) scroll = 0;
            for (Block block : Component.menuBlocks) {
                block.getPosition().y = block.getPosition().y + (e.getScrollAmount() * -10);
            }
        } else if (!dragBoard){
            scale += e.getPreciseWheelRotation() / 20;
            Component.mainPoint = new Point(e.getX() - (Main.component.getWidth() / 2), e.getY() - (Main.component.getHeight() / 2));
        }
    }
}
