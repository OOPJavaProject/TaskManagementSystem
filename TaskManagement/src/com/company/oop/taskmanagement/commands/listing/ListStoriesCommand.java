package com.company.oop.taskmanagement.commands.listing;

import com.company.oop.taskmanagement.commands.BaseCommand;
import com.company.oop.taskmanagement.core.contracts.TaskManagementRepository;

import java.util.List;

public class ListStoriesCommand extends BaseCommand {


    public ListStoriesCommand(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        return null;
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }

}
