package com.company.oop.taskmanagement.models.contracts;

import com.company.oop.taskmanagement.models.tasks.contracts.Task;

import java.util.List;

public interface Member {
    String getName();
    String getUsername();
    String getPassword();
    List<Task> getTasks();
    void addTask(Task task);
    void removeTask(Task task);
    void removeComment(Comment comment, Task taskToRemoveComment);
    void addComment(Comment comment, Task taskToAddComment);
    String printTasks();
    boolean isLoggedIn();
    void logAddedToTeam(Team team);
    void logRemovedFromTeam(Team team);
    List<ActivityHistory> getActivityHistory(); //TODO
    String toString();

}
