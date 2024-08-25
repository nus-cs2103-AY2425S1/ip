package david.Task;

import java.util.List;

public class TaskList {

    List<Task> tasks;

    public TaskList(List<Task> t) {
        this.tasks = t;
    }

    public void addTask(Task t) {
        this.tasks.add(t);
    }

    public int getSize() {
        return tasks.size();
    }

    public void deleteTask(int n) {
        tasks.remove(n);
    }

    public void markTaskAsDone(int n) {
        Task t = tasks.get(n);
        t.markAsDone();
    }

    public void markTaskAsUndone(int n) {
        Task t = tasks.get(n);
        t.markAsUnDone();
    }

    public Task getTask(int n) {
        return this.tasks.get(n);
    }

    @Override
    public String toString() {
        if (tasks.size() == 0) {
            return "All is good :) There is no tasks for the time being.";
        }
        String str = "";
        for (int i = 0; i<tasks.size(); i++) {
            str += i+1 + ": " + tasks.get(i) + "\n";
        }
        return str;
    }
}
