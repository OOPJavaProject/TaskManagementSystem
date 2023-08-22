package com.company.oop.taskmanagement.models.tasks;

import com.company.oop.taskmanagement.models.contracts.Member;
import com.company.oop.taskmanagement.models.contracts.Status;
import com.company.oop.taskmanagement.models.enums.Priority;
import com.company.oop.taskmanagement.models.enums.StorySize;
import com.company.oop.taskmanagement.models.enums.TaskStatus.StoryStatus;
import com.company.oop.taskmanagement.models.enums.TaskType;
import com.company.oop.taskmanagement.models.tasks.contracts.Story;

public class StoryImpl extends AbstractPrioritizable implements Story {

    public static final String STATUS_CHANGE_ERROR_MESSAGE = "Story status is already at %s";
    private static final String STATUS_CHANGED_LOG = " Status has been changed from %s to %s.";
    private static final String STATUS_UNSUCCESSFUL_CHANGE_LOG = "Status change has been unsuccessful, current status is: %s.";
    private StorySize size;

    public StoryImpl(int id, String title, String description, Priority priority, StorySize size, Member assignee) {
        super(id, title, description, StoryStatus.NOT_DONE, TaskType.STORY, priority, assignee);
        setSize(size);
    }

    @Override
    public StorySize getSize() {
        return size;
    }

    @Override
    public void progressStatus() {
        Status tempStatus = getStatus();
        if (getStatus() == StoryStatus.NOT_DONE) {
            setStatus(StoryStatus.IN_PROGRESS);

            logEvent(String.format(STATUS_CHANGED_LOG, tempStatus, getStatus()));
        } else if (getStatus() == StoryStatus.IN_PROGRESS) {
            setStatus(StoryStatus.DONE);

            logEvent(String.format(STATUS_CHANGED_LOG, tempStatus, getStatus()));
        } else {
            logEvent(String.format(STATUS_UNSUCCESSFUL_CHANGE_LOG, getStatus()));
            throw new IllegalArgumentException(String.format(STATUS_CHANGE_ERROR_MESSAGE, getStatus()));
        }
    }

    @Override
    public void revertStatus() {
        Status tempStatus = getStatus();
        if (getStatus() == StoryStatus.DONE) {
            setStatus(StoryStatus.IN_PROGRESS);

            logEvent(String.format(STATUS_CHANGED_LOG, tempStatus, getStatus()));
        } else if (getStatus() == StoryStatus.IN_PROGRESS) {
            setStatus(StoryStatus.NOT_DONE);

            logEvent(String.format(STATUS_CHANGED_LOG, tempStatus, getStatus()));
        } else {
            logEvent(String.format(STATUS_UNSUCCESSFUL_CHANGE_LOG, getStatus()));
            throw new IllegalArgumentException(String.format(STATUS_CHANGE_ERROR_MESSAGE, getStatus()));
        }
    }

    @Override
    public String toString() {
        return String.format("""
                --Story--
                %s
                Size: %s
                Comments:
                %s
                History:
                %s
                """, super.toString(), getSize(), getComments(), getHistoryChanges());
    }


    public void changeSize(StorySize size) {
        setSize(size);
    }

    private void setSize(StorySize size) {
        this.size = size;
    }

}
