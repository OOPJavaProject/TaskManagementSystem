package com.company.oop.taskmanagement.commands;

import com.company.oop.taskmanagement.core.contracts.TaskRepository;
import com.company.oop.taskmanagement.models.contracts.Comment;
import com.company.oop.taskmanagement.models.contracts.Member;
import com.company.oop.taskmanagement.models.tasks.contracts.Task;
import com.company.oop.taskmanagement.utilities.ParsingHelpers;
import com.company.oop.taskmanagement.utilities.Validation;

import java.util.List;

//public class AddMemberToTeamCommand extends BaseCommand{

//    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;
//    public static final String INVALID_PRICE = "Invalid value for price. Should be a number.";
//    public static final String INVALID_SEATS_MESSAGE = "Invalid seats. Expected a number.";
//    public static final String INVALID_WEIGHT_MESSAGE = "Invalid weight capacity. Expected a number.";
//    public static final String CANNOT_CREATE_THIS_TYPE_OF_VEHICLE = "Cannot create this type of vehicle.";
//    public final static String VEHICLE_ADDED_SUCCESSFULLY = "%s added vehicle successfully!";
//
//    public AddMemberToTeamCommand(TaskRepository taskRepository) {
//        super(taskRepository);
//    }
//
//    @Override
//    protected String executeCommand(List<String> parameters) {
//        Validation.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
//        String name = parameters.get(0);
//        String username = parameters.get(1);
//        String password = parameters.get(2);
//
//        return addMember(name, username, password);
//    }
//
//    private String addMember(String name, String username, String password) {
//        Member member = createMember(name, username,password);
//
//        getTaskRepository().getLoggedInMember().addMember(member);
//
//        return String.format(VEHICLE_ADDED_SUCCESSFULLY, getTaskRepository().getLoggedInMember().getUsername());
//    }
//
//    private Member createMember(String name, String username, String password) {
//        return getTaskRepository().createMember(name,username,password);
//        }
//
//    @Override
//    protected boolean requiresLogin() {
//        return true;
//    } //TODO NOT SURE HOW TO DO THIS
//}
