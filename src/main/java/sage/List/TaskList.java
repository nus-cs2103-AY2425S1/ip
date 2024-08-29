package sage.List;

import sage.Sage;
import sage.SageException;
import sage.Task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public int size() {
        return tasks.size();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task delete(int index) throws SageException {
        if (index < 0 || index >= tasks.size()) {
            throw new SageException("Invalid task index.");
        }
        return tasks.remove(index);
    }

    public void mark(int index, boolean isDone) throws SageException {
        if (index < 0 || index >= tasks.size()) {
            throw new SageException("Invalid task index.");
        }
        StringBuilder confirmationMessage = new StringBuilder(isDone ? "Nice! I've marked this task as done:\n"
                : "OK, I've marked this task as not done yet:\n");

        Task task = tasks.get(index);
        task.setDone(isDone);
        Sage.ui.showResponse(String.valueOf(confirmationMessage.append(task)));
    }

    public void listTasks(String text) {
        if (tasks.isEmpty()) {
            Sage.ui.showEmptyList();
        } else {
            StringBuilder result = new StringBuilder(text + "\n");
            for (int i = 0; i < tasks.size(); i++) {
                result.append(String.format("%d. %s\n", i + 1, tasks.get(i)));
            }
            Sage.ui.showResponse(result.toString());
        }
    }
}
