package task;

import exception.TaskNotFoundException;
import io.Saveable;

import java.util.ArrayList;
import java.util.List;

public class TaskList implements Saveable {
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

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (Task task : this.taskList) {
            output.append(task);
            output.append("\n");
        }
        return output.toString();
    }

    @Override
    public String save() {
        StringBuilder output = new StringBuilder();
        for (Saveable task : this.taskList) {
            output.append(task.save());
            output.append("\n");
        }
        return output.toString();
    }
}
