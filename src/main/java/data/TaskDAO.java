package data;

import java.util.List;
import java.util.Optional;
import features.task.Task;

public interface TaskDAO {
    List<Task> getAllTasks();
    Optional<Task> findTaskById(int id);
    void addTask(Task task);
    void updateTask(Task task);
    Task deleteTask(int id);
}

