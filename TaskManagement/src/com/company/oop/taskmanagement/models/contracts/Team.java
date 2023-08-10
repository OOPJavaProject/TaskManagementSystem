package com.company.oop.taskmanagement.models.contracts;

import java.util.List;

public interface Team {

    String getName();

    List<Member> getMembers();

    List<Board> getBoards();
    void addBoard(Board board);
    void removeBoard(Board board);
    String printMember();
    String printBoard();
    void removeMember(Member memberToRemove);
    String toString();

    void addMember(Member memberToAdd);



}
