package com.company.oop.taskmanagement.commands.listing;

import com.company.oop.taskmanagement.commands.BaseCommand;
import com.company.oop.taskmanagement.core.contracts.TaskManagementRepository;

import java.util.List;

public class ListFeedbacksCommand extends BaseCommand {
    public ListFeedbacksCommand(TaskManagementRepository taskManagementRepository) {
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
