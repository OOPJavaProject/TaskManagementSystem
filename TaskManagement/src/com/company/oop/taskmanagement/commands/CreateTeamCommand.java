package com.company.oop.taskmanagement.commands;

import com.company.oop.taskmanagement.core.contracts.TaskManagementRepository;
import com.company.oop.taskmanagement.models.contracts.Team;
import com.company.oop.taskmanagement.utilities.Validation;

import java.util.List;

public class CreateTeamCommand extends BaseCommand{

    /**
     * creating a team
     * input: CreateTeam teamName
     */

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    public static final String TEAM_CREATED = "Team with name %s was created!";
    private String teamName;

    public CreateTeamCommand(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        Validation.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        teamName = parameters.get(0);

        Team createdTeam = getTaskRepository().createTeam(teamName);

        return String.format(TEAM_CREATED, teamName);
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }
}
