package com.jtodo.commandToDo;

import com.jtodo.commandToDo.utils.CommandUtils;
import com.jtodo.viewController.IViewController;

import static com.jtodo.commandToDo.utils.CommandUtils.*;

public class CommandForCreate implements ICommand {
    private final IViewController viewer;

    public CommandForCreate(IViewController viewer) {
        this.viewer = viewer;
    }

    @Override
    public void execute(String[] commandArr) throws Exception {
        if (commandArr.length != MIN_CREATE_COMMAND_SIZE) {
            throw new Exception(ARGS_COUNT_ERROR_MSG + CREATE_USAGE_EXAMPLE);
        }

        String commandArgs = CommandUtils.findCommandArgs(commandArr, 2, commandArr.length);
        if (commandArr[1].equals(LIST)) {
                viewer.peekView().addNewList(commandArgs);
        }else{
            if(commandArr[1].equals(WORK)){
                viewer.peekView().addNewWork(commandArgs);
            }
            else
                throw new Exception(INVALID_COMMAND_ERROR_MSG + CREATE_USAGE_EXAMPLE);
        }
    }
}
