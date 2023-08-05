package models.contracts;

import models.enums.TaskStatus.FeedbackStatus;

public interface Feedback extends Task{
    int getRating();

    FeedbackStatus getStatus();




}
