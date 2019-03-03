package com.toDoObjects;

import com.jtodo.statusOfWork.*;
import com.jtodo.toDoList.IToDoList;
import com.jtodo.toDoList.ToDoList;
import com.jtodo.workClass.Work;
import com.jtodo.workClass.IWork;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class ToDoListTest {
    private IToDoList list;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void initialization() {
        list = new ToDoList("Test");
        Work work = new Work("Work");
        list.addNewWork(work);
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanStream() {
        System.setOut(null);
    }

    @Test
    public void TestMethodToString(){

        ToDoList toDoList = new ToDoList();
        assertEquals(toDoList.toString(), "Undefined\nyour todo list is empty.\nBut you can create new deals.");

        IWork deal = new Work();
        toDoList.addNewWork(deal);
        assertEquals(toDoList.toString(), "Undefined\n1. Undefined: In process\n");
    }

    @Test
    public void canCreateUndefinedToDoList() {
        ToDoList toDoList = new ToDoList();
        assertEquals(toDoList.getName(), "Undefined");
        assertEquals(0, toDoList.getWorks().size());
    }

    @Test
    public void canCreateDealInToDoList() throws Exception {
        list.addNewWork("deal");
        assertEquals(2, list.getWorks().size());
    }

    @Test
    public void canCreateTheSameDealInToDoList() throws Exception {
        list.addNewWork("deal");
        list.addNewWork("deal");
        String msg = "This deal is already exist.";
        assertEquals("This deal is already exist.", msg);
    }

    @Test
    public void canDeleteDealFromToDoList() throws Exception {
        list.deleteWork(1);
        assertEquals(list.toString(), list.getName() + "\nyour todo list is empty.\nBut you can create new deals.");
        assertEquals(0, list.getWorks().size());
    }

    @Test
    public void canChangeStatusOfToDoList() throws Exception {
        list.changeStatusOfWork(1 , new Completed());
    }

    @Test
    public void cantOpenList() {
        assertNull(list.openList(1));
    }

    @Test
    public void cantCreateListInToDoList() {
        try{
            list.addNewList("Lestrade");
        }catch (Exception e){
            String error = e.getMessage();
            assertEquals("you can't create list here.", error);
        }
    }

    @Test
    public void cantDeleteListFromToDoList() {
        try{
            list.deleteList(2);
        }catch (Exception e){
            String error = e.getMessage();
            assertEquals("you can't delete list here.", error);
        }
    }

    @Test
    public void getDealsTest(){
        ToDoList toDoList = new ToDoList();
        IWork deal = new Work();
        toDoList.addNewWork(deal);
        assertEquals(toDoList.getWorks().get(0), deal);
    }

    @Test
    public void canSetToDoListName() {
        list.setName("list");
        assertEquals(list.getName(), "list");
    }
    @Test
    public void cantGetToDoListStatus() {
        assertNull(list.getStatus());
    }
    @Test
    public void canRenameDeal() {
        list.rename(1, "new name");
    }
}