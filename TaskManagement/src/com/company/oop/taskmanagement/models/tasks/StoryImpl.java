package com.company.oop.taskmanagement.models.tasks;

import com.company.oop.taskmanagement.models.contracts.Member;
import com.company.oop.taskmanagement.models.enums.Priority;
import com.company.oop.taskmanagement.models.enums.StorySize;
import com.company.oop.taskmanagement.models.enums.TaskStatus.StoryStatus;
import com.company.oop.taskmanagement.models.tasks.contracts.Story;

public class StoryImpl extends TaskImpl implements Story {

    private Priority priority;
    private StorySize size;
    private Member assignee;

    public StoryImpl(int id, String title, String description, Priority priority, StorySize size, Member assignee) {
        super(id, title, description, StoryStatus.NOT_DONE);
        setPriority(priority);
        setSize(size);
        setAssignee(assignee);
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
}
