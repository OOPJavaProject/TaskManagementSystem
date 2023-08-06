package com.company.oop.taskmanagement.commands;

import com.company.oop.taskmanagement.core.contracts.TaskRepository;
import com.company.oop.taskmanagement.models.tasks.contracts.Task;

import java.util.List;

public class LogoutCommand extends BaseCommand{

    public final static String MEMBER_LOGGED_OUT = "You logged out!";

    public LogoutCommand(TaskRepository taskRepository) {
        super(taskRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        getTaskRepository().logout();
        return MEMBER_LOGGED_OUT;
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }
}
