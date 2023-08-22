package com.company.oop.taskmanagement.commands.listing;

import com.company.oop.taskmanagement.commands.BaseCommand;
import com.company.oop.taskmanagement.core.contracts.TaskManagementRepository;

import java.util.List;

public class ListAssigneeTasksCommand extends BaseCommand {

    public static final int NUMBER_OF_EXPECTED_ARGUMENTS = 1;
    public static final String ERR_MESSAGE_WHEN_TASKSIZE_ZERO = "There are no tasks to display";
    public static final String INVALID_INPUT = "Invalid input. Please follow the instructions in the console.";

    public ListAssigneeTasksCommand(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        return "";
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }
}
