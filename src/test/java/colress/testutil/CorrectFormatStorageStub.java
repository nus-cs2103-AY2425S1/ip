package colress.testutil;

import java.io.File;

import colress.storage.Storage;
import colress.tasklist.TaskList;

/**
 * A stub class for the Storage class for testing purposes.
 */
public class CorrectFormatStorageStub extends Storage {

    public CorrectFormatStorageStub() {
        super(new File("src/test/java/colress/testutil/correctFormat.txt"));
    }

    @Override
    public void loadTasks(TaskList taskList) {}

    @Override
    public void createFile() {}

    @Override
    public void writeToTaskFile(TaskList taskList) {}
}
