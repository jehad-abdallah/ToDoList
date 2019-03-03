package com.jtodo.commandToDo;

import com.jtodo.viewController.IViewController;
import static com.jtodo.commandToDo.utils.CommandUtils.*;

public class CommandForDelete implements ICommand {
    private final IViewController viewer;

    public CommandForDelete(IViewController viewer) {
        this.viewer = viewer;
    }

    @Override
    public void execute(String[] args) throws Exception {
        switch (args[1]) {
            case LIST:
                viewer.peekView().deleteList(Integer.parseInt(args[2]));
                break;
            case WORK:
                viewer.peekView().deleteWork(Integer.parseInt(args[2]));
                break;
            case ALL_WORKS:
                viewer.peekView().deleteAllWork();
                break;
            case ALL_LISTS:
                viewer.peekView().deleteAllList();
                break;
            case LAST_LIST:
                viewer.peekView().deleteLastList();
                break;
            case LAST_WORK:
                viewer.peekView().deleteLastWork();
                break;
            default:
                throw new Exception(INVALID_COMMAND_ERROR_MSG + DELETE_USAGE_EXAMPLE);
        }
    }
}
