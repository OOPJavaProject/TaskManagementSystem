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
    public void constructor_Should_InitializeComments_When_ArgumentsValid() {

    }

    @Test
    public void constructor_Should_InitializeHistory_When_ArgumentsValid() {

    }

    @Test
    public void constructor_Should_InitializeStatus_When_ArgumentsValid() {

    }

    @Test
    public void constructor_Should_InitializeTaskType_When_ArgumentsValid() {

    }

    @Test
    public void constructor_Should_LogEvent_When_ArgumentsValid() {

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

    @Test
    public void getComments_Should_ReturnCopyOfNewCollection_When_Invoked() {

    }

    @Test
    public void getHistoryChanges_Should_ReturnNewCollection_When_Invoked() {

    }

    @Test
    public void progressStatus_Should_assignTheNextStatus_When_Invoked() {

    }

    @Test
    public void progressStatus_Should_ThrowException_When_StatusCannotBeProgressedFurther() {

    }

    @Test
    public void revertStatus_Should_assignThePreviousStatus_When_Invoked() {

    }

    @Test
    public void revertStatus_Should_ThrowException_When_StatusCannotBeRevertedFurther() {

    }

    @Test
    public void addComment_Should_addCommentToTask() {

    }

    @Test
    public void removeComment_Should_RemoveCommentFromTask_When_Comment_Exists() {

    }

    @Test
    public void removeComment_Should_ThrowException_When_CommentDoesNotExist() {

    }

    @Test
    public void logEvent_Should_CreateNewEvent_When_Invoked() {

    }

    public static FeedbackImpl initializeTestFeedback() {
        return new FeedbackImpl(
                TaskImplConstants.VALID_ID,
                TaskImplConstants.VALID_TITLE,
                TaskImplConstants.VALID_DESCRIPTION,
                TaskImplConstants.VALID_RATING);
    }
}
