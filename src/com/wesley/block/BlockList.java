package com.wesley.block;

import com.wesley.ConsoleManager;
import com.wesley.VariableManager;

import java.awt.*;
import java.util.ArrayList;

public class BlockList {
    public static ArrayList<Block> blocks = new ArrayList<>();
    public static void initilizeBlocks () {
        blocks.add(new Block(params -> ConsoleManager.addToConsole("Hello World!"), BlockType.Operation, new Point(1011/2, 1011), "Print"));
        blocks.add(new Block(params -> ConsoleManager.addToConsole("Event"), BlockType.Event, new Point(1011/2, 1011), "Event"));
        blocks.add(new Block(params -> ConsoleManager.addToConsole("Hello World!"), BlockType.Operation, new Point(1011/2, 1011), "Print"));
        blocks.add(new Block(params -> VariableManager.addVariable("Variable", true), BlockType.Variable, new Point(1011/2, 1011),
                "New Variable"));


    }
}
