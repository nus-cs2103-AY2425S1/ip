package ekud.task.comparators;

import java.util.Comparator;

import ekud.task.Task;

/**
 * {@link Comparator} that sorts {@link Task} by priority.
 * </p>
 * Task with higher priority should be first.
 *
 * @author uniqly
 */
public class SortTaskByPriority implements Comparator<Task> {
    @Override
    public int compare(Task task1, Task task2) {
        assert task1.getPriority() != null : "priority should not be null";
        assert task2.getPriority() != null : "priority should not be null";

        // Since HIGH is declared first it will have higher priority over LOW
        return task1.getPriority().compareTo(task2.getPriority());
    }
}
