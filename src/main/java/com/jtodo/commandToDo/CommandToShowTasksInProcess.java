package com.jtodo.commandToDo;

import com.jtodo.maintInterfaceOfToDoObjects.IMainToDoObject;
import com.jtodo.toDoList.ToDoList;
import com.jtodo.viewController.IViewController;
import com.jtodo.workClass.IWork;

import java.util.List;
import java.util.Objects;

import static com.jtodo.commandToDo.utils.CommandUtils.*;


public class CommandToShowTasksInProcess implements ICommand {
  private final IViewController viewer;

  public CommandToShowTasksInProcess(IViewController viewer) {
    this.viewer = viewer;
  }

  @Override
public void execute(String[] args) throws Exception {
    if (args.length != SHOW_COMMAND_SIZE) {
      throw new Exception(ARGS_COUNT_ERROR_MSG + SHOW_USAGE_EXAMPLE);
    }

    int num = Integer.parseInt(args[1]);
    System.out.println("Your processed tasks in List " + num + ":");
    IMainToDoObject obj = viewer.peekView().openList(num);
    if (obj != null) {
      ToDoList list = (ToDoList) obj;
      List<IWork> works = list.getWorks();
        if(!works.isEmpty()){
          for (IWork work : works) {
            if (Objects.equals(work.getStatus().toString(), "In process")) {
              System.out.println("  " + work);
            }
          }
        }else throw new Exception("the list is empty\n");
      System.out.println("\n");
    } else {
      throw new Exception(NO_LIST);
    }
  }
}
