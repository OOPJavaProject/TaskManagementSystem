package com.company.oop.taskmanagement.models.contracts;

import com.company.oop.taskmanagement.models.tasks.contracts.Task;

import java.util.List;

public interface Board {

    String getName();

    List<Task> getTasks();

    List<ActivityHistory> getActivityHistory(); //TODO

    String toString();
}
