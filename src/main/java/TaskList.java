import java.util.ArrayList;

/**
 * The TaskList class represents a list of tasks.
 * It extends ArrayList<Task> to provide a custom implementation
 * that holds Task objects and includes functionality specific
 * to managing tasks.
 */
public class TaskList extends ArrayList<Task> {

    /**
     * A list to store the tasks.
     */
    ArrayList<Task> listOfTasks;

    /**
     * Constructs an empty TaskList object.
     * Initializes the internal list of tasks.
     */
    public TaskList() {
        listOfTasks = new ArrayList<>();
    }
}
