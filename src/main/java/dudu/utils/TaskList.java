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
        FileWriter fw = new FileWriter("./data/dudu.txt", true);
        fw.write("\n" + task.formatString());
        fw.close();
        String output = LineWrapper.wrap(String.format("Got it. I've added this task:\n    %s\nNow you have %d tasks in the list.", task, tasks.size()));
        System.out.println(output);
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
