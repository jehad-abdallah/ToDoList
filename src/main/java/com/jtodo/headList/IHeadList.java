package com.jtodo.headList;

import com.jtodo.maintInterfaceOfToDoObjects.IMainToDoObject;
import com.jtodo.toDoList.IToDoList;

import java.util.List;

public interface IHeadList extends IMainToDoObject {
    List<IToDoList> getLists();

    void addNewList(IToDoList list);
}
