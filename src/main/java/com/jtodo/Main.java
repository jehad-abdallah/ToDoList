package com.jtodo;

import com.jtodo.commandToDo.ICommand;
import com.jtodo.controllers.CommandController;
import com.jtodo.controllers.ICommandController;
import com.jtodo.headList.IHeadList;
import com.jtodo.viewController.IViewController;
import com.jtodo.viewController.ViewController;
import com.jtodo.classesForEditorOfFiles.JSONSerializer;
import com.jtodo.classesForEditorOfFiles.IJSONSerializer;

import java.io.File;
import java.util.Map;
import java.util.Scanner;

import static com.jtodo.commandToDo.utils.CommandUtils.getCommands;

class Main {

    private static IHeadList loadFiles(IJSONSerializer worker, File dir) {
        IHeadList list = null;
        try {
            list = worker.readingDataFromFiles(dir);
        } catch (Exception ignored) {
        }

        return list;
    }

    private static void info(){
        System.out.println("Create List Operation: create list <list name>");
        System.out.println("Open List Operation: open <position number>");
        System.out.println("Create Work Operation: create work <work name>");
        System.out.println("Delete Work Operation: delete work <position number>");
        System.out.println("Delete List Operation: delete List <position number>");
        System.out.println("Delete All Lists Operation: delete lists <position number>");
        System.out.println("Delete All Works Operation: delete works <position number>");
        System.out.println("Delete Last List Operation: delete lastl");
        System.out.println("Delete Last Work Operation: delete lastw");
        System.out.println("Rename List Operation: rename <position number> <list name>");
        System.out.println("Rename Work Operation: rename <position number> <work name>");
        System.out.println("Change Status Operation: change status <position number> completed ");
        System.out.println("Show Works With Status \"Completed\": showCompleted");
        System.out.println("Show Works With Status \"In Process\": showInProcess");
        System.out.println("Exit Operation: exit");
    }

    public static void main(String[] args) {
        final String mainPath = "src/main/resources/ToDoLists";
        final String READ_POINTER = "> ";
        final String HELLO_MSG = "Hello! Welcome to jtodo-list.";
        final String EXIT_MSG = "Thank you, Goodbye!";
        final String LIST_MSG = "Your todo-list:";

        File dir = new File(mainPath);
        if (!dir.exists()) {
            System.out.println("Sorry, but directory \"" + dir.toString() + "\" is not exists.");
            return;
        }

        IJSONSerializer dWorker = new JSONSerializer();
        IHeadList mainList = loadFiles(dWorker, dir);

        if (mainList == null) {
            System.out.println("Failed to load files.");
            return;
        }

        ViewController viewer = new ViewController();
        viewer.addToViewerNewObj(mainList);

        Scanner in = new Scanner(System.in);
        ICommandController handler = new CommandController(initCommand(viewer));


        System.out.println(HELLO_MSG);
        System.out.println("\n");
        info();
        while (!viewer.empty()) {
            System.out.println(LIST_MSG);
            viewer.display();
            System.out.print(READ_POINTER);
            String command = in.nextLine();
            handler.controlCommand(command);
        }

        try {
            dWorker.WritingDataToFiles(mainList, dir);
        } catch (Exception ex) {
            System.out.println("Failed to save files, last saved version of files was saved.");
        }
        System.out.println(EXIT_MSG);
    }

    private static Map<String, ICommand> initCommand(IViewController viewer) {
        return getCommands(viewer);
    }
}