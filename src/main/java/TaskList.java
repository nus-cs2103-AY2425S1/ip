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
     * Tries to get list of tasks from file path. If it does not exist, create an empty save file.
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

    /**
     * Gets the task at the specified index in the list.
     * @param index The list number of the task.
     * @return Task the task at the specific index
     */
    public Task get(int index) {
        if (index >= taskList.size() || index < 0) {
            throw new NoSuchTaskException();
        }
        return taskList.get(index);
    }

    /**
     * Adds the task to the end of the task list.
     * @param task
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Updates task list according to file. If file not found, creates a blank file.
     */
    public void readFromFile() {
        try {
            taskList = storage.readTasksFromFile();
        } catch (FileNotFoundException e) {
            storage.writeTasksToFile(taskList);
        }
    }

    public void writeToFile() {
        storage.writeTasksToFile(taskList);
    }
}
