package com.wesley;

import com.wesley.block.Block;
import com.wesley.block.BlockBlock;
import com.wesley.block.BlockType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MouseListener implements java.awt.event.MouseListener, KeyListener {
    public static BlockBlock heldBlockBlock;
    public static boolean selectMode;
    public static Block selectBlock;

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() != 3 && !selectMode) {

            for (int i = 0; i < Component.blockArrayList.size(); i++) {
                BlockBlock blockBlock = Component.blockArrayList.get(i);
                for (int j = 0; j < blockBlock.getBlocks().size(); j++) {
                    Block block = blockBlock.getBlocks().get(j);

                    if (inBounds(block.getPosition(), e.getPoint())) {
                        if (j == 0) {
                            heldBlockBlock = blockBlock;
                        } else {
                            BlockBlock newBlockBlock = new BlockBlock();
                            Component.blockArrayList.add(newBlockBlock);

                            for (int k = j; k < blockBlock.getBlocks().size(); k++) {
                                newBlockBlock.getBlocks().add(blockBlock.getBlocks().get(k));
                            }

                            for (int k = j; k < blockBlock.getBlocks().size(); k++) {
                                blockBlock.getBlocks().remove(blockBlock.getBlocks().get(k));
                                k--;
                            }

                            heldBlockBlock = newBlockBlock;

                        }
                    }

                }
            }
        } else if (selectMode) {
            selectMode = false;
            Block block = boundsWith(e.getPoint());
            if (block != null) {
                System.out.println("Block `" + selectBlock.getName() + "` has set its arguments to `" + block.getName() + "`");
                System.out.println(block.getReturns());
                ArrayList<Block> blockArrayList = new ArrayList<>();
                blockArrayList.add(block);
                selectBlock.setArguments(blockArrayList);
            }
        } else {

            Block block = boundsWith(e.getPoint());
            if (block != null && !block.isManualInput()) {
                selectMode = true;
                selectBlock = block;
            } else if (block != null){
                String name = JOptionPane.showInputDialog(Main.component,
                        "What is your name?", null);
            }
        }
    }

    public Block boundsWith(Point e){
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

    public boolean inBounds(Point block, Point mouse) {
        if (mouse.getY() > block.getY() - 20 && mouse.getX() > block.getX() - 20 && mouse.getX() < block.getX() + 70 && mouse.getY() < block.getY() + 70) return true;
        return false;
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        for (int j = 0; j < Component.blockArrayList.size(); j++) {
            BlockBlock blockBlock = Component.blockArrayList.get(j);
            Block iterBlock = blockBlock.getBlocks().get(blockBlock.getBlocks().size() - 1);
            if (heldBlockBlock != null) {
                if (heldBlockBlock.getBlocks().get(0).getPosition().distance(iterBlock.getPosition().x, iterBlock.getPosition().y) <= 85 && heldBlockBlock.getBlocks().get(0).getPosition().distance(iterBlock.getPosition().x, iterBlock.getPosition().y) != 0) {
                    heldBlockBlock.getBlocks().get(0).setPosition((Point) iterBlock.getPosition().clone());
                    heldBlockBlock.getBlocks().get(0).getPosition().x = heldBlockBlock.getBlocks().get(0).getPosition().x + 75;


                    if (!blockBlock.equals(heldBlockBlock)) {
                        for (int i = 0; i < heldBlockBlock.getBlocks().size(); i++) {
                            blockBlock.getBlocks().add(heldBlockBlock.getBlocks().get(i));
                        }
                    }
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
                if (b.getBlocks().get(0).getType().equals(BlockType.Event)) {
                    Thread thread = new Thread(b);
                    thread.start();
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
}
