package com.company.oop.taskmanagement.commands.ShowCommands.ShowActivityComands;

import com.company.oop.taskmanagement.commands.BaseCommand;
import com.company.oop.taskmanagement.core.contracts.TaskManagementRepository;
import com.company.oop.taskmanagement.models.contracts.Team;
import com.company.oop.taskmanagement.utilities.Validation;

import java.util.List;

public class ShowTeamActivityCommand extends BaseCommand {
    /**
     * displays team's activity
     * input: /showTeamActivity teamName
     */

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    public ShowTeamActivityCommand(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }



    @Override
    protected String executeCommand(List<String> parameters) {
        Validation.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String teamName = parameters.get(0);
        Team team = getTaskRepository().findTeamByName(teamName);

        return showActivityHistory(team);
    }
    private String showActivityHistory(Team team){ return team.printHistory();}
    @Override
    public boolean requiresLogin() {
        return false;
    }
}
