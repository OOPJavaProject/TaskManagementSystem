package com.company.oop.taskamanagement.commands;

import com.company.oop.taskmanagement.commands.CreateBugCommand;
import com.company.oop.taskmanagement.core.TaskManagementRepositoryImpl;
import com.company.oop.taskmanagement.core.contracts.TaskManagementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CreateBugCommandTests{


    private TaskManagementRepository taskManagementRepository;
    private CreateBugCommand command;

    @BeforeEach
    public void before() {
        taskManagementRepository = new TaskManagementRepositoryImpl();
        command = new CreateBugCommand(taskManagementRepository);
    }

    @Test
    public void executeCommand_Should_ThrowException_When_InvalidArgumentsCount() {

    }

    @Test
    public void executeCommand_Should_CreateBug_When_ValidArgumentsCount() {

    }

    @Test
    public void executeCommand_Should_ThrowException_When_AssigneeDoesNotExist() {

    }

    @Test
    public void executeCommand_Should_CreateBug_When_AssigneeExists() {

    }

    @Test
    public void executeCommand_Should_CreateBug_When_ValidArguments() {

    }
}
