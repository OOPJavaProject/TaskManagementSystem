package com.company.oop.taskmanagement.models;

import com.company.oop.taskmanagement.exceptions.ElementNotFoundException;
import com.company.oop.taskmanagement.models.contracts.ActivityHistory;
import com.company.oop.taskmanagement.models.contracts.Board;
import com.company.oop.taskmanagement.models.tasks.contracts.Task;
import com.company.oop.taskmanagement.utilities.Validation;

import java.util.ArrayList;
import java.util.List;

public class BoardImpl implements Board {

    private static final int BOARD_NAME_MIN = 3;
    private static final int BOARD_NAME_MAX = 15;
    private static final String BOARD_NAME_LENGTH_ERR = String.format(
            "Board name must be between %s and %s characters",
            BOARD_NAME_MIN,
            BOARD_NAME_MAX);
    private static final String BOARD_CREATED = "Board with name %s has been created.";
    private static final String TASK_HAS_BEEN_ADDED = "Task with title %s has been Added.";
    private static final String TASK_HAS_BEEN_REMOVED = "Task with title %s has been Removed";

    private static final String NO_TASKS_MESSAGE = "There are no tasks in the current in the board.";

    private static final String BOARD_NAME_PRINT = "Board with name: %s";

    private static final String TASK_PRINTING_TEMPLATE = """
            Tasks:
            %s
            ----------
            """;
    private static final String HISTORY_PRINTING_TEMPLATE = """
            History:
            %s
            ----------
            """;
    public static final String REMOVE_ERR_MSG = "The task you are trying to remove does not exist!";

    private String boardName;
    private final List<Task> tasks = new ArrayList<>();
    private final List<EventLog> activityHistory = new ArrayList<>();

    public BoardImpl(String name) {
        setBoardName(name);
        logEvent(String.format(BOARD_CREATED, name));
    }

    public BoardImpl(String name, List<Task> taskList) {
        setBoardName(name);
        setTasks(taskList);
        logEvent(String.format(BOARD_CREATED, name));
    }

    private void setBoardName(String name) {
        Validation.validateStringRange(name, BOARD_NAME_MIN, BOARD_NAME_MAX, BOARD_NAME_LENGTH_ERR);
        this.boardName = name;
    }

    private void logEvent(String event) {
        activityHistory.add(new EventLog(event));
    }

    private void setTasks(List<Task> tasksList) {
        this.tasks.addAll(tasksList);
        for (Task task : tasksList) {
            logEvent(String.format(TASK_HAS_BEEN_ADDED, task.getTitle()));
        }
    }

    @Override
    public void insertTasks(List<Task> tasksList) {
        setTasks(tasksList);
    }

    @Override
    public void addTask(Task task) {
        this.tasks.add(task);
        logEvent(String.format(TASK_HAS_BEEN_ADDED, task.getTitle()));
    }

    @Override
    public void removeTask(Task task) {
        if (tasks.contains(task)) {
            this.tasks.remove(task);
            logEvent(String.format(TASK_HAS_BEEN_REMOVED, task.getTitle()));
        } else {
            throw new ElementNotFoundException(REMOVE_ERR_MSG);
        }
    }

    @Override
    public String getName() {
        return this.boardName;
    }

    @Override
    public List<Task> getTasks() {
        return new ArrayList<>(this.tasks);
    }

    @Override
    public List<ActivityHistory> getActivityHistory() {
        return new ArrayList<>(this.activityHistory);
    }

    public String printHistory() {
        StringBuilder historyLogString = new StringBuilder();

        for (ActivityHistory log : getActivityHistory()) {
            historyLogString.append(log.toString());
        }
        return historyLogString.toString();
    }

    //TODO Duplicating with the member task print
    public String printTasks() {
        if (getTasks().isEmpty()) {
            return NO_TASKS_MESSAGE;
        }
        //TODO Check the task printing method
        StringBuilder result = new StringBuilder();
        for (Task task : getTasks()) {
            result.append(task.toString()).append(System.lineSeparator());
        }
        return result.toString();
    }

    @Override
    public String toString() {
        StringBuilder boardString = new StringBuilder();

        boardString.append(String.format(BOARD_NAME_PRINT, getName()));
        boardString.append(String.format(TASK_PRINTING_TEMPLATE, printTasks()));
        boardString.append(String.format(HISTORY_PRINTING_TEMPLATE, getActivityHistory()));
        boardString.append(System.lineSeparator());

        return boardString.toString();
    }
}
