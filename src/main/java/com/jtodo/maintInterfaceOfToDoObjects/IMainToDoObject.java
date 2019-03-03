package com.jtodo.maintInterfaceOfToDoObjects;

import com.jtodo.statusOfWork.IWorkStatus;

public interface IMainToDoObject {
    IMainToDoObject openList(int index);

    void setName(String nameOfList);

    String getName();

    void changeStatusOfWork(int workIndex, IWorkStatus newStatus) throws Exception;

    IWorkStatus getStatus();

    void addNewList(String listName) throws Exception;

    void deleteList(int listIndex) throws Exception;

    void addNewWork(String workName) throws Exception;

    void deleteWork(int workIndex) throws Exception;

    void deleteAllWork() throws Exception;

    void deleteAllList() throws Exception;

    void deleteLastList() throws Exception;

    void deleteLastWork() throws Exception;

    void rename(int index, String newName);

    String toString();
}
