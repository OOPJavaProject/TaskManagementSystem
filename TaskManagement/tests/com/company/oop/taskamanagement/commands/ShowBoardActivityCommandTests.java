package com.company.oop.taskamanagement.commands;

import com.company.oop.taskmanagement.commands.ShowCommands.ShowActivityComands.ShowBoardActivityCommand;
import com.company.oop.taskmanagement.core.TaskManagementRepositoryImpl;
import com.company.oop.taskmanagement.core.contracts.TaskManagementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ShowBoardActivityCommandTests {

    private TaskManagementRepository taskManagementRepository;
    private ShowBoardActivityCommand command;

    @BeforeEach
    public void before() {
        taskManagementRepository = new TaskManagementRepositoryImpl();
        command = new ShowBoardActivityCommand(taskManagementRepository);
    }

    @Test
    public void executeCommand_Should_ThrowException_When_NonExistingTeam() {

    }

    @Test
    public void executeCommand_Should_ThrowException_When_NonExistingBoard() {

    }

    @Test
    public void executeCommand_Should_ThrowException_When_NonExistingBoardInTeam() {

    }

    @Test
    public void executeCommand_Should_ShowHistory_When_ValidTeam() {

    }

    @Test
    public void executeCommand_Should_ShowHistory_When_ValidBoard() {

    }

    @Test
    public void executeCommand_Should_ThrowException_When_NoMemberLogged() {

    }

    @Test
    public void executeCommand_Should_ShowHistory_When_MemberLoggedIn() {

    }
}

