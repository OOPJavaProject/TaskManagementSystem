package com.company.oop.taskmanagement.models.tasks.contracts;

public interface Feedback extends Task{
    int getRating();

    void changeRating(int rating);
}
