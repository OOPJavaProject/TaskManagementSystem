package com.company.oop.taskmanagement.commands;

import com.company.oop.taskmanagement.core.contracts.TaskManagementRepository;
import com.company.oop.taskmanagement.models.contracts.Member;
import com.company.oop.taskmanagement.models.enums.TaskType;
import com.company.oop.taskmanagement.models.tasks.contracts.PrioritizableTask;
import com.company.oop.taskmanagement.models.tasks.contracts.Task;
import com.company.oop.taskmanagement.utilities.ParsingHelpers;
import com.company.oop.taskmanagement.utilities.Validation;

import java.util.List;

public class AssignCommand extends BaseCommand {
    /**
     * assigns task to member
     * Input: assign Member Task
     */

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    public static final String INVALID_TASK_ID = "Invalid task id.";
    public static final String TASK_ASSIGNED_TO_MEMBER = "Member %s was assigned a task.";

    public AssignCommand(TaskManagementRepository taskRepository) {
        super(taskRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        Validation.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        Member member = getTaskRepository().findMemberByUserName(parameters.get(0));
        PrioritizableTask task = getTaskRepository().findPrioritizableTaskById(ParsingHelpers.tryParseInt(parameters.get(1), INVALID_TASK_ID));
        return assignTaskToMember(member, task);
    }

    private String assignTaskToMember(Member member, PrioritizableTask task) {
        getTaskRepository().assignTaskToMember(member, task);
        return String.format(TASK_ASSIGNED_TO_MEMBER, member.getName());
    }


    @Override
    protected boolean requiresLogin() {
        return true;
    }

}
