package milutrock;

import java.util.ArrayList;

import milutrock.tasks.Task;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<Task>();

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task removeTask(int i) {
        Task task = tasks.get(i);
        this.tasks.remove(i);

        return task;
    }

    public void markTaskAsDone(int i) {
        tasks.get(i).markDone();
    }

    public void unmarkTaskAsDone(int i) {
        tasks.get(i).unmarkDone();
    }

    public int getNumberOfTasks() {
        return tasks.size();
    }

    public String getTaskAtIndexAsString(int i) {
        return tasks.get(i).toString();
    }
}
