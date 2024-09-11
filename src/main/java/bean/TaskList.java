package bean;

import bean.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
        assert this.tasks != null : "Task list shound not be null";
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
        assert this.tasks != null : "Task list shound not be null";
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int taskIndex) {
        return tasks.remove(taskIndex);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void markTask(int taskIndex) {
        Task task = tasks.get(taskIndex);
        task.completeTask();
        assert task.getIsDone() : "Task should be marked as done after completing it";
    }

    public void unmarkTask(int taskIndex) {
        Task task = tasks.get(taskIndex);
        task.undoTask();
        assert !task.getIsDone() : "Task should be marked as not done after undoing it";
    }

    public int size() {
        return tasks.size();
    }

    /**
     * Finds and returns a list of tasks that contain the specified keyword in their description.
     * @param keyword The keyword to search for in task descriptions.
     * @return A list of tasks that match the keyword.
     */
    public List<Task> findTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getName().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
