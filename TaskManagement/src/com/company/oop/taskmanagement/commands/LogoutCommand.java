package com.company.oop.taskmanagement.commands;

import com.company.oop.taskmanagement.core.contracts.TaskManagementRepository;

import java.util.List;

public class LogoutCommand extends BaseCommand{

    public final static String MEMBER_LOGGED_OUT = "You logged out!";

    public LogoutCommand(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
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
