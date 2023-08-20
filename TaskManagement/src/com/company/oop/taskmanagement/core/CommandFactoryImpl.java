package com.company.oop.taskmanagement.core;

import com.company.oop.taskmanagement.commands.*;
import com.company.oop.taskmanagement.commands.ChangePriorityCommand;
import com.company.oop.taskmanagement.commands.ChangeRatingCommand;
import com.company.oop.taskmanagement.commands.CreateCommands.CreateBugCommand;
import com.company.oop.taskmanagement.commands.CreateCommands.CreateFeedbackCommand;
import com.company.oop.taskmanagement.commands.CreateCommands.CreateStoryCommand;
import com.company.oop.taskmanagement.commands.CreateCommands.CreateTeamCommand;
import com.company.oop.taskmanagement.commands.ShowCommands.ShowActivityComands.ShowMemberActivityCommand;
import com.company.oop.taskmanagement.commands.ShowCommands.ShowAllTeamMembersCommand;
import com.company.oop.taskmanagement.commands.ShowCommands.ShowTeamBoardsCommand;
import com.company.oop.taskmanagement.commands.TaskChangesCommands.ChangeSeverityCommand;
import com.company.oop.taskmanagement.commands.TaskChangesCommands.ChangeStatusCommand;
import com.company.oop.taskmanagement.commands.TaskChangesCommands.ChangeStorySizeCommand;
import com.company.oop.taskmanagement.commands.contracts.Command;
import com.company.oop.taskmanagement.commands.enums.CommandType;
import com.company.oop.taskmanagement.core.contracts.CommandFactory;
import com.company.oop.taskmanagement.core.contracts.TaskManagementRepository;
import com.company.oop.taskmanagement.utilities.ParsingHelpers;

public class CommandFactoryImpl implements CommandFactory {

    @Override
    public Command createCommandFromCommandName(String commandTypeAsString, TaskManagementRepository taskRepository) {
        CommandType commandType = ParsingHelpers.tryParseEnum(commandTypeAsString, CommandType.class);
        switch (commandType) {
            case REGISTERMEMBER:
                return new RegisterMemberCommand(taskRepository);
            case LOGIN:
                return new LoginCommand(taskRepository);
            case LOGOUT:
                return new LogoutCommand(taskRepository);
            case SHOWMEMBERS:
                return new ShowMembersCommand(taskRepository);
            case SHOWMEMBERACTIVITY:
                return new ShowMemberActivityCommand(taskRepository);
            case CREATETEAM:
                return new CreateTeamCommand(taskRepository);
            case SHOWTEAMS:
                return new ShowTeamsCommand(taskRepository);
//                case SHOWTEAMACTIVITY:
//                    return new ShowTeamActivityCommand(taskRepository);
            case ADDMEMBERTOTEAM:
                return new AddMemberToTeamCommand(taskRepository);
            case SHOWALLTEAMMEMBERS:
                return new ShowAllTeamMembersCommand(taskRepository);
            case CREATEBOARD:
                return new CreateBoardCommand(taskRepository);
            case SHOWTEAMBOARDS:
                return new ShowTeamBoardsCommand(taskRepository);
            case SHOWBOARDACTIVITY:
                return new ShowBoardActivityCommand(taskRepository);
            case CREATEBUG:
                return new CreateBugCommand(taskRepository);
            case CREATEFEEDBACK:
                return new CreateFeedbackCommand(taskRepository);
            case CREATESTORY:
                return new CreateStoryCommand(taskRepository);
            case CHANGEPRIORITY:
                return new ChangePriorityCommand(taskRepository);
            case CHANGESEVERITY:
                return new ChangeSeverityCommand(taskRepository);
            case CHANGESTATUS:
                return new ChangeStatusCommand(taskRepository);
            case CHANGESIZE:
                return new ChangeStorySizeCommand(taskRepository);
            case CHANGERATING:
                return new ChangeRatingCommand(taskRepository);
            case ASSIGN:
                return new AssignCommand(taskRepository);
            case UNASSIGN:
                return new UnassignCommand(taskRepository);
            case ADDCOMMENT:
                return new AddCommentCommand(taskRepository);
            default:
                throw new IllegalArgumentException();
        }
    }
}
