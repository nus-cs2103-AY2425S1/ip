package momo;

import java.io.IOException;
import java.util.ArrayList;

import momo.task.Task;

/**
 * A stub class for Storage used for testing purposes. Overrides the
 * rewriteTasksToFile method without actual file writing to simulate
 * storage behavior in tests.
 */
public class StorageStub extends Storage {
    public StorageStub(String filePath) {
        super(filePath);
    }

    @Override
    public void rewriteTasksToFile(String filePath, ArrayList<Task> list) throws IOException {

    }

}
