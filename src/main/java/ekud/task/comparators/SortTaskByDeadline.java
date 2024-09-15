package ekud.task.comparators;

import java.time.LocalDateTime;
import java.util.Comparator;

import ekud.task.IHasDeadline;
import ekud.task.Task;

/**
 * {@link Comparator} that is used to sort {@link Task} by a due date.
 * <p/>
 * If either one of the task does not have a deadline, then the one with no deadline is bigger.
 *
 * @author uniqly
 */
public class SortTaskByDeadline implements Comparator<Task> {
    @Override
    public int compare(Task task1, Task task2) {
        boolean doesTask1HaveDeadline = task1 instanceof IHasDeadline;
        boolean doesTask2HaveDeadline = task2 instanceof IHasDeadline;
        boolean isBothHaveDeadline = doesTask1HaveDeadline && doesTask2HaveDeadline;
        if (isBothHaveDeadline) {
            LocalDateTime deadline1 = ((IHasDeadline) task1).getDeadline();
            LocalDateTime deadline2 = ((IHasDeadline) task2).getDeadline();

            assert deadline1 != null : "deadline should not be null";
            assert deadline2 != null : "deadline should not be null";

            return deadline1.compareTo(deadline2);
        }

        return -Boolean.compare(doesTask1HaveDeadline, doesTask2HaveDeadline);
    }
}
