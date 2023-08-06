package com.company.oop.taskmanagement.models;

import com.company.oop.taskmanagement.models.contracts.ActivityHistory;
import com.company.oop.taskmanagement.models.contracts.Comment;
import com.company.oop.taskmanagement.models.contracts.Member;
import com.company.oop.taskmanagement.models.tasks.contracts.Task;
import com.company.oop.taskmanagement.utilities.Validation;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class MemberImpl implements Member {

    public static final int USERNAME_LEN_MIN = 2;
    public static final int USERNAME_LEN_MAX = 20;
    private static final String USERNAME_LEN_ERR = format(
            "Username must be between %d and %d characters long!",
            USERNAME_LEN_MIN,
            USERNAME_LEN_MAX);

    public static final int PASSWORD_LEN_MIN = -1;
    public static final int PASSWORD_LEN_MAX = -1;
    private static final String PASSWORD_REGEX_PATTERN = "^[A-Za-z0-9@*_-]+$";
    private static final String PASSWORD_PATTERN_ERR = "Password contains invalid symbols!";
    private static final String PASSWORD_LEN_ERR = format(
            "Password must be between %s and %s characters long!",
            PASSWORD_LEN_MIN,
            PASSWORD_LEN_MAX);

    private static final String MEMBER_PRINT_HEADER = "Member with name: %s";
    private static final String NOT_THE_AUTHOR_ERR = "You are not the author of the comment you are trying to remove!";

    private static final String USER_ALREADY_LOGGED_IN = "This user is already logged in.";

    private static final String USER_NOT_LOGGED_IN_TO_LOG_OUT = "This user is not yet logged in.";
    private static final String MEMBER_REGISTERED_MSG = "Member with name %s has been registered";
    private static final String NO_TASKS_MESSAGE = "There are no tasks in the current in the board.";
    private static final String MEMBER_HAS_REMOVED_COMMEND = "Member %s has removed commend from a task with the tile %s.";
    private static final String MEMBER_HAS_ADDED_COMMEND = "Member %s has added commend to a task with the tile %s.";

    private static final String MEMBER_NOT_AUTHOR_LOG = "Member %S has tried to remove a commend from task with title %s written by %s";

    private static final String MEMBER_HAS_LOGGED_IN = "Member %s has logged in.";
    private static final String MEMBER_HAS_LOGGED_OUT = "Member %s has logged out.";

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

    private String username;
    private String password;
    private final List<Task> tasks = new ArrayList<>();

    private final List<ActivityHistory> activityLog = new ArrayList<>();

    private boolean isLoggedIn;

    public MemberImpl(String name, String password) {
        setName(name);
        setPassword(password);
        logEvent(String.format(MEMBER_REGISTERED_MSG, name));
    }
    private void logEvent(String event) {
        activityLog.add(new EventLog(event));
    }

    private void setPassword(String password) {
        Validation.validateStringRange(password, PASSWORD_LEN_MIN, PASSWORD_LEN_MAX, PASSWORD_LEN_ERR);
        Validation.validatePattern(password, PASSWORD_REGEX_PATTERN, PASSWORD_PATTERN_ERR);
        this.password = password;
    }

    private void setName(String name) {
        Validation.validateStringRange(name, USERNAME_LEN_MIN, USERNAME_LEN_MAX, USERNAME_LEN_ERR);
        this.username = name;
    }

    @Override
    public String getName() {
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }


    @Override
    public List<Task> getTasks() {
        return new ArrayList<>(this.tasks);
    }

    @Override
    public List<ActivityHistory> getActivityHistory() {
        return new ArrayList<>(this.activityLog);
    }

    @Override
    public void removeComment(Comment comment, Task taskToRemoveComment) {
        int commentIndex = taskToRemoveComment.getComments().indexOf(comment);
        Member author = taskToRemoveComment.getComments().get(commentIndex).getAuthor();

        if (author.getName().equals(getName())) {
            taskToRemoveComment.removeComment(comment);

            logEvent(String.format(MEMBER_HAS_REMOVED_COMMEND, getName(), taskToRemoveComment.getTitle()));
            return;
        }

        logEvent(String.format(MEMBER_NOT_AUTHOR_LOG, getName(), taskToRemoveComment.getTitle(), comment.getAuthor()));
        throw new IllegalArgumentException(NOT_THE_AUTHOR_ERR);
    }

    @Override
    public void addComment(Comment comment, Task taskToAddComment) {
        taskToAddComment.addComment(comment);

        logEvent(String.format(MEMBER_HAS_ADDED_COMMEND, getName(), taskToAddComment.getTitle()));
    }

    @Override
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
    public String printHistory() {
        StringBuilder historyLogString = new StringBuilder();

        for (ActivityHistory log : getActivityHistory()) {
            historyLogString.append(log.toString()).append(System.lineSeparator());
        }
        return historyLogString.toString();
    }

    public void logIn(){
        if (this.isLoggedIn){
            throw new IllegalArgumentException(USER_ALREADY_LOGGED_IN);
        }
        logEvent(String.format(MEMBER_HAS_LOGGED_IN, getName()));
        isLoggedIn = true;
    }

    public void logOut(){
        if(!this.isLoggedIn){
            throw new IllegalArgumentException(USER_NOT_LOGGED_IN_TO_LOG_OUT);
        }
        logEvent(String.format(MEMBER_HAS_LOGGED_OUT, getName()));
        this.isLoggedIn = false;
    }
    @Override
    public boolean isLoggedIn() {
        return this.isLoggedIn;
    }

    //TODO an idea to have only name printing in the toString and a full print method
    @Override
    public String toString(){
        StringBuilder memberString = new StringBuilder();
        memberString.append(String.format(MEMBER_PRINT_HEADER, getName()));
        memberString.append(String.format(TASK_PRINTING_TEMPLATE, printTasks()));
        memberString.append(String.format(HISTORY_PRINTING_TEMPLATE, printHistory()));
         return memberString.toString();
    }
}
