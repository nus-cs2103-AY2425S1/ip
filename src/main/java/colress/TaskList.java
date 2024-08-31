package colress;

import java.time.LocalDate;
import java.util.ArrayList;

import colress.task.Task;

/**
 * Represents the TaskList of the Colress chatbot.
 */
public final class TaskList {
    private final ArrayList<Task> TASKS;
    public TaskList() {
        this.TASKS = new ArrayList<>();
    }
    public Task get(int index) {
        return TASKS.get(index);
    }
    public int size() {
        return TASKS.size();
    }
    public boolean isEmpty() {
        return TASKS.isEmpty();
    }
    public boolean isOutOfBounds(int x) {
        return x > TASKS.size();
    }
    private String getCurrTask(int taskNumber) {
        return taskNumber + ". " + TASKS.get(taskNumber - 1);
    }

    /**
     * Facilitates adding the provided Task object.
     *
     * @return A string representation of the task that was added.
     */
    public String addTask(Task task) {
        TASKS.add(task);
        return getCurrTask(TASKS.size());
    }

    /**
     * Facilitates marking the Task object that corresponds to the provided task number as done.
     *
     * @return A string representation of the task that was marked done.
     */
    public String checkTask(int taskNumber) {
        try {
            Task task = TASKS.get(taskNumber - 1);
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
            Task task = TASKS.get(taskNumber - 1);
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
            TASKS.remove(taskNumber - 1);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    /**
     * Facilitates building a string representation of the list of tasks and returns it.
     */
    public String retrieveTasks() {
        String result = "";
        if (TASKS.isEmpty()) {
            return result;
        }

        for (int i = 0; i < TASKS.size(); i++) {
            result += String.format("\n%d. " + TASKS.get(i), i + 1);
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
        if (TASKS.isEmpty()) {
            return result;
        } else {
            for (int i = 0; i < TASKS.size(); i++) {
                Task currTask = TASKS.get(i);
                if (!currTask.fallsOnDate(date)) {
                    continue;
                }
                result += String.format("\n%d. " + TASKS.get(i), i + 1);
            }
        }

        if (result.isEmpty()) {
            return result;
        }
        return "Here is your list:" + result;
    }

    public String retrieveTasks(String keyword) {
        String result = "";
        if (TASKS.isEmpty()) {
            return result;
        } else {
            for (int i = 0; i < TASKS.size(); i++) {
                Task currTask = TASKS.get(i);
                if (!currTask.containsInDescription(keyword)) {
                    continue;
                }
                result += String.format("\n%d. " + TASKS.get(i), i + 1);
            }
        }

        if (result.isEmpty()) {
            return result;
        }
        return "Here is your list:" + result;
    }
}
