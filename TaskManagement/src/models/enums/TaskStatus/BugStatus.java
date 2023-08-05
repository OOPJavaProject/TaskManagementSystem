package models.enums.TaskStatus;

import models.contracts.Status;

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
