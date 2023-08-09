package com.company.oop.taskmanagement.models.tasks.contracts;

import com.company.oop.taskmanagement.models.contracts.ActivityHistory;
import com.company.oop.taskmanagement.models.contracts.Comment;
import com.company.oop.taskmanagement.models.contracts.Status;
import com.company.oop.taskmanagement.models.enums.TaskType;

import java.util.List;

public interface Task {

    int getId();

    String getTitle();

    String getDescription();

    Status getStatus();

    List<Comment> getComments();

    void addComment(Comment commentToAdd);

    void removeComment(Comment commentToRemove);

    List<ActivityHistory> getHistoryChanges();

    void progressStatus();
    TaskType getTaskType();

    void revertStatus();

    String toString();

}
