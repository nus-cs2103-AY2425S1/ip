package duck.storage;

import duck.data.TaskList;
import duck.data.exception.DuckException;
import duck.data.task.Task;

/**
 * A stub class for Storage.
 */
public class StorageStub extends Storage {

    public StorageStub() throws DuckException {
        super("data/tasks.txt");
    }

    @Override
    public void writeTasks(TaskList tasks) {
        // do nothing
    }

    @Override
    public void appendTask(Task task) {
        // do nothing
    }
}
