package com.wesley.block;

import java.awt.*;
import java.util.ArrayList;

public class Block {
    private BlockAction actions;
    private BlockType type;
    private Point position;
    private String name;
    private int numArguments;
    private boolean manualInput;
    private boolean willContinue = true;
    private ArrayList<Object> arguments = new ArrayList<>();
    private ArrayList<Object> returns = new ArrayList<>();


    private boolean isActive = false;

    public Block(BlockAction actions, BlockType type, Point position, String name, int numArguments, boolean manualInput) {
        this.actions = actions;
        this.type = type;
        this.position = position;
        this.name = name;
        this.numArguments = numArguments;
        this.manualInput = manualInput;
    }

    public Block(BlockAction actions) {
    }

    public BlockType getType() {
        return type;
    }

    public void setType(BlockType type) {
        this.type = type;
    }

    public BlockAction getActions() {
        return actions;
    }

    public void setActions(BlockAction actions) {
        this.actions = actions;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isActive() {
        return isActive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Object> getReturns() {
        return returns;
    }

    public void setReturns(ArrayList<Object> returns) {
        this.returns = returns;
    }

    public void setArguments(ArrayList<Object> arguments) {
        this.arguments = arguments;

    }

    public ArrayList<Object> getArguments() {
        return arguments;
    }

    public int getNumArguments() {
        return numArguments;
    }

    public void setNumArguments(int numArguments) {
        this.numArguments = numArguments;
    }

    public boolean isManualInput() {
        return manualInput;
    }

    public void setManualInput(boolean manualInput) {
        this.manualInput = manualInput;
    }

    public boolean isWillContinue() {
        return willContinue;
    }

    public void setWillContinue(boolean willContinue) {
        System.out.println(willContinue);
        this.willContinue = willContinue;
    }
}
