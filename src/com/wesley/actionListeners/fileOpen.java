package com.wesley.actionListeners;

import com.wesley.Component;
import com.wesley.Main;
import com.wesley.block.Block;
import com.wesley.block.BlockBlock;
import com.wesley.block.BlockList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class fileOpen implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        int result = JOptionPane.showConfirmDialog(Main.component, "Are you sure you want to do this? It will delete any unsaved data.", "", JOptionPane.YES_NO_OPTION, JOptionPane.DEFAULT_OPTION, null);
        if (result == 0) {
            Component.blockArrayList = new ArrayList<>();
        }

        File file = null;

        final JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(Main.component);

        if (returnVal == 0) {
            file = fc.getSelectedFile();
        }

        if (file == null) return;

        if (!file.getPath().endsWith(".is")) return;

        try {
            HashMap<Integer, Block> IntToBlockMap = new HashMap<>();
            ArrayList<Object[]> argumentsToChange = new ArrayList<>();

            String fileString = Files.readString(file.toPath());
            String[] lines = fileString.split("\n");

            String[] blockBlocks = fileString.split("\n-");

            int blockBlockNum = 0;
            int totalBlock = 0;
            for (int i = 1; i < blockBlocks.length; i++) {
                BlockBlock blockBlock = new BlockBlock();

                String[] blocks = blockBlocks[i].split("\n");
                int blockNum = 0;
                for (int j = 1; j < blocks.length; j++) {
                    String name = blocks[j].split("/")[0];
                    String argsString = "";

                    try {
                        argsString = blocks[j].split("/")[1].substring(1, blocks[j].split("/")[1].length() - 1);

                    } catch (Exception exception){}
                    String[] args = argsString.split(", ");
                    System.out.println(Arrays.toString(args));


                    Block blockTemp = findBlock(name);
                    Block block = new Block(blockTemp.getActions(), blockTemp.getType(), new Point(50 + (blockNum * 75), 250 + (blockBlockNum * 90)), name, blockTemp.getNumArguments(), blockTemp.isManualInput());

                    IntToBlockMap.put(totalBlock, block);
                    totalBlock ++;


                    ArrayList<Object> objectArrayList = new ArrayList<Object>();
                    for (String arg : args) {
//                        arg = arg.replace(" ", "");
                        if (!arg.startsWith("!block")) objectArrayList.add(arg);
                        else {
                            try {
                                int r = Integer.parseInt(arg.substring(6));


                                argumentsToChange.add(new Object[]{block, r, objectArrayList.size()});
                                objectArrayList.add(IntToBlockMap.get(r));
                            } catch (Exception ignored) {

                            }
                        }
                    }

                    block.setArguments(objectArrayList);

                    blockBlock.getBlocks().add(block);
                    blockNum ++;

                }
                if (blockBlock.getBlocks().size() > 0) {
                    Component.blockArrayList.add(blockBlock);
                    blockBlockNum++;
                }
            }
            for (Object[] objects : argumentsToChange) {
                Block block = (Block) objects[0];
                int place = (int) objects[2];
                int blockInt = (int) objects[1];
                System.out.println("Replaced " + block.getName() + " argument " + place + " with block " + IntToBlockMap.get(blockInt).getName());
                block.getArguments().set(place, IntToBlockMap.get(blockInt));
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }


    }

    public Block findBlock(String name) {
        for (Block bl : BlockList.blocks) {
            if (bl.getName().equals(name)) return bl;
        }
        return null;
    }
}
