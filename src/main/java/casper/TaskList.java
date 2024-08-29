package casper;

import exception.CasperBotOutOfBoundsException;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public boolean isEmpty() {
        return this.taskList.isEmpty();
    }

    public int getNumberOfTasks() {
        return this.taskList.size();
    }

    public Task getTask(int index) throws CasperBotOutOfBoundsException {
        if (index >= 0 && index < this.taskList.size()) {
            return this.taskList.get(index);
        } else {
            throw new CasperBotOutOfBoundsException();
        }
    }

    public void deleteTask(Task task) {
        this.taskList.remove(task);
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Returns a TaskList containing all tasks matching the keyword
     */
    public TaskList findMatchTasks(String keyword) {
        TaskList matched = new TaskList();
        for (Task task: this.taskList) {
            if (task.toString().contains(keyword)) {
                matched.addTask(task);
            }
        }
        return matched;
    }
}
