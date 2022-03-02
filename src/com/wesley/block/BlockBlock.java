package com.wesley.block;

import java.util.ArrayList;

public class BlockBlock extends Thread {
    private ArrayList<Block> blocks;
    public BlockBlock(ArrayList<Block> blocks) {
        this.blocks = blocks;
    }

    public BlockBlock(Block block) {
        this.blocks = new ArrayList<>();
        blocks.add(block);
    }

    public void run() {
        for (Block block : blocks) {
            block.getActions().trigger();
        }
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }
}
