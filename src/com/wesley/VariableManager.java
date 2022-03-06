package com.wesley;

import java.util.HashMap;

public class VariableManager {
    public static HashMap<String, Object> variables = new HashMap<String, Object>();
    public static void addVariable (String name, Object value){
        variables.put(name, value);
        System.out.println("put");
    }
}
