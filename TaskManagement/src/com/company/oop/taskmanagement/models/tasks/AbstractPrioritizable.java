package com.company.oop.taskmanagement.models.tasks;

import com.company.oop.taskmanagement.models.contracts.Member;
import com.company.oop.taskmanagement.models.contracts.Status;
import com.company.oop.taskmanagement.models.enums.Priority;
import com.company.oop.taskmanagement.models.enums.TaskType;
import com.company.oop.taskmanagement.models.tasks.contracts.PrioritizableTask;

public abstract class AbstractPrioritizable extends TaskImpl implements PrioritizableTask {
    private Priority priority;
    private Member assignee;


    public AbstractPrioritizable(int id, String title, String description, Status status, TaskType tasktype, Priority priority, Member assignee) {
        super(id, title, description, status, tasktype);
        setPriority(priority);
        setAssignee(assignee);
    }

    @Override
    public Priority getPriority() {
        return this.priority;
    }

    @Override
    public Member getAssignee() {
        return this.assignee;
    }

    @Override
    public void changePriority(Priority priority) {
        setPriority(priority);
    }

    @Override
    public void changeAssignee(Member member) {
        setAssignee(member);
    }

    private void setPriority(Priority priority) {
        this.priority = priority;
    }

    private void setAssignee(Member assignee) {
        //TODO POSSIBLE VALIDATION OF TEAM MEMBER
        this.assignee = assignee;
    }


    @Override
    public String toString() {
        return String.format("""
                %s
                Priority: %s
                Assignee: %s
                """,super.toString(), getPriority(), getAssignee().getName());
    }
}
