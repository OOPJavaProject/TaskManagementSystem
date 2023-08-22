package com.company.oop.taskamanagement.models.tasks;

import com.company.oop.taskamanagement.utils.TaskImplConstants;
import com.company.oop.taskmanagement.models.contracts.Member;
import com.company.oop.taskmanagement.models.enums.Priority;
import com.company.oop.taskmanagement.models.enums.Severity;
import com.company.oop.taskmanagement.models.tasks.BugImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.TestExecutionResult;

public class BugImplTests {

    //Think about before each

    @Test
    public void constructor_Should_InitializeComments_When_ArgumentsValid() {
    }

    @Test
    public void constructor_Should_InitializeHistory_When_ArgumentsValid() {


    }

    @Test
    public void constructor_Should_InitializeTaskType_When_ArgumentsValid() {

    }

    @Test
    public void constructor_Should_LogEvent_When_ArgumentsValid() {

    }

    @Test
    public void constructor_Should_InitializeSeverity_WhenArgumentsAreValid() {

    }

    @Test
    public void constructor_Should_InitializeId_When_ArgumentsValid() {

    }

    @Test
    public void constructor_Should_InitializeTitle_When_ArgumentsValid() {

    }

    @Test
    public void constructor_Should_InitializeDescription_When_ArgumentsValid() {

    }

    @Test
    public void constructor_Should_InitializePriority_When_ArgumentsValid() {

    }

    @Test
    public void constructor_Should_InitializeAssignee_When_ArgumentsValid() {

    }

    @Test
    public void constructor_Should_InitializeSteps_When_ArgumentsValid() {

    }

    @Test
    public void constructor_Should_InitializeStatus_When_ArgumentsValid() {

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
    public void setAssignee_Should_ThrowException_When_MemberDoesNotExistInTeam() {

    }

    @Test
    public void getSteps_Should_ReturnNewCollection_When_Invoked() {

    }

    @Test
    public void getComments_Should_ReturnNewCollection_When_Invoked() {

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

    public static BugImpl initializeTestBug() {
        return new BugImpl(
                TaskImplConstants.VALID_ID,
                TaskImplConstants.VALID_TITLE,
                TaskImplConstants.VALID_DESCRIPTION,
                TaskImplConstants.VALID_PRIORITY,
                TaskImplConstants.VALID_SEVERITY,
                TaskImplConstants.VALID_ASSIGNEE);
    }
}
