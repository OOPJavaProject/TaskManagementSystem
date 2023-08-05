package com.company.oop.taskamanagement.models.tasks;

import com.company.oop.taskmanagement.models.contracts.Member;
import com.company.oop.taskmanagement.models.enums.Priority;
import com.company.oop.taskmanagement.models.enums.Severity;
import com.company.oop.taskmanagement.models.tasks.BugImpl;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.TestExecutionResult;

public class BugImplTests {

    public static final int VALID_ID = 1;
    public static final String VALID_TITLE = "Test name for bug";
    public static final String VALID_DESCRIPTION = "Test description for bug";
//    public static final Member VALID_ASSIGNEE = "Misho";

    @Test
    public void constructor_Should_InitializeName_When_ArgumentsValid() {
        //TODO I NEED TO IMPLEMENT MEMBER CLASS IN ORDER TO WRITE THE TESTS
//        BugImpl bug = new BugImpl(VALID_ID,VALID_TITLE, VALID_DESCRIPTION, Priority.HIGH, Severity.MAJOR, VALID_ASSIGNEE);
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
    public void setAssignee_Should_AssignMemberToTask_When_ValidArguments() {

    }

    @Test
    public void getSteps_Should_ReturnNewCollection_When_Invoked() {

    }

    @Test
    public void changeStatus_Should_changeCurrentStatus_When_ValidArguments() {

    }
}
