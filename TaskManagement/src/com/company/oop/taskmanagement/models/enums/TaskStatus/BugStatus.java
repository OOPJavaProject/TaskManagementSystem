package com.company.oop.taskmanagement.models.enums.TaskStatus;

import com.company.oop.taskmanagement.models.contracts.Status;

public enum BugStatus implements Status {
    ACTIVE,
    FIXED;

    @Override
    public String toString() {
        switch (this) {
            case ACTIVE:
                return "Admin";
            case FIXED:
                return "Fixed";
            default:
                return "";
        }
    }
}
