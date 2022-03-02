package com.wesley;

import com.wesley.block.Block;
import com.wesley.block.BlockBlock;

import java.awt.*;
import java.awt.event.MouseEvent;

public class MouseListener implements java.awt.event.MouseListener {
    public static Block heldBlock;

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (BlockBlock blockBlock : Component.blockArrayList ) {
            for (Block block : blockBlock.getBlocks()) {
                if (inBounds(block.getPosition(), e.getPoint())) {


                    heldBlock = block;
                }
            }
        }
    }

    public boolean inBounds(Point block, Point mouse) {
        if (mouse.getY() > block.getY() - 70 && mouse.getX() > block.getX() - 70 && mouse.getX() < block.getX() + 70 && mouse.getY() < block.getY() + 70) return true;
        return false;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (BlockBlock blockBlock : Component.blockArrayList ) {
            for (Block iterBlock : blockBlock.getBlocks()) {
                if (heldBlock != null) {
                    if (heldBlock.getPosition().distance(iterBlock.getPosition().x, iterBlock.getPosition().y) <= 85 && heldBlock.getPosition().distance(iterBlock.getPosition().x, iterBlock.getPosition().y) != 0) {
                        System.out.println(heldBlock.getPosition().distance(iterBlock.getPosition().x, iterBlock.getPosition().y));
                        heldBlock.setPosition((Point) iterBlock.getPosition().clone());
                        heldBlock.getPosition().x = heldBlock.getPosition().x + 75;
                    }
                }
            }
        }
        heldBlock = null;

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
