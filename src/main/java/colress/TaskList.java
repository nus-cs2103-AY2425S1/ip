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
    public Task get(int index) {
        return tasks.get(index);
    }
    public int size() {
        return tasks.size();
    }
    public boolean isEmpty() {
        return tasks.isEmpty();
    }
    public boolean isOutOfBounds(int x) {
        return x > tasks.size();
    }
    private String getCurrTask(int taskNumber) {
        return taskNumber + ". " + tasks.get(taskNumber - 1);
    }

    /**
     * Facilitates adding the provided Task object.
     *
     * @return A string representation of the task that was added.
     */
    public String addTask(Task task) {
        tasks.add(task);
        return getCurrTask(tasks.size());
    }

    /**
     * Facilitates marking the Task object that corresponds to the provided task number as done.
     *
     * @return A string representation of the task that was marked done.
     */
    public String checkTask(int taskNumber) {
        try {
            Task task = tasks.get(taskNumber - 1);
            task.check();
            return getCurrTask(taskNumber);
        } catch (IndexOutOfBoundsException e) {
            return "";
        }
    }

    /**
     * Facilitates marking the Task object that corresponds to the provided task number as not done.
     *
     * @return A string representation of the task that was marked not done.
     */
    public String uncheckTask(int taskNumber) {
        try {
            Task task = tasks.get(taskNumber - 1);
            task.uncheck();
            return getCurrTask(taskNumber);
        } catch (IndexOutOfBoundsException e) {
            return "";
        }
    }

    /**
     * Facilitates removing the Task object that corresponds to the provided task number.
     */
    public void deleteTask(int taskNumber) {
        try {
            tasks.remove(taskNumber - 1);
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
            result += String.format("\n%d. " + tasks.get(i), i + 1);
        }

        if (result.isEmpty()) {
            return result;
        } else {
            return "Here is your list:" + result;
        }
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
                result += String.format("\n%d. " + tasks.get(i), i + 1);
            }
        }

        if (result.isEmpty()) {
            return result;
        }
        return "Here is your list:" + result;
    }

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
                result += String.format("\n%d. " + tasks.get(i), i + 1);
            }
        }

        if (result.isEmpty()) {
            return result;
        }
        return "Here is your list:" + result;
    }
}
