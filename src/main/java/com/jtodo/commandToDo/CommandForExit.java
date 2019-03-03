package com.jtodo.commandToDo;

import com.jtodo.viewController.IViewController;

public class CommandForExit implements ICommand {
    private final IViewController viewer;

    public CommandForExit(IViewController viewer) {
        this.viewer = viewer;
    }

    @Override
    public void execute(String[] args) {
        viewer.popView();
    }
}
