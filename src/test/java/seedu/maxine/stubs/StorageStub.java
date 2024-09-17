package seedu.maxine.stubs;

import seedu.maxine.MaxineList;
import seedu.maxine.MaxineStorage;
import seedu.maxine.task.Task;

import java.util.ArrayList;

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
        return null;
    }
    /**
     * Adds new lines to the txt file, based on updated list.
     * @param list updated TaskList
     */
    public void refreshStorage(MaxineList list) {
        // nothing
    }
}
