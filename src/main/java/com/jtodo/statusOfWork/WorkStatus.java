package com.jtodo.statusOfWork;

public class WorkStatus implements IWorkStatus {
    private final String name;

    public WorkStatus(String statusName) {
        name = statusName;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof IWorkStatus) {
            IWorkStatus rhs = (IWorkStatus)object;
            return toString().equals(rhs.toString());
        }
        return false;
    }
}
