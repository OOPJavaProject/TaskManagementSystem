package com.company.oop.taskmanagement.core;

import com.company.oop.taskmanagement.core.contracts.TaskManagementRepository;
import com.company.oop.taskmanagement.exceptions.ElementNotFoundException;
import com.company.oop.taskmanagement.models.BoardImpl;
import com.company.oop.taskmanagement.models.CommentImpl;
import com.company.oop.taskmanagement.models.MemberImpl;
import com.company.oop.taskmanagement.models.TeamImpl;
import com.company.oop.taskmanagement.models.contracts.Board;
import com.company.oop.taskmanagement.models.contracts.Comment;
import com.company.oop.taskmanagement.models.contracts.Member;
import com.company.oop.taskmanagement.models.contracts.Team;
import com.company.oop.taskmanagement.models.enums.Priority;
import com.company.oop.taskmanagement.models.enums.Severity;
import com.company.oop.taskmanagement.models.enums.StorySize;
import com.company.oop.taskmanagement.models.tasks.BugImpl;
import com.company.oop.taskmanagement.models.tasks.FeedbackImpl;
import com.company.oop.taskmanagement.models.tasks.StoryImpl;
import com.company.oop.taskmanagement.models.tasks.contracts.*;

import java.util.ArrayList;
import java.util.List;

public class TaskManagementRepositoryImpl implements TaskManagementRepository {

    public static final String MEMBER_EXISTS_MESSAGE = "Member with name %s already exists.";
    public static final String TEAM_EXISTS_MESSAGE = "Team with name %s already exists.";
    public static final String TEAM_NOT_EXIST_MESSAGE = "Team with name %s does not exist!";
    public static final String BOARD_NOT_EXIST_MESSAGE = "Board with name %s does not exist!";
    public static final String MEMBER_NOT_EXIST_MESSAGE = "Member with name %s does not exist!";
    public static final String MEMBER_USERNAME_NOT_EXIST_MESSAGE = "Member with username %s does not exist!";
    public static final String TASK_NOT_EXIST_MESSAGE = "Task with ID %d does not exist!";
    public static final String NO_LOGGED_IN_MEMBER = "There is no logged in member";
    public static final String MEMBER_ALREADY_PRESENT = "Member %s is already present in team %s";

    private int nextId;

    private final List<Team> teams = new ArrayList<>();

    private final List<Member> members = new ArrayList<>();

    private final List<Task> tasks = new ArrayList<>();

    private final List<Board> boards = new ArrayList<>();
    private final List<Comment> comments = new ArrayList<>();

    private final List<PrioritizableTask> prioritizableTasks = new ArrayList<>();

    private Member loggedMember;

    public TaskManagementRepositoryImpl() {
        nextId = 0;
    }

    @Override
    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    @Override
    public List<Team> getTeams() {
        return new ArrayList<>(teams);
    }

    @Override
    public List<Member> getMembers() {
        return new ArrayList<>(members);
    }

    @Override
    public List<Comment> getComments() {
        return new ArrayList<>(comments);
    }

    @Override
    public List<Board> getBoards() {
        return new ArrayList<>(boards);
    }

    @Override
    public List<PrioritizableTask> getPrioritizableTasks() {
        return new ArrayList<>(prioritizableTasks);
    }

    @Override
    public boolean teamExists(String teamName) {
        return teams.stream()
                .anyMatch(team -> team.getName().equals(teamName));
    }


    @Override
    public boolean boardExists(String boardName) {
        return teams.stream()
                .anyMatch(team -> team.getBoards()
                        .stream()
                        .anyMatch(board -> board.getName().equals(boardName)));
    }

    @Override
    public boolean memberExists(String memberUsername) {
        return members.stream()
                .anyMatch(member -> member.getUsername().equals(memberUsername));
    }

    @Override
    public void addMember(Member member) {
        this.members.add(member);
    }

    @Override
    public void addMemberToTeam(String memberUsername, String teamName) {
        Team team = findTeamByName(teamName);
        Member member = findMemberByUserName(memberUsername);

        if (team.getMembers().contains(member)) {
            throw new IllegalArgumentException(String.format(MEMBER_ALREADY_PRESENT, memberUsername, teamName));
        }
        team.addMember(member);
    }

