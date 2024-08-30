package dudu.utils;

import dudu.task.Task;
import dudu.utils.LineWrapper;

import java.io.FileWriter;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public int addTask(Task task) throws java.io.IOException {
        tasks.add(task);
        return this.tasks.size();
    }

    public Task markTask(int index) {
        tasks.get(index).markCompleted();
        return tasks.get(index);
    }

    public Task unmarkTask(int index) {
        tasks.get(index).markUncompleted();
        return tasks.get(index);
    }

    public Task deleteTask(int index) {
        Task removed = tasks.get(index);
        tasks.remove(index);
        return removed;
    }

    public ArrayList<Task> findTasks(String query) {
        ArrayList<Task> filteredTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.includes(query)) {
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }
}
