package momo;

import momo.task.Task;

import java.io.IOException;
import java.util.ArrayList;

public class StorageStub extends Storage {
    public StorageStub(String filePath) {
        super(filePath);
    }

    @Override
    public void RewriteTasksToFile(String filePath, ArrayList<Task> list) throws IOException {

    }

}
