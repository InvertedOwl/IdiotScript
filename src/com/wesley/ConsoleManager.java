package com.wesley;

import com.wesley.block.Block;
import com.wesley.block.BlockBlock;

import java.util.ArrayList;
import java.util.Objects;

public class ConsoleManager {
    public static ArrayList<String> list = new ArrayList<>();

    public static void addToConsole(String str, Block block) {
        list.add("[" + findBlockinBlockBlocks(block) + "," + findBlockInBlockBlock(block, findBlockinBlockBlockss(block)) + "] " + str);
    }
    public static void addToConsole(String str) {
        list.add(str);
    }

    public static BlockBlock findBlockinBlockBlockss(Block block) {
        if (block == null) {
            return null;
        }
        for (int i = 0; i < Component.blockArrayList.size(); i++) {
            BlockBlock blockBlock = Component.blockArrayList.get(i);
            for (int j = 0; j < blockBlock.getBlocks().size() - 1; j++) {
                if (blockBlock.getBlocks().get(j).equals(block)) {
                    return blockBlock;
                }
            }
        }

        return null;
    }

    public static int findBlockinBlockBlocks(Block block) {
        if (block == null) {
            return -1;
        }
        for (int i = 0; i < Component.blockArrayList.size(); i++) {
            BlockBlock blockBlock = Component.blockArrayList.get(i);
            for (int j = 0; j < blockBlock.getBlocks().size() - 1; j++) {
                if (blockBlock.getBlocks().get(j).equals(block)) {
                    return i;
                }
            }
        }

        return -1;
    }

    public static int findBlockInBlockBlock(Block block, BlockBlock blockBlock) {
        if (block == null || blockBlock == null) {
            return -1;
        }
        for (int i = 0; i < blockBlock.getBlocks().size(); i++) {
            if (blockBlock.getBlocks().get(i).equals(block)){
                return i;
            }
        }
        return -1;
    }
}
