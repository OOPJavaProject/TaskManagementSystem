package com.company.oop.taskmanagement;

import com.company.oop.taskmanagement.core.TaskEngineImpl;

public class Startup {
    public static void main(String[] args) {

        TaskEngineImpl engine = new TaskEngineImpl();
        engine.start();
    }
}
