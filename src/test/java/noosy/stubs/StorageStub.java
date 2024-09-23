package noosy.stubs;

import noosy.exception.NoosyException;
import noosy.storage.Storage;
import noosy.task.Task;
import noosy.task.TaskList;

public class StorageStub extends Storage {

    public StorageStub() {
        super("dummy/path/to/file.txt");
    }

    @Override
    public void addTask(Task task) {
        // Simulate adding a task without actual file storage logic
        System.out.println("Task stored: " + task.toString());
    }

    @Override
    public void save(TaskList tasks) throws NoosyException {
        // Do nothing
    }
}
