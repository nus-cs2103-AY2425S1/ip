package task;

import storage.Storage;
import UI.UI;
import exception.JadeException;

import java.util.ArrayList;

public class TaskManager {
    private final ArrayList<Task> tasks;
    private final Storage storage;

    public TaskManager(Storage storage) {
        this.storage = storage;
        this.tasks = new ArrayList<>(storage.loadTasks());
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void addTask(Task task) {
        tasks.add(task);
        storage.saveTasks(tasks);
    }

    public void markTask(int index, boolean isDone) {
        if (isValidTaskIndex(index)) {
            if (isDone) {
                tasks.get(index).markAsDone();
            } else {
                tasks.get(index).markAsNotDone();
            }
            storage.saveTasks(tasks);
        }
    }

    public void deleteTask(int index) throws JadeException {
        if (isValidTaskIndex(index)) {
            tasks.remove(index);
            storage.saveTasks(tasks);
        } else {
            throw new JadeException("Hmm, no such task. Try again.");
        }
    }

    public void findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        UI.showMatchingTasks(matchingTasks);
    }

    public boolean isValidTaskIndex(int index) {
        return index >= 0 && index < tasks.size();
    }

    public int getTaskCount() {
        return tasks.size();
    }
}