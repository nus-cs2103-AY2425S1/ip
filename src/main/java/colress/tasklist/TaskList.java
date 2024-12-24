package colress.tasklist;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
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

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public boolean isEmpty() {
        return getTasks().isEmpty();
    }

    public boolean isOutOfBounds(int x) {
        return x > getTasks().size();
    }

    public String getCurrTask(int index) {
        return String.format("\n%d. " + getTasks().get(index), index + 1);
    }

    /**
     * Facilitates building a string representation of the list of tasks and returns it.
     */
    public String retrieveTasks() {
        return buildListOfTasks(x -> true);
    }

    /**
     * Facilitates building a string representation of the list of tasks that falls on the provided LocalDate object
     * and returns it.
     */
    public String retrieveTasks(LocalDate date) {
        return buildListOfTasks(x -> x.fallsOnDate(date));
    }

    /**
     * Facilitates building a string representation of the list of tasks whose description contains a specified keyword
     * and returns it.
     */
    public String retrieveTasks(String keyword) {
        return buildListOfTasks(x -> x.containsInDescription(keyword));
    }

    private String buildListOfTasks(Function<Task, Boolean> condition) {
        String result = "";
        if (getTasks().isEmpty()) {
            return result;
        } else {
            for (int i = 0; i < getTasks().size(); i++) {
                Task currTask = getTasks().get(i);
                if (condition.apply(currTask)) {
                    result += getCurrTask(i);
                }
            }
        }

        if (result.isEmpty()) {
            return result;
        }
        return RESULT_PREAMBLE + result;
    }

    public Stream<Task> stream() {
        return tasks.stream();
    }

    public abstract String addTask(Task task);
    public abstract String checkTask(int... taskNumbers);
    public abstract String uncheckTask(int... taskNumbers);
    public abstract void deleteTask(int... taskNumbers);

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
