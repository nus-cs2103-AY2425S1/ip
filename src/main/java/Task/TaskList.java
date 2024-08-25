package Task;

import java.util.ArrayList;
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
        String str = "";
        for (int i = 0; i<tasks.size(); i++) {
            str += i+1 + ": " + tasks.get(i) + "\n";
        }
        return str;
    }
}
