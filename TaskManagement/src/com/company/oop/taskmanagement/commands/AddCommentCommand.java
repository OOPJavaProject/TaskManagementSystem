package com.company.oop.taskmanagement.commands;

import com.company.oop.taskmanagement.core.contracts.TaskRepository;
import com.company.oop.taskmanagement.models.contracts.Comment;
import com.company.oop.taskmanagement.models.contracts.Member;
import com.company.oop.taskmanagement.models.enums.Priority;
import com.company.oop.taskmanagement.models.enums.Severity;
import com.company.oop.taskmanagement.models.tasks.contracts.Bug;
import com.company.oop.taskmanagement.models.tasks.contracts.Task;
import com.company.oop.taskmanagement.utilities.ParsingHelpers;
import com.company.oop.taskmanagement.utilities.Validation;

import java.util.List;

public class AddCommentCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;
    public static final String INVALID_INPUT_MESSAGE = "Invalid input. Expected a number.";
    public final static String COMMENT_ADDED_SUCCESSFULLY = "%s added comment successfully!";
    public final static String TASK_DOES_NOT_EXIST = "The task does not exist!";

    public AddCommentCommand(TaskRepository taskRepository) {
        super(taskRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        Validation.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String content = parameters.get(0);
        String author = parameters.get(1);
        int taskIndex = ParsingHelpers.tryParseInt(parameters.get(2), INVALID_INPUT_MESSAGE) - 1;
        return addComment(content, taskIndex, author);
    }

    private String addComment(String content, int taskIndex, String author) {
        Member member = getTaskRepository().findMemberByUserName(author);

        Validation.validateIntRange(taskIndex, 0, member.getTasks().size() - 1, TASK_DOES_NOT_EXIST);

        Task task = member.getTasks().get(taskIndex);

        Comment comment = getTaskRepository().createComment(content, getTaskRepository().getLoggedInMember());

        getTaskRepository().getLoggedInMember().addComment(comment, task);

        return String.format(COMMENT_ADDED_SUCCESSFULLY, getTaskRepository().getLoggedInMember().getUsername());
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }
}
