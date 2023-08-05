package models.contracts;

import models.enums.Priority;
import models.enums.TaskStatus.BugStatus;

import javax.print.attribute.standard.Severity;
import java.lang.reflect.Member;
import java.util.List;

public interface Bug extends Task{
    List<String> getSteps();

    Priority getPriority();

    Severity getSeverity();

    Member getAssignee();

    BugStatus getStatus();
}
