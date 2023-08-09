package com.company.oop.taskamanagement.commands;

import com.company.oop.taskmanagement.commands.LogoutCommand;
import com.company.oop.taskmanagement.core.TaskManagementRepositoryImpl;
import com.company.oop.taskmanagement.core.contracts.TaskManagementRepository;
import com.company.oop.taskmanagement.models.contracts.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class LogoutCommandTests {

    private TaskManagementRepository repository;
    private LogoutCommand logoutCommand;

    @BeforeEach
    public void before() {
        repository = new TaskManagementRepositoryImpl();
        logoutCommand = new LogoutCommand(repository);
    }

    @Test
    public void should_Throw_When_MemberNotLoggedIn() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> logoutCommand.execute(new ArrayList<>()));
    }

    @Test
    public void should_LogoutUser() {
        // Arrange
        Member memberToLogIn = MemberImplTests.initializeTestUser();
        repository.login(memberToLogIn);

        // Act
        logoutCommand.execute(new ArrayList<>());

        // Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> repository.getLoggedInMember());
    }
}
