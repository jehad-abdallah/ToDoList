package com.command;

import com.jtodo.commandToDo.CommandForDelete;
import com.jtodo.commandToDo.ICommand;
import com.jtodo.toDoList.ToDoList;
import com.jtodo.headList.*;
import com.jtodo.viewController.*;
import com.jtodo.workClass.Work;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommandForDeleteTest {
    private final HeadList mainList = new HeadList();
    private final IViewController viewController = new ViewController();
    private final ICommand command = new CommandForDelete(viewController);

    @Test(expected = Exception.class)
    public void failsDeleteNotExistsList() throws Exception {
        viewController.addToViewerNewObj(mainList);

        String[] data = { "delete", "list", "1" };
        assertEquals(0, mainList.getLists().size());
        command.execute(data);
    }

    @Test(expected = Exception.class)
    public void failsDeleteNotExistsWork() throws Exception {
        viewController.addToViewerNewObj(mainList);

        String[] data = { "delete", "Work", "1" };
        assertEquals(0, mainList.getLists().size());
        command.execute(data);
    }

    @Test
    public void successDeleteExistsList() throws Exception {
        viewController.addToViewerNewObj(mainList);
        mainList.addNewList(new ToDoList("Task1"));
        mainList.addNewList(new ToDoList("Task3"));
        mainList.addNewList(new ToDoList("Task2"));


        String[] data = { "delete", "list", "1" };
        assertTrue(0 != mainList.getLists().size());
        command.execute(data);
    }


    @Test
    public void successDeleteExists() throws Exception {
        viewController.addToViewerNewObj(mainList);
        mainList.addNewList(new ToDoList("Task1"));
        mainList.addNewList(new ToDoList("Task2"));

        String[] data = { "delete", "list", "2" };
        int sizeBefore = mainList.getLists().size();
        command.execute(data);
        int sizeAfter = mainList.getLists().size();
        assertNotEquals(sizeBefore, sizeAfter);
    }

    @Test(expected = Exception.class)
    public void failsDeleteExistsWork() throws Exception {
        ToDoList list = new ToDoList();
        viewController.addToViewerNewObj(list);
        list.addNewWork(new Work("Task1"));
        list.addNewWork(new Work("Task3"));
        list.addNewWork(new Work("Task2"));


        String[] data = { "delete", "list", "1" };
        assertTrue(0 != list.getWorks().size());
        command.execute(data);
    }


    @Test
    public void successDeleteAllLists() throws Exception {
        viewController.addToViewerNewObj(mainList);
        mainList.addNewList(new ToDoList("Task1"));
        mainList.addNewList(new ToDoList("Task3"));
        mainList.addNewList(new ToDoList("Task2"));


        String[] data = { "delete", "lists"};
        assertTrue(0 != mainList.getLists().size());
        command.execute(data);
    }

    @Test(expected = Exception.class)
    public void failsDeleteAllExistsList() throws Exception {
        viewController.addToViewerNewObj(mainList);

        String[] data = { "delete", "lists"};
        assertEquals(0, mainList.getLists().size());
        command.execute(data);
    }

    @Test
    public void successDeleteAllWorks() throws Exception {
        ToDoList list = new ToDoList();
        viewController.addToViewerNewObj(list);
        list.addNewWork(new Work("Task1"));
        list.addNewWork(new Work("Task3"));
        list.addNewWork(new Work("Task2"));


        String[] data = { "delete", "works"};
        assertTrue(0 != list.getWorks().size());
        command.execute(data);
    }

    @Test(expected = Exception.class)
    public void failsDeleteAllExistsWork() throws Exception {
        viewController.addToViewerNewObj(mainList);

        String[] data = { "delete", "deals"};
        assertEquals(0, mainList.getLists().size());
        command.execute(data);
    }

    @Test
    public void successDeleteLastList() throws Exception {
        viewController.addToViewerNewObj(mainList);
        mainList.addNewList(new ToDoList("Task1"));
        mainList.addNewList(new ToDoList("Task3"));
        mainList.addNewList(new ToDoList("Task2"));


        String[] data = { "delete", "lastl"};
        assertTrue(0 != mainList.getLists().size());
        command.execute(data);
    }

    @Test(expected = Exception.class)
    public void failsDeleteLastExistsList() throws Exception {
        viewController.addToViewerNewObj(mainList);

        String[] data = { "delete", "lastl"};
        assertEquals(0, mainList.getLists().size());
        command.execute(data);
    }

    @Test
    public void successDeleteLastWork() throws Exception {
        ToDoList list = new ToDoList();
        viewController.addToViewerNewObj(list);
        list.addNewWork(new Work("Task1"));
        list.addNewWork(new Work("Task3"));
        list.addNewWork(new Work("Task2"));


        String[] data = { "delete", "lastw"};
        assertTrue(0 != list.getWorks().size());
        command.execute(data);
    }

    @Test(expected = Exception.class)
    public void failsDeleteLastExistsWork() throws Exception {
        viewController.addToViewerNewObj(mainList);

        String[] data = { "delete", "lastw"};
        assertEquals(0, mainList.getLists().size());
        command.execute(data);
    }

}
