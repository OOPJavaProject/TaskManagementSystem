package com.company.oop.taskmanagement.commands;

import com.company.oop.taskmanagement.commands.contracts.Command;
import com.company.oop.taskmanagement.core.contracts.TaskRepository;

import java.util.List;

public abstract class BaseCommand implements Command {

    private final static String USER_NOT_LOGGED = "You are not logged in! Please login first!";

    private final TaskRepository taskRepository;

    protected BaseCommand(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    protected TaskRepository getTaskRepository() {
        return taskRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        if (requiresLogin() && !taskRepository.hasLoggedInMember()) {
            throw new IllegalArgumentException(USER_NOT_LOGGED);
        }
        return executeCommand(parameters);
    }

    protected abstract boolean requiresLogin();

    protected abstract String executeCommand(List<String> parameters);
}
