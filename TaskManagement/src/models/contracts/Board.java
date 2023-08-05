package models.contracts;

import java.util.List;

public interface Board {

    String getName();

    List<Task> getTasks();

    List<ActivityHistory> getActivityHistory(); //TODO

    String toString();
}
