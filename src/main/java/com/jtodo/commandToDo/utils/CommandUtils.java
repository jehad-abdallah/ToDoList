package com.jtodo.commandToDo.utils;

import com.jtodo.commandToDo.*;
import com.jtodo.statusOfWork.Completed;
import com.jtodo.statusOfWork.IWorkStatus;
import com.jtodo.statusOfWork.InProcess;
import com.jtodo.viewController.IViewController;

import java.util.HashMap;
import java.util.Map;

public class CommandUtils {
    public static final int MinCommands = 1;
    public static final int MIN_CREATE_COMMAND_SIZE = 3;
    public static final int MIN_RENAME_COMMAND_SIZE = 3;
    public static final int CHANGE_COMMAND_SIZE = 4;
    public static final int OPEN_COMMAND_SIZE = 2;
    public static final int SHOW_COMMAND_SIZE = 2;

    public static final String CREATE_COMMAND = "create";
    private static final String OPEN_COMMAND = "open";
    private static final String DELETE_COMMAND = "delete";
    private static final String RENAME_COMMAND = "rename";
    private static final String CHANGE_COMMAND = "change";
    private static final String EXIT_COMMAND = "exit";

    public static final String LIST = "list";
    public static final String WORK = "work";
    public static final String ALL_WORKS = "works";
    public static final String ALL_LISTS = "lists";
    public static final String LAST_WORK = "lastw";
    public static final String LAST_LIST = "lastl";
    public static final String STATUS = "status";
    private static final String STATUS_IN_PROCESS = "process";
    private static final String STATUS_COMPLETED = "completed";
    private static final String SHOW_COMPLETED = "showCompleted";
    private static final String SHOW_IN_PROCESS = "showInProcess";

    public static final String ARGS_COUNT_ERROR_MSG = "Invalid args count.\n";
    public static final String INVALID_COMMAND_ERROR_MSG = "Invalid commandToDo.\n";
    public static final String NO_LIST = "There is no such list.\n";
    public static final String UNKNOWN_STATUS_ERROR_MSG = "Unknown statusOfWork.\n";
    public static final String UnknownCommand = "Unknown commandToDo.\n";
    public static final String SHOW_USAGE_EXAMPLE = "Use: <showCompleted|showInProcess> <number>.\n";
    public static final String CREATE_USAGE_EXAMPLE = "Use: create <list|work> <name>.\n";
    public static final String OPEN_USAGE_EXAMPLE = "Use: open <number>.\n";
    public static final String DELETE_USAGE_EXAMPLE = "Use: delete <list|work> <number>.\n";
    public static final String RENAME_USAGE_EXAMPLE = "Use: rename <number> <new name>.\n";
    public static final String STATUS_USAGE_EXAMPLE = "Use: \"in process\" or \"completed\".\n";
    public static final String CHANGE_STATUS_USAGE_EXAMPLE = "Use: change status <number> <" + STATUS_IN_PROCESS + "|" + STATUS_COMPLETED + ">.\n";

    public static final String UncorrectInput = "You need input number ";

    public static String findCommandArgs(String[] arr, int start, int end) {
        StringBuilder res = new StringBuilder();
        for (int i = start; i < end; i++) {
            if (res.length() > 0) {
                res.append(" ");
            }
            res.append(arr[i]);
        }
        return res.toString();
    }

    public static IWorkStatus defineStatus(String statusStr) {
        IWorkStatus status = null;
        switch (statusStr) {
            case STATUS_IN_PROCESS:
                status = new InProcess();
                break;
            case STATUS_COMPLETED:
                status = new Completed();
                break;
        }
        return status;
    }

    public static Map<String, ICommand> getCommands(IViewController viewer) {
        Map<String, ICommand> commands = new HashMap<>();
        commands.put(OPEN_COMMAND, new CommandForOpen(viewer));
        commands.put(CREATE_COMMAND, new CommandForCreate(viewer));
        commands.put(CHANGE_COMMAND, new CommandForChange(viewer));
        commands.put(DELETE_COMMAND, new CommandForDelete(viewer));
        commands.put(SHOW_COMPLETED, new CommandToShowCompletedTasks(viewer));
        commands.put(SHOW_IN_PROCESS, new CommandToShowTasksInProcess(viewer));
        commands.put(RENAME_COMMAND, new CommandForRename(viewer));
        commands.put(EXIT_COMMAND, new CommandForExit(viewer));
        return commands;
    }
}
