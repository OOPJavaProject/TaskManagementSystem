package com.company.oop.taskamanagement.models.tasks;

import com.company.oop.taskamanagement.utils.TaskImplConstants;
import com.company.oop.taskmanagement.models.tasks.FeedbackImpl;
import org.junit.jupiter.api.Test;

public class FeedbackImplTests {

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
    public void constructor_Should_InitializeRating_When_ValidArguments() {

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
    public void constructor_Should_ThrowException_When_InvalidRating() {

    }

    public static FeedbackImpl initializeTestFeedback() {
        return new FeedbackImpl(
                TaskImplConstants.VALID_ID,
                TaskImplConstants.VALID_TITLE,
                TaskImplConstants.VALID_DESCRIPTION,
                TaskImplConstants.VALID_RATING);
    }
}
