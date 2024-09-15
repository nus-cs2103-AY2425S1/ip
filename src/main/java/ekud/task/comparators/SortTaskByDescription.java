package ekud.task.comparators;

import java.util.Comparator;

import ekud.task.Task;

/**
 * {@link Comparator} that sorts {@link Task} by description alphabetically.
 *
 * @author uniqly
 */
public class SortTaskByDescription implements Comparator<Task> {
    @Override
    public int compare(Task task1, Task task2) {
        assert task1.getDescription() != null : "getDescription() should not be null";
        assert task2.getDescription() != null : "getDescription() should not be null";

        return String.CASE_INSENSITIVE_ORDER.compare(task1.getDescription(), task2.getDescription());
    }
}
