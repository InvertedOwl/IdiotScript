package com.wesley;

import com.wesley.block.Block;
import com.wesley.block.BlockBlock;
import com.wesley.block.BlockType;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

public class MouseListener implements java.awt.event.MouseListener, KeyListener {
    public static BlockBlock heldBlockBlock;

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
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
                            System.out.println(blockBlock.getBlocks().get(k));
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
                    System.out.println(heldBlockBlock.getBlocks().get(0).getPosition().distance(iterBlock.getPosition().x, iterBlock.getPosition().y));
                    heldBlockBlock.getBlocks().get(0).setPosition((Point) iterBlock.getPosition().clone());
                    heldBlockBlock.getBlocks().get(0).getPosition().x = heldBlockBlock.getBlocks().get(0).getPosition().x + 75;


                    if (!blockBlock.equals(heldBlockBlock)) {
                        for (int i = 0; i < heldBlockBlock.getBlocks().size(); i++) {
                            blockBlock.getBlocks().add(heldBlockBlock.getBlocks().get(i));
                            System.out.println("ono");
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
            for (BlockBlock b : Component.blockArrayList) {
                if (b.getBlocks().get(0).getType().equals(BlockType.Event)) {
                    b.run();
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
