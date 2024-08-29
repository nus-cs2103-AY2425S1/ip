package Assistinator;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) throws AssitinatorExceptions {
        if (index < 0 || index >= tasks.size()) {
            throw new AssitinatorExceptions("Invalid task index");
        }
        tasks.remove(index);
    }

    public void markTask(int index, boolean isDone) throws AssitinatorExceptions {
        if (index < 0 || index >= tasks.size()) {
            throw new AssitinatorExceptions("Invalid task index");
        }
        if (isDone) {
            tasks.get(index).markAsDone();
        } else {
            tasks.get(index).markAsUndone();
        }
    }

    public String listTasks() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(".").append(tasks.get(i).toString()).append("\n");
        }
        return sb.toString().trim();
    }

    /**
     * Filters and generates output string
     * @param keyword Search keyword
     * @return Filtered string
     */
    public String filterTasks(String keyword) {
        int j = 1;
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            if (task.contains(keyword)) {
                sb.append(j).append(".").append(task.toString()).append("\n");
                j++;
            }
        }
        return sb.toString().trim();
    }

    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
