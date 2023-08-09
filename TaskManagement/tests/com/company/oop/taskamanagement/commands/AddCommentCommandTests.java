package com.company.oop.taskamanagement.commands;

import com.company.oop.taskamanagement.models.tasks.BugImplTests;
import com.company.oop.taskamanagement.utils.TestUtilities;
import com.company.oop.taskmanagement.commands.AddCommentCommand;
import com.company.oop.taskmanagement.commands.contracts.Command;
import com.company.oop.taskmanagement.core.TaskManagementRepositoryImpl;
import com.company.oop.taskmanagement.core.contracts.TaskManagementRepository;
import com.company.oop.taskmanagement.models.contracts.Comment;
import com.company.oop.taskmanagement.models.contracts.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AddCommentCommandTests {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;

    private TaskManagementRepository repository;
    private Command addCommentsCommand;
    private Member member;

    @BeforeEach
    public void before() {
        repository = new TaskManagementRepositoryImpl();
        addCommentsCommand = new AddCommentCommand(repository);
        member = LoginCommandTests.loginInitializedUserToRepository(repository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> addCommentsCommand.execute(params));
    }

    @Test
    public void should_ThrowException_When_TaskIndexIsNotNumber() {
        // Arrange
        List<String> params = List.of(
                CommentImplTests.VALID_CONTENT,
                member.getUsername(),
                "INVALID_INDEX");

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> addCommentsCommand.execute(params));
    }

    @Test
    public void should_ThrowException_When_TaskDoesNotExist() {
        // Arrange
        List<String> params = List.of(
                CommentImplTests.VALID_CONTENT,
                member.getUsername(),
                "1");

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> addCommentsCommand.execute(params));
    }

    @Test
    public void should_Create_When_InputIsValid() {
        // Arrange
        member.addTask(BugImplTests.initializeTestBug());

        List<String> params = List.of(
                CommentImplTests.VALID_CONTENT,
                member.getUsername(),
                "1");

        // Act
        addCommentsCommand.execute(params);

        //Assert
        Comment comment = repository.getLoggedInMember().getTasks().get(0).getComments().get(0);
        Assertions.assertEquals(CommentImplTests.VALID_CONTENT, comment.getContent());
        Assertions.assertEquals(member.getUsername(), comment.getAuthor());
    }
}
