package com.company.oop.taskmanagement.models.tasks;

import com.company.oop.taskmanagement.models.enums.TaskStatus.FeedbackStatus;
import com.company.oop.taskmanagement.models.tasks.contracts.Feedback;

public class FeedbackImpl extends TaskImpl implements Feedback {
    private int rating;

    public FeedbackImpl(int id , String title, String description, int rating) {
        super(id, title, description, FeedbackStatus.NEW);
        setRating(rating);
    }

    private void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public int getRating() {
        return rating;
    }
}
