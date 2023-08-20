package com.company.oop.taskmanagement.models.tasks.contracts;

import com.company.oop.taskmanagement.models.contracts.Member;
import com.company.oop.taskmanagement.models.enums.Priority;
import com.company.oop.taskmanagement.models.enums.StorySize;


public interface Story extends PrioritizableTask {

    Priority getPriority();

    StorySize getSize();

    Member getAssignee();
}
