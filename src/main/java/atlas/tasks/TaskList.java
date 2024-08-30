package atlas.tasks;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Initialises the TaskList with the tasks provided as argument.
     *
     * @param tasks The tasks this TaskList instance should be initialised with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Initialises the TaskList with an empty ArrayList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Lists all tasks in a numbered list.
     *
     * @return String The output shown to the user through the chatbot ui.
     */
    public String listAllTasks() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            output.append(String.format("%d: ", i + 1)).append(this.tasks.get(i));
            if (i < this.tasks.size() - 1) {
                output.append('\n');
            }
        }

        return output.toString();
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to be marked as done.
     * @return Task The task which is marked as done.
     */
    public Task mark(int index) {
        Task task = this.tasks.get(index);
        task.setIsDone();
        return task;
    }

    /**
     * Unmarks a task as done.
     *
     * @param index The index of the task to be unmarked as not done.
     * @return Task The task which is unmarked as not done.
     */
    public Task unmark(int index) {
        Task task = this.tasks.get(index);
        task.setIsNotDone();
        return task;
    }

    /**
     * Adds a task into the list of tasks.
     *
     * @param task The task which is to be added into the list of tasks.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a task from the list.
     *
     * @param index The index of the task to be deleted.
     * @return Task The task which is deleted from the list.
     */
    public Task delete(int index) {
        Task task = this.tasks.get(index);
        this.tasks.remove(index);
        return task;
    }

    /**
     * Finds tasks from the list which match the pattern.
     *
     * @param pattern The pattern which the task name/description needs to match.
     * @return String The output of matched tasks shown to the user through the chatbot ui.
     */
    public String find(String pattern) {
        List<Task> filteredTasks = this.tasks.stream().filter(task -> task.toString().contains(pattern)).toList();
        return new TaskList(new ArrayList<>(filteredTasks)).listAllTasks();
    }

    /**
     * Checks if task list is empty.
     *
     * @return boolean The isEmpty status of the list of tasks.
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    /**
     * Gets the size of the task list.
     *
     * @return int The size of the list of tasks.
     */
    public int size() {
        return this.tasks.size();
    }
}
