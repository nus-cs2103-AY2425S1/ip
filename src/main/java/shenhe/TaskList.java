package shenhe;

import shenhe.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Task getTask(int taskNumber) {
        return tasks.get(taskNumber);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int taskNumber) {
        tasks.remove(taskNumber);
    }

    public int getSize() {
        return tasks.size();
    }

    /**
     * Finds and returns a list of tasks that contain the specified keyword in their descriptions.
     * <p>
     * This method searches through the current list of tasks and checks if each task's description
     * contains the provided keyword. Tasks that match the keyword are added to a list, which is
     * then returned to the caller.
     * </p>
     *
     * @param keyword The keyword to search for within task descriptions.
     * @return A list of tasks that contain the specified keyword in their descriptions. If no tasks
     *         match the keyword, an empty list is returned.
     */
    public List<Task> findTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.toString().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

}
