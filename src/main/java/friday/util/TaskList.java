package friday.util;

import friday.task.Task;

import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int taskIndex) {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            Task removedTask = tasks.remove(taskIndex);
            return removedTask;
        } else {
            return null;
        }
    }

    public Task markTaskAsDone(int taskIndex) {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            Task markedTask = tasks.get(taskIndex);
            markedTask.markAsDone();
            return markedTask;
        } else {
            return null;
        }
    }

    public Task unmarkTaskAsDone(int taskIndex) {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            Task unmarkedTask = tasks.get(taskIndex);
            unmarkedTask.unmarkAsDone();
            return unmarkedTask;
        } else {
            return null;
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getSize() {
        return tasks.size();
    }

    public boolean isTaskListEmpty() {
        return tasks.isEmpty();
    }

    public Task getTask(int i) {
        return tasks.get(i);
    }
}
