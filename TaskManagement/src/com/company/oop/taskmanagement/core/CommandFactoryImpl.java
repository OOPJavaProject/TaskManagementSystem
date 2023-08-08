package com.company.oop.taskmanagement.core;

import com.company.oop.taskmanagement.commands.*;
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
//                    return new ShowMembersCommand(taskRepository);
//                case SHOWMEMBERACTIVITY:
//                    return new ShowMemberActivityCommand(taskRepository);
                case CREATETEAM:
                    return new CreateTeamCommand(taskManagementRepository);
//                case SHOWTEAMS:
//                    return new ShowTeamsCommand(taskRepository);
//                case SHOWTEAMACTIVITY:
//                    return new ShowTeamActivityCommand(taskRepository);
//                case ADDMEMBERTOTEAM:
//                    return new AddMemberToTeamCommand(taskRepository);
//                case SHOWALLTEAMMEMBERS:
//                    return new ShowAllTeamMembersCommand(taskRepository);
//                case CREATEBOARD:
//                    return new CreateBoardCommand(taskRepository);
//                case SHOWTEAMBOARDS:
//                    return new ShowTeamBoardsCommand(taskRepository);
//                case SHOWBOARDACTIVITY:
//                    return new ShowBoardActivityCommand(taskRepository);
                case CREATEBUG:
                    return new CreateBugCommand(taskManagementRepository);
                case CREATEFEEDBACK:
                    return new CreateFeedbackCommand(taskManagementRepository);
                case CREATESTORY:
                    return new CreateStoryCommand(taskManagementRepository);
//                case CHANGEPRIORITY:
//                    return new ChangePriorityCommand(taskRepository);
//                case CHANGESEVERITY:
//                    return new ChangeSeverityCommand(taskRepository);
//                case CHANGESTATUS:
//                    return new ChangeStatusCommand(taskRepository);
//                case CHANGESIZE:
//                    return new ChangeSizeCommand(taskRepository);
//                case CHANGERATING:
//                    return new ChangeRatingCommand(taskRepository);
//                case ASSIGN:
//                    return new AssignCommand(taskRepository);
//                case UNASSIGN:
//                    return new UnassignCommand(taskRepository);
                case ADDCOMMENT:
                    return new AddCommentCommand(taskManagementRepository);
                default:
                    throw new IllegalArgumentException();
            }
    }
}
