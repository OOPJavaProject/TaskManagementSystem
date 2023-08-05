package models.contracts;

import java.util.List;

public interface Task {
    int getId();

    String getTitle();

    String getDescription();

    List<Comment> getComments();

    List<ActivityHistory> getHistoryChanges();

    void progressStatus();

    void revertStatus();

    void setStatus(Status status);

    








}
