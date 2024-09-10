package strand;

import java.util.ArrayList;

import strand.exception.StrandException;
import strand.exception.StrandWrongIndexException;
import strand.task.Task;

/**
 * The {@code TaskList} class interacts keeps track of the list of tasks and
 * edits it according to the commands.
 * <p>
 * This class may throw exceptions if the index of the task is invalid.
 * </p>
 */
public class TaskList {
    private final ArrayList<Task> listOfTasks;

    /**
     * Constructs a new {@code TaskList} instance with an empty task list.
     */
    public TaskList() {
        this.listOfTasks = new ArrayList<>();
    }

    /**
     * Constructs a new {@code TaskList} instance with the given list of tasks.
     *
     * @param listOfTasks The list of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> listOfTasks) {
        this.listOfTasks = new ArrayList<>(listOfTasks);
    }

    /**
     * Adds a new task to the list.
     *
     * @param newTask The task to be added.
     */
    public void addTask(Task newTask) {
        assert newTask != null : "Cannot add a null task";
        listOfTasks.add(newTask);
    }

    /**
     * Deletes a task from the list based on the provided index.
     *
     * @param index The index of the task to be removed (1-based index).
     * @return The removed task.
     * @throws StrandException If the index is invalid.
     */
    public Task deleteTask(Integer index) throws StrandException {
        if (index > this.listOfTasks.size() || index < 1) {
            throw new StrandWrongIndexException(this.listOfTasks.size());
        }
        Task t = this.listOfTasks.get(index - 1);
        this.listOfTasks.remove(index - 1);
        return t;
    }

    /**
     * Marks or unmarks a task based on the provided index.
     *
     * @param index The index of the task to be marked or unmarked (1-based index).
     * @param mark  {@code true} to mark the task as done, {@code false} to mark it as not done.
     * @return The updated task.
     * @throws StrandException If the index is invalid.
     */
    public Task mark(Integer index, Boolean mark) throws StrandException {
        assert index != null : "Index cannot be null";
        if (index > listOfTasks.size() || index < 1) {
            throw new StrandWrongIndexException(listOfTasks.size());
        }
        Task task = listOfTasks.get(index - 1);
        if (mark) {
            task.markAsDone();
        } else {
            task.markAsNotDone();
        }
        return task;
    }

    /**
     * Marks a task as a certain priority based on the provided index.
     *
     * @param index    The index of the task to be marked or unmarked (1-based index).
     * @param priority The priority to be assigned to the task.
     * @return The updated task.
     * @throws StrandException If the index is invalid.
     */
    public Task mark(Integer index, Task.PriorityEnum priority) throws StrandException {
        assert index != null : "Index cannot be null";
        if (index > listOfTasks.size() || index < 1) {
            throw new StrandWrongIndexException(listOfTasks.size());
        }
        Task task = listOfTasks.get(index - 1);
        task.markPriority(priority);
        return task;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks.
     */
    public Integer getNumOfTasks() {
        return this.listOfTasks.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < listOfTasks.size(); i++) {
            sb.append(i + 1).append(". ").append(listOfTasks.get(i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Returns a string representation of the tasks in a format suitable for saving to a file.
     *
     * @return The file representation of the tasks.
     */
    public String convertToFileFormat() {
        StringBuilder sb = new StringBuilder();
        for (Task task : listOfTasks) {
            sb.append(task.convertToFileFormat()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Returns a string representation of all tasks that contain the specified segment in their description.
     *
     * @param segment The segment that tasks should contain in their description.
     * @return A string containing all matching tasks.
     */
    public String getFoundTasks(String segment) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < listOfTasks.size(); i++) {
            Task task = listOfTasks.get(i);
            if (task.containsSegment(segment)) {
                sb.append(i + 1).append(". ").append(task).append("\n");
            }
        }
        return sb.toString();
    }
}
