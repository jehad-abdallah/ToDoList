package com.jtodo.classesForEditorOfFiles;

import com.jtodo.statusOfWork.*;
import com.jtodo.toDoList.IToDoList;
import com.jtodo.toDoList.ToDoList;
import com.jtodo.headList.*;
import com.jtodo.workClass.Work;
import com.jtodo.workClass.IWork;
import org.json.JSONObject;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class JSONSerializer implements IJSONSerializer {

    private static final String IN_PROCESS = "In process";
    private static final String COMPLETED = "Completed";

    public IWorkStatus getStatus(String statusStr) {
        IWorkStatus status = null;
        switch (statusStr) {
            case IN_PROCESS:
                status = new InProcess();
                break;
            case COMPLETED:
                status = new Completed();
                break;
        }

        return status;
    }

    @Override
    public IHeadList readingDataFromFiles(File dir) throws Exception {
        IHeadList list = new HeadList();

        File[] files = dir.listFiles();
        assert files != null;

        for (File file : files) {
            String fileName = file.getName();
            String listName = fileName.split("\\.")[0];
            IToDoList newList = new ToDoList(listName);

            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            String line = reader.readLine();
            JSONObject jsonObject = new JSONObject(line);

            JSONArray phoneNumbersArr = jsonObject.getJSONArray("tasks");

            for (Object o : phoneNumbersArr) {
                JSONObject test = (JSONObject) o;

                String name = (String) test.get("name");
                String status = (String) test.get("statusOfWork");

                IWorkStatus dealStatus;
                dealStatus = getStatus(status);
                IWork newDeal = new Work(name, dealStatus);
                newList.addNewWork(newDeal);
            }

            list.addNewList(newList);
            reader.close();
        }

        return list;
    }

    @Override
    public void WritingDataToFiles(IHeadList mainList, File dir) throws Exception {
        List<IToDoList> lists = mainList.getLists();

        exclusionOfDifferences(mainList, dir);

        String catalogPath = dir.toString() + "\\";
        for (IToDoList list : lists) {
            String pathStr = catalogPath + list.getName() + ".txt";

            File newFile = new File(pathStr);
            if(!newFile.exists()) {
                if (!newFile.createNewFile() || !newFile.setExecutable(true))
                {
                    throw new Exception("Creation file failed");
                }
            }
            FileWriter writer = new FileWriter(pathStr);

            List<IWork> deals = list.getWorks();

            JSONObject json = new JSONObject();

            JSONArray array = new JSONArray();

            int id = 0;

            for (IWork deal : deals) {

                JSONObject element = new JSONObject();
                element.put("name", deal.getName());
                element.put("statusOfWork", deal.getStatus());
                id++;
                array.put(element);
            }
            json.put("tasks", array);
            writer.write(json.toString());
            writer.flush();
            writer.close();
        }
    }

    private List<String> getArrNamesOfFileFromList(List<IToDoList> lists) {
        List<String> fileNames = new ArrayList<>();
        for (IToDoList list : lists) {
            fileNames.add(list.getName());
        }
        return fileNames;
    }

    private void exclusionOfDifferences(IHeadList mainList, File dir) throws  Exception {
        IHeadList filesList = readingDataFromFiles(dir);
        if (!mainList.equals(filesList)) {
            List<IToDoList> mainLists = mainList.getLists();
            List<IToDoList> loadedLists = filesList.getLists();

            List<String> mainFileNames = getArrNamesOfFileFromList(mainLists);
            List<String> fileNames = getArrNamesOfFileFromList(loadedLists);

            for (String fileName : fileNames) {
                if (!mainFileNames.contains(fileName)) {
                    fileName = dir.toString() + "\\" + fileName + ".txt";
                    File file = new File(fileName);
                    if (!file.delete()) { throw new Exception("Deletion failed."); }
                }
            }
        }
    }
}
