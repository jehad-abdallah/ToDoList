package com.jtodo.viewController;

import com.jtodo.maintInterfaceOfToDoObjects.IMainToDoObject;

import java.util.Stack;

public class ViewController implements IViewController {
    private final Stack<IMainToDoObject> StackOfObjects = new Stack<>();

    @Override
    public boolean empty() {
        return this.StackOfObjects.empty();
    }

    @Override
    public IMainToDoObject peekView() {
        return this.StackOfObjects.peek();
    }

    @Override
    public void display() {
        if (this.StackOfObjects.size() > 0)
            System.out.println(this.StackOfObjects.peek());
    }

    @Override
    public void addToViewerNewObj(IMainToDoObject newObject) {
        this.StackOfObjects.add(newObject);
    }

    @Override
    public void popView() {
        this.StackOfObjects.pop();
    }
}
