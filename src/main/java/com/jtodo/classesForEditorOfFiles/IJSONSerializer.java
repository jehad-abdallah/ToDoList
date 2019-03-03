package com.jtodo.classesForEditorOfFiles;

import com.jtodo.headList.IHeadList;

import java.io.File;

public interface IJSONSerializer {
    IHeadList readingDataFromFiles(File dir) throws Exception;

    void WritingDataToFiles(IHeadList mainList, File dir) throws Exception;

    // --Commented out by Inspection (03.03.2019 9:30):void exclusionOfDifferences(IHeadList mainList, File dir) throws Exception;
}
