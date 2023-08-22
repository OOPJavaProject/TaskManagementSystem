package com.company.oop.taskamanagement.models.TeamImplTest;

import com.company.oop.taskamanagement.models.member.MemberImplTest;
import com.company.oop.taskmanagement.models.MemberImpl;
import com.company.oop.taskmanagement.models.TeamImpl;
import com.company.oop.taskmanagement.models.contracts.Member;
import com.company.oop.taskmanagement.models.contracts.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TeamImplTest {
    public static final String VALID_TEAM_NAME = "ValidName";

    @Test
    public void constructor_ShouldThrow_WhenNameIsInvalid(){
        Assertions.assertThrows(IllegalArgumentException.class,
                ()-> new TeamImpl(
                        "zz"
                )
        );
    }
    @Test
    public void should_CreateTeam_WhenNameIsValid(){
        Team team = initializeTeam();
        Assertions.assertEquals(VALID_TEAM_NAME, team.getName());
    }
    @Test
    public void should_AddMember_ToTeam(){
        Team team = initializeTeam();
        Member member = MemberImplTest.initializeMember();

        team.addMember(member);

        Assertions.assertEquals(1, team.getMembers().size());
    }
    @Test
    public void should_RemoveMember_FromTeam(){
        Team team = initializeTeam();
        Member member = MemberImplTest.initializeMember();

        team.addMember(member);
        team.removeMember(member);

        Assertions.assertEquals(0, team.getMembers().size());
    }
    //TODO (add/remove)Board, ?createBoard?, getHistory


    public static Team initializeTeam(){
        return new TeamImpl(
          VALID_TEAM_NAME
        );
    }
}
