package com.company.oop.taskamanagement.commands;

import com.company.oop.taskamanagement.utils.TestUtilities;
import com.company.oop.taskmanagement.commands.LoginCommand;
import com.company.oop.taskmanagement.core.TaskManagementRepositoryImpl;
import com.company.oop.taskmanagement.core.contracts.TaskManagementRepository;
import com.company.oop.taskmanagement.models.contracts.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LoginCommandTests {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private TaskManagementRepository repository;
    private LoginCommand loginCommand;

    @BeforeEach
    public void before() {
        repository = new TaskManagementRepositoryImpl();
        loginCommand = new LoginCommand(repository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> loginCommand.execute(params));
    }

    @Test
    public void should_LoginUser_When_MemberNotLoggedIn() {
        // Arrange
        Member memberToLogIn = MemberImplTests.initializeTestMember();
        repository.addMember(memberToLogIn);
        List<String> params = List.of(memberToLogIn.getUsername(), memberToLogIn.getPassword());

        // Act
        loginCommand.execute(params);

        // Assert
        Assertions.assertEquals(memberToLogIn.getUsername(), repository.getLoggedInMember().getUsername());
    }

    @Test
    public void should_Throw_When_PasswordIsWrong() {
        // Arrange
        Member memberToLogIn = MemberImplTests.initializeTestMember();
        repository.addMember(memberToLogIn);
        List<String> params = List.of(memberToLogIn.getUsername(), "WRONG PASSWORD");

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> loginCommand.execute(params));
    }

    @Test
    public void should_Throw_When_MemberDoesNotExists() {
        // Arrange
        List<String> params = List.of(
                MemberImplTests.VALID_USERNAME,
                MemberImplTests.VALID_PASSWORD);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> loginCommand.execute(params));
    }

    @Test
    public void should_Throw_When_MemberAlreadyLoggedIn() {
        // Arrange
        Member memberToLogIn = loginInitializedMemberToRepository(repository);
        List<String> params = List.of(memberToLogIn.getUsername(), memberToLogIn.getPassword());

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> loginCommand.execute(params));
    }


    @Test
    public void should_Login_When_InputIsValid() {
        // Arrange
        Member memberToLogIn = MemberImplTests.initializeTestMember();
        repository.addMember(memberToLogIn);
        List<String> params = List.of(
                memberToLogIn.getUsername(),
                memberToLogIn.getPassword());

        // Act
        loginCommand.executeCommand(params);

        // Assert
        Assertions.assertEquals(repository.getLoggedInMember().getUsername(), memberToLogIn.getUsername());
    }

    public static Member loginInitializedUserToRepository(TaskManagementRepository repository) {
        Member testMember = MemberImplTests.initializeTestMember();
        repository.addMember(testMember);
        repository.login(testMember);
        return testMember;
    }
}
