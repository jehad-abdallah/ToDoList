package com.jtodo.workClass;

import com.jtodo.maintInterfaceOfToDoObjects.IMainToDoObject;
import com.jtodo.statusOfWork.IWorkStatus;
import com.jtodo.statusOfWork.InProcess;

public class Work implements IWork {

    private String dealName;
    private IWorkStatus currStatus;

    //конструкторы
    public Work() {
        dealName = "Undefined";
        currStatus = new InProcess();
    }

    public Work(String dealName) {
        this.dealName = dealName;
        this.currStatus = new InProcess();
    }

    public Work(String dealName, IWorkStatus currStatus) {
        this.dealName = dealName;
        this.currStatus = currStatus;
    }


    //тест есть
    @Override
    public IMainToDoObject openList(int otherListNumber) {
        System.out.println("Sorry, at the moment you are unable to open this list. (You are in another list)");
        return null;
    }


    //исключения (все покрыты тестами)
    @Override
    public void addNewList(String listName) throws Exception {
        throw new Exception("Sorry, at the moment you are unable to create this list. (You are in another list)");
    }

    @Override
    public void deleteList(int listNum) throws Exception {
        throw new Exception("Sorry, at the moment you are unable to delete this list. (You are in another list)");
    }

    @Override
    public void deleteAllList() throws Exception {
        throw new Exception("Sorry, at the moment you are unable to delete this list. (You are in another list)");
    }

    @Override
    public void deleteLastList() throws Exception {
        throw new Exception("you can't delete Last list here.");
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
    public void addNewWork(String dealName) throws Exception {
        throw new Exception("Sorry, at the moment you are unable to create work. (You are in another list)");
    }

    @Override
    public void deleteWork(int dealNum) throws Exception {
        throw new Exception("Sorry, at the moment you are unable to delete work. (You are in another list)");
    }


    //меняем данные по данному делу
    @Override
    public void rename(int num, String newName) {
        //setName(newName);
    }

    @Override
    public void changeStatusOfWork(int dealNum, IWorkStatus newStatus) {
        //setStatus(newStatus);
    }


    //получаем данные по данному делу
    @Override
    public String getName() {
        return dealName;
    }

    @Override
    public IWorkStatus getStatus() {
        return currStatus;
    }

    @Override
    public String toString() {
        return dealName + ": " + currStatus.toString();
    }


    //устанавливаем параметры
    @Override
    public void setName(String dealName) {
        this.dealName = dealName;
    }

    //может быть @Override???
    public void setStatus(IWorkStatus currStatus) {
        this.currStatus = currStatus;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof IWork) {
            Work work = (Work) obj;
            return work.getName().equals(this.getName());
        }

        return false;
    }
}