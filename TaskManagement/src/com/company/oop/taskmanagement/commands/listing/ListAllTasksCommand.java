package com.company.oop.taskmanagement.commands.listing;

import com.company.oop.taskmanagement.commands.BaseCommand;
import com.company.oop.taskmanagement.core.contracts.TaskManagementRepository;
import com.company.oop.taskmanagement.utilities.FilteringHelper;
import com.company.oop.taskmanagement.utilities.Validation;

import java.util.List;
import java.util.Scanner;

public class ListAllTasksCommand extends BaseCommand {

    public static final int NUMBER_OF_EXPECTED_ARGUMENTS = 1;
    public static final String ERR_MESSAGE_WHEN_TASKSIZE_ZERO = "There are no tasks to display";
    public static final String INVALID_INPUT_ERR = "Invalid input. You are only able to sort or filter tasks.";


    public ListAllTasksCommand(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        Validation.validateArgumentsCount(parameters, NUMBER_OF_EXPECTED_ARGUMENTS);
        String commandType = parameters.get(0);
        if (getTaskRepository().getTasks().size() == 0) {
            throw new IllegalArgumentException(ERR_MESSAGE_WHEN_TASKSIZE_ZERO);
        }
        if (commandType.equalsIgnoreCase("filter")) {
            Scanner scanner = new Scanner(System.in);
            return FilteringHelper.filterTasksByTitle(getTaskRepository().getTasks(), scanner.nextLine());
        } else if (commandType.equalsIgnoreCase("sort")) {
            return FilteringHelper.sortByTitle(getTaskRepository().getTasks());
        } else {
            throw new IllegalArgumentException(ListAllTasksCommand.INVALID_INPUT_ERR);
        }
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }
}
