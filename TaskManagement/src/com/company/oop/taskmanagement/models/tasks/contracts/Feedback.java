package com.company.oop.taskmanagement.models.tasks.contracts;

import com.company.oop.taskmanagement.models.enums.TaskStatus.FeedbackStatus;
import com.company.oop.taskmanagement.models.tasks.contracts.Task;

public interface Feedback extends Task{
    int getRating();
}
