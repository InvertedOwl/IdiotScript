package com.wesley.block;

import java.awt.*;

public class Block {
    private BlockAction actions;
    private BlockType type;
    private Point position;
    private String name;
    private boolean isActive = false;

    public Block(BlockAction actions, BlockType type, Point position, String name) {
        this.actions = actions;
        this.type = type;
        this.position = position;
        this.name = name;
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
}
