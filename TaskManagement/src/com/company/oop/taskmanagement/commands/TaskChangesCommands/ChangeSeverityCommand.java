package com.company.oop.taskmanagement.commands.TaskChangesCommands;

import com.company.oop.taskmanagement.commands.BaseCommand;
import com.company.oop.taskmanagement.core.contracts.TaskManagementRepository;
import com.company.oop.taskmanagement.models.enums.Severity;
import com.company.oop.taskmanagement.models.tasks.contracts.Bug;
import com.company.oop.taskmanagement.utilities.ParsingHelpers;
import com.company.oop.taskmanagement.utilities.Validation;

import java.util.List;

public class ChangeSeverityCommand extends BaseCommand {
    /**
     * Changes the severity of a bug
     * input: /changeseverity bigId newSeverity
     *
     * @param taskManagementRepository
     */

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private static final String INVALID_ID_INPUT = "Invalid input for id, must be a number.";
    private static final String INVALID_INPUT = "Invalid id. There was no bug with id %d.";
    private static final String SEVERITY_CHANGED = "Severity changed successfully";
    //private static final String INVALID_SEVERITY_INPUT = "Invalid input for severity type.";

    public ChangeSeverityCommand(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        Validation.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        Bug bug;
        int id = ParsingHelpers.tryParseInt(parameters.get(0), INVALID_ID_INPUT);
        try {
            bug = (Bug) getTaskRepository().findTaskById(id);
        } catch (ClassCastException e) {
            throw new IllegalArgumentException(String.format(INVALID_INPUT, id));
        }
        Severity severity = ParsingHelpers.tryParseEnum(parameters.get(1), Severity.class);
        bug.changeSeverity(severity);

        return SEVERITY_CHANGED;
    }
}
