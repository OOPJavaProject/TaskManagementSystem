package com.company.oop.taskmanagement.commands.listing;

import com.company.oop.taskmanagement.commands.BaseCommand;
import com.company.oop.taskmanagement.core.contracts.TaskManagementRepository;

import java.util.List;
import java.util.function.Function;

public class ListAssigneeTasksCommand extends BaseCommand {
    public ListAssigneeTasksCommand(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        return null;
    }
}
