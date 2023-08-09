package com.company.oop.taskmanagement.commands;

import com.company.oop.taskmanagement.core.contracts.TaskManagementRepository;
import com.company.oop.taskmanagement.models.contracts.Member;
import com.company.oop.taskmanagement.utilities.Validation;

import java.util.List;

public class LoginCommand extends BaseCommand{
    private final static String MEMBER_LOGGED_IN = "Member %s successfully logged in!";
    private final static String WRONG_USERNAME_OR_PASSWORD = "Wrong username or password!";
    public final static String MEMBER_LOGGED_IN_ALREADY = "Member %s is logged in! Please log out first!";

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    public LoginCommand(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        throwIfMemberLoggedIn();
        Validation.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String username = parameters.get(0);
        String password = parameters.get(1);

        return login(username, password);
    }

    private String login(String username, String password) {
        Member memberFound = getTaskRepository().findMemberByName(username);

        if (!memberFound.getPassword().equals(password)) {
            throw new IllegalArgumentException(WRONG_USERNAME_OR_PASSWORD);
        }

        getTaskRepository().login(memberFound);
        return String.format(MEMBER_LOGGED_IN, username);
    }

    @Override
    protected boolean requiresLogin() {
        return false;
    }

    private void throwIfMemberLoggedIn() {
        if (getTaskRepository().hasLoggedInMember()) {
            throw new IllegalArgumentException(
                    String.format(MEMBER_LOGGED_IN_ALREADY, getTaskRepository().getLoggedInMember().getUsername())
            );
        }
    }
}
