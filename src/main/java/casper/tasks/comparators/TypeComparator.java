package casper.tasks.comparators;

import java.util.Comparator;

import casper.tasks.Deadline;
import casper.tasks.Event;
import casper.tasks.Task;
import casper.tasks.ToDo;

/**
 * Compares two {@link Task} objects based on their types.
 * <p>
 * The comparison order is as follows:
 * <ul>
 * <li>ToDo tasks come first</li>
 * <li>Deadline tasks come next</li>
 * <li>Event tasks come last</li>
 * </ul>
 * </p>
 */
public class TypeComparator implements Comparator<Task> {

    /**
     * Compares two tasks by their type.
     * <p>
     * Returns a negative integer, zero, or a positive integer as the first task's type
     * is less than, equal to, or greater than the second task's type.
     * </p>
     *
     * @param t1 The first task to be compared.
     * @param t2 The second task to be compared.
     * @return A negative integer, zero, or a positive integer as the first task's type
     *         is less than, equal to, or greater than the second task's type.
     */
    @Override
    public int compare(Task t1, Task t2) {
        return getTypeOrder(t1).compareTo(getTypeOrder(t2));
    }

    /**
     * Returns the order of the task type for comparison.
     *
     * @param task The task whose type order is to be determined.
     * @return An integer representing the order of the task type.
     */
    private Integer getTypeOrder(Task task) {
        if (task instanceof ToDo) {
            return 1;
        } else if (task instanceof Deadline) {
            return 2;
        } else if (task instanceof Event) {
            return 3;
        }
        return -Integer.MAX_VALUE;
    }
}
