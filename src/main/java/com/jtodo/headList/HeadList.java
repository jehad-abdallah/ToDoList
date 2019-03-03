package com.jtodo.headList;

import com.jtodo.maintInterfaceOfToDoObjects.IMainToDoObject;
import com.jtodo.statusOfWork.IWorkStatus;
import com.jtodo.toDoList.IToDoList;
import com.jtodo.toDoList.ToDoList;

import java.util.ArrayList;
import java.util.List;

public class HeadList implements IHeadList {
    private static final String EMPTY_MSG = "Sorry, but your todo lists are missing.\nDon't worry, you can create them right now!";
    private List<IToDoList> lists;

    public HeadList() {
        lists = new ArrayList<>();
    }

    public HeadList(List<IToDoList> lists) {
        this.lists = new ArrayList<>();
        this.lists = lists;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        int i = 0;
        while(i < lists.size()){
            int j = i + 1;
            res.append(j);
            res.append(". ");
            res.append(lists.get(i).getName());
            res.append('\n');
            i++;
        }
        if (res.toString().equals("")) {
            res = new StringBuilder(EMPTY_MSG);
        }
        return res.toString();
    }

    @Override
    public IMainToDoObject openList(int num) {
        int numOfList = num - 1;
        if (numOfList < lists.size() && numOfList >= 0) {
            return lists.get(numOfList);
        }
        return null;
    }

    @Override
    public void addNewList(String listName) {
        boolean key = false;
        for (IToDoList list : lists) {
            String listExist = list.getName();
            if (listExist.equals(listName)) {
                key = true;
            }
        }
        if(key){
            System.out.println("This list is already exist.");
        }else{
            lists.add(new ToDoList(listName));
        }
    }

    @Override
    public void addNewWork(String dealName) throws Exception {
        throw new Exception("Sorry, but you can't create deal here.");
    }

    @Override
    public void deleteList(int listNum) throws Exception {
        int numOfList = listNum - 1;
        if (numOfList >= lists.size()) {
            throw new Exception("List isn't exists.");
        }
        lists.remove(numOfList);
    }

    @Override
    public void deleteAllList() throws Exception {
        if (lists.isEmpty()) {
            throw new Exception("List is empty already");
        }
        lists.clear();
    }

    @Override
    public void deleteLastList() throws Exception {
        if (lists.isEmpty()) {
            throw new Exception("List is empty already");
        }
        lists.remove(lists.size() -1);
    }

    @Override
    public void deleteLastWork() throws Exception {
        throw new Exception("Sorry, but you can't delete last deal here.");
    }

    @Override
    public void deleteAllWork() throws Exception {
        throw new Exception("Sorry, but you can't delete deal here.");
    }

    @Override
    public void deleteWork(int dealNum) throws Exception {
        throw new Exception("Sorry, but you can't delete deal here.");
    }

    @Override
    public void rename(int num, String newName) {
        int numOfList = num - 1;
        if ( numOfList < lists.size()) {
            lists.get(numOfList).setName(newName);
        }
    }

    @Override
    public void changeStatusOfWork(int dealNum, IWorkStatus newStatus) throws Exception {
        throw new Exception("Sorry, but you can't change statusOfWork here.");
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public IWorkStatus getStatus() {
        return null;
    }

    @Override
    public List<IToDoList> getLists() {
        return lists;
    }

    @Override
    public void addNewList(IToDoList list) {
        lists.add(list);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof HeadList) {
            HeadList list = (HeadList) obj;
            return list.getLists().equals(this.getLists());
        }
        return false;
    }
}