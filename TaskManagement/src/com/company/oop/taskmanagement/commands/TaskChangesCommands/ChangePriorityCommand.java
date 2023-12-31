package com.company.oop.taskmanagement.commands.TaskChangesCommands;

import com.company.oop.taskmanagement.commands.BaseCommand;
import com.company.oop.taskmanagement.core.contracts.TaskManagementRepository;
import com.company.oop.taskmanagement.models.enums.Priority;
import com.company.oop.taskmanagement.models.tasks.contracts.PrioritizableTask;
import com.company.oop.taskmanagement.utilities.ParsingHelpers;
import com.company.oop.taskmanagement.utilities.Validation;

import java.util.List;

public class ChangePriorityCommand extends BaseCommand {
    /**
     * Input: /changePriority task priority
     */
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private static final String INVALID_ID_INPUT = "Invalid input for id, must be a number.";
    private static final String PRIORITY_CHANGED = "Priority was set successfully";

    public ChangePriorityCommand(TaskManagementRepository taskRepository) {
        super(taskRepository);
    }


    @Override
    public String executeCommand(List<String> parameters) {
        Validation.validateArgumentsCount(parameters, 2);
        PrioritizableTask task = getTaskRepository().findPrioritizableTaskById(ParsingHelpers.tryParseInt(parameters.get(0), INVALID_ID_INPUT));
        Priority priority = ParsingHelpers.tryParseEnum(parameters.get(1), Priority.class);
        task.changePriority(priority);
        return PRIORITY_CHANGED;
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }
}
