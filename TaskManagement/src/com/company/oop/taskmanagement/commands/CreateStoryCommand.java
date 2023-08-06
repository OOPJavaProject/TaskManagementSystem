package com.company.oop.taskmanagement.commands;

import com.company.oop.taskmanagement.core.contracts.TaskRepository;
import com.company.oop.taskmanagement.models.contracts.Member;
import com.company.oop.taskmanagement.models.contracts.Team;
import com.company.oop.taskmanagement.models.enums.Priority;
import com.company.oop.taskmanagement.models.enums.StorySize;
import com.company.oop.taskmanagement.models.tasks.contracts.Story;
import com.company.oop.taskmanagement.utilities.ParsingHelpers;
import com.company.oop.taskmanagement.utilities.Validation;

import java.util.List;

public class CreateStoryCommand extends BaseCommand{

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 5;
    public static final String STORY_CREATED = "Story with title %s was created!";


    public CreateStoryCommand(TaskRepository taskRepository) {
        super(taskRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        Validation.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        String title = parameters.get(0);
        String description = parameters.get(1);
        Priority priority = ParsingHelpers.tryParseEnum(parameters.get(2), Priority.class);
        StorySize storySize = ParsingHelpers.tryParseEnum(parameters.get(3), StorySize.class);
        Member assignee = getTaskRepository().findMemberByName(parameters.get(4));

        Story createdStory = getTaskRepository().createStory(title, description, priority, storySize, assignee);

        return String.format(STORY_CREATED, title);
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }
}
