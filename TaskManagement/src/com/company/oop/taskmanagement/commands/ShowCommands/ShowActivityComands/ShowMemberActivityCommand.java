package com.company.oop.taskmanagement.commands.ShowCommands.ShowActivityComands;

import com.company.oop.taskmanagement.commands.BaseCommand;
import com.company.oop.taskmanagement.core.contracts.TaskManagementRepository;
import com.company.oop.taskmanagement.models.contracts.Member;
import com.company.oop.taskmanagement.utilities.Validation;

import java.util.List;

public class ShowMemberActivityCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    public ShowMemberActivityCommand(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        Validation.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String memberName = parameters.get(0);
        Member member = getTaskRepository().findMemberByName(memberName);

        return showActivityHistory(member);
    }

    private String showActivityHistory(Member member) {
        return member.printHistory();
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }

}
