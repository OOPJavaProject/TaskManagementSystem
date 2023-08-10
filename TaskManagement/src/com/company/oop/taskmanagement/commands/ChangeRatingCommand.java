package com.company.oop.taskmanagement.commands;

import com.company.oop.taskmanagement.core.contracts.TaskManagementRepository;
import com.company.oop.taskmanagement.models.tasks.contracts.Feedback;
import com.company.oop.taskmanagement.utilities.ParsingHelpers;
import com.company.oop.taskmanagement.utilities.Validation;

import java.util.List;

public class ChangeRatingCommand extends BaseCommand {
    /**
     * changes the rating of feedback
     * input: /chacngrating id newRating
     *
     * @param taskRepository
     */

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private static final String INVALID_ID_INPUT = "Invalid input for id, must be a number.";
    private static final String INVALID_TASK_TYPE = "Only feedback has rating";
    private static final String INVALID_RATING_INPUT = "The rating can be between 1..100";
    private static final String RATING_CHANGED = "Rating has been successfully changed.";


    protected ChangeRatingCommand(TaskManagementRepository taskRepository) {
        super(taskRepository);
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        Validation.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        Feedback feedback;
        int id = ParsingHelpers.tryParseInt(parameters.get(0), INVALID_ID_INPUT);

        try {
            feedback = (Feedback) getTaskRepository().findTaskById(id);
        } catch (ClassCastException e) {
            throw new IllegalArgumentException(INVALID_TASK_TYPE);
        }
        int rating = ParsingHelpers.tryParseInt(parameters.get(1), INVALID_RATING_INPUT);
        feedback.changeRating(rating);

        return RATING_CHANGED;
    }
}
