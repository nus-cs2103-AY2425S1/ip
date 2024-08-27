import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Represents a list of Tasks.
 * @author Lee Ze Hao (A0276123J)
 */

public class TaskList {
    private final String filePath;
    private Storage storage;
    private ArrayList<Task> taskList = new ArrayList<Task>();

    /**
     * Constructs a TaskList object.
     * @param filePath Path where the local task list file is stored.
     */
    public TaskList(String filePath) {
        this.filePath = filePath;
        storage = new Storage(filePath);

        try {
            taskList = storage.readTasksFromFile();
        } catch (FileNotFoundException e) {
            storage.writeTasksToFile(taskList);
        }
    }
}
