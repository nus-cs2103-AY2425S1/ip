package colress.tasklist;

import java.time.LocalDate;
import java.util.stream.Stream;

import colress.task.Task;

/**
 * An interface for task lists of the Colress chatbot.
 */
public interface TaskListable {
    boolean isEmpty();
    boolean isOutOfBounds(int x);
    String addTask(Task task);
    String checkTask(int... taskNumbers);
    String uncheckTask(int... taskNumbers);
    void deleteTask(int... taskNumbers);
    String retrieveTasks();
    String retrieveTasks(LocalDate date);
    String retrieveTasks(String keyword);
    Stream<Task> stream();
}
