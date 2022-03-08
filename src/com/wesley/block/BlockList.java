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
                        System.out.println("TER");
                        System.out.println(VariableManager.variables.get(((Block) block.getArguments().get(0)).getReturns().get(0)));
                        Object variable = VariableManager.variables.get(((Block) block.getArguments().get(0)).getReturns().get(0));
                        ConsoleManager.addToConsole(String.valueOf(variable), block);
                        System.out.println(VariableManager.variables.keySet());
                        return null;
                    }
                } else {
                    System.out.println("Was a string not a block");
                    ConsoleManager.addToConsole(String.valueOf(block.getArguments().get(0)), block);
                }
            }

            ConsoleManager.addToConsole("Null", block);
            return null;
        });

        blocks.add(print_var);


        Block new_var = new Block(null, BlockType.Variable, new Point(1011/2, 1011),
                "New Value", 0, true);


        new_var.setActions((block, params) -> {
            VariableManager.addVariable("Variable" + VariableManager.variables.size(), block);
            ArrayList<String> returns = new ArrayList<>();
            returns.add("Variable" + (VariableManager.variables.size() - 1));
            block.setReturns(returns);

            return returns;
        });
        blocks.add(new_var);


        blocks.add(new Block((block, params) -> {
            System.out.println(((Block) block.getArguments().get(0)).getReturns().get(0));
            VariableManager.addVariable("Variable" + VariableManager.variables.size(), VariableManager.variables.get(((Block) block.getArguments().get(0)).getReturns().get(0)));
            ArrayList<String> returns = new ArrayList<>();
            returns.add("Variable" + (VariableManager.variables.size() - 1));
            block.setReturns(returns);

            return returns;
        }, BlockType.Variable, new Point(1011/2, 1011), "Set Var", 1, false));

        Block rand_var = new Block(null, BlockType.Variable, new Point(1011/2, 1011),
                "Random", 0, false);
        rand_var.setActions((block, params) -> {
            Random random = new Random();
            VariableManager.addVariable("Random" , random.nextFloat());
            ArrayList<String> returns = new ArrayList<>();
            rand_var.setReturns(returns);
            returns.add("Random");

            return returns;
        });
        blocks.add(rand_var);



        blocks.add(new Block((block, params) -> {
            System.out.println(((Block) block.getArguments().get(0)).getReturns().get(0));
            int value1 = Integer.parseInt((String) VariableManager.variables.get(((Block) block.getArguments().get(0)).getReturns().get(0)));
            int value2 = Integer.parseInt((String) VariableManager.variables.get(((Block) block.getArguments().get(1)).getReturns().get(0)));


            VariableManager.addVariable("Variable" + VariableManager.variables.size(), value1 + value2);
            ArrayList<String> returns = new ArrayList<>();
            returns.add("Variable" + (VariableManager.variables.size() - 1));
            block.setReturns(returns);

            return returns;
        }, BlockType.Operation, new Point(1011/2, 1011), "Add", 2, false));
    }
}
