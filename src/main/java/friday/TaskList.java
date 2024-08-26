package friday;

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
        return this.tasks;
    }

    public int getSize() {
        return this.tasks.size();
    }

    public boolean isTaskListEmpty() {
        return this.tasks.isEmpty();
    }

    public Task getTask(int i) {
        return this.tasks.get(i);
    }
}
