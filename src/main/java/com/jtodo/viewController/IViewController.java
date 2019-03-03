package com.jtodo.viewController;

import com.jtodo.maintInterfaceOfToDoObjects.IMainToDoObject;

public interface IViewController {
    void addToViewerNewObj(IMainToDoObject newObj);

    void popView();

    IMainToDoObject peekView();

    void display();

    boolean empty();

}
