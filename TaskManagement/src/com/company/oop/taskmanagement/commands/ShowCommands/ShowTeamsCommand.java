package com.company.oop.taskmanagement.commands.ShowCommands;

import com.company.oop.taskmanagement.commands.BaseCommand;
import com.company.oop.taskmanagement.core.contracts.TaskManagementRepository;
import com.company.oop.taskmanagement.exceptions.ElementNotFoundException;
import com.company.oop.taskmanagement.utilities.Validation;

import java.util.List;

public class ShowTeamsCommand extends BaseCommand {
    public static final int NUMBER_OF_ARGUMENTS_EXPECTED = 0;
    public static final String NO_TEAMS_ERR = "There are no existing teams to show.";

    public ShowTeamsCommand(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        Validation.validateArgumentsCount(parameters, NUMBER_OF_ARGUMENTS_EXPECTED);
        return showTeams();
    }

    private String showTeams() {
        if (getTaskRepository().getTeams().size() == 0) {
            throw new ElementNotFoundException(NO_TEAMS_ERR);
        }
        return String.format("""
                ---------------------%s
                ---------------------
                """, getTaskRepository().getTeams().toString()
                .replace("[", "")
                .replace("]", "")
                .replace(",", "")
                .replace(" ", ""));
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }
}
