package com.company.oop.taskmanagement.core;

import com.company.oop.taskmanagement.commands.*;
import com.company.oop.taskmanagement.commands.CreateCommands.CreateBugCommand;
import com.company.oop.taskmanagement.commands.CreateCommands.CreateFeedbackCommand;
import com.company.oop.taskmanagement.commands.CreateCommands.CreateStoryCommand;
import com.company.oop.taskmanagement.commands.CreateCommands.CreateTeamCommand;
import com.company.oop.taskmanagement.commands.TaskChangesCommands.ChangePriorityCommand;
import com.company.oop.taskmanagement.commands.TaskChangesCommands.ChangeSeverityCommand;
import com.company.oop.taskmanagement.commands.contracts.Command;
import com.company.oop.taskmanagement.commands.enums.CommandType;
import com.company.oop.taskmanagement.core.contracts.CommandFactory;
import com.company.oop.taskmanagement.core.contracts.TaskManagementRepository;
import com.company.oop.taskmanagement.utilities.ParsingHelpers;

public class CommandFactoryImpl implements CommandFactory {

    @Override
    public Command createCommandFromCommandName(String commandTypeAsString, TaskManagementRepository taskManagementRepository) {
        CommandType commandType = ParsingHelpers.tryParseEnum(commandTypeAsString, CommandType.class);
            switch (commandType) {
                case REGISTERMEMBER:
                    return new RegisterMemberCommand(taskManagementRepository);
                case LOGIN:
                    return new LoginCommand(taskManagementRepository);
                case LOGOUT:
                    return new LogoutCommand(taskManagementRepository);
//                case SHOWMEMBERS:
//                    return new ShowMembersCommand(taskManagementRepository);
//                case SHOWMEMBERACTIVITY:
//                    return new ShowMemberActivityCommand(taskManagementRepository);
                case CREATETEAM:
                    return new CreateTeamCommand(taskManagementRepository);
//                case SHOWTEAMS:
//                    return new ShowTeamsCommand(taskManagementRepository);
//                case SHOWTEAMACTIVITY:
//                    return new ShowTeamActivityCommand(taskManagementRepository);
//                case ADDMEMBERTOTEAM:
//                    return new AddMemberToTeamCommand(taskManagementRepository);
//                case SHOWALLTEAMMEMBERS:
//                    return new ShowAllTeamMembersCommand(taskManagementRepository);
//                case CREATEBOARD:
//                    return new CreateBoardCommand(taskManagementRepository);
//                case SHOWTEAMBOARDS:
//                    return new ShowTeamBoardsCommand(taskManagementRepository);
//                case SHOWBOARDACTIVITY:
//                    return new ShowBoardActivityCommand(taskManagementRepository);
                case CREATEBUG:
                    return new CreateBugCommand(taskManagementRepository);
                case CREATEFEEDBACK:
                    return new CreateFeedbackCommand(taskManagementRepository);
                case CREATESTORY:
                    return new CreateStoryCommand(taskManagementRepository);
                case CHANGEPRIORITY:
                    return new ChangePriorityCommand(taskManagementRepository);
                case CHANGESEVERITY:
                    return new ChangeSeverityCommand(taskManagementRepository);
//                case CHANGESTATUS:
//                    return new ChangeStatusCommand(taskManagementRepository);
//                case CHANGESIZE:
//                    return new ChangeSizeCommand(taskManagementRepository);
//                case CHANGERATING:
//                    return new ChangeRatingCommand(taskManagementRepository);
//                case ASSIGN:
//                    return new AssignCommand(taskManagementRepository);
//                case UNASSIGN:
//                    return new UnassignCommand(taskManagementRepository);
                case ADDCOMMENT:
                    return new AddCommentCommand(taskManagementRepository);
                default:
                    throw new IllegalArgumentException();
            }
    }
}
