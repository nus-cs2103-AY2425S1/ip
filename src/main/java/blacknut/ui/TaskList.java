package blacknut.ui;

import blacknut.ui.BlacknutExceptions.*;
import java.util.ArrayList;


class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int index) throws BlacknutExceptions.InvalidTaskNumberException {
        if (index < 0 || index >= tasks.size()) {
            throw new BlacknutExceptions.InvalidTaskNumberException("Invalid task number. Please provide a valid number from the list.");
        }
        return tasks.remove(index);
    }

    public void markTask(int index, boolean markAsDone) throws BlacknutExceptions.InvalidTaskNumberException {
        if (index < 0 || index >= tasks.size()) {
            throw new BlacknutExceptions.InvalidTaskNumberException("Invalid task number. Please provide a valid number from the list.");
        }
        if (markAsDone) {
            tasks.get(index).markAsDone();
        } else {
            tasks.get(index).markAsNotDone();
        }
    }

    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.description.contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    public Task getTask(int index) throws BlacknutExceptions.InvalidTaskNumberException {
        if (index < 0 || index >= tasks.size()) {
            throw new BlacknutExceptions.InvalidTaskNumberException("Invalid task number. Please provide a valid number from the list.");
        }
        return tasks.get(index);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }
}