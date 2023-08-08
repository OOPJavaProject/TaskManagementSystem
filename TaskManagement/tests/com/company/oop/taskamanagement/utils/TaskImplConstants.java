package com.company.oop.taskamanagement.utils;

import com.company.oop.taskmanagement.models.MemberImpl;
import com.company.oop.taskmanagement.models.contracts.Member;
import com.company.oop.taskmanagement.models.enums.Priority;
import com.company.oop.taskmanagement.models.enums.Severity;
import com.company.oop.taskmanagement.models.enums.StorySize;

public class TaskImplConstants {

    public static final int VALID_ID = 1;

    public static final String VALID_TITLE = "Valid Title";
    public static final String VALID_DESCRIPTION = "Valid Description";

    public static final Priority VALID_PRIORITY = Priority.HIGH;

    public static final Severity VALID_SEVERITY = Severity.MAJOR;

    public static final Member VALID_ASSIGNEE = new MemberImpl("TestMember", "testmember123", "123456");

    public static final StorySize VALID_STORYSIZE = StorySize.LARGE;

    public static final int VALID_RATING = 5;

}
