package com.jtodo.commandToDo;

import com.jtodo.viewController.IViewController;

import static com.jtodo.commandToDo.utils.CommandUtils.*;

public class CommandForRename implements ICommand {
    private final IViewController viewer;

    public CommandForRename(IViewController viewer) {
        this.viewer = viewer;
    }

    @Override
    public void execute(String[] args) throws Exception {
        if (args.length < MIN_RENAME_COMMAND_SIZE) {
            throw new Exception(ARGS_COUNT_ERROR_MSG + RENAME_USAGE_EXAMPLE);
        }
        viewer.peekView().rename(Integer.parseInt(args[1]), findCommandArgs(args, 2, args.length));
    }
}
