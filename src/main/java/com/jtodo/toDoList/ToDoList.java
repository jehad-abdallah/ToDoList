package com.jtodo.toDoList;

import com.jtodo.maintInterfaceOfToDoObjects.IMainToDoObject;
import com.jtodo.statusOfWork.IWorkStatus;
import com.jtodo.workClass.Work;
import com.jtodo.workClass.IWork;

import java.util.ArrayList;
import java.util.List;

public class ToDoList implements IToDoList {
    private String nameOfToDoList;
    private final List<IWork> dealsInList = new ArrayList<>();
    private static final String BLANK_LIST = "your todo list is empty.\nBut you can create new deals.";


    public ToDoList() {
        this.nameOfToDoList = "Undefined";
    }

    public ToDoList(String name) {
        this.nameOfToDoList = name;
    }

    @Override
    public void setName(String nameOfList) {
        this.nameOfToDoList = nameOfList;
    }

    @Override
    public String getName() {
        return this.nameOfToDoList;
    }

    @Override
    public void addNewList(String listName) throws Exception {
        throw new Exception("you can't create list here.");
    }

    @Override
    public void deleteList(int listNum) throws Exception {
        throw new Exception("you can't delete list here.");
    }

    @Override
    public void deleteAllList() throws Exception {
        throw new Exception("you can't delete list here.");
    }

    @Override
    public void deleteLastList() throws Exception {
        throw new Exception("you can't delete Last list here.");
    }

    @Override
    public void addNewWork(String dealName) {
        boolean key = false;
        for (IWork iWork : dealsInList) {
            String listExist = iWork.getName();
            if (listExist.equals(dealName)) {
                key = true;
            }
        }
        if(key){
            System.out.println("This deal is already exist.");
        }else{
            IWork deal = new Work(dealName);
            this.dealsInList.add(deal);
        }
    }

    @Override
    public void deleteWork(int indexOfDeal) throws Exception {
        indexOfDeal--;
        if (indexOfDeal >= this.dealsInList.size())
            throw new Exception("Work isn't exits");
        else
            this.dealsInList.remove(indexOfDeal);
    }

    @Override
    public void deleteAllWork() throws Exception {
        if (this.dealsInList.isEmpty())
            throw new Exception("Deal is empty already");
        else
            this.dealsInList.clear();
    }

    @Override
    public void deleteLastWork() throws Exception {
        if (this.dealsInList.isEmpty())
            throw new Exception("Deal is empty already");
        else
            this.dealsInList.remove(dealsInList.size() -1);
    }

    @Override
    public List<IWork> getWorks() {
        return this.dealsInList;
    }

    @Override
    public void addNewWork(IWork newDeal) {
        this.dealsInList.add(newDeal);
    }

    @Override
    public void changeStatusOfWork(int dealCount, IWorkStatus newStatus) throws Exception {
        if (this.dealsInList.size() <= (--dealCount))
            throw new Exception("Work isn't exists.");

        IWork changedDeal = this.dealsInList.get(dealCount);
        changedDeal.setStatus(newStatus);
    }

    @Override
    public IWorkStatus getStatus() {
        return null;
    }

    @Override
    public IMainToDoObject openList(int num) {
        System.out.println("you can't open list here.");
        return null;
    }


    @Override
    public void rename(int indexOfDeal, String name) {
        indexOfDeal--;
        IWork changedDeal = new Work(name);
        this.dealsInList.remove(indexOfDeal);
        this.dealsInList.add(indexOfDeal, changedDeal);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder(this.nameOfToDoList);
        res.append('\n');
        for (int i = 0; i < this.dealsInList.size(); i++) {
            int j = i + 1;
            res.append(j).append(". ").append(this.dealsInList.get(i).getName()).append(": ").append(this.dealsInList.get(i).getStatus()).append("\n");
        }
        if (res.toString().equals(this.nameOfToDoList + '\n'))
            res.append(BLANK_LIST);

        return res.toString();
    }

    @Override
    public boolean equals(Object anotherObject) {
        if (anotherObject instanceof ToDoList) {
            ToDoList list = (ToDoList) anotherObject;
            return (list.getName().equals(this.getName())) && (list.getWorks().equals(this.getWorks()));
        }

        return false;
    }
}
