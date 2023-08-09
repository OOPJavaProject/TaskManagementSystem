package com.company.oop.taskmanagement.core.contracts;

import com.company.oop.taskmanagement.models.contracts.Board;
import com.company.oop.taskmanagement.models.contracts.Comment;
import com.company.oop.taskmanagement.models.contracts.Member;
import com.company.oop.taskmanagement.models.contracts.Team;
import com.company.oop.taskmanagement.models.enums.Priority;
import com.company.oop.taskmanagement.models.enums.Severity;
import com.company.oop.taskmanagement.models.enums.StorySize;
import com.company.oop.taskmanagement.models.tasks.contracts.Bug;
import com.company.oop.taskmanagement.models.tasks.contracts.Feedback;
import com.company.oop.taskmanagement.models.tasks.contracts.Story;
import com.company.oop.taskmanagement.models.tasks.contracts.Task;

import java.util.List;

public interface TaskRepository {

    List<Task> getTasks();

    List<Team> getTeams();

    List<Board> getBoards();

    List<Member> getMembers();

    boolean teamExists(String teamName);

    boolean boardExists(String boardName);

    boolean memberExists(String memberName);

    Team findTeamByName(String teamName);

    Board findBoardByName(String boardName);

    Member findMemberByName(String memberName);

    Member findMemberByUserName(String username);

    Task findTaskById(int id);

    Bug createBug(String title, String description, Priority priority, Severity severity, Member assignee);

    Feedback createFeedback(String title, String description, int rating);

    Story createStory(String title, String description, Priority priority, StorySize size, Member assignee);

    Member createMember(String name, String username, String password);

    Board createBoard(String name);

    Team createTeam(String name);

    boolean hasLoggedInMember();
    void addMemberToTeam(String memberUsername, String teamName);
    void addMember(Member member);

    void assignTaskToMember(Member member, Task task);

    void login(Member member);

    void logout();

    Comment createComment(String content, Member author);

    Member getLoggedInMember();
}
