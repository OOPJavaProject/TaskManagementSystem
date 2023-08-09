package com.company.oop.taskmanagement.models.tasks.contracts;

import com.company.oop.taskmanagement.models.contracts.Member;
import com.company.oop.taskmanagement.models.contracts.Status;
import com.company.oop.taskmanagement.models.enums.Priority;

public interface PrioritableTask extends Task{
    Priority getPriority();

    Member getAssignee();

    void changePriority(Priority priority);

    void changeAssignee(Member member);



}
