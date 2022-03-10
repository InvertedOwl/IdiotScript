package com.wesley.block;

import java.util.ArrayList;

public interface BlockAction {
    ArrayList<Object> trigger(Block block, Object... params);
}
