package donna;

import donna.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) { // in case data/donna-tasks.txt exists
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int taskIdx) throws DonnaException {
        if (taskIdx >= 0 && taskIdx < tasks.size()) {
            return tasks.remove(taskIdx);
        } else {
            throw DonnaException.invalidTaskNumber();
        }
    }

    public Task markTask(int taskIdx, boolean isDone) throws DonnaException {
        if (taskIdx >= 0 && taskIdx < tasks.size()) {
            Task taskToMark = tasks.get(taskIdx);
            if (isDone) {
                taskToMark.markDone();
            } else {
                taskToMark.markNotDone();
            }
            return taskToMark;
        } else {
            throw DonnaException.invalidTaskNumber();
        }
    }

    public List<Task> searchTasks(String keyword) {
        List<Task> searchQuery = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.containsKeyword(keyword)) {
                searchQuery.add(task);
            }
        }
        return searchQuery;
    }

    public int getTaskCount() {
        return tasks.size();
    }

    public Task get(int idx) {
        return this.tasks.get(idx);
    }

    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    public int size() {
        return this.tasks.size();
    }
}
