package colress;

import java.time.LocalDate;
import java.util.ArrayList;

import colress.task.Task;

public class TaskList {
    private ArrayList<Task> tasks;
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

    public String addTask(Task task) {
        tasks.add(task);
        return getCurrTask(tasks.size());
    }

    public String checkTask(int taskNumber) {
        Task task = tasks.get(taskNumber - 1);
        task.check();
        return getCurrTask(taskNumber);
    }

    public String uncheckTask(int taskNumber) {
        Task task = tasks.get(taskNumber - 1);
        task.uncheck();
        return getCurrTask(taskNumber);
    }

    public void deleteTask(int taskNumber) {
        tasks.remove(taskNumber - 1);
    }

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
}
