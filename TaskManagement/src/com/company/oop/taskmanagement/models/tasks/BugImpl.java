package com.company.oop.taskmanagement.models.tasks;

import com.company.oop.taskmanagement.models.contracts.Member;
import com.company.oop.taskmanagement.models.contracts.Status;
import com.company.oop.taskmanagement.models.enums.Priority;
import com.company.oop.taskmanagement.models.enums.Severity;
import com.company.oop.taskmanagement.models.enums.TaskStatus.BugStatus;
import com.company.oop.taskmanagement.models.tasks.contracts.Bug;

import java.util.ArrayList;
import java.util.List;

public class BugImpl extends TaskImpl implements Bug {

    private final List<String> steps;
    private Priority priority;
    private Severity severity;
    private Member assignee;

    public BugImpl(int id, String title, String description, Priority priority, Severity severity, Member assignee) {
        super(id, title, description, BugStatus.ACTIVE);
        setPriority(priority);
        setSeverity(severity);
        setAssignee(assignee);
        steps = new ArrayList<>();
    }

    private void setPriority(Priority priority) {
        this.priority = priority;
    }

    private void setSeverity(Severity severity) {
        this.severity = severity;
    }

    public void setAssignee(Member assignee) {
        //TODO - check whether the member is from the team
        this.assignee = assignee;
    }

    @Override
    public List<String> getSteps() {
        return new ArrayList<>(steps);
    }

    @Override
    public Priority getPriority() {
        return priority;
    }

    @Override
    public Severity getSeverity() {
        return severity;
    }

    @Override
    public Member getAssignee() {
        return assignee;
    }

    public void changeStatus(BugStatus status) {
//TODO MAKE SURE THAT STATUS IS IMPLEMENTED CORRECTLY HERE
    }

    @Override
    public String toString() {
        return String.format("""
                --Bug--
                %s
                Steps to reproduce -> %s
                Priority: %s
                Severity: %s
                Assignee: %s
                Comments:
                %s
                History:
                %s
                -----------
                """, super.toString(), getPriority(), getSeverity(), getAssignee(), getComments(), getHistoryChanges());
    }
}
