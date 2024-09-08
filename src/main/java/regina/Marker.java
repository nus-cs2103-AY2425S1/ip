package regina;

import errorhandling.ReginaException;
import tasks.Task;
import tasks.TaskList;

/**
 * The regina.Marker class provides methods to mark and unmark tasks in a task list.
 * It interacts with the tasks.TaskList to check off tasks as completed or not.
 */
public class Marker {

    private final TaskList listOfTasks;

    /**
     * Constructs a regina.Marker object with a specified task list.
     *
     * @param listOfTasks The tasks.TaskList to be managed by this regina.Marker instance.
     */
    public Marker(TaskList listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    /**
     * Marks a task as done based on the specified index.
     *
     * @param index The index of the task in the list to be marked.
     * @throws ReginaException If the index is out of bounds or less than zero.
     */
    public void mark(int index) throws ReginaException {
        assert listOfTasks != null : "Task list cannot be null";
        int taskCount = listOfTasks.size();
        if (index >= taskCount) {
            String message = String.format("You cannot count ah! There %s only %d task%s!",
                    taskCount > 1 ? "are" : "is",
                    taskCount,
                    taskCount > 1 ? "s" : "");
            throw new ReginaException(message);
        }
        if (index < 0) {
            throw new ReginaException("Oops! Please choose an index greater than 0.");
        }
        Task task = listOfTasks.get(index);
        task.checkTask();
    }

    /**
     * Unmarks a task as not done based on the specified index.
     *
     * @param index The index of the task in the list to be unmarked.
     * @throws ReginaException If the index is out of bounds or less than zero.
     */
    public void unmark(int index) throws ReginaException {
        assert listOfTasks != null : "Task list cannot be null";
        int taskCount = listOfTasks.size();
        if (index >= taskCount) {
            String message = String.format("You cannot count ah! There %s only %d task%s",
                    taskCount > 1 ? "are" : "is",
                    taskCount,
                    taskCount > 1 ? "s" : "");
            throw new ReginaException(message);
        }
        if (index < 0) {
            throw new ReginaException("Oops! Please choose an index greater than 0.");
        }
        Task task = listOfTasks.get(index);
        task.uncheckTask();
    }
}
