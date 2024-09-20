package casper.tasks.comparators;

import java.time.LocalDateTime;
import java.util.Comparator;

import casper.tasks.Deadline;
import casper.tasks.Event;
import casper.tasks.Task;
import casper.tasks.ToDo;

/**
 * Compares two {@link Task} objects based on their due or start dates.
 * <p>
 * The comparison order is as follows:
 * <ul>
 * <li>ToDo tasks are pushed to the back</li>
 * <li>Deadline tasks are sorted by their due date</li>
 * <li>Event tasks are sorted by their start date</li>
 * </ul>
 * </p>
 */
public class TimeComparator implements Comparator<Task> {

    /**
     * Compares two tasks by their due or start dates.
     * <p>
     * Returns a negative integer, zero, or a positive integer as the first task's date
     * is less than, equal to, or greater than the second task's date. ToDo tasks are always
     * placed at the end of the sorted list.
     * </p>
     *
     * @param t1 The first task to be compared.
     * @param t2 The second task to be compared.
     * @return A negative integer, zero, or a positive integer as the first task's date
     *         is less than, equal to, or greater than the second task's date.
     */
    @Override
    public int compare(Task t1, Task t2) {
        LocalDateTime time1 = getDateTime(t1);
        LocalDateTime time2 = getDateTime(t2);

        if (time1 == null && time2 == null) {
            return 0;
        } else if (time1 == null) {
            return 1;
        } else if (time2 == null) {
            return -1;
        }

        return time1.compareTo(time2);
    }

    /**
     * Retrieves the date/time associated with the task.
     * <p>
     * If the task is a {@link ToDo}, this method returns null as {@link ToDo} tasks
     * are pushed to the end of the list. For {@link Deadline} and {@link Event} tasks,
     * it returns the relevant date/time.
     * </p>
     *
     * @param task The task whose date/time is to be retrieved.
     * @return The date/time associated with the task, or null if the task is a {@link ToDo}.
     */
    private LocalDateTime getDateTime(Task task) {
        if (task instanceof Deadline) {
            return ((Deadline) task).getBy();
        } else if (task instanceof Event) {
            return ((Event) task).getFrom();
        } else {
            return null; // For ToDo tasks
        }
    }
}

