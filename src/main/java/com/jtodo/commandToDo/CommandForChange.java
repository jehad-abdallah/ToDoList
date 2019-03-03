package com.jtodo.commandToDo;

import com.jtodo.statusOfWork.IWorkStatus;
import com.jtodo.viewController.IViewController;

import static com.jtodo.commandToDo.utils.CommandUtils.*;

public class CommandForChange implements ICommand {
    private final IViewController viewer;

    public CommandForChange(IViewController viewer) {
        this.viewer = viewer;
    }

    @Override
    public void execute(String[] args) throws Exception {
        if (args.length == CHANGE_COMMAND_SIZE && args[1].equals(STATUS)) {
            IWorkStatus status = defineStatus(args[3]);
            if (status != null) {
                viewer.peekView().changeStatusOfWork(Integer.parseInt(args[2]), status);
            } else {
                throw new Exception(UNKNOWN_STATUS_ERROR_MSG + STATUS_USAGE_EXAMPLE);
            }
        } else {
            throw new Exception(ARGS_COUNT_ERROR_MSG + CHANGE_STATUS_USAGE_EXAMPLE);
        }
    }
}
