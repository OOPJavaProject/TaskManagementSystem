package models.contracts;

import java.util.List;

public interface Member {
    String getName();

    List<Task> getTasks();

    List<ActivityHistory> getActivityHistory(); //TODO

    String toString();
}
