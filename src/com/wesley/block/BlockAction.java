package com.wesley.block;

import java.util.ArrayList;

public interface BlockAction {
    ArrayList<String> trigger(Block block, Object... params);
}
