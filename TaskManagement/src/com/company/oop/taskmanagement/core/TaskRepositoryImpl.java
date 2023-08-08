package com.company.oop.taskmanagement.core;

import com.company.oop.taskmanagement.core.contracts.TaskRepository;
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

public class TaskRepositoryImpl implements TaskRepository {

    public static final String MEMBER_EXISTS_MESSAGE = "Member with name %s already exists.";
    public static final String TEAM_EXISTS_MESSAGE = "Team with name %s already exists.";
    public static final String TEAM_NOT_EXIST_MESSAGE = "Team with name %s does not exist!";
    public static final String BOARD_NOT_EXIST_MESSAGE = "Board with name %s does not exist!";
    public static final String MEMBER_NOT_EXIST_MESSAGE = "Member with name %s does not exist!";

    public static final String MEMBER_USERNAME_NOT_EXIST_MESSAGE = "Member with username %s does not exist!";
    public static final String TASK_NOT_EXIST_MESSAGE = "Task with ID%d does not exist!";
    public static final String NO_LOGGED_IN_MEMBER = "There is no logged in member";
    private int nextId;

    private final List<Team> teams = new ArrayList<>();

    private final List<Member> members = new ArrayList<>();

    private final List<Task> tasks = new ArrayList<>();

    private final List<Board> boards = new ArrayList<>();

    private final List<Comment> comments = new ArrayList<>();

    private Member loggedMember;

    public TaskRepositoryImpl() {
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
    public List<Board> getBoards() {
        return new ArrayList<>(boards);
    }

    @Override
    public List<Member> getMembers() {
        return new ArrayList<>(members);
    }

    @Override
    public boolean teamExists(String teamName) {
        for (Team team : teams) {
            if (team.getName().equals(teamName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean boardExists(String boardName) {
        for (Board board : boards) {
            if (board.getName().equals(boardName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean memberExists(String memberName) {
        for (Member member : members) {
            if (member.getName().equals(memberName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Team findTeamByName(String teamName) {
        for (Team team : teams) {
            if (team.getName().equals(teamName)) {
                return team;
            }
        }
        throw new ElementNotFoundException(String.format(TEAM_NOT_EXIST_MESSAGE, teamName));
    }

    @Override
    public Board findBoardByName(String boardName) {
        for (Board board : boards) {
            if (board.getName().equals(boardName)) {
                return board;
            }
        }
        throw new ElementNotFoundException(String.format(BOARD_NOT_EXIST_MESSAGE, boardName));
    }

    @Override
    public Member findMemberByName(String memberName) {
        for (Member member : members) {
            if (member.getName().equals(memberName)) {
                return member;
            }
        }
        throw new ElementNotFoundException(String.format(MEMBER_NOT_EXIST_MESSAGE, memberName));
    }

    @Override
    public Member findMemberByUserName(String username) {
        for (Member member : members) {
            if (member.getUsername().equals(username)) {
                return member;
            }
        }
        throw new ElementNotFoundException(String.format(MEMBER_USERNAME_NOT_EXIST_MESSAGE, username));
    }

    @Override
    public Task findTaskById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        throw new ElementNotFoundException(String.format(TASK_NOT_EXIST_MESSAGE, id));
    }

    @Override
    public Bug createBug(String title, String description, Priority priority, Severity severity, Member assignee) {
        Bug bug = new BugImpl(++nextId, title, description, priority, severity, assignee);
        this.tasks.add(bug);
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
        this.tasks.add(story);
        return story;
    }

    @Override
    public Member createMember(String name, String username, String password) {
        if (memberExists(name)) {
            throw new IllegalArgumentException(String.format(MEMBER_EXISTS_MESSAGE, name));
        } else {
            Member member = new MemberImpl(name,username, password);
            this.members.add(member);
            return member;
        }
    }

    @Override
    public Board createBoard(String name) {
        Board board = new BoardImpl(name);
        this.boards.add(board);
        return board;
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
    public Comment createComment(String content, Member author) {
        Comment comment = new CommentImpl(content, author);
        this.comments.add(comment);
        return comment;
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
