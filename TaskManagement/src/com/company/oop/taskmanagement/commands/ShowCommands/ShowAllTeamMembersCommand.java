package com.company.oop.taskmanagement.commands.ShowCommands;

import com.company.oop.taskmanagement.commands.BaseCommand;
import com.company.oop.taskmanagement.core.contracts.TaskManagementRepository;
import com.company.oop.taskmanagement.models.contracts.Team;
import com.company.oop.taskmanagement.utilities.Validation;

import java.util.List;

public class ShowAllTeamMembersCommand extends BaseCommand {

    /**
     * Shows all team members of the specified team
     * input: /shawTeamMembers teamName
     *
     * @param taskManagementRepository
     */

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    public ShowAllTeamMembersCommand(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected boolean requiresLogin() {
        return false;
    }

    @Override
    public String executeCommand(List<String> parameters) {
        Validation.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        String teamName = parameters.get(0);
        Team team = getTaskRepository().findTeamByName(teamName);

        StringBuilder result = new StringBuilder();
        result.append(String.format("Team %s has members:", team.getName()));
        result.append(team.printMembers());

        return result.toString();
    }

}
