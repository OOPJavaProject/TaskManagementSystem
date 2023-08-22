package com.company.oop.taskamanagement.models.member;

import com.company.oop.taskmanagement.models.MemberImpl;
import com.company.oop.taskmanagement.models.contracts.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MemberImplTest {

    public static final String VALID_NAME = "validName";
    public static final String VALID_USERNAME = "validUsername";
    public static final String VALID_PASSWORD = "Password123";

    public static final MemberImpl VALID_MEMBER = new MemberImpl(VALID_NAME, VALID_USERNAME, VALID_PASSWORD);

    @Test
    public void constructor_ShouldThrowException_WhenNameIsInvalid() {

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new MemberImpl(
                        "",
                        VALID_USERNAME,
                        VALID_PASSWORD
                ));
    }

    @Test
    public void constructor_ShouldThrowException_WhenPasswordIsInvalid() {

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new MemberImpl(
                        VALID_NAME,
                        VALID_USERNAME,
                        "11xx"
                ));
    }

    @Test
    public void constructor_ShouldThrowException_WhenUsernameIsInvalid() {

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new MemberImpl(
                        VALID_NAME,
                        "xx",
                        VALID_PASSWORD
                ));
    }

    @Test
    public void should_CreateMember_When_ValidValuesArePassed() {

        Member member = new MemberImpl(
                VALID_NAME,
                VALID_USERNAME,
                VALID_PASSWORD
        );
        assertAll(
                () -> assertEquals(VALID_NAME, member.getName()),
                () -> assertEquals(VALID_USERNAME, member.getUsername()),
                () -> assertEquals(VALID_PASSWORD, member.getPassword())
        );
    }

    //TODO addTask, removeTask, getActivityHistory, log(added/removed)FromTeam, equals, logIn, logOut

    public static Member initializeMember(){
        return new MemberImpl(
                VALID_NAME,
                VALID_USERNAME,
                VALID_PASSWORD
        );
    }


}
