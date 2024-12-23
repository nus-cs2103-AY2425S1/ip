package colress;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

import colress.task.Deadline;
import colress.task.Task;

/**
 * Represents the TaskList of the Colress chatbot.
 */
public final class TaskList {
    public static final String RESULT_PREAMBLE = "Here! This is your list:";
    public static final String MESSAGE_TASK_NUMBER_OUT_OF_BOUND = "Task Number should not be out of bounds.";
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public boolean isOutOfBounds(int x) {
        return x > tasks.size();
    }

    private String getCurrTask(int index) {
        return String.format("\n%d. " + tasks.get(index), index + 1);
    }

    /**
     * Facilitates adding the provided Task object.
     *
     * @return A string representation of the task that was added.
     */
    public String addTask(Task task) {
        tasks.add(task);
        return getCurrTask(tasks.size() - 1);
    }

    /**
     * Facilitates marking the Task object that corresponds to the provided task number as done.
     *
     * @return A string representation of the task that was marked done.
     */
    public String checkTask(int... taskNumbers) {
        return processTask(Task::check, taskNumbers);
    }

    /**
     * Facilitates marking the Task object that corresponds to the provided task number as not done.
     *
     * @return A string representation of the task that was marked not done.
     */
    public String uncheckTask(int... taskNumbers) {
        return processTask(Task::uncheck, taskNumbers);
    }

    private String processTask(Consumer<Task> action, int... taskNumbers) {
        try {
            String result = "";
            for (int i: taskNumbers) {
                assert !isOutOfBounds(i) : MESSAGE_TASK_NUMBER_OUT_OF_BOUND;
                Task task = tasks.get(i - 1);
                action.accept(task);
                result += getCurrTask(i - 1);
            }
            return result;
        } catch (IndexOutOfBoundsException e) {
            return MESSAGE_TASK_NUMBER_OUT_OF_BOUND;
        }
    }

    /**
     * Facilitates removing the Task object that corresponds to the provided task number.
     */
    public void deleteTask(int... taskNumbers) {
        try {
            for (int i = taskNumbers.length - 1; i >= 0; i--) {
                assert !isOutOfBounds(taskNumbers[i]) : MESSAGE_TASK_NUMBER_OUT_OF_BOUND;
                int currIndex = taskNumbers[i] - 1;
                tasks.remove(currIndex);
            }
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
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
        if (tasks.isEmpty()) {
            return result;
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                Task currTask = tasks.get(i);
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

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TaskList)) {
            return false;
        }

        TaskList otherTaskList = (TaskList) other;
        return tasks.equals(otherTaskList.tasks);
    }
}
