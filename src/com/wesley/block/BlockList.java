package com.wesley.block;

import com.wesley.ConsoleManager;
import com.wesley.Main;
import com.wesley.VariableManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class BlockList {
    public static ArrayList<Block> blocks = new ArrayList<>();
    public static void initilizeBlocks () {
        blocks.add(new Block((block, params) -> {
            if (block.getArguments().size() == 0) {
                ConsoleManager.addToConsole("Hello World!", block);
            } else {
                ConsoleManager.addToConsole(String.valueOf(block.getArguments().get(0)), block);
            }
            return null;
        }, BlockType.Operation, new Point(1011/2, 1011), "Print", 1, true));

        blocks.add(new Block((block, params) -> {
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
                "New Value", 1, true);


        new_var.setActions((block, params) -> {
            VariableManager.addVariable("Variable" + VariableManager.variables.size(), block.getArguments().get(0));
            ArrayList<Object> returns = new ArrayList<>();
            returns.add("Variable" + (VariableManager.variables.size() - 1));
            block.setReturns(returns);

            return returns;
        });
        blocks.add(new_var);


        blocks.add(new Block((block, params) -> {
            Block set = (Block) block.getArguments().get(0);
            Block to = (Block) block.getArguments().get(1);

            VariableManager.variables.put((String) set.getReturns().get(0), VariableManager.variables.get((String) to.getReturns().get(0)));



            VariableManager.addVariable("Variable" + VariableManager.variables.size(), VariableManager.variables.get(((Block) block.getArguments().get(0)).getReturns().get(0)));
            ArrayList<Object> returns = new ArrayList<>();
            returns.add("Variable" + (VariableManager.variables.size() - 1));
            block.setReturns(returns);

            return returns;
        }, BlockType.Variable, new Point(1011/2, 1011), "Set Var", 2, false));

        Block rand_var = new Block(null, BlockType.Variable, new Point(1011/2, 1011),
                "Random", 0, false);
        rand_var.setActions((block, params) -> {
            Random random = new Random();
            VariableManager.addVariable("Random" , random.nextFloat());
            ArrayList<Object> returns = new ArrayList<>();
            rand_var.setReturns(returns);
            returns.add("Random");

            return returns;
        });
        blocks.add(rand_var);


        blocks.add(new Block((block, params) -> {
            String value = JOptionPane.showInputDialog(Main.component,
                    "Value: ", null);
            VariableManager.addVariable("Variable" + VariableManager.variables.size(), value);
            ArrayList<Object> returns = new ArrayList<>();
            returns.add("Variable" + (VariableManager.variables.size() - 1));
            block.setReturns(returns);

            return returns;
        }, BlockType.Operation, new Point(1011/2, 1011), "Input", 2, false));


        blocks.add(new Block((block, params) -> {
            double value1 = Double.parseDouble(String.valueOf(VariableManager.variables.get(((Block) block.getArguments().get(0)).getReturns().get(0))));
            double value2 = Double.parseDouble(String.valueOf(VariableManager.variables.get(((Block) block.getArguments().get(1)).getReturns().get(0))));

            VariableManager.addVariable("Variable" + VariableManager.variables.size(), value1 + value2);
            ArrayList<Object> returns = new ArrayList<>();
            returns.add("Variable" + (VariableManager.variables.size() - 1));
            block.setReturns(returns);

            return returns;
        }, BlockType.Operation, new Point(1011/2, 1011), "Add", 2, false));
        blocks.add(new Block((block, params) -> {
            double value1 = Double.parseDouble(String.valueOf(VariableManager.variables.get(((Block) block.getArguments().get(0)).getReturns().get(0))));
            double value2 = Double.parseDouble(String.valueOf(VariableManager.variables.get(((Block) block.getArguments().get(1)).getReturns().get(0))));

            VariableManager.addVariable("Variable" + VariableManager.variables.size(), value1 - value2);
            ArrayList<Object> returns = new ArrayList<>();
            returns.add("Variable" + (VariableManager.variables.size() - 1));
            block.setReturns(returns);

            return returns;
        }, BlockType.Operation, new Point(1011/2, 1011), "Subtract", 2, false));

        blocks.add(new Block((block, params) -> {
            double value1 = Double.parseDouble(String.valueOf(VariableManager.variables.get(((Block) block.getArguments().get(0)).getReturns().get(0))));

            VariableManager.addVariable("Variable" + VariableManager.variables.size(), value1 * value1);
            ArrayList<Object> returns = new ArrayList<>();
            returns.add("Variable" + (VariableManager.variables.size() - 1));
            block.setReturns(returns);

            return returns;
        }, BlockType.Operation, new Point(1011/2, 1011), "Square", 1, false));

        blocks.add(new Block((block, params) -> {
            double value1 = Double.parseDouble(String.valueOf(VariableManager.variables.get(((Block) block.getArguments().get(0)).getReturns().get(0))));

            VariableManager.addVariable("Variable" + VariableManager.variables.size(), Math.sqrt(value1));
            ArrayList<Object> returns = new ArrayList<>();
            returns.add("Variable" + (VariableManager.variables.size() - 1));
            block.setReturns(returns);

            return returns;
        }, BlockType.Operation, new Point(1011/2, 1011), "Sqrt", 1, false));

        blocks.add(new Block((block, params) -> {
            double value1 = Double.parseDouble(String.valueOf(VariableManager.variables.get(((Block) block.getArguments().get(0)).getReturns().get(0))));
            double value2 = Double.parseDouble(String.valueOf(VariableManager.variables.get(((Block) block.getArguments().get(1)).getReturns().get(0))));

            VariableManager.addVariable("Variable" + VariableManager.variables.size(), value1 * value2);
            ArrayList<Object> returns = new ArrayList<>();
            returns.add("Variable" + (VariableManager.variables.size() - 1));
            block.setReturns(returns);

            return returns;
        }, BlockType.Operation, new Point(1011/2, 1011), "Multiply", 2, false));

        blocks.add(new Block((block, params) -> {
            double value1 = Double.parseDouble(String.valueOf(VariableManager.variables.get(((Block) block.getArguments().get(0)).getReturns().get(0))));
            double value2 = Double.parseDouble(String.valueOf(VariableManager.variables.get(((Block) block.getArguments().get(1)).getReturns().get(0))));

            VariableManager.addVariable("Variable" + VariableManager.variables.size(), value1 / value2);
            ArrayList<Object> returns = new ArrayList<>();
            returns.add("Variable" + (VariableManager.variables.size() - 1));
            block.setReturns(returns);

            return returns;
        }, BlockType.Operation, new Point(1011/2, 1011), "Divided by", 2, false));

        blocks.add(new Block((block, params) -> {
            double value1 = Double.parseDouble(String.valueOf(VariableManager.variables.get(((Block) block.getArguments().get(0)).getReturns().get(0))));

            VariableManager.addVariable("Variable" + VariableManager.variables.size(), Math.round(value1));
            ArrayList<Object> returns = new ArrayList<>();
            returns.add("Variable" + (VariableManager.variables.size() - 1));
            block.setReturns(returns);

            return returns;
        }, BlockType.Operation, new Point(1011/2, 1011), "Round", 1, false));

        blocks.add(new Block((block, params) -> {
            double value1 = Double.parseDouble(String.valueOf(VariableManager.variables.get(((Block) block.getArguments().get(0)).getReturns().get(0))));

            VariableManager.addVariable("Variable" + VariableManager.variables.size(), value1);
            ArrayList<Object> returns = new ArrayList<>();
            returns.add("Variable" + (VariableManager.variables.size() - 1));
            block.setReturns(returns);

            return returns;
        }, BlockType.Variable, new Point(1011/2, 1011), "Cast Double", 1, false));

        blocks.add(new Block((block, params) -> {
            try {
                double value1 = Double.parseDouble(String.valueOf(VariableManager.variables.get(((Block) block.getArguments().get(0)).getReturns().get(0))));
                double value2 = Double.parseDouble(String.valueOf(VariableManager.variables.get(((Block) block.getArguments().get(1)).getReturns().get(0))));

                Block triggerBlock = (Block) block.getArguments().get(2);

                System.out.println(VariableManager.variables);
                System.out.println("Value1: " + value1 + ", Value2: " + value2);
                if (value1 == value2) {
                    block.setWillContinue(false);
                    triggerBlock.getActions().trigger(triggerBlock);
                    System.out.println("They are not equal");
                }
            }
            catch (Exception ignored){

            }
            return null;
        }, BlockType.Logic, new Point(1011/2, 1011), "Jmp If =", 3, false));

        blocks.add(new Block((block, params) -> {
            Block triggerBlock = (Block) block.getArguments().get(0);

            block.setWillContinue(false);
            triggerBlock.getActions().trigger(triggerBlock);

            return null;
        }, BlockType.Logic, new Point(1011/2, 1011), "Jmp", 1, false));


        blocks.add(new Block((block, params) -> null, BlockType.Operation, new Point(1011/2, 1011), "NoOp", 0, false));

        blocks.add(new Block((block, params) -> {
            ConsoleManager.findBlockinBlockBlockss(block).run();
            return null;
        }, BlockType.Trigger, new Point(1011/2, 1011), "Triggered", 0, false));



        // DRAW STUFF
        blocks.add(new Block((block, params) -> {
            double value1 = Double.parseDouble(String.valueOf(VariableManager.variables.get(((Block) block.getArguments().get(0)).getReturns().get(0))));
            double value2 = Double.parseDouble(String.valueOf(VariableManager.variables.get(((Block) block.getArguments().get(1)).getReturns().get(0))));

            JFrame frame = null;

            if (Main.blockJFrame == null || !Main.blockJFrame.isVisible()) {
                frame = new JFrame("IS Draw Window");
                frame.setSize((int) value1, (int) value2 + 40);
                frame.setVisible(true);

                Main.blockJFrame = frame;


            } else {
                Main.blockJFrame.setSize((int) value1, (int) value2 + 40);
                Main.blockJFrame.remove(Main.blockDrawWindow);
            }

            frame = Main.blockJFrame;
            frame.toFront();
            frame.requestFocus();
            frame.setResizable(false);

            BlockDrawWindow blockDrawWindow = new BlockDrawWindow();


            Main.blockDrawWindow = blockDrawWindow;
            frame.remove(Main.blockDrawWindow);
            frame.add(blockDrawWindow);


            return null;
        }, BlockType.Draw, new Point(1011/2, 1011), "Window", 2, false));

        blocks.add(new Block((block, params) -> {
                Main.blockJFrame.setVisible(false);
            return null;
        }, BlockType.Draw, new Point(1011/2, 1011), "Close Window", 0, false));

        blocks.add(new Block((block, params) -> {
            double value1 = Double.parseDouble(String.valueOf(VariableManager.variables.get(((Block) block.getArguments().get(0)).getReturns().get(0))));
            double value2 = Double.parseDouble(String.valueOf(VariableManager.variables.get(((Block) block.getArguments().get(1)).getReturns().get(0))));
            BlockDrawMove blockDrawMove = new BlockDrawMove(new Point((int) value1, (int) value2), 20);
            blockDrawMove.run();
            return null;
        }, BlockType.Draw, new Point(1011/2, 1011), "Move To", 2, false));

        blocks.add(new Block((block, params) -> {
            double value1 = Double.parseDouble(String.valueOf(VariableManager.variables.get(((Block) block.getArguments().get(0)).getReturns().get(0))));
            BlockDrawMove blockDrawMove = new BlockDrawMove(new Point((int) (Main.blockDrawWindow.pen.x + value1 * Main.blockDrawWindow.direction.x), (int) (Main.blockDrawWindow.pen.y + value1 * Main.blockDrawWindow.direction.y)), 20);
            blockDrawMove.run();
            return null;
        }, BlockType.Draw, new Point(1011/2, 1011), "Forward", 1, false));

    }
}
