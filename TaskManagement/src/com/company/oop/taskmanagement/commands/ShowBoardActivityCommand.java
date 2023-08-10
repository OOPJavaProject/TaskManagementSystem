package com.company.oop.taskmanagement.commands;

import com.company.oop.taskmanagement.core.contracts.TaskManagementRepository;
import com.company.oop.taskmanagement.exceptions.ElementNotFoundException;
import com.company.oop.taskmanagement.models.contracts.Board;
import com.company.oop.taskmanagement.models.contracts.Team;
import com.company.oop.taskmanagement.utilities.Validation;

import java.util.List;

public class ShowBoardActivityCommand extends BaseCommand{

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String NO_BOARD_IN_TEAM_MSG = "There is no board with name %s in team %s";

    public ShowBoardActivityCommand(TaskManagementRepository taskManagementRepository) {
        super(taskManagementRepository);
    }
    @Override
    protected String executeCommand(List<String> parameters) {
        Validation.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);
        String teamName = parameters.get(0);
        String boardName = parameters.get(1);
        Team team = getTaskRepository().findTeamByName(teamName);
        Board board = getTaskRepository().findBoardByName(boardName);

        return showActivityHistory(team, board);
    }

    private String showActivityHistory(Team team, Board board) {
        if (team.getBoards().contains(board)) {
            return board.printHistory();
        } else {
            throw new ElementNotFoundException(String.format(NO_BOARD_IN_TEAM_MSG, team.getName(), board.getName()));
        }
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }
}
