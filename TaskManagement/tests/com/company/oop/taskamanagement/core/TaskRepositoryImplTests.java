package com.company.oop.taskamanagement.core;

import com.company.oop.taskmanagement.core.TaskManagementRepositoryImpl;
import com.company.oop.taskmanagement.core.contracts.TaskManagementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskRepositoryImplTests {

    private TaskManagementRepository repository;

    @BeforeEach
    public void create_Repository_BeforeEach() {
        repository = new TaskManagementRepositoryImpl();
    }

    @Test
    public void constructor_Should_InitializeTeams() {

    }

    @Test
    public void constructor_Should_InitializeMembers() {

    }

    @Test
    public void constructor_Should_InitializeTasks() {

    }

    @Test
    public void constructor_Should_InitializeBoards() {

    }

    @Test
    public void getTeams_Should_ReturnCopyOfCollection() {

    }

    @Test
    public void getMembers_Should_ReturnCopyOfCollection() {

    }

    @Test
    public void getTasks_Should_ReturnCopyOfCollection() {

    }

    @Test
    public void getBoards_Should_ReturnCopyOfCollection() {

    }

    @Test
    public void teamExists_Should_ReturnTrue_When_TeamExists() {

    }

    @Test
    public void memberExists_Should_ReturnTrue_When_MemberExists() {

    }

    @Test
    public void boardExists_Should_ReturnTrue_When_BoardExists() {

    }

    @Test
    public void teamExists_Should_ReturnFalse_When_TeamDoesNotExist() {

    }

    @Test
    public void memberExists_Should_ReturnFalse_When_MemberDoesNotExist() {

    }

    @Test
    public void boardExists_Should_ReturnFalse_When_BoardDoesNotExist() {

    }

    @Test
    public void createBug_Should_AddToTasks_When_ArgumentsAreValid() {

    }

    @Test
    public void createFeedback_Should_AddToTasks_When_ArgumentsAreValid() {

    }

    @Test
    public void createStory_Should_AddToTasks_When_ArgumentsAreValid() {

    }

    @Test
    public void createBoard_Should_AddToBoards_When_ArgumentsAreValid() {

    }

    @Test
    public void createTeam_Should_AddToTeams_When_ArgumentsAreValid() {

    }

    @Test
    public void createMember_Should_AddToMembers_When_ArgumentsAreValid() {

    }

    @Test
    public void findBoardByName_Should_ReturnBoard_When_BoardExists() {

    }

    @Test
    public void findTeamByName_Should_ReturnTeam_When_TeamExists() {

    }

    @Test
    public void findMemberByName_Should_ReturnMember_When_MemberExists() {

    }

    @Test
    public void findBoardByName_Should_ThrowException_When_BoardDoesNotExist() {

    }

    @Test
    public void findTeamByName_Should_ThrowException_When_TeamDoesNotExist() {

    }

    @Test
    public void findMemberByName_Should_ThrowException_When_MemberDoesNotExist() {

    }


}
