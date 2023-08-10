package com.company.oop.taskmanagement.commands.CreateCommands;

import com.company.oop.taskmanagement.commands.BaseCommand;
import com.company.oop.taskmanagement.core.contracts.TaskManagementRepository;
import com.company.oop.taskmanagement.models.contracts.Member;
import com.company.oop.taskmanagement.models.enums.Priority;
import com.company.oop.taskmanagement.models.enums.Severity;
import com.company.oop.taskmanagement.models.tasks.contracts.Bug;
import com.company.oop.taskmanagement.utilities.ParsingHelpers;
import com.company.oop.taskmanagement.utilities.Validation;

import java.util.List;

public class CreateBugCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 5;
    public static final String BUG_CREATED = "Bug with title %s was created!";


    public CreateBugCommand(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        Validation.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        String title = parameters.get(0);
        String description = parameters.get(1);
        Priority priority = ParsingHelpers.tryParseEnum(parameters.get(2), Priority.class);
        Severity severity = ParsingHelpers.tryParseEnum(parameters.get(3), Severity.class);
        Member assignee = getTaskRepository().findMemberByName(parameters.get(4));

        Bug createdBug = getTaskRepository().createBug(title, description, priority, severity, assignee);

        return String.format(BUG_CREATED, createdBug.getTitle());
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }
}
