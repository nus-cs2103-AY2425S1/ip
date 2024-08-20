import exception.TaskNotFoundException;
import task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public Task deleteTaskByIndex(int index) throws TaskNotFoundException {
        try {
            return this.taskList.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFoundException();
        }
    }

    public List<Task> getTaskList() {
        return this.taskList;
    }

    public Task getTaskByIndex(int index) throws TaskNotFoundException {
        try {
            return this.taskList.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFoundException();
        }
    }
}
