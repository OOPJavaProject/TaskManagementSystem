package com.company.oop.taskmanagement.models.tasks;

import com.company.oop.taskmanagement.models.contracts.Member;
import com.company.oop.taskmanagement.models.contracts.Status;
import com.company.oop.taskmanagement.models.enums.Priority;
import com.company.oop.taskmanagement.models.enums.Severity;
import com.company.oop.taskmanagement.models.enums.TaskStatus.BugStatus;
import com.company.oop.taskmanagement.models.enums.TaskType;
import com.company.oop.taskmanagement.models.tasks.contracts.Bug;

import java.util.ArrayList;
import java.util.List;

public class BugImpl extends AbstractPrioritizable implements Bug {

    public static final String STATUS_CHANGE_ERROR_MESSAGE = "Bug status is already at %s";
    private static final String STATUS_CHANGED_LOG = " Status has been changed from %s to %s.";
    private static final String STATUS_UNSUCCESSFUL_CHANGE_LOG = "Status change has been unsuccessful, current status is: %s.";
    public static final String STEP_ADDED_LOG = "Step added";
    private final List<String> steps;
    private Severity severity;

    public BugImpl(int id, String title, String description, Priority priority, Severity severity, Member assignee) {
        super(id, title, description, BugStatus.ACTIVE, TaskType.BUG, priority, assignee);
        setSeverity(severity);
        steps = new ArrayList<>();
    }

    @Override
    public List<String> getSteps() {
        return new ArrayList<>(steps);
    }
    @Override
    public Severity getSeverity() {
        return severity;
    }
    @Override
    public void progressStatus() {
        Status tempStatus = getStatus();
        if (getStatus() == BugStatus.ACTIVE) {
            setStatus(BugStatus.FIXED);

            logEvent(String.format(STATUS_CHANGED_LOG, tempStatus, getStatus()));
        } else {
            logEvent(String.format(STATUS_UNSUCCESSFUL_CHANGE_LOG, getStatus()));

            throw new IllegalArgumentException(String.format(STATUS_CHANGE_ERROR_MESSAGE, getStatus()));
        }

    }

    public void addStep(String step) {
        this.steps.add(step);
        logEvent(STEP_ADDED_LOG);
    }
    @Override
    public void revertStatus() {
        Status tempStatus = getStatus();
        if (getStatus() == BugStatus.FIXED) {
            setStatus(BugStatus.ACTIVE);

            logEvent(String.format(STATUS_CHANGED_LOG, tempStatus, getStatus()));
        } else {
            logEvent(String.format(STATUS_UNSUCCESSFUL_CHANGE_LOG, getStatus()));

            throw new IllegalArgumentException(String.format(STATUS_CHANGE_ERROR_MESSAGE, getStatus()));
        }
    }

    @Override
    public String toString() {
        return String.format("""
                --Bug--
                %s
                Steps to reproduce -> %s
                Severity: %s
                Comments:
                %s
                History:
                %s
                -----------
                """, super.toString(), getSteps(), getSeverity(), getComments(), getHistoryChanges());
    }

    public void changeSeverity(Severity newSeverity){
        setSeverity(newSeverity);
    }
    private void setSeverity(Severity severity) {
        this.severity = severity;
    }
}
