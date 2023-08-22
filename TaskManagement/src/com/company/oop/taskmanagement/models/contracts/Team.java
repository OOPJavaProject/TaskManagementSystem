package com.company.oop.taskmanagement.models.contracts;

import java.util.List;

public interface Team {

    String getName();

    List<Member> getMembers();

    List<Board> getBoards();

    Board createBoard(String name);

    void removeBoard(Board board);
    String printMembers();
    String printBoards();
    void addBoard(Board board);
    void removeMember(Member memberToRemove);
    String printHistory();
    String toString();

    void addMember(Member memberToAdd);



}
