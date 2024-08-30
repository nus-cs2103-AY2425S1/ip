package atlas.tasks;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public String listAllTasks() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            output.append(String.format("%d: ", i + 1)).append(this.tasks.get(i));
            if (i < this.tasks.size() - 1) {
                output.append('\n');
            }
        }

        return output.toString();
    }

    public Task mark(int index) {
        Task task = this.tasks.get(index);
        task.setIsDone();
        return task;
    }

    public Task unmark(int index) {
        Task task = this.tasks.get(index);
        task.setIsNotDone();
        return task;
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public Task delete(int index) {
        Task task = this.tasks.get(index);
        this.tasks.remove(index);
        return task;
    }

    public String find(String pattern) {
        List<Task> filteredTasks = this.tasks.stream().filter(task -> task.toString().contains(pattern)).toList();
        return new TaskList(new ArrayList<>(filteredTasks)).listAllTasks();
    }

    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    public int size() {
        return this.tasks.size();
    }
}
