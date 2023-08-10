package com.company.oop.taskmanagement.commands.TaskChangesCommands;

import com.company.oop.taskmanagement.commands.BaseCommand;
import com.company.oop.taskmanagement.core.contracts.TaskManagementRepository;
import com.company.oop.taskmanagement.models.enums.Priority;
import com.company.oop.taskmanagement.models.enums.StorySize;
import com.company.oop.taskmanagement.models.tasks.contracts.PrioritableTask;
import com.company.oop.taskmanagement.models.tasks.contracts.Story;
import com.company.oop.taskmanagement.utilities.ParsingHelpers;
import com.company.oop.taskmanagement.utilities.Validation;

import java.util.List;

public class ChangeStorySizeCommand extends BaseCommand {
    /**
     * Changes the size of a story to input.
     * input: /changeStorySize storyId  size
     */

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private static final String INVALID_ID_INPUT = "Invalid input for id, must be a number.";
    private static final String INVALID_TASK_TYPE = "Only feedback has rating";
    private static final String INVALID_RATING_INPUT = "The rating can be between 1..100";
    private static final String STORY_SIZE_CHANGED = "Rating has been successfully changed.";

    protected ChangeStorySizeCommand(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected boolean requiresLogin() {
        return false;
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        Validation.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        int id = ParsingHelpers.tryParseInt(parameters.get(0), INVALID_ID_INPUT);

        Story story;
        try {
            story = (Story) getTaskRepository().findTaskById(id);
        } catch (ClassCastException e) {
            throw new IllegalArgumentException(INVALID_TASK_TYPE);
        }
        StorySize size = ParsingHelpers.tryParseEnum(parameters.get(1), StorySize.class);

        story.changeSize(size);

        return STORY_SIZE_CHANGED;
    }
}
