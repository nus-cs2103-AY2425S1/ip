package colress.tasklist;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import colress.task.Task;

/**
 * An interface for task lists of the Colress chatbot.
 */
public abstract class TaskList {
    public static final String RESULT_PREAMBLE = "Here! This is your list:";
    public static final String MESSAGE_TASK_NUMBER_OUT_OF_BOUND = "Task Number should not be out of bounds.";
    private final List<Task> tasks;

    public TaskList(List<Task> taskList) {
        this.tasks = taskList;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public abstract boolean isEmpty();
    public abstract boolean isOutOfBounds(int x);
    public abstract String addTask(Task task);
    public abstract String checkTask(int... taskNumbers);
    public abstract String uncheckTask(int... taskNumbers);
    public abstract void deleteTask(int... taskNumbers);
    public abstract String retrieveTasks();
    public abstract String retrieveTasks(LocalDate date);
    public abstract String retrieveTasks(String keyword);
    public abstract Stream<Task> stream();

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TaskList)) {
            return false;
        }

        TaskList otherTaskList = (TaskList) other;
        return getTasks().equals(otherTaskList.getTasks());
    }
}
