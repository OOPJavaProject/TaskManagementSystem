package com.company.oop.taskmanagement.models.tasks;

import com.company.oop.taskmanagement.models.contracts.Comment;
import com.company.oop.taskmanagement.models.contracts.Member;
import com.company.oop.taskmanagement.models.contracts.Status;
import com.company.oop.taskmanagement.models.enums.Priority;
import com.company.oop.taskmanagement.models.enums.StorySize;
import com.company.oop.taskmanagement.models.enums.TaskStatus.BugStatus;
import com.company.oop.taskmanagement.models.enums.TaskStatus.StoryStatus;
import com.company.oop.taskmanagement.models.enums.TaskType;
import com.company.oop.taskmanagement.models.tasks.contracts.Story;

public class StoryImpl extends TaskImpl implements Story {

    public static final String STATUS_CHANGE_ERROR_MESSAGE = "Story status is already at %s";
    private static final String STATUS_CHANGED_LOG =" Status has been changed from %s to %s." ;
    private static final String STATUS_UNSUCCESSFUL_CHANGE_LOG = "Status change has been unsuccessful, current status is: %s.";
    private Priority priority;
    private StorySize size;
    private Member assignee;

    public StoryImpl(int id, String title, String description, Priority priority, StorySize size, Member assignee) {
        super(id, title, description, StoryStatus.NOT_DONE, TaskType.STORY);
        setPriority(priority);
        setSize(size);
        setAssignee(assignee);
    }

    @Override
    public Priority getPriority() {
        return priority;
    }

    @Override
    public StorySize getSize() {
        return size;
    }

    @Override
    public Member getAssignee() {
        return assignee;
    }

    @Override
    public void progressStatus() {
        Status tempStatus = getStatus();
        if (getStatus() == StoryStatus.NOT_DONE) {
            setStatus(StoryStatus.IN_PROGRESS);

            logEvent(String.format(STATUS_CHANGED_LOG, tempStatus, getStatus()));
        }
        else if (getStatus() == StoryStatus.IN_PROGRESS) {
            setStatus(StoryStatus.DONE);

            logEvent(String.format(STATUS_CHANGED_LOG, tempStatus, getStatus()));
        }
        else {
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
        }
        else if (getStatus() == StoryStatus.IN_PROGRESS) {
            setStatus(StoryStatus.NOT_DONE);

            logEvent(String.format(STATUS_CHANGED_LOG, tempStatus, getStatus()));
        }
        else {
            logEvent(String.format(STATUS_UNSUCCESSFUL_CHANGE_LOG, getStatus()));
            throw new IllegalArgumentException(String.format(STATUS_CHANGE_ERROR_MESSAGE, getStatus()));
        }
    }

    @Override
    public String toString() {
        return String.format("""
                --Story--
                %s
                Priority: %s
                Size: %s
                Assignee: %s
                Comments:
                %s
                History:
                %s
                """, super.toString(), getPriority(), getSize(), getAssignee(), getComments(), getHistoryChanges());
    }

    private void setPriority(Priority priority) {
        this.priority = priority;
    }

    private void setSize(StorySize size) {
        this.size = size;
    }

    private void setAssignee(Member assignee) {
        //TODO POSSIBLE VALIDATION OF TEAM MEMBER
        this.assignee = assignee;
    }
}
