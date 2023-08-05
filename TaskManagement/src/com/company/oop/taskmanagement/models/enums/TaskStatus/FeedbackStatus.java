package com.company.oop.taskmanagement.models.enums.TaskStatus;

import com.company.oop.taskmanagement.models.contracts.Status;

public enum FeedbackStatus implements Status {
    NEW,
    UNSCHEDULED,
    SCHEDULED,
    DONE;

    @Override
    public String toString() {
        switch (this) {
            case NEW:
                return "New";

            case UNSCHEDULED:
                return "Unscheduled";
            case SCHEDULED:
                return "Scheduled";
            case DONE:
                return "Done";
            default:
                return "";
        }
    }
}
