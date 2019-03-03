package com.toDoObjects;

import com.jtodo.toDoList.ToDoList;
import com.jtodo.statusOfWork.*;
import com.jtodo.workClass.Work;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class WorkTest {
    private Work work;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void init() {
        System.setOut(new PrintStream(outContent));
        work = new Work("Main work");
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }


    @Test
    public void canCreateUndefinedDeal() {
        Work undefinedWork = new Work();
        assertEquals(undefinedWork.getName(), "Undefined");
        assertEquals(undefinedWork.getStatus().toString(), new InProcess().toString());
    }

    @Test
    public void canCreateDeal() {
        Work work = new Work("New");
        assertEquals(work.getName(), "New");
        assertEquals(work.getStatus().toString(), new InProcess().toString());
    }

    @Test
    public void canCreateDealWithStatus() {
        IWorkStatus CompletedStatus = new Completed();

        Work workWithStatus = new Work("New", CompletedStatus);
        assertEquals(workWithStatus.getName(), "New");
        assertEquals(workWithStatus.getStatus().toString(), new Completed().toString());
    }

    @Test
    public void cantPerformToDoListFunctions() {
        assertNull(work.openList(1));
    }

    @Test
    public void cantCreateList(){
        try {
            work.addNewList("name");
        } catch (Exception e) {
            String error = e.getMessage();
            assertEquals("Sorry, at the moment you are unable to create this list. (You are in another list)", error);
        }
    }

    @Test
    public void cantCreateDealInsideThem() {
        try {
            work.addNewWork("Work");
        } catch (Exception e) {
            String error = e.getMessage();
            assertEquals("Sorry, at the moment you are unable to create work. (You are in another list)", error);
        }
    }

    @Test
    public void cantDeleteDeal() {
        try {
            work.deleteWork(1);
        } catch (Exception e) {
            String error = e.getMessage();
            assertEquals("Sorry, at the moment you are unable to delete work. (You are in another list)", error);
        }
    }

    @Test
    public void cantDeleteList() {
        try {
            work.deleteList(4);
        } catch (Exception e) {
            String error = e.getMessage();
            assertEquals("Sorry, at the moment you are unable to delete this list. (You are in another list)", error);
        }
    }

    @Test
    public void cantDeleteLastList() {
        try {
            work.deleteLastList();
        } catch (Exception e) {
            String error = e.getMessage();
            assertEquals("you can't delete Last list here.", error);
        }
    }

    @Test
    public void cantDeleteLastWork() {
        try {
            work.deleteLastWork();
        } catch (Exception e) {
            String error = e.getMessage();
            assertEquals("Sorry, but you can't delete last deal here.", error);
        }
    }

    @Test
    public void cantDeleteAllList() {
        try {
            work.deleteAllList();
        } catch (Exception e) {
            String error = e.getMessage();
            assertEquals("Sorry, at the moment you are unable to delete this list. (You are in another list)", error);
        }
    }

    @Test
    public void canRenameDeal() {
        work.setName("New name");
        assertEquals(work.getName(), "New name");
    }

    @Test
    public void isNotEqualsOtherObject()
    {
        Object str = "String isn't equals Work";
        Object list = new ToDoList("List");

        assertTrue(!work.equals(str) || !work.equals(list));
    }

    @Test
    public void canToString() {
        String excepted = "Task1: In process";

        Work task = new Work("Task1");
        String dealToString = task.toString();

        assertEquals(excepted, dealToString);
    }

    @Test
    public void canSetName() {
        Work work = new Work();
        String currName = "Task True";

        work.setName(currName);
        assertEquals(work.getName(), currName);
    }

    @Test
    public void changeStatus() {
        Work work = new Work();

        IWorkStatus CompletedStatus = new Completed();

        assertNotEquals(work.getStatus(), CompletedStatus);
        work.setStatus(CompletedStatus);
        assertEquals(work.getStatus(), CompletedStatus);
    }
}