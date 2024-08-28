package chatsy;

import chatsy.exceptions.InvalidTaskIndexException;
import chatsy.tasks.Task;

import java.util.ArrayList;
import java.util.List;


public class TaskManager {
    private List<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) throws InvalidTaskIndexException {
        validateIndex(index);
        tasks.remove(index - 1);
    }

    public void markTask(int index) throws InvalidTaskIndexException {
        validateIndex(index);
        tasks.get(index - 1).markAsDone();
    }

    public void unmarkTask(int index) throws InvalidTaskIndexException {
        validateIndex(index);
        tasks.get(index - 1).markAsNotDone();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public String listTasks() {
        if (tasks.isEmpty()) {
            return "No tasks to list.";
        } else {
            StringBuilder listOutput = new StringBuilder("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                listOutput.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
            }
            return listOutput.toString();
        }
    }

    private void validateIndex(int index) throws InvalidTaskIndexException {
        if (index < 1 || index > tasks.size()) {
            throw new InvalidTaskIndexException();
        }
    }

    public String findTasks(String keyword) {
        StringBuilder matchingTasks = new StringBuilder();
        int matchCount = 0;

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchCount++;
                matchingTasks.append(matchCount).append(". ").append(task).append("\n");
            }
        }

        return matchingTasks.toString();
    }
}

