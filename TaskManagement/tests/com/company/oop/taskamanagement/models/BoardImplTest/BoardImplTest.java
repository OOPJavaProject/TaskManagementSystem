package com.company.oop.taskamanagement.models.BoardImplTest;

import com.company.oop.taskamanagement.models.tasks.BugImplTests;
import com.company.oop.taskamanagement.models.tasks.FeedbackImplTests;
import com.company.oop.taskamanagement.models.tasks.StoryImplTests;
import com.company.oop.taskmanagement.models.BoardImpl;
import com.company.oop.taskmanagement.models.contracts.Board;
import com.company.oop.taskmanagement.models.tasks.contracts.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class BoardImplTest {


    public static final String BOARD_NAME = "BoardName";
    @Test
    public void constructor_ShouldThrow_WhenNameIsInvalid() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new BoardImpl(
                        "zz"
                )
        );
    }
    @Test
    public void should_CreateBoard_When_NameIsValid(){
        Board board = new BoardImpl(BOARD_NAME);

        Assertions.assertEquals(BOARD_NAME, board.getName());
    }
    @Test
    public void should_AddTask_ToBoard(){
        Board board = initializeBoard();
        Task bug = BugImplTests.initializeTestBug();

        board.addTask(bug);

        Assertions.assertEquals(1, board.getTasks().size());
    }
    @Test
    public void should_RemoveTask_FromBoard(){
        Board board = initializeBoard();
        Task bug = BugImplTests.initializeTestBug();

        board.addTask(bug);
        board.removeTask(bug);

        Assertions.assertEquals(0, board.getTasks().size());
    }

    //TODO logTesting?
    @Test
    public void should_LogEvents_Correctly(){
        Board board = initializeBoard();//1

        Task bug = BugImplTests.initializeTestBug();
        Task feedback = FeedbackImplTests.initializeTestFeedback();
        Task story = StoryImplTests.initializeTestStory();

        List<Task> list = new ArrayList<>();
        list.add(bug);//2
        list.add(feedback);//3
        list.add(story);//4

        board.insertTasks(list);//2,3,4 <-
        board.removeTask(bug);//5

        Assertions.assertEquals(5, board.getActivityHistory().size());

    }

    public static Board initializeBoard(){
        return new BoardImpl(BOARD_NAME);

    }

}
