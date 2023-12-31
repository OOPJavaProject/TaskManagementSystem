package com.company.oop.taskmanagement.models.enums.TaskStatus;

import com.company.oop.taskmanagement.models.contracts.Status;

public enum StoryStatus implements Status {
    NOT_DONE,
    IN_PROGRESS,
    DONE;
    @Override
    public String toString(){
        switch (this){
            case NOT_DONE:
                return "Not done";
            case IN_PROGRESS:
                return "In progress";
            case DONE:
                return "Done";
            default:
                return "";
        }
    }

}
