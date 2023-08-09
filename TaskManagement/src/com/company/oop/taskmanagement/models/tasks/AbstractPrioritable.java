package com.company.oop.taskmanagement.models.tasks;

import com.company.oop.taskmanagement.models.contracts.Member;
import com.company.oop.taskmanagement.models.contracts.Status;
import com.company.oop.taskmanagement.models.enums.Priority;
import com.company.oop.taskmanagement.models.enums.TaskType;
import com.company.oop.taskmanagement.models.tasks.contracts.PrioritableTask;

public abstract class AbstractPrioritable extends TaskImpl implements PrioritableTask {


    private Priority priority;
    private Member assignee;

    public AbstractPrioritable(int id, String title, String description, Status status, TaskType tasktype, Priority priority, Member assignee) {
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

    private void setPriority(Priority priority){
        this.priority = priority;
    }
    private void setAssignee(Member assignee) {
        //TODO POSSIBLE VALIDATION OF TEAM MEMBER
        this.assignee = assignee;
    }

    @Override
    public void changeAssignee(Member member) {
        setAssignee(member);
    }
}
