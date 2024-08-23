package botty;

import exceptions.TaskListEmptyException;
import exceptions.TaskNumberNotFoundException;
import tasks.Task;

import java.util.ArrayList;

public class TaskManager {
    private final ArrayList<Task> taskList = new ArrayList<>(100);
    public String list() throws TaskListEmptyException {
        if (size() == 0) {
            throw new TaskListEmptyException();
        }
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < taskList.size() - 1; i++) {
            content.append((i + 1)).append(". ").append(taskList.get(i)).append("\n");
        }
        content.append(taskList.size()).append(". ").append(taskList.get(taskList.size() - 1));
        return content.toString();
    }
    public Task markTask(int index) throws TaskListEmptyException, TaskNumberNotFoundException {
        if (size() == 0) {
            throw new TaskListEmptyException();
        }
        if (index < 0 || index > size() - 1) {
            throw new TaskNumberNotFoundException(index + 1, size());
        }
        Task task = taskList.get(index);
        task.setCompleted(true);
        return task;
    }
    public Task unmarkTask(int index) throws TaskListEmptyException, TaskNumberNotFoundException {
        if (size() == 0) {
            throw new TaskListEmptyException();
        }
        if (index < 0 || index > size() - 1) {
            throw new TaskNumberNotFoundException(index + 1, size());
        }
        Task task = taskList.get(index);
        task.setCompleted(false);
        return task;
    }
    public Task deleteTask(int index) throws TaskListEmptyException, TaskNumberNotFoundException {
        if (size() == 0) {
            throw new TaskListEmptyException();
        }
        if (index < 0 || index > size() - 1) {
            throw new TaskNumberNotFoundException(index + 1, size());
        }
        Task task = taskList.get(index);
        taskList.remove(index);
        return task;
    }
    public void addTask(Task task) {
        taskList.add(task);
    }
    public int size() {
        return taskList.size();
    }
}
