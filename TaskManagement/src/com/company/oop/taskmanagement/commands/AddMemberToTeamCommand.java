package com.company.oop.taskmanagement.commands;

import com.company.oop.taskmanagement.core.contracts.TaskRepository;
import com.company.oop.taskmanagement.exceptions.ElementNotFoundException;
import com.company.oop.taskmanagement.utilities.Validation;

import java.util.List;

public class AddMemberToTeamCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;
    public final static String MEMBER_ADDED_SUCCESSFULLY = "Member %s was added successfully to team %s!";
    public final static String INVALID_USERNAME = "The username %s does not exist.";
    public final static String INVALID_TEAM = "Team with name %s does not exist.";

    public AddMemberToTeamCommand(TaskRepository taskRepository) {
        super(taskRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        Validation.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String memberUsername = parameters.get(0);
        String teamName = parameters.get(1);

        validateArguments(memberUsername, teamName);

        return addMemberToTeam(memberUsername, teamName);
    }

    private String addMemberToTeam(String memberName, String teamName) {
     getTaskRepository().addMemberToTeam(memberName, teamName);

     return String.format(MEMBER_ADDED_SUCCESSFULLY, memberName, teamName);
    }

    private void validateArguments(String memberUsername, String teamName) {
        if(!getTaskRepository().memberExists(memberUsername)) throw new ElementNotFoundException(String.format(INVALID_USERNAME, memberUsername));
        if(!getTaskRepository().teamExists(teamName)) throw new ElementNotFoundException(String.format(INVALID_TEAM, teamName));
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }
}
