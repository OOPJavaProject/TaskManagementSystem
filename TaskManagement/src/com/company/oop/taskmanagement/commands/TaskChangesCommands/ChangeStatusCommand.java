package com.company.oop.taskmanagement.commands.TaskChangesCommands;

import com.company.oop.taskmanagement.commands.BaseCommand;
import com.company.oop.taskmanagement.core.contracts.TaskManagementRepository;
import com.company.oop.taskmanagement.models.contracts.Status;
import com.company.oop.taskmanagement.models.enums.TaskStatus.BugStatus;
import com.company.oop.taskmanagement.models.enums.TaskStatus.FeedbackStatus;
import com.company.oop.taskmanagement.models.enums.TaskStatus.StoryStatus;
import com.company.oop.taskmanagement.models.tasks.contracts.Task;
import com.company.oop.taskmanagement.utilities.ParsingHelpers;
import com.company.oop.taskmanagement.utilities.Validation;

import java.util.List;

public class ChangeStatusCommand extends BaseCommand {

    /**
     * Changes the status of a task to a given one
     * input: /changeStatus taskId status
     */
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private static final String INVALID_ID_INPUT = "Invalid input for id, must be a number.";
    private static final String INVALID_TYPE = "Invalid type.";
    private static final String STATUS_CHANGED = "Status changed successfully.";

    public ChangeStatusCommand(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    public boolean requiresLogin() {
        return true;
    }

    @Override
    public String executeCommand(List<String> parameters) {
        Validation.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        int id = ParsingHelpers.tryParseInt(parameters.get(0), INVALID_ID_INPUT);
        Task task = getTaskRepository().findTaskById(id);
        String statusString = parameters.get(1);
        Status classType;
        Status status;
        switch (task.getTaskType()) {
            case BUG -> status = ParsingHelpers.tryParseEnum(statusString, BugStatus.class);
            case STORY -> status = ParsingHelpers.tryParseEnum(statusString, StoryStatus.class);
            case FEEDBACK -> status = ParsingHelpers.tryParseEnum(statusString, FeedbackStatus.class);
            default -> throw new IllegalArgumentException(INVALID_TYPE);
        }
        task.changeStatus(status);

        return STATUS_CHANGED;
    }
}