    @Override
    public Team findTeamByName(String teamName) {
        return teams.stream()
                .filter(team -> team.getName().equals(teamName))
                .findFirst()
                .orElseThrow(() -> new ElementNotFoundException(String.format(TEAM_NOT_EXIST_MESSAGE, teamName)));
    }

    @Override
    public Board findBoardByName(String boardName) {
        return teams.stream()
                .flatMap(team -> team.getBoards().stream())
                .filter(board -> board.getName().equals(boardName))
                .findFirst()
                .orElseThrow(() -> new ElementNotFoundException(String.format(BOARD_NOT_EXIST_MESSAGE, boardName)));
    }

    @Override
    public Member findMemberByName(String memberName) {
        return members.stream()
                .filter(member -> member.getName().equals(memberName))
                .findFirst()
                .orElseThrow(() -> new ElementNotFoundException(String.format(MEMBER_NOT_EXIST_MESSAGE, memberName)));
    }

    @Override
    public Member findMemberByUserName(String username) {
        return members.stream()
                .filter(member -> member.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new ElementNotFoundException(String.format(MEMBER_USERNAME_NOT_EXIST_MESSAGE, username)));
    }

    @Override
    public Task findTaskById(int id) {
        return tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ElementNotFoundException(String.format(TASK_NOT_EXIST_MESSAGE, id)));
    }

    @Override
    public PrioritizableTask findPrioritizableTaskById(int id) {
        return prioritizableTasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ElementNotFoundException(String.format(TASK_NOT_EXIST_MESSAGE, id)));
    }

    @Override
    public Bug createBug(String title, String description, Priority priority, Severity severity, Member assignee) {
        Bug bug = new BugImpl(++nextId, title, description, priority, severity, assignee);
        assignee.addTask(bug);
        this.tasks.add(bug);
        this.prioritizableTasks.add(bug);
        return bug;
    }

    @Override
    public Feedback createFeedback(String title, String description, int rating) {
        Feedback feedback = new FeedbackImpl(++nextId, title, description, rating);
        this.tasks.add(feedback);
        return feedback;
    }

    @Override
    public Story createStory(String title, String description, Priority priority, StorySize size, Member assignee) {
        Story story = new StoryImpl(++nextId, title, description, priority, size, assignee);
        assignee.addTask(story);
        this.tasks.add(story);
        this.prioritizableTasks.add(story);
        return story;
    }

    @Override
    public Member createMember(String name, String username, String password) {
        if (memberExists(name)) {
            throw new IllegalArgumentException(String.format(MEMBER_EXISTS_MESSAGE, name));
        } else {
            Member member = new MemberImpl(name, username, password);
            this.members.add(member);
            return member;
        }
    }

    @Override
    public Team createTeam(String name) {
        if (teamExists(name)) {
            throw new IllegalArgumentException(String.format(TEAM_EXISTS_MESSAGE, name));
        } else {
            Team team = new TeamImpl(name);
            this.teams.add(team);
            return team;
        }
    }


    @Override
    public Board createBoard(String name) {
        Board board = new BoardImpl(name);
        this.boards.add(board);
        return board;
    }

    @Override
    public Comment createComment(String content, Member author) {
        Comment comment = new CommentImpl(content, author);
        this.comments.add(comment);
        return comment;
    }

    @Override
    public void assignTaskToMember(Member member, PrioritizableTask task) {
        member.addTask(task);
        task.changeAssignee(member);
    }

    @Override
    public void unassignTaskFromMember(Member member, PrioritizableTask task) {
        member.removeTask(task);
        task.changeAssignee(null);
    }

    @Override
    public Member getLoggedInMember() {
        if (loggedMember == null) {
            throw new IllegalArgumentException(NO_LOGGED_IN_MEMBER);
        }
        return loggedMember;
    }


    @Override
    public boolean hasLoggedInMember() {
        return loggedMember != null;
    }

    @Override
    public void login(Member member) {
        loggedMember = member;
    }

    @Override
    public void logout() {
        loggedMember = null;
    }
}
