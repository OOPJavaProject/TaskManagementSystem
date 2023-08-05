package com.company.oop.taskmanagement.models.tasks.contracts;

import com.company.oop.taskmanagement.models.contracts.Member;
import com.company.oop.taskmanagement.models.enums.Severity;
import com.company.oop.taskmanagement.models.enums.TaskStatus.BugStatus;
import com.company.oop.taskmanagement.models.enums.Priority;

import java.util.List;

public interface Bug extends Task{
    List<String> getSteps();

    Priority getPriority();

    Severity getSeverity();

    Member getAssignee();

}