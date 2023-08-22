package com.company.oop.taskamanagement.commands.CreateCommands;

import com.company.oop.taskamanagement.models.TeamImplTest.TeamImplTest;
import com.company.oop.taskamanagement.models.member.MemberImplTest;
import com.company.oop.taskmanagement.commands.CreateCommands.CreateBoardCommand;
import com.company.oop.taskmanagement.core.TaskManagementRepositoryImpl;
import com.company.oop.taskmanagement.core.contracts.TaskManagementRepository;
import com.company.oop.taskmanagement.models.contracts.Member;
import com.company.oop.taskmanagement.models.contracts.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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

        Member member = MemberImplTest.initializeMember();
        taskManagementRepository.login(member);
        Team team = TeamImplTest.initializeTeam();
        TeamImplTest.createTeam();
        command.execute(List.of("Board", team.getName()));

        Assertions.assertEquals(1, taskManagementRepository.getTeams().get(0).getBoards().size());
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
