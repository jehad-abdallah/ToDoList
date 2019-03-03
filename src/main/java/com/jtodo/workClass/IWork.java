package com.jtodo.workClass;

import com.jtodo.maintInterfaceOfToDoObjects.IMainToDoObject;
import com.jtodo.statusOfWork.IWorkStatus;

public interface IWork extends IMainToDoObject {
    String toString();

    String getName();
    void setStatus(IWorkStatus status);
}