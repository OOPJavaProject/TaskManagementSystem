package com.company.oop.taskmanagement.commands;

import com.company.oop.taskmanagement.core.contracts.TaskManagementRepository;
import com.company.oop.taskmanagement.models.contracts.Member;
import com.company.oop.taskmanagement.models.enums.TaskType;
import com.company.oop.taskmanagement.models.tasks.contracts.PrioritizableTask;
import com.company.oop.taskmanagement.models.tasks.contracts.Task;
import com.company.oop.taskmanagement.utilities.ParsingHelpers;
import com.company.oop.taskmanagement.utilities.Validation;

import java.util.List;

public class UnassignCommand extends BaseCommand {

    /**
     * input: Unassign TaskId, MemberName
     * This command removes a task from the member's task list.
     * */

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String TASK_UNASSIGNED = "Task with ID %d was unassigned from member %s!";
    public static final String INVALID_TASK_ID_MSG = "Please provide a valid number for task ID.";

    public UnassignCommand(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        Validation.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int taskId = ParsingHelpers.tryParseInt(parameters.get(0), INVALID_TASK_ID_MSG);
        String memberUsername = parameters.get(1);
        Member member = getTaskRepository().findMemberByUserName(memberUsername);
        PrioritizableTask task = getTaskRepository().findPrioritizableTaskById(taskId);
        return unassignTask(task, member);
    }

    private String unassignTask(PrioritizableTask task, Member member) {
        getTaskRepository().unassignTaskFromMember(member, task);
        return String.format(TASK_UNASSIGNED, task.getId(), member.getUsername());
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }
}
