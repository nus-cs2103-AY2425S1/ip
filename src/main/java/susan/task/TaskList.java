package susan.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void delete(int index) {
        tasks.remove(index);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public String printList() {
        StringBuilder list = new StringBuilder();
        list.append(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            list.append("\n");
            list.append(" ").append(i + 1).append(".").append(tasks.get(i));
        }
        return list.toString();
    }
}
