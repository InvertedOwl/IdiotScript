package com.wesley.actionListeners;

import com.wesley.Component;
import com.wesley.ConsoleManager;
import com.wesley.Main;
import com.wesley.block.Block;
import com.wesley.block.BlockBlock;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class fileSave implements ActionListener {
    HashMap<Block, Integer> blockToIntMap = new HashMap<>();
    int line = 0;

    public void actionPerformed(ActionEvent e) {
        line = 0;
        blockToIntMap = new HashMap<>();

        String name = JOptionPane.showInputDialog(Main.component,
                "What should the file name be?", null, JOptionPane.DEFAULT_OPTION);
        int blockNum = 0;
        try {
            JFileChooser fr = new JFileChooser();
            FileSystemView fw = fr.getFileSystemView();

            FileWriter writer = new FileWriter(fw.getDefaultDirectory().getPath() + "\\" + name + ".is");
            ConsoleManager.addToConsole("Saved to : " + fw.getDefaultDirectory().getPath() + "\\" + name + ".is");


            for (BlockBlock blockBlock : Component.blockArrayList) {
                for (Block block : blockBlock.getBlocks()) {
                    blockToIntMap.put(block, blockNum);
                    blockNum++;
                }
            }

            line ++;
            writer.write("\n");

            for (BlockBlock blockBlock : Component.blockArrayList) {
                writer.write("-(" + (blockBlock.getBlocks().get(0).getPosition().x + "," + blockBlock.getBlocks().get(0).getPosition().y).replace("-", "n") + ")\n");

                for (Block block : blockBlock.getBlocks()) {

                    ArrayList<Object> arguments = (ArrayList<Object>) block.getArguments().clone();
                    for (int i = 0; i < arguments.size(); i++) {
                        Object o = arguments.get(i);
                        if (o instanceof Block) {
                            int place = arguments.indexOf(o);
                            arguments.add(place, "!block" + blockToIntMap.get(o));
                            arguments.remove(o);
                        }
                    }

                    writer.write(block.getName() + "/" + arguments + "\n");
                    line ++;



                }
            }
            writer.close();
        }
        catch (Exception ignored){
            ConsoleManager.addToConsole("ERROR OCCURRED " + ignored);
        }
    }
}
