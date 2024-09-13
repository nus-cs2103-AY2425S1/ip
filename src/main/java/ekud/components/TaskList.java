package ekud.components;

import java.util.ArrayList;
import java.util.Iterator;

import ekud.exceptions.EkudException;
import ekud.task.Task;

/**
 * Represents a list of {@link Task}.
 * <p/>
 * Implements the {@link Iterable} interface, meaning that the {@code TaskList} can be iterated over,
 * retrieving each {@code Task} in the list sequentially.
 *
 * @author uniqly
 */
public class TaskList implements Iterable<Task> {
    /** The list of tasks */
    private final ArrayList<Task> tasks;

    /**
     * Creates an empty list of tasks.
     *
     * @see Task
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Returns {@code true} if the list is empty.
     *
     * @return If the list is empty.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns {@code true} if all the tasks in the list are completed.
     *
     * @return If all the tasks are completed.
     * @see TaskList#isEmpty()
     */
    public boolean isAllComplete() {
        return (!isEmpty() && getIncompleteCount() == 0);
    }

    /**
     * Returns the number of incomplete tasks; a {@link Task} is incomplete if {@link Task#isDone()}
     * returns {@code true}.
     *
     * @return The number of incomplete tasks.
     * @see TaskList#getCount()
     */
    public int getIncompleteCount() {
        int count = 0;
        for (Task task: tasks) {
            if (!task.isDone()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Returns the number of tasks.
     *
     * @return The number of tasks.
     * @see TaskList#getIncompleteCount()
     */
    public int getCount() {
        return tasks.size();
    }

    /**
     * Returns the {@link Iterator} that corresponds to the sequence of tasks in added-first order.
     *
     * @return The {@link Iterator} representing the list.
     */
    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }

    /**
     * Checks if there is a task in the list at the specified index and throws an {@link EkudException}
     * if it does not exist.
     *
     * @param index The index to check.
     * @throws EkudException If there doesn't exist a {@link Task} the {@code index}.
     */
    public void checkTask(int index) throws EkudException {
        // checks if there is a task at the given index
        boolean isIndexOutOfRange = index < 0 || index >= tasks.size();
        if (isIndexOutOfRange) {
            String errorMessageFormat = """
                    Whats this? You tried an invalid list index!?
                    Pfft... That's so hilarious!
                    Lemme spell it out for ya.
                    Your number must be between 1 and %d and clearly %d isn't""";
            String errorMessage = String.format(errorMessageFormat, tasks.size(), index + 1);
            throw new EkudException(errorMessage);
        }
    }

    /**
     * Adds a task to the list.
     *
     * @param task The {@link Task} to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes the {@link Task} at the specified index of the list.
     *
     * @param index The index of the list to remove.
     * @return The removed {@link Task}.
     * @throws EkudException If {@code index} out-of-bounds of the list.
     */
    public Task removeTask(int index) throws EkudException {
        checkTask(index);
        return tasks.remove(index);
    }

    /**
     * Returns the {@link Task} at the specified index of the list.
     *
     * @param index The index of the list to get.
     * @return The {@link Task} at the {@code index} of the list.
     * @throws EkudException If {@code index} out-of-bounds of the list.
     */
    public Task getTask(int index) throws EkudException {
        checkTask(index);
        return tasks.get(index);
    }

    /**
     * Marks the {@link Task} at the specified index of the list as completed.
     *
     * @param index The index of the list to mark as complete.
     * @throws EkudException If {@code index} out-of-bounds of the list.
     * @see Task#markAsDone()
     */
    public void markComplete(int index) throws EkudException {
        checkTask(index);
        tasks.get(index).markAsDone();
    }

    /**
     * Marks the {@link Task} at the specified index of the list as incomplete.
     *
     * @param index The index of the list to mark as incomplete.
     * @throws EkudException If {@code index} out-of-bounds of the list.
     * @see Task#markAsUndone()
     */
    public void markIncomplete(int index) throws EkudException {
        checkTask(index);
        tasks.get(index).markAsUndone();
    }
}
