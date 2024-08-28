package meeju;

import java.util.ArrayList;

public class StorageStub extends Storage{

    @Override
    public ArrayList<Task> initialiseList() {
        ArrayList<Task> taskList = new ArrayList<>();
        return taskList;
    }

    @Override
    public void updateFile(ArrayList<Task> taskList) throws MeejuException {
        return ;
    }
}
