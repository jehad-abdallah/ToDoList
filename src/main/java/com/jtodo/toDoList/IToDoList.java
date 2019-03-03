package com.jtodo.toDoList;

import com.jtodo.maintInterfaceOfToDoObjects.IMainToDoObject;
import com.jtodo.workClass.IWork;

import java.util.List;

public interface IToDoList extends IMainToDoObject {
    List<IWork> getWorks();

    void addNewWork(IWork newWork);
}
