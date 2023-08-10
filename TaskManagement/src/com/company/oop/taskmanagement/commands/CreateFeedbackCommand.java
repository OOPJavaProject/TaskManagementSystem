package com.company.oop.taskmanagement.commands;

import com.company.oop.taskmanagement.core.contracts.TaskManagementRepository;
import com.company.oop.taskmanagement.models.contracts.Board;
import com.company.oop.taskmanagement.models.tasks.contracts.Feedback;
import com.company.oop.taskmanagement.utilities.ParsingHelpers;
import com.company.oop.taskmanagement.utilities.Validation;

import java.util.List;

public class CreateFeedbackCommand extends BaseCommand {

    /**
     * creating a task of type Feedback
     * input: CreateFeedback title description rating
     */

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 4;
    public static final String FEEDBACK_CREATED = "Feedback with title %s was created in board %s!";
    public static final String RATING_ERR_MESSAGE = "Rating must be a number!";


    public CreateFeedbackCommand(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        Validation.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        String title = parameters.get(0);
        String description = parameters.get(1);
        int rating = ParsingHelpers.tryParseInt(parameters.get(2), RATING_ERR_MESSAGE);
        Board board = getTaskRepository().findBoardByName(parameters.get(3));

        Feedback createdFeedback = getTaskRepository().createFeedback(title, description, rating);
        board.addTask(createdFeedback);
        return String.format(FEEDBACK_CREATED, title , board.getName());
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }
}
