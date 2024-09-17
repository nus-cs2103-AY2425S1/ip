package data;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import features.task.Task;

public class TaskDAOStub implements TaskDAO {
    private final Map<Integer, Task> tasks = new HashMap<>();
    private int nextId = 1;

    @Override
    public void addTask(Task task) {
        task.setId(nextId++);
        tasks.put(task.getId(), task);
    }

    @Override
    public int deleteTasks(List<Integer> ids) {
        return 0;
    }

    @Override
    public Task deleteTask(int id) {
        return tasks.remove(id);
    }

    @Override
    public Optional<Task> findTaskById(int id) {
        return Optional.ofNullable(tasks.get(id));
    }

    @Override
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public void updateTask(Task task) {
        tasks.put(task.getId(), task);
    }
}
