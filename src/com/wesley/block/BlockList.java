package com.wesley.block;

import com.wesley.ConsoleManager;
import com.wesley.VariableManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class BlockList {
    public static ArrayList<Block> blocks = new ArrayList<>();
    public static void initilizeBlocks () {
        blocks.add(new Block((block, params) -> {
            ConsoleManager.addToConsole("Hello World!", block);
            return null;
        }, BlockType.Operation, new Point(1011/2, 1011), "Print", 1, true));

        blocks.add(new Block((block, params) -> {
            ConsoleManager.addToConsole("Event", block);
            return null;
        }, BlockType.Event, new Point(1011/2, 1011), "Event", 0, false));


        Block print_var = new Block(null, BlockType.Operation, new Point(1011/2, 1011), "Print Var", 1, false);
        print_var.setActions((block, params) -> {

            if (block.getArguments().size() != 0) {
                if (block.getArguments().get(0) instanceof Block) {
                    if (((Block) block.getArguments().get(0)).getReturns() != null) {
                        Object variable = VariableManager.variables.get(((Block) block.getArguments().get(0)).getReturns().get(0));
                        ConsoleManager.addToConsole(String.valueOf(variable), block);
                        return null;
                    }
                } else {
                    ConsoleManager.addToConsole(String.valueOf(block.getArguments().get(0)), block);
                }
            }

            ConsoleManager.addToConsole("Null", block);
            return null;
        });

        blocks.add(print_var);


        Block new_var = new Block(null, BlockType.Variable, new Point(1011/2, 1011),
                "New Variable", 0, true);


        new_var.setActions((block, params) -> {
            System.out.println(new_var.getArguments());
            VariableManager.addVariable("Variable", new_var.getArguments().get(0));
            ArrayList<String> returns = new ArrayList<>();
            returns.add("Variable");
            new_var.setReturns(returns);

            return returns;
        });
        blocks.add(new_var);


        Block rand_var = new Block(null, BlockType.Variable, new Point(1011/2, 1011),
                "Random", 0, false);
        rand_var.setActions((block, params) -> {
            Random random = new Random();
            VariableManager.addVariable("Random", random.nextFloat());
            ArrayList<String> returns = new ArrayList<>();
            rand_var.setReturns(returns);
            returns.add("Random");

            return returns;
        });
        blocks.add(rand_var);

    }
}
