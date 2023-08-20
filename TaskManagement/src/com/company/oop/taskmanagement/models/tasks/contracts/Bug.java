package com.company.oop.taskmanagement.models.tasks.contracts;

import com.company.oop.taskmanagement.models.enums.Severity;

import java.util.List;

public interface Bug extends PrioritizableTask {
    List<String> getSteps();

    Severity getSeverity();

}
