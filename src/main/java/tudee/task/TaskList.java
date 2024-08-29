package tudee.task;

import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private List<Task> taskList;

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    public List<Task> get() {
        return taskList;
    }

    public Task delete(int index) {
        return taskList.remove(index);
    }

    public int size() {
        return taskList.size();
    }
}
