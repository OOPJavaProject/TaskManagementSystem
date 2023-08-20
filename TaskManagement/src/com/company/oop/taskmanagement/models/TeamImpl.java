package com.company.oop.taskmanagement.models;

import com.company.oop.taskmanagement.models.contracts.Board;
import com.company.oop.taskmanagement.models.contracts.Member;
import com.company.oop.taskmanagement.models.contracts.Team;
import com.company.oop.taskmanagement.utilities.Validation;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class TeamImpl implements Team {

    public static final int TEAM_NAME_LEN_MIN = 5;
    public static final int TEAM_NAME_LEN_MAX = 15;
    private static final String TEAM_NAME_LEN_ERR = format(
            "Username must be between %d and %d characters long!",
            TEAM_NAME_LEN_MIN,
            TEAM_NAME_LEN_MAX);

    private static final String NO_MEMBERS_MESSAGE = "There are no members in this team.";
    private static final String MEMBER_PRINT_TEMPLATE = "Members of this team are: %s.";
    private static final String NO_BOARDS_MESSAGE = "This team has no boards." ;
    private static final String BOARD_PRINT_TEMPLATE = "Boards of this team are: %s.";

    private static final String TEAM_CREATED_LOG = "Team with name %s has been created";
    private static final String BOARD_ADDED_LOG = "Board with name %s has been added.";
    private static final String BOARD_REMOVED_LOG = "Board with name %s has been removed.";
    private static final String MEMBER_ADDED_LOG = "Member with name %s has been added.";
    private static final String MEMBER_REMOVED_LOG = "Member with name %s has been removed.";
    public static final String BOARD_EXISTS_MESSAGE = "Board with name %s already exists in this team.";

    private String teamName;
    private final List<Member> members = new ArrayList<>();

    private final List<Board> boards = new ArrayList<>();

    private final List<EventLog> history = new ArrayList<>();

    public TeamImpl(String name) {
        setTeamName(name);
        logEvent(String.format(TEAM_CREATED_LOG, name));
    }


    @Override
    public String getName() {
        return this.teamName;
    }

    @Override
    public List<Member> getMembers() {
        return new ArrayList<>(members);
    }

    @Override
    public List<Board> getBoards() {
        return new ArrayList<>(boards);
    }

    @Override
    public void addMember(Member memberToAdd) {
        this.members.add(memberToAdd);
        logEvent(String.format(MEMBER_ADDED_LOG, memberToAdd.getName()));
    }
    @Override
    public void removeMember(Member memberToRemove) {
        this.members.remove(memberToRemove);
        logEvent(String.format(MEMBER_REMOVED_LOG, memberToRemove.getName()));
    }

    @Override
    public Board createBoard(String name) {
        Board board = new BoardImpl(name);
        this.boards.add(board);
        logEvent(String.format(BOARD_ADDED_LOG, board.getName()));
        return board;
    }

    @Override
    public void removeBoard(Board board) {
        this.boards.remove(board);
        logEvent(String.format(BOARD_REMOVED_LOG, board.getName()));
    }

    public String printMember() {
        if (getMembers().isEmpty()) {
            throw new IllegalArgumentException(NO_MEMBERS_MESSAGE);
        }
        StringBuilder memberNames = new StringBuilder();
        if(getMembers().size() == 1){
            memberNames.append(String.format(" %s.", getMembers().get(0).getName()));
        }
        else {
            for (int i = 0; i < getMembers().size() - 1; i++) {
                memberNames.append(String.format("%s, ", getMembers().get(i).getName()));
            }
            memberNames.append((String.format("%s", getMembers().get(getMembers().size() - 1).getName())));
        }
        return String.format(MEMBER_PRINT_TEMPLATE, memberNames);
    }

    public String printBoard(){
        if (getBoards().isEmpty()) {
            throw new IllegalArgumentException(NO_BOARDS_MESSAGE);
        }
        StringBuilder boardNames = new StringBuilder();

        if(getBoards().size() == 1){
            boardNames.append(String.format(" %s.", getBoards().get(0).getName()));
        }
        else {
            for (int i = 0; i < getBoards().size() - 1; i++) {
                boardNames.append(String.format("%s, ", getBoards().get(i).getName()));
            }
            boardNames.append((String.format("%s.", getBoards().get(getBoards().size() - 1).getName())));
        }
        return String.format(BOARD_PRINT_TEMPLATE, boardNames);
    }

    @Override
    public String toString(){
        return String.format("""
                %n%s""", getName());
    }
    private void logEvent(String event) {
        this.history.add(new EventLog(event));
    }

    private void setTeamName(String name) {
        Validation.validateStringRange(name, TEAM_NAME_LEN_MIN, TEAM_NAME_LEN_MAX, TEAM_NAME_LEN_ERR);
        this.teamName = name;
    }
}
