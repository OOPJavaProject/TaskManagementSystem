package com.company.oop.taskmanagement.models.contracts;

import com.company.oop.taskmanagement.models.tasks.contracts.Task;

import java.util.List;

public interface Member {
    String getName();

    List<Task> getTasks();
    void removeComment(Comment comment, Task taskToRemoveComment);
    void addComment(Comment comment, Task taskToAddComment);

    String printTasks();
    boolean isLoggedIn();

    List<ActivityHistory> getActivityHistory(); //TODO

    String toString();
}
