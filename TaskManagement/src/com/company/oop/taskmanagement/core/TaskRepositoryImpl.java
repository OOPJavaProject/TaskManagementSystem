package com.company.oop.taskmanagement.core;

import com.company.oop.taskmanagement.core.contracts.TaskRepository;
import com.company.oop.taskmanagement.exceptions.ElementNotFoundException;
import com.company.oop.taskmanagement.models.contracts.Board;
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

    private int nextId;

    private final List<Team> teams = new ArrayList<>();

    private final List<Member> members = new ArrayList<>();

    private final List<Task> tasks = new ArrayList<>();

    private final List<Board> boards = new ArrayList<>();

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
        throw new ElementNotFoundException(String.format("Team with name %s does not exist!", teamName));
    }

    @Override
    public Board findBoardByName(String boardName) {
        for (Board board : boards) {
            if (board.getName().equals(boardName)) {
                return board;
            }
        }
        throw new ElementNotFoundException(String.format("Board with name %s does not exist!", boardName));
    }

    @Override
    public Member findMemberByName(String memberName) {
        for (Member member : members) {
            if (member.getName().equals(memberName)) {
                return member;
            }
        }
        throw new ElementNotFoundException(String.format("Member with name %s does not exist!", memberName));
    }

    @Override
    public Task findTaskById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        throw new ElementNotFoundException(String.format("Task with ID%d does not exist!", id));
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
    public Member createMember() {
        return null;
    }

    @Override
    public Board createBoard() {
        return null;
    }

    @Override
    public Team createTeam() {
        return null;
    }
}
