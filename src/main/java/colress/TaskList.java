package colress;

import java.time.LocalDate;
import java.util.ArrayList;

import colress.task.Task;

/**
 * Represents the TaskList of the Colress chatbot.
 */
public final class TaskList {
    private final ArrayList<Task> tasks;
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public Task getTask(int index) {
        return tasks.get(index);
    }
    public int getSize() {
        return tasks.size();
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
        try {
            String result = "";
            for (int i: taskNumbers) {
                assert !isOutOfBounds(i) : "Task Number should not be out of bounds.";
                Task task = tasks.get(i - 1);
                task.check();
                result += getCurrTask(i - 1);
            }
            return result;
        } catch (IndexOutOfBoundsException e) {
            return "";
        }
    }

    /**
     * Facilitates marking the Task object that corresponds to the provided task number as not done.
     *
     * @return A string representation of the task that was marked not done.
     */
    public String uncheckTask(int... taskNumbers) {
        try {
            String result = "";
            for (int i: taskNumbers) {
                assert !isOutOfBounds(i) : "Task Number should not be out of bounds.";
                Task task = tasks.get(i - 1);
                task.uncheck();
                result += getCurrTask(i - 1);
            }
            return result;
        } catch (IndexOutOfBoundsException e) {
            return "";
        }
    }

    /**
     * Facilitates removing the Task object that corresponds to the provided task number.
     */
    public void deleteTask(int... taskNumbers) {
        try {
            for (int i: taskNumbers) {
                assert !isOutOfBounds(i) : "Task Number should not be out of bounds.";
                tasks.remove(i - 1);
            }
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    /**
     * Facilitates building a string representation of the list of tasks and returns it.
     */
    public String retrieveTasks() {
        String result = "";
        if (tasks.isEmpty()) {
            return result;
        }

        for (int i = 0; i < tasks.size(); i++) {
            result += getCurrTask(i);
        }
        return "Here is your list:" + result;
    }

    /**
     * Facilitates building a string representation of the list of tasks that falls on the provided LocalDate object
     * and returns it.
     */
    public String retrieveTasks(LocalDate date) {
        String result = "";
        if (tasks.isEmpty()) {
            return result;
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                Task currTask = tasks.get(i);
                if (!currTask.fallsOnDate(date)) {
                    continue;
                }
                result += getCurrTask(i);
            }
        }

        if (result.isEmpty()) {
            return result;
        }
        return "Here is your list:" + result;
    }

    /**
     * Facilitates building a string representation of the list of tasks whose description contains a specified keyword
     * and returns it.
     */
    public String retrieveTasks(String keyword) {
        String result = "";
        if (tasks.isEmpty()) {
            return result;
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                Task currTask = tasks.get(i);
                if (!currTask.containsInDescription(keyword)) {
                    continue;
                }
                result += getCurrTask(i);
            }
        }

        if (result.isEmpty()) {
            return result;
        }
        return "Here is your list:" + result;
    }
}
