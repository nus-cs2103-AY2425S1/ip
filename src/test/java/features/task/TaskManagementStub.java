package features.task;

import data.TaskDAOStub;
import java.util.*;

public class TaskManagementStub extends TaskManagement {

    private final List<Task> tasks = new ArrayList<>();

    public TaskManagementStub() {
        super(new TaskDAOStub());
    }

    @Override
    public void add(Task task) {
        tasks.add(task);
        super.add(task);
    }

    @Override
    public Task remove(int id) {
        Task task = findTaskById(id).orElse(null);
        if (task != null) {
            tasks.remove(task);
            super.remove(id);
        }
        return task;
    }

    @Override
    public List<Task> getAllTasks() {
        return tasks;
    }
}
