package seedu.maxine.stubs;

import java.util.ArrayList;

import seedu.maxine.storage.MaxineStorage;
import seedu.maxine.task.MaxineList;
import seedu.maxine.task.Task;
import seedu.maxine.task.Todo;

/**
 * This is a stub for the storage class to test the Command class
 */
public class StorageStub implements MaxineStorage {
    public StorageStub(String filePath) {
        // nothing
    }
    /**
     * Returns the most updated collection of tasks.
     * The method reads the txt file and parses it to convert it into
     * its corresponding task. The tasks are then added to an ArrayList
     * @return ArrayList of collection of current tasks
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        Task task = new Todo("test command class");
        tasks.add(task);
        return tasks;
    }
    /**
     * Adds new lines to the txt file, based on updated list.
     * @param list updated TaskList
     */
    public void refreshStorage(MaxineList list) {
        // nothing
    }
}
