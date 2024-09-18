package task;

import java.util.Comparator;

/**
 * A comparator class that compares two {@code Task} objects for sorting
 */
public class TaskComparator implements Comparator<Task> {
    /**
     * Compares two {@code Task} objects to determine their order.
     * If both tasks are {@code ToDos}, they are compared alphabetically by their descriptions.
     * If both tasks are {@code Deadlines}, they are compared by their deadlines.
     * If both tasks are {@code Events}, they are compared by their start times.
     * {@code ToDos} are always ranked before {@code Deadlines} and {@code Events}.
     * {@code Deadlines} are ranked before {@code Events}
     *
     * @param t1 the first object to be compared.
     * @param t2 the second object to be compared.
     * @return a negative integer, zero, or a positive integer as the first task
     *         is less than, equal to, or greater than the second task.
     */
    @Override
    public int compare(Task t1, Task t2) {
        if (t1 instanceof ToDos && t2 instanceof ToDos) {
            return t1.toString()
                    .substring(7)
                    .compareTo(t2.toString().substring(7));
        }

        if (t1 instanceof Deadlines && t2 instanceof Deadlines) {
            return ((Deadlines) t1)
                    .getDeadline()
                    .compareTo(((Deadlines) t2).getDeadline());
        }

        if (t1 instanceof Events && t2 instanceof Events) {
            return ((Events) t1)
                    .getStart()
                    .compareTo(((Events) t2).getStart());
        }

        // Todos should always appear first
        if (t1 instanceof ToDos) {
            return -1;
        }

        return t1 instanceof Deadlines
                ? t2 instanceof Events
                ? -1
                : 1
                : 1;
    }
}
