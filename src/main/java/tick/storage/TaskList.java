package tick.storage;

import tick.tasks.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> checklist) {
        this.tasks = checklist;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task deleteTask(int index) {
        return this.tasks.remove(index);
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public int getSize() {
        return this.tasks.size();
    }

    public Task markTaskAsDone(int index) {
        Task doneTask = this.tasks.get(index);
        doneTask.markAsDone();
        return doneTask;
    }

    public Task markTaskAsUndone(int index) {
        Task undoneTask = this.tasks.get(index);
        undoneTask.markAsUndone();
        return undoneTask;
    }

    public ArrayList<Task> getAllTasks() {
        return this.tasks;
    }

    /**
     * Finds and returns a list of tasks that contain the specified keyword in their description.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return An ArrayList of tasks that contain the keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> tasksWithKeyword = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task.getDescription().contains(keyword)) {
                tasksWithKeyword.add(task);
            }
        }
        return tasksWithKeyword;
    }

    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }
}
