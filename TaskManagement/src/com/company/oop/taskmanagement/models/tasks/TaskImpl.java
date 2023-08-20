package com.company.oop.taskmanagement.models.tasks;

import com.company.oop.taskmanagement.models.EventLog;
import com.company.oop.taskmanagement.models.contracts.ActivityHistory;
import com.company.oop.taskmanagement.models.contracts.Comment;
import com.company.oop.taskmanagement.models.enums.Priority;
import com.company.oop.taskmanagement.models.enums.TaskStatus.StoryStatus;
import com.company.oop.taskmanagement.models.enums.TaskType;
import com.company.oop.taskmanagement.models.tasks.contracts.Task;
import com.company.oop.taskmanagement.utilities.Validation;
import com.company.oop.taskmanagement.models.contracts.Status;

import java.util.ArrayList;
import java.util.List;

public abstract class TaskImpl implements Task {

    public static final int TITLE_MIN_LENGTH = 10;
    public static final int TITLE_MAX_LENGTH = 50;
    public static final String TITLE_ERROR_MESSAGE =
            String.format("The title must be between %d and %d characters long!",
                    TITLE_MIN_LENGTH,
                    TITLE_MAX_LENGTH);
    public static final int DESCRIPTION_MIN_LENGTH = 10;
    public static final int DESCRIPTION_MAX_LENGTH = 500;

    public static final String DESCRIPTION_ERROR_MESSAGE =
            String.format("The description must be between %d and %d characters long!",
                    DESCRIPTION_MIN_LENGTH,
                    DESCRIPTION_MAX_LENGTH);
    private static final String TASK_CREATED_LOG = "%s with title %s has been created.";
    private static final String COMMENT_ADDED_LOG = "%s added a comment.";
    private static final String COMMENT_REMOVED_LOG = "%s removed a comment.";

    private int id;
    private String title;
    private String description;

    private TaskType taskType;
    private final List<Comment> comments;
    private final List<ActivityHistory> history;
    private Status status;

    public TaskImpl(int id, String title, String description, Status status, TaskType tasktype) {
        setId(id);
        setTitle(title);
        setDescription(description);
        this.status = status;
        this.history = new ArrayList<>();
        this.comments = new ArrayList<>();
        setTaskType(tasktype);

        logEvent(String.format(TASK_CREATED_LOG, tasktype.toString(), title));
    }


    //protected, so that subclasses can add history of changes on their progress
    protected void logEvent(String event) {
        history.add(new EventLog(event));
    }

    @Override
    public void addComment(Comment commentToAdd) {
        this.comments.add(commentToAdd);

        logEvent(String.format(COMMENT_ADDED_LOG, commentToAdd.getAuthor()));
    }

    @Override
    public void removeComment(Comment commentToRemove) {
        this.comments.remove(commentToRemove);

        logEvent(String.format(COMMENT_REMOVED_LOG, commentToRemove.getAuthor()));
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public List<Comment> getComments() {
        return new ArrayList<>(comments);
    }

    @Override
    public List<ActivityHistory> getHistoryChanges() {
        return new ArrayList<>(history);
    }

    @Override
    public Status getStatus() {
        return status;
    }

    public TaskType getTaskType(){
        //TODO should it be removed? replacement with substring.
        return this.taskType;
    }

    public void changeStatus(Status status){
        setStatus(status);
    }

    protected void setStatus(Status status){
        this.status = status;
    }

    private void setId(int id) {
        this.id = id;
    }

    private void setTitle(String title) {
        Validation.validateIntRange(title.length(), TITLE_MIN_LENGTH, TITLE_MAX_LENGTH, TITLE_ERROR_MESSAGE);
        this.title = title;
    }

    private void setDescription(String description) {
        Validation.validateIntRange(description.length(), DESCRIPTION_MIN_LENGTH, DESCRIPTION_MAX_LENGTH, DESCRIPTION_ERROR_MESSAGE);
        this.description = description;
    }

    protected void setTaskType(TaskType type){
        this.taskType = type;
    }


    /**
     * Print format of the Task, used for listing
     * @return String in the format: [{TaskType}] Id:[{id}] Title:{}
     */
    public String printTaskByTitle(){
        StringBuilder result = new StringBuilder();

        result.append(String.format("[%s] ",this.getTaskType().toString()));
        result.append(String.format("Id: [%d] ",this.getId()));
        result.append(String.format("Title: %s", this.getTitle()));

        return result.toString();
    }

    @Override
    public String toString() {
        return String.format("""
                Title: %s
                Description: %s
                Status: %s
                """, title, description, status);
    }

    //TODO Research on wild card and how to use it for the change of status methods
//    public static <E extends Status> void incrementStatus(<? extends Status> type) {
//
//    }

}
