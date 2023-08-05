package models;

import com.company.oop.taskmanagement.utilities.Validation;
import models.contracts.ActivityHistory;
import models.contracts.Comment;
import models.contracts.Status;
import models.contracts.Task;

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

    private int id;
    private String title;
    private String description;
    List<Comment> comments;

    List<ActivityHistory> history;

    Status status;

    public TaskImpl(int id, String title, String description, Status status) {
        setId(id);
        setTitle(title);
        setDescription(description);
        this.status = status;
        history = new ArrayList<>();
        comments = new ArrayList<>();
    }

    @Override
    public void progressStatus() {
//TODO
    }

    @Override
    public void revertStatus() {
//TODO
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

    @Override
    public void setStatus(Status status) {
        this.status = status;
    }
}
