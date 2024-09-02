import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private final List<Task> taskList;

    public TaskManager() {
        taskList = new ArrayList<>();
    }

    public int getTaskCount() {
        return taskList.size();
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public Task removeTask(int index) {
        return taskList.remove(index);
    }

    public Task createTodo(String description) {
        Task todo = new Todo(description);
        taskList.add(todo);
        return todo;
    }

    public Task createDeadline(String description, String by) {
        Task deadline = new Deadline(description, by);
        taskList.add(deadline);
        return deadline;
    }

    public Task createEvent(String description, String from, String to) {
        Task event = new Event(description, from, to);
        taskList.add(event);
        return event;
    }
}
