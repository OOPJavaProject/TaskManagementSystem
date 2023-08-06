package com.company.oop.taskmanagement.models.tasks;

import com.company.oop.taskmanagement.models.contracts.Comment;
import com.company.oop.taskmanagement.models.enums.TaskStatus.FeedbackStatus;
import com.company.oop.taskmanagement.models.tasks.contracts.Feedback;

public class FeedbackImpl extends TaskImpl implements Feedback {

    public static final String STATUS_CHANGE_ERROR_MESSAGE = "Feedback status is already at %s";

    private int rating;

    public FeedbackImpl(int id , String title, String description, int rating) {
        super(id, title, description, FeedbackStatus.NEW);
        setRating(rating);
    }

    @Override
    public int getRating() {
        return rating;
    }

    @Override
    public void progressStatus() {
        if (getStatus() == FeedbackStatus.NEW) {
            setStatus(FeedbackStatus.UNSCHEDULED);
        } else if (getStatus() == FeedbackStatus.UNSCHEDULED) {
            setStatus(FeedbackStatus.SCHEDULED);
        } else if (getStatus() == FeedbackStatus.SCHEDULED) {
            setStatus(FeedbackStatus.DONE);
        } else {
            throw new IllegalArgumentException(String.format(STATUS_CHANGE_ERROR_MESSAGE, getStatus()));
        }
    }

    @Override
    public void revertStatus() {
        if (getStatus() == FeedbackStatus.DONE) {
            setStatus(FeedbackStatus.SCHEDULED);
        } else if (getStatus() == FeedbackStatus.SCHEDULED) {
            setStatus(FeedbackStatus.UNSCHEDULED);
        } else if (getStatus() == FeedbackStatus.UNSCHEDULED) {
            setStatus(FeedbackStatus.NEW);
        } else {
            throw new IllegalArgumentException(String.format(STATUS_CHANGE_ERROR_MESSAGE, getStatus()));
        }
    }

    @Override
    public String toString() {
        return String.format("""
                --Feedback--
                %s
                Rating: %d
                Comments:
                %s
                History:
                %s
                """, super.toString(), getRating(), getComments(), getHistoryChanges());
    }

    private void setRating(int rating) {
        this.rating = rating;
    }
}
