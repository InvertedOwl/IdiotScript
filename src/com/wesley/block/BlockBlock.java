package com.wesley.block;

import java.util.ArrayList;

public class BlockBlock implements Runnable {
    private ArrayList<Block> blocks;
    public BlockBlock(ArrayList<Block> blocks) {
        this.blocks = blocks;
    }

    public BlockBlock(Block block) {
        this.blocks = new ArrayList<>();
        blocks.add(block);
    }

    public BlockBlock() {
        this.blocks = new ArrayList<>();
        System.out.println("block block constructor");
    }

    @Override
    public void run() {
        for (int i = 0; i < blocks.size() - 1; i++) {
            Block block = blocks.get(i + 1);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            blocks.get(i).setActive(false);
            block.setActive(true);
            block.setReturns(block.getActions().trigger(block));
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        blocks.get(blocks.size() - 1).setActive(false);
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }
}
