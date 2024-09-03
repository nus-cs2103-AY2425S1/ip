package meeju;

import java.util.ArrayList;

/**
 * This class is a Stub that enables independent testing of Parser without interacting
 * with the file on disk.
 */
public class StorageStub extends Storage {

    @Override
    public ArrayList<Task> initialiseList() {
        ArrayList<Task> taskList = new ArrayList<>();
        return taskList;
    }

    @Override
    public void updateFile(ArrayList<Task> taskList) throws MeejuException {
        return;
    }
}
