package ekud.task.comparators;

import java.util.Comparator;

import ekud.task.Task;

/**
 * {@link Comparator} that sorts {@link Task} by completion status.
 * <p/>
 * Incomplete tasks have a higher priority.
 *
 * @author uniqly
 */
public class SortTaskByCompletion implements Comparator<Task> {
    @Override
    public int compare(Task task1, Task task2) {
        boolean isTask1Complete = task1.isDone();
        boolean isTask2Complete = task2.isDone();

        return Boolean.compare(isTask1Complete, isTask2Complete);
    }
}
