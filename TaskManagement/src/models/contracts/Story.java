package models.contracts;

import models.enums.Priority;
import models.enums.StorySize;
import models.enums.TaskStatus.StoryStatus;

import java.lang.reflect.Member;

public interface Story extends Task{

    Priority getPriority();

    StorySize getSize();

    Member getAssignee();

    StoryStatus getStatus();
}
