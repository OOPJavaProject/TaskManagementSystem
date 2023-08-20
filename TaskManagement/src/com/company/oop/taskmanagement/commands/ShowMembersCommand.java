package com.company.oop.taskmanagement.commands;

import com.company.oop.taskmanagement.core.contracts.TaskManagementRepository;
import com.company.oop.taskmanagement.utilities.Validation;

import java.util.List;

public class ShowMembersCommand extends BaseCommand {

    public static final int NUMBER_OF_ARGUMENTS_EXPECTED = 0;

    public ShowMembersCommand(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        Validation.validateArgumentsCount(parameters, NUMBER_OF_ARGUMENTS_EXPECTED);
        return showMembers();
    }

    private String showMembers() {
        return String.format("""
                ---------------------%s
                ---------------------
                 """, getTaskRepository().getMembers().toString()
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
