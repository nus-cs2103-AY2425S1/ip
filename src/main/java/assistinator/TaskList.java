package assistinator;

import java.util.ArrayList;

/**
 * Represents list of task
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Add task to task list
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Delete specified task
     * @param index Index of task
     * @throws AssitinatorExceptions If index is invalid
     */
    public void deleteTask(int index) throws AssitinatorExceptions {
        if (index < 0 || index >= tasks.size()) {
            throw new AssitinatorExceptions("Invalid task index");
        }
        tasks.remove(index);
    }

    /**
     * Marks specified task as done or undone
     * @param index Task index
     * @param isDone Whether task is done or not done
     * @throws AssitinatorExceptions If task index is invalid
     */
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

    /**
     * Return formatted string for task list for list command
     * @return formatted string
     */
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

    /**
     * Returns task list size
     * @return task list size
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Return task list
     * @return Task list
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
