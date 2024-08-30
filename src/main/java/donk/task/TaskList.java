package donk.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> tasks;
    public TaskList (List<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList () {
        this.tasks = new ArrayList<Task>();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public void remove(int index) {
        this.tasks.remove(index);
    }

    public void add(Task t) {
        tasks.add(t);
    }
}
