package bibi.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public int getTaskCount() {
        return tasks.size();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public Task removeFromTaskList(int index) {
        return tasks.remove(index - 1);
    }

    public void addToTaskList(Task t) {
        tasks.add(t);
    }

    public void printTaskList() {
        if (tasks.isEmpty()) {
            System.out.println("Good for you, nothing to do today :3");
            return;
        }

        for (int i = 1; i <= tasks.size(); i++) {
            System.out.printf("%d: %s%n", i, tasks.get(i - 1));
        }
    }
}
