package tasks;

import exceptions.NoTasksException;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a class to store TaskList
 */
public class TaskList {

    private List<Task> list;

    /**
     * Initialises tasklist with local data.
     *
     * @param loadedData List of tasks to store into tasklist.
     */
    public TaskList(List<Task> loadedData) {
        this.list = loadedData;
    }

    /**
     * Initialises empty tasklist.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Adds the specified task to the task list.
     *
     * @param task Task to be added.
     * @param saved True if the task to added should be pre-marked as done, otherwise False.
     */
    public String addTask(Task task, boolean saved) {
        this.list.add(task);
        String reply = "I've added the task: \n" + task
                + "\n Now you have " + this.list.size() + " tasks in the list";
        if (!saved) {
            return reply;
        } else {
            return "Tasks Loaded";
        }
    }

    /**
     * Lists out all the tasks in the task list.
     *
     * @throws NoTasksException If tasklist is empty when trying to list or operate on specific tasks.
     */
    public String listOut() throws NoTasksException {
        if (this.list.isEmpty()) {
            throw new NoTasksException();
        }
        StringBuilder reply = new StringBuilder();
        for (int i = 1; i < this.list.size(); i++) {
            reply.append(i).append(".").append(this.list.get(i - 1)).append("\n");
        }
        reply.append(this.list.size()).append(".").append(this.list.get(this.list.size() - 1));
        return reply.toString();
    }

    /**
     * Marks the specified task as done.
     *
     * @param index Index of task to be marked as done.
     * @throws NoTasksException If tasklist is empty when trying to list or operate on specific tasks.
     */
    public String markTask(int index) throws NoTasksException {
        if (this.list.isEmpty()) {
            throw new NoTasksException();
        }
        this.list.get(index).mark();
        String reply = "You have marked the following task as done!\n" + this.list.get(index);
        return reply;
    }

    /**
     * Marks the specified task as undone.
     *
     * @param index Index of task to be marked as done.
     * @throws NoTasksException If tasklist is empty when trying to list or operate on specific tasks.
     */
    public String unmarkTask(int index) throws NoTasksException {
        if (this.list.isEmpty()) {
            throw new NoTasksException();
        }
        this.list.get(index).unmark();
        String reply = "You have unmarked the following task!\n" + this.list.get(index);
        return reply;
    }

    /**
     * Deletes the specified task.
     *
     * @param index Index of task to be deleted.
     * @throws NoTasksException If tasklist is empty when trying to list or operate on specific tasks.
     */
    public String deleteTask(int index) throws NoTasksException {
        if (this.list.isEmpty()) {
            throw new NoTasksException();
        }
        Task deleted = this.list.get(index);
        this.list.remove(index);
        String reply = "Let's go deleting!\nDeleted task " + deleted;
        return reply;
    }

    /**
     * Returns the list of tasks.
     *
     * @return List of tasks.
     */
    public List<Task> getTaskList(){
        return this.list;
    }

    /**
     * Filters tasklist by keyword.
     *
     * @param word Keyword to filter by.
     * @return Filtered list.
     */
    public List<Task> filterByWord(String word) {
        return this.list.stream().filter(task -> task.containsWord(word)).toList();
    }
}
