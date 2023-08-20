package com.company.oop.taskmanagement.commands.CreateCommands;

import com.company.oop.taskmanagement.commands.BaseCommand;
import com.company.oop.taskmanagement.core.contracts.TaskManagementRepository;
import com.company.oop.taskmanagement.models.contracts.Board;
import com.company.oop.taskmanagement.models.contracts.Member;
import com.company.oop.taskmanagement.models.enums.Priority;
import com.company.oop.taskmanagement.models.enums.StorySize;
import com.company.oop.taskmanagement.models.tasks.contracts.Story;
import com.company.oop.taskmanagement.utilities.ParsingHelpers;
import com.company.oop.taskmanagement.utilities.Validation;

import java.util.List;

public class CreateStoryCommand extends BaseCommand {

    /**
     * creating a task of type Story
     * input: CreateStory title description priority storySize assignee
     */

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 6;
    public static final String STORY_CREATED = "Story with title %s was created in board %s!";


    public CreateStoryCommand(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        Validation.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        String title = parameters.get(0);
        String description = parameters.get(1);
        Priority priority = ParsingHelpers.tryParseEnum(parameters.get(2), Priority.class);
        StorySize storySize = ParsingHelpers.tryParseEnum(parameters.get(3), StorySize.class);
        Member assignee = getTaskRepository().findMemberByName(parameters.get(4));
        Board board = getTaskRepository().findBoardByName(parameters.get(5));

        Story createdStory = getTaskRepository().createStory(title, description, priority, storySize, assignee);
        board.addTask(createdStory);
        return String.format(STORY_CREATED, title, board.getName());
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }
}
