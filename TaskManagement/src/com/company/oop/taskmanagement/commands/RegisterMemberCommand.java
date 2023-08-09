package com.company.oop.taskmanagement.commands;

import com.company.oop.taskmanagement.core.contracts.TaskManagementRepository;
import com.company.oop.taskmanagement.models.contracts.Member;
import com.company.oop.taskmanagement.utilities.Validation;

import java.util.List;

public class RegisterMemberCommand extends BaseCommand{

    private final static String MEMBER_REGISTERED = "Member %s registered successfully!";
    public final static String MEMBER_LOGGED_IN_ALREADY = "Member %s is logged in! Please log out first!";
    private final static String MEMBER_ALREADY_EXIST = "Member %s already exist. Choose a different username!";
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;

    public RegisterMemberCommand(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        throwIfMemberLoggedIn();
        Validation.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String name = parameters.get(0);
        String username = parameters.get(1);
        String password = parameters.get(2);


        return registerUser(name, username, password);
    }

    private String registerUser(String name, String username, String password) {
        Member member = getTaskRepository().createMember(name, username, password);
        getTaskRepository().login(member);

        return String.format(MEMBER_REGISTERED, username);
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
