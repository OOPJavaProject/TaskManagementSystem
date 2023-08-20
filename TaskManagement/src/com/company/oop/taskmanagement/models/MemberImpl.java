package com.company.oop.taskmanagement.models;

import com.company.oop.taskmanagement.models.contracts.ActivityHistory;
import com.company.oop.taskmanagement.models.contracts.Comment;
import com.company.oop.taskmanagement.models.contracts.Member;
import com.company.oop.taskmanagement.models.contracts.Team;
import com.company.oop.taskmanagement.models.tasks.contracts.Task;
import com.company.oop.taskmanagement.utilities.Validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static java.lang.String.format;

public class MemberImpl implements Member {

    //TODO boolean isPartOfTeam;
    public static final int USERNAME_LEN_MIN = 5;

    public static final int USERNAME_LEN_MAX = 20;

    private static final String USERNAME_LEN_ERR = format(
            "Username must be between %d and %d characters long!",
            USERNAME_LEN_MIN,
            USERNAME_LEN_MAX);

    public static final int NAME_LEN_MIN = 2;
    public static final int NAME_LEN_MAX = 20;
    private static final String NAME_LEN_ERR = format(
            "Name must be between %d and %d characters long!",
            NAME_LEN_MIN,
            NAME_LEN_MAX);

    public static final int PASSWORD_LEN_MIN = 5;
    public static final int PASSWORD_LEN_MAX = 15;
    private static final String PASSWORD_REGEX_PATTERN = "^[A-Za-z0-9@*_-]+$";
    private static final String PASSWORD_PATTERN_ERR = "Password contains invalid symbols!";
    private static final String PASSWORD_LEN_ERR = format(
            "Password must be between %d and %d characters long!",
            PASSWORD_LEN_MIN,
            PASSWORD_LEN_MAX);

    private static final String MEMBER_PRINT_HEADER = "Member with name: %s";
    private static final String NOT_THE_AUTHOR_ERR = "You are not the author of the comment you are trying to remove!";

    private static final String MEMBER_ALREADY_LOGGED_IN = "This user is already logged in.";

    private static final String MEMBER_NOT_LOGGED_IN_TO_LOG_OUT = "This user is not yet logged in.";
    private static final String MEMBER_REGISTERED_MSG = "Member with name %s has been registered";
    private static final String NO_TASKS_MESSAGE = "There are no tasks in the current in the .";
    private static final String MEMBER_HAS_REMOVED_COMMEND = "Member %s has removed comment from a task with the title %s.";
    private static final String MEMBER_HAS_ADDED_COMMEND = "Member %s has added comment to a task with the title %s.";
    private static final String MEMBER_NOT_AUTHOR_LOG = "Member %s has tried to remove a comment from task with title %s written by %s";
    private static final String MEMBER_ADDED_TO_TEAM_LOG = "Member %s was added to team %s";
    private static final String MEMBER_REMOVED_FROM_TEAM_LOG = "Member %s was removed from team %s";

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
    public static final String TASK_ADDED_MSG = "Task was successfully added to the member's tasklist.";
    public static final String TASK_REMOVED_MSG = "Task was successfully removed from the member's tasklist";
    public static final String TASK_NOT_IN_THE_LIST_MSG = "The task you want to remove does not exist in the list.";
    public static final String FAILED_TO_REMOVE_TASK = "Failed to remove task due to non - existence.";


    private String name;
    private String username;
    private String password;

    private String teamName;
    private final List<Task> tasks = new ArrayList<>();

    private final List<ActivityHistory> activityLog = new ArrayList<>();

    private boolean isLoggedIn;

    public MemberImpl(String name, String username, String password) {
        setName(name);
        setUsername(username);
        setPassword(password);
        logEvent(String.format(MEMBER_REGISTERED_MSG, name));
    }
    private void logEvent(String event) {
        activityLog.add(new EventLog(event));
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getPassword(){
        return this.password;
    }

    @Override
    public List<Task> getTasks() {
        return new ArrayList<>(this.tasks);
    }

    @Override
    public void addTask(Task task) {
        tasks.add(task);
        logEvent(TASK_ADDED_MSG);
    }

    @Override
    public void removeTask(Task task) {
        if (tasks.contains(task)) {
            tasks.remove(task);
            logEvent(TASK_REMOVED_MSG);
        } else {
            logEvent(FAILED_TO_REMOVE_TASK);
            throw new IllegalArgumentException(TASK_NOT_IN_THE_LIST_MSG);
        }
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
    public void logAddedToTeam(Team team){
        logEvent(String.format(MEMBER_ADDED_TO_TEAM_LOG, this.getName(), team.getName()));
    }
    @Override
    public void logRemovedFromTeam(Team team){
        logEvent(String.format(MEMBER_REMOVED_FROM_TEAM_LOG, this.getName(), team.getName()));
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

    @Override
    public String printHistory() {
        StringBuilder historyLogString = new StringBuilder();

        for (ActivityHistory log : getActivityHistory()) {
            historyLogString.append(log.toString()).append(System.lineSeparator());
        }
        return historyLogString.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemberImpl member)) return false;

        return username.equals(member.username);
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    public void logIn(){
        if (this.isLoggedIn){
            throw new IllegalArgumentException(MEMBER_ALREADY_LOGGED_IN);
        }
        logEvent(String.format(MEMBER_HAS_LOGGED_IN, getName()));
        isLoggedIn = true;
        //TODO not sure if this will need in future
    }

    public void logOut(){
        if(!this.isLoggedIn){
            throw new IllegalArgumentException(MEMBER_NOT_LOGGED_IN_TO_LOG_OUT);
        }
        logEvent(String.format(MEMBER_HAS_LOGGED_OUT, getName()));
        this.isLoggedIn = false;
        //TODO not sure if this will need in future
    }
    @Override
    public boolean isLoggedIn() {
        return this.isLoggedIn;
    }

    //TODO an idea to have only name printing in the toString and a full print method
    @Override
    public String toString(){
//        StringBuilder memberString = new StringBuilder();
//        memberString.append(System.lineSeparator());
//        memberString.append(String.format(MEMBER_PRINT_HEADER, getName()));
//        memberString.append(System.lineSeparator());
//        memberString.append(String.format(TASK_PRINTING_TEMPLATE, printTasks()));
//        memberString.append(System.lineSeparator());
//        memberString.append(String.format(HISTORY_PRINTING_TEMPLATE, printHistory()));
//        memberString.append(System.lineSeparator());
//         return memberString.toString();
        return String.format("""
                %n%s""", getName());
    }

    private void setPassword(String password) {
        Validation.validateStringRange(password, PASSWORD_LEN_MIN, PASSWORD_LEN_MAX, PASSWORD_LEN_ERR);
        Validation.validatePattern(password, PASSWORD_REGEX_PATTERN, PASSWORD_PATTERN_ERR);
        this.password = password;
    }

    private void setName(String name) {
        Validation.validateStringRange(name, NAME_LEN_MIN, NAME_LEN_MAX, NAME_LEN_ERR);
        this.name = name;
    }

    private void setUsername(String username) {
        Validation.validateStringRange(username, USERNAME_LEN_MIN, USERNAME_LEN_MAX, USERNAME_LEN_ERR);
        this.username = username;
    }
}
