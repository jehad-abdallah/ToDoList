package com.handlers;

import com.jtodo.controllers.CommandController;
import com.jtodo.controllers.ICommandController;
import com.jtodo.headList.IHeadList;
import com.jtodo.headList.HeadList;
import com.jtodo.viewController.IViewController;
import com.jtodo.viewController.ViewController;
import org.junit.Before;
import org.junit.Test;

import static com.jtodo.commandToDo.utils.CommandUtils.getCommands;
import static org.junit.Assert.assertTrue;

public class CommandHandlerTest {
    private ICommandController commandHandler;

    @Before
    public void init() {
        IHeadList mainList = new HeadList();
        IViewController viewController = new ViewController();
        commandHandler = new CommandController(getCommands(viewController));
        viewController.addToViewerNewObj(mainList);

    }

    @Test
    public void testCreateList() {
        assertTrue(commandHandler.controlCommand("create list Test"));
    }


    @Test
    public void testOpenList() {
        assertTrue(commandHandler.controlCommand("create list Test"));
        assertTrue(commandHandler.controlCommand("open 1"));
    }

    @Test
    public void testCreateDeal() {
        assertTrue(commandHandler.controlCommand("create list Test"));
        assertTrue(commandHandler.controlCommand("open 1"));
        assertTrue(commandHandler.controlCommand("create work new"));
    }

    @Test
    public void testRenameList() {
        assertTrue(commandHandler.controlCommand("create list Test"));
        assertTrue(commandHandler.controlCommand("rename 1 name"));
    }

    @Test
    public void testRenameDeal() {
        assertTrue(commandHandler.controlCommand("create list Test"));
        assertTrue(commandHandler.controlCommand("open 1"));
        assertTrue(commandHandler.controlCommand("create work deal"));
        assertTrue(commandHandler.controlCommand("rename 1 name"));
    }

    @Test
    public void testDeleteDeal() {
        assertTrue(commandHandler.controlCommand("create list Test"));
        assertTrue(commandHandler.controlCommand("open 1"));
        assertTrue(commandHandler.controlCommand("create work deal"));
        assertTrue(commandHandler.controlCommand("delete work 1"));
    }


    @Test
    public void testDeleteList() {
        assertTrue(commandHandler.controlCommand("create list Test"));
        assertTrue(commandHandler.controlCommand("delete list 1"));
    }

    @Test
    public void testExitDeal() {
        assertTrue(commandHandler.controlCommand("create list Test"));
        assertTrue(commandHandler.controlCommand("open 1"));
        assertTrue(commandHandler.controlCommand("exit"));
    }

    @Test
    public void testExitProg() {
        assertTrue(commandHandler.controlCommand("create list Test"));
        assertTrue(commandHandler.controlCommand("open 1"));
        assertTrue(commandHandler.controlCommand("exit"));
        assertTrue(commandHandler.controlCommand("exit"));
    }

    @Test
    public void testChange() {
        assertTrue(commandHandler.controlCommand("create list Test"));
        assertTrue(commandHandler.controlCommand("open 1"));
        assertTrue(commandHandler.controlCommand("create work deal"));
        assertTrue(commandHandler.controlCommand("change status 1 completed"));
        assertTrue(commandHandler.controlCommand("change status 1 process"));
    }


}
