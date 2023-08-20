package com.company.oop.taskamanagement.commands;

import com.company.oop.taskmanagement.core.TaskManagementRepositoryImpl;
import com.company.oop.taskmanagement.core.contracts.TaskManagementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CreateBoardCommandTests {

    private TaskManagementRepository taskManagementRepository;
    private CreateBoardCommand command;

    @BeforeEach
    public void before() {
        taskManagementRepository = new TaskManagementRepositoryImpl();
        command = new CreateBoardCommand(taskManagementRepository);
    }

    @Test
    public void executeCommand_Should_CreateBoard_When_ValidArgumentsCount() {

    }

    @Test
    public void executeCommand_Should_ThrowException_When_InvalidArgumentsCount() {

    }

    @Test
    public void executeCommand_Should_CreateBoard_When_ExistingTeamName() {

    }

    @Test
    public void executeCommand_Should_CreateBoard_When_UniqueInTeam() {

    }

    @Test
    public void executeCommand_Should_ThrowException_When_BoardExistsInTeam() {

    }

    @Test
    public void executeCommand_Should_ThrowException_When_NonExistingTeam() {

    }

    @Test
    public void executeCommand_Should_CreateBoard_When_MemberLoggedIn() {

    }

    @Test
    public void executeCommand_Should_ThrowException_When_NoMemberLogged() {

    }


}
