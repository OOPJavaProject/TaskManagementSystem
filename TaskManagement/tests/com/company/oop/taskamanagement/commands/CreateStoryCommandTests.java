package com.company.oop.taskamanagement.commands;

import com.company.oop.taskmanagement.commands.CreateBugCommand;
import com.company.oop.taskmanagement.commands.CreateStoryCommand;
import com.company.oop.taskmanagement.core.TaskManagementRepositoryImpl;
import com.company.oop.taskmanagement.core.contracts.TaskManagementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CreateStoryCommandTests {

    private TaskManagementRepository taskManagementRepository;
    private CreateStoryCommand command;

    @BeforeEach
    public void before() {
        taskManagementRepository = new TaskManagementRepositoryImpl();
        command = new CreateStoryCommand(taskManagementRepository);
    }

    @Test
    public void executeCommand_Should_CreateStory_When_ValidArguments() {

    }

    @Test
    public void executeCommand_Should_CreateStory_When_ValidArgumentsCount() {

    }

    @Test
    public void executeCommand_Should_CreateStory_When_AssigneeExists() {

    }

    @Test
    public void executeCommand_Should_ThrowException_When_AssigneeDoesNotExist() {

    }

    @Test
    public void executeCommand_Should_CreateStory_When_MemberLoggedIn() {

    }

    @Test
    public void executeCommand_Should_ThrowException_When_NoMemberLogged() {

    }

}
