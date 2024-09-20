package casper.tasks.comparators;

import java.util.Comparator;

import casper.tasks.Task;

/**
 * Compares two {@link Task} objects based on their descriptions in alphabetical order.
 * <p>
 * This comparator uses case-insensitive comparison to sort tasks.
 * </p>
 */
public class AlphabeticalComparator implements Comparator<Task> {
    /**
     * Compares two tasks by their descriptions.
     * <p>
     * Returns a negative integer, zero, or a positive integer as the first task's description
     * is less than, equal to, or greater than the second task's description.
     * </p>
     *
     * @param t1 The first task to be compared.
     * @param t2 The second task to be compared.
     * @return A negative integer, zero, or a positive integer as the first task's description
     *         is less than, equal to, or greater than the second task's description.
     */
    @Override
    public int compare(Task t1, Task t2) {
        return t1.getDescription().compareTo(t2.getDescription());
    }
}
