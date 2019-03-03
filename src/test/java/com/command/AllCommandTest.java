package com.command;

import com.jtodo.commandToDo.ICommand;
import com.jtodo.headList.IHeadList;
import com.jtodo.maintInterfaceOfToDoObjects.IMainToDoObject;
import com.jtodo.headList.HeadList;
import com.jtodo.viewController.IViewController;
import com.jtodo.viewController.ViewController;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

import static com.jtodo.commandToDo.utils.CommandUtils.*;

public class AllCommandTest {
    private static final IViewController controller = new ViewController();
    private static final Map<String, ICommand> commands = getCommands(controller);

    @Test(expected = Exception.class)
    public void createList() throws Exception {
        controller.addToViewerNewObj(new HeadList());
        String[] commandLine = {"create", "list", "Liston"};
        commands.get(CREATE_COMMAND).execute(commandLine);
        IMainToDoObject obj = controller.peekView();
        Assert.assertTrue(obj instanceof IHeadList);
        Assert.assertEquals(1, ((IHeadList) obj).getLists().size());
        String[] commandLineError = {"create", "list", "Liston", "List"};
        commands.get(CREATE_COMMAND).execute(commandLineError);
        throw new AssertionError("Error: execute commandToDo with invalid argument");
    }

    @Test
    public void emptyTest()
    {
        controller.display();
        boolean isEmpty = controller.empty();
        Assert.assertTrue(isEmpty);
    }
}
