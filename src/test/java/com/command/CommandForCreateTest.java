package com.command;

import com.jtodo.commandToDo.CommandForCreate;
import com.jtodo.maintInterfaceOfToDoObjects.IMainToDoObject;
import com.jtodo.toDoList.ToDoList;
import com.jtodo.headList.*;
import com.jtodo.viewController.IViewController;
import com.jtodo.viewController.ViewController;
import com.jtodo.workClass.Work;
import org.junit.Test;

public class CommandForCreateTest {
    private final IMainToDoObject mainList = new HeadList();
    private final IViewController viewController = new ViewController();

    @Test(expected = Exception.class)
    public void failsWithInsufficientArguments() throws Exception {
        viewController.addToViewerNewObj(mainList);
        CommandForCreate command = new CommandForCreate(viewController);

        String[] data = { "create", "list" };
        command.execute(data);
    }

    @Test(expected = Exception.class)
    public void failsWithExcessiveArguments() throws Exception {
        viewController.addToViewerNewObj(mainList);
        CommandForCreate command = new CommandForCreate(viewController);

        String[] data = { "create", "list", "list", "boom" };
        command.execute(data);
    }

    @Test(expected = Exception.class)
    public void failsCreateDealWhenPointerIntoMainList() throws Exception {
        viewController.addToViewerNewObj(mainList);
        CommandForCreate command = new CommandForCreate(viewController);

        String[] data = { "create", "deal", "Task1" };
        command.execute(data);
    }

    @Test(expected = Exception.class)
    public void failsCreateUnknownEntity() throws Exception {
        viewController.addToViewerNewObj(mainList);
        CommandForCreate command = new CommandForCreate(viewController);

        String[] data = { "create", "boom", "Boom" };
        command.execute(data);
    }

    @Test
    public void successCreateDealWhenPointerINtoToDoList() throws Exception {
        viewController.addToViewerNewObj(new ToDoList());
        CommandForCreate command = new CommandForCreate(viewController);

        String[] data = { "create", "work", "Task1" };
        command.execute(data);
    }

    @Test
    public void successCreateListWhenPointerInMainList() throws Exception {
        viewController.addToViewerNewObj(new HeadList());
        CommandForCreate command = new CommandForCreate(viewController);

        String[] data = { "create", "list", "Task1" };
        command.execute(data);
    }

    @Test(expected = Exception.class)
    public void failsCreateListWhenPointerINtoToDoList() throws Exception {
        viewController.addToViewerNewObj(new ToDoList());
        CommandForCreate command = new CommandForCreate(viewController);

        String[] data = { "create", "list", "Task1" };
        command.execute(data);
    }

    @Test(expected = Exception.class)
    public void failsCreateDealWhenPointerIntoDeal() throws Exception {
        viewController.addToViewerNewObj(new Work());
        CommandForCreate command = new CommandForCreate(viewController);

        String[] data = { "create", "deal", "Task1" };
        command.execute(data);
    }

    @Test(expected = Exception.class)
    public void failsCreateListWhenPointerIntoDeal() throws Exception {
        viewController.addToViewerNewObj(new Work());
        CommandForCreate command = new CommandForCreate(viewController);

        String[] data = { "create", "list", "Task1" };
        command.execute(data);
    }
}
