package com.company.oop.taskamanagement.models.tasks;

import com.company.oop.taskamanagement.utils.TaskImplConstants;
import com.company.oop.taskmanagement.models.tasks.BugImpl;
import com.company.oop.taskmanagement.models.tasks.StoryImpl;
import org.junit.jupiter.api.Test;

public class StoryImplTests {

    @Test
    public void constructor_Should_InitializeId_When_ValidArguments() {

    }
    @Test
    public void constructor_Should_InitializeTitle_When_ValidArguments() {

    }
    @Test
    public void constructor_Should_InitializeDescription_When_ValidArguments() {

    }
    @Test
    public void constructor_Should_InitializePriority_When_ValidArguments() {

    }
    @Test
    public void constructor_Should_InitializeStorySize_When_ValidArguments() {

    }
    @Test
    public void constructor_Should_InitializeMember_When_ValidArguments() {

    }
    @Test
    public void constructor_Should_ThrowException_When_InvalidId() {

    }
    @Test
    public void constructor_Should_ThrowException_When_InvalidTitle() {

    }
    @Test
    public void constructor_Should_ThrowException_When_InvalidDescription() {

    }
    @Test
    public void constructor_Should_ThrowException_When_MemberDoesNotExistInTeam() {

    }

    public static StoryImpl initializeTestStory() {
        return new StoryImpl(
                TaskImplConstants.VALID_ID,
                TaskImplConstants.VALID_TITLE,
                TaskImplConstants.VALID_DESCRIPTION,
                TaskImplConstants.VALID_PRIORITY,
                TaskImplConstants.VALID_STORYSIZE,
                TaskImplConstants.VALID_ASSIGNEE);
    }
}
