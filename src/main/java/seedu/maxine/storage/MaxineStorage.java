package seedu.maxine.storage;

import java.util.ArrayList;

import seedu.maxine.task.MaxineList;
import seedu.maxine.task.Task;
/**
 * The MaxineStorage interface defines the methods for managing the
 * storage of tasks in the Maxine application. It allows for loading
 * tasks from storage and refreshing the storage with the current list of tasks.
 */
public interface MaxineStorage {
    ArrayList<Task> load();
    void refreshStorage(MaxineList list);
}
