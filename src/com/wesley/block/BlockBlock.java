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
    }

    public void run() {
        for (Block block : blocks) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            block.getActions().trigger();
        }
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }
}
