package casper.tasks.comparators;

import java.util.Comparator;

import casper.tasks.Task;

/**
 * Compares two {@link Task} objects based on their completion status.
 * <p>
 * The comparison order is as follows:
 * <ul>
 * <li>Undone tasks come first</li>
 * <li>Done tasks come last</li>
 * </ul>
 * </p>
 */
public class StatusComparator implements Comparator<Task> {

    /**
     * Compares two tasks by their completion status.
     * <p>
     * Returns a negative integer, zero, or a positive integer as the first task's status
     * is less than, equal to, or greater than the second task's status.
     * </p>
     *
     * @param t1 The first task to be compared.
     * @param t2 The second task to be compared.
     * @return A negative integer, zero, or a positive integer as the first task's status
     *         is less than, equal to, or greater than the second task's status.
     */
    @Override
    public int compare(Task t1, Task t2) {
        return Boolean.compare(t1.isDone(), t2.isDone());
    }
}
