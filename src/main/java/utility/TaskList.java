package utility;

import java.io.Serializable;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import task.Task;

/**
 * This class serves as a central management object to manage all {@link Task}.
 */
public class TaskList implements Serializable {
    private final ImList<Task> imTaskList;

    /**
     * Creates a {@link TaskList} with an empty {@link ImList} of {@link Task}.
     */
    public TaskList() {
        imTaskList = new ImList<Task>();
    }

    private TaskList(ImList<Task> imTaskList) {
        this.imTaskList = imTaskList;
    }

    /**
     * Adds {@link Task} to the {@link TaskList}.
     *
     * @param task {@link Task} to be added.
     */
    public TaskList addTask(Task task) {
        return new TaskList(imTaskList.add(task));
    }

    /**
     * Deletes {@link Task} from {@link TaskList}.
     *
     * @param taskIndex the index of the task which is to be deleted.
     */
    public TaskList deleteTask(int taskIndex) {
        return new TaskList(imTaskList.remove(taskIndex));
    }

    /**
     * Marks a {@link Task} from the {@link TaskList} as done.
     *
     * @param taskIndex the index of the task which is to be marked as done.
     */
    public TaskList markTaskAsDone(int taskIndex) {
        return isValidIndex(taskIndex)
            ? new TaskList(imTaskList.set(
                    taskIndex, imTaskList.get(taskIndex).markAsDone()))
            : this;
    }

    /**
     * Changes a {@link Task} from the {@link TaskList} as not done.
     *
     * @param taskIndex the index of the task which is to be changed to not done.
     */
    public TaskList markTaskAsUndone(int taskIndex) {
        return isValidIndex(taskIndex)
            ? new TaskList(imTaskList.set(
                    taskIndex, imTaskList.get(taskIndex).markAsUndone()))
            : this;
    }

    /**
     * Returns the specific {@link Task} by index in the List
     */
    public Task get(int taskIndex) {
        return taskIndex < imTaskList.size() && taskIndex >= 0
            ? imTaskList.get(taskIndex)
            : new Task("");
    }

    /**
     * Returns the number of {@link Task} on the List.
     */
    public int size() {
        return imTaskList.size();
    }

    /**
     * Returns if the index provided is a valid index of the List of Tasks.
     */
    public boolean isValidIndex(int taskIndex) {
        return taskIndex < imTaskList.size() && taskIndex >= 0;
    }

    /**
     * Returns the iterator for the {@link TaskList}.
     */
    public Iterator<Task> iterator() {
        return imTaskList.iterator();
    }

    /**
     * Returns a string representaton of the entire {@link TaskList} which includes every
     * {@link Task}. Returns a different string if empty.
     */
    @Override
    public String toString() {
        String outputString;
        if (imTaskList.size() == 0) {
            outputString = "No tasks! What tasks would you like to add?\n";
        }
        outputString = "Here are your tasks in your list:\n"
            + IntStream.rangeClosed(1, imTaskList.size())
            .mapToObj(i -> i + "." + imTaskList.get(i - 1))
            .collect(Collectors.joining("\n"))
            + "\n";
        return outputString;
    }

}
