package com.workWithFiles;

import com.jtodo.statusOfWork.Completed;
import com.jtodo.statusOfWork.IWorkStatus;
import com.jtodo.headList.IHeadList;
import com.jtodo.headList.HeadList;
import com.jtodo.classesForEditorOfFiles.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class DataWorkerTest {
    private JSONSerializer dataWorker;
    private IHeadList mainList;
    private File dir;

    @Before
    public void init() {
        dataWorker = new JSONSerializer();
        mainList = new HeadList();
        dir = new File("src/main/resources/test");
    }

    @Test
    public void canDefineStatus() {
        String status = "Completed";
        IWorkStatus iWorkStatus = new Completed();

        String a1 = dataWorker.getStatus(status).toString();
        String a2 = iWorkStatus.toString();

        assertEquals(a1, a2);
    }
    @Test
    public void cantDefineOtherStatus() {
        String deferredStatus = "deferred";
        assertNull(dataWorker.getStatus(deferredStatus));
    }

    @Test
    public void canReadAndWriteListToFiles() {
        try {
            mainList.addNewList("New list");
            mainList.getLists().get(0).addNewWork("work");
            dataWorker.WritingDataToFiles(mainList, dir);
            IHeadList testList = dataWorker.readingDataFromFiles(dir);
            assertTrue(testList.getLists().size() > 0);
            File outFile = new File(dir + "/" + mainList.getLists().get(0).getName() +".txt");
            assertTrue(outFile.exists());
            outFile.deleteOnExit();
        } catch (Exception ignored) {
        }
    }

    @Test
    public void cantReadFilesIfDirectoryEmpty() {
        File emptyDir = new File(dir.getPath() + "/empty");
        if (!emptyDir.exists()) {
            boolean mkdir = emptyDir.mkdir();
            assert mkdir;
        }
        try {
           IHeadList testList = dataWorker.readingDataFromFiles(emptyDir);
           assertEquals(testList, mainList);
        } catch (Exception ignored) {

        }
        emptyDir.deleteOnExit();
    }
}
