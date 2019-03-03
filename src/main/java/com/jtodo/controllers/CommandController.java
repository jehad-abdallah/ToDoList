package com.jtodo.controllers;

import com.jtodo.commandToDo.ICommand;

import java.util.Map;

import static com.jtodo.commandToDo.utils.CommandUtils.*;

public class CommandController implements ICommandController {
    private final Map<String, ICommand> commands;

    //constructor
    public CommandController(Map<String, ICommand> commands) {
        this.commands = commands;
    }

    @Override
    public boolean controlCommand(String commandLine) {
        boolean result = true;
        String[] arguments = commandLine.split(" ");
        try {
            if (arguments.length < MinCommands || !commands.containsKey(arguments[0])) {
                throw new Exception(UnknownCommand);
            }

            ICommand command = commands.get(arguments[0]);
            command.execute(arguments);
        } catch (NumberFormatException ex) {
            System.out.println(UncorrectInput + ex.getMessage());
            result = false;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            result = false;
        }
        return result;
    }
}