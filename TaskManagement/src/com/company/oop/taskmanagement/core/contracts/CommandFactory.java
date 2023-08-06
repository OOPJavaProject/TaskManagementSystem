package com.company.oop.taskmanagement.core.contracts;

import com.company.oop.taskmanagement.commands.contracts.Command;

public interface CommandFactory {

        Command createCommandFromCommandName(String commandTypeAsString, TaskRepository taskRepository);

}
