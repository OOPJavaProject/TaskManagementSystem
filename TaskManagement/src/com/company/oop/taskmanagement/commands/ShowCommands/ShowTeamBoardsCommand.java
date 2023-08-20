package com.company.oop.taskmanagement.commands.ShowCommands;

import com.company.oop.taskmanagement.commands.BaseCommand;
import com.company.oop.taskmanagement.core.contracts.TaskManagementRepository;
import com.company.oop.taskmanagement.models.contracts.Team;
import com.company.oop.taskmanagement.utilities.Validation;

import java.util.List;

public class ShowTeamBoardsCommand extends BaseCommand {
    /**
     * Shows a specific team's boards
     * input: /showTeamBoards teamName
     *
     * @param taskManagementRepository
     */


    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    public ShowTeamBoardsCommand(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    public boolean requiresLogin() {
        return false;
    }

    @Override
    public String executeCommand(List<String> parameters) {
        Validation.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        String teamName = parameters.get(0);
        Team team = getTaskRepository().findTeamByName(teamName);
        StringBuilder result = new StringBuilder();
        result.append("Team ").append(team.toString()).append(" has the boards:\n");
        result.append(team.printBoards());

        return result.toString();
    }
}
