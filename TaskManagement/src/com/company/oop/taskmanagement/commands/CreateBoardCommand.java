package com.company.oop.taskmanagement.commands;

import com.company.oop.taskmanagement.core.contracts.TaskManagementRepository;
import com.company.oop.taskmanagement.exceptions.ElementNotFoundException;
import com.company.oop.taskmanagement.models.contracts.Board;
import com.company.oop.taskmanagement.models.contracts.Team;
import com.company.oop.taskmanagement.utilities.Validation;

import java.util.List;

public class CreateBoardCommand extends BaseCommand{

    /**
     * creates a board in team
     * input: CreateBoard boardName teamName
     */

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String BOARD_CREATED = "Board with name %s in team %s was created!";
    public static final String EXISTING_BOARD_IN_TEAM = "There is an existing board with name %s in this team!";

    public CreateBoardCommand(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        Validation.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String boardName = parameters.get(0);
        String teamName = parameters.get(1);
        Team team = getTaskRepository().findTeamByName(teamName);

        for (Board board : team.getBoards()) {
            if (boardName.equals(board.getName())) {
                throw new IllegalArgumentException(String.format(EXISTING_BOARD_IN_TEAM, boardName));
            }
        }
            Board newBoard = team.createBoard(boardName);
            return String.format(BOARD_CREATED, newBoard.getName(), teamName);
    }
}
