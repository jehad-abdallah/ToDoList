package com.jtodo.commandToDo;

import com.jtodo.maintInterfaceOfToDoObjects.IMainToDoObject;
import com.jtodo.viewController.IViewController;

import static com.jtodo.commandToDo.utils.CommandUtils.*;

public class CommandForOpen implements ICommand {
    private final IViewController viewer;

    public CommandForOpen(IViewController viewer) {
        this.viewer = viewer;
    }

    @Override
    public void execute(String[] args) throws Exception {
        if (args.length != OPEN_COMMAND_SIZE) {
            throw new Exception(ARGS_COUNT_ERROR_MSG + OPEN_USAGE_EXAMPLE);
        }

        int num = Integer.parseInt(args[1]);

        IMainToDoObject obj = viewer.peekView().openList(num);
        if (obj != null) {
            viewer.addToViewerNewObj(obj);
        } else {
            throw new Exception(INVALID_COMMAND_ERROR_MSG);
        }
    }
}
