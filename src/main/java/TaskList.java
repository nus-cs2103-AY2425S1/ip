import Tasks.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    private List<Task> tasks;
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public void deleteTask(int taskNumber) {
        Task task = tasks.remove(taskNumber - 1);
    }
    public void addTask(Task task) {
        tasks.add(task);
    }
    @Override
    public String toString() {
        return tasks.stream()
                .map(task -> (tasks.indexOf(task) + 1) + "." + task)
                .collect(Collectors.joining("\n"));
    }
}
