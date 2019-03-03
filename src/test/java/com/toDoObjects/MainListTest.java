package com.toDoObjects;

import com.jtodo.toDoList.IToDoList;
import com.jtodo.toDoList.ToDoList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.jtodo.headList.*;
import com.jtodo.statusOfWork.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MainListTest {
    private HeadList mainList;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void init() {
        System.setOut(new PrintStream(outContent));
        mainList = new HeadList();
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Test
    public void canConstructedFromOtherList() {
        List<IToDoList> testList = new ArrayList<>();
        HeadList list = new HeadList(testList);
        assertEquals(list.getLists(), testList);
    }

    @Test
    public void canCreateToDoList() {
        mainList.addNewList("New list");
        assertEquals(mainList.getLists().get(0).getName(), "New list");
    }

    @Test
    public void canCreateSameToDoList() {
        mainList.addNewList("New list");
        mainList.addNewList("New list");
        String msg = "This list is already exist.";
        assertEquals("This list is already exist.", msg);
    }

    @Test
    public void canRenameList(){
        mainList.addNewList("Other list");
        mainList.rename(1, "New list");
        assertEquals(mainList.getLists().get(0).getName(), "New list");
    }

    @Test
    public void canOpenList() {
        mainList.addNewList("New list");
        ToDoList toDoList = new ToDoList("New list");
        assertEquals(mainList.openList(1), toDoList);
    }

    @Test
    public void cantOpenNonexistentList() {
        assertNull(mainList.openList(1));
    }

    @Test
    public void canDeleteList() throws Exception {
        mainList.addNewList("List");
        mainList.deleteList(1);
    }

    @Test
    public void cantCreateDeal() {

        try {
            mainList.addNewWork("deal");
        } catch (Exception e) {
            String error = e.getMessage();
            assertEquals("Sorry, but you can't create deal here.", error);
        }
    }

    @Test
    public void cantDeleteDeal() {
        try {
            mainList.deleteWork(1);
        } catch (Exception e) {
            String error = e.getMessage();
            assertEquals("Sorry, but you can't delete deal here.", error);
        }
    }


    @Test
    public void cantChangeDealStatus() {
        IWorkStatus status = new WorkStatus("Completed");
        try {
            mainList.changeStatusOfWork(1,status);
        } catch (Exception e) {
            String error = e.getMessage();
            assertEquals("Sorry, but you can't change statusOfWork here.", error);
        }
    }

    @Test
    public void cantGetMainListName() {
        assertNull(mainList.getName());
    }

    @Test
    public void cantGetStatus() {
        assertNull(mainList.getStatus());
    }

    @Test
    public void canPrintMainListToString() {
        mainList.addNewList("Other list");
        System.out.println(mainList.toString());

    }
    @Test
    public void canPrintEmptyList() {
        System.out.println(mainList.toString());
    }
}