package pochat.bot;

import java.util.ArrayList;

import pochat.tasks.Task;

/**
 * TaskList contains a list of tasks that the chatbot currently stores
 *     in the active session.
 */
public class TaskList {
    private final ArrayList<Task> listTasks;

    /**
     * Takes in no parameters and sets the task list as empty
     */
    public TaskList() {
        this.listTasks = new ArrayList<Task>();
    }

    /**
     * Takes in listTasks parameter and loads the TaskList with the tasks stored in it
     * @param listTasks <Code>ArrayList</Code> of tasks to be loaded
     */
    public TaskList(ArrayList<Task> listTasks) {
        this.listTasks = listTasks;
    }

    /**
     * Adds the task specified to the TaskList
     * @param task to be added to the TaskList
     */
    public void add(Task task) {
        this.listTasks.add(task);
        assert this.listTasks.contains(task);
    }

    /**
     * Returns the number of tasks stored as an <code>int</code>
     * @return size of type int
     */
    public int size() {
        return this.listTasks.size();
    }

    /**
     * Removes the task specified from the TaskList
     * @param task to be removed from the TaskList
     */
    public void remove(Task task) {
        this.listTasks.remove(task);
        assert !this.listTasks.contains(task);
    }

    public Task get(int index) {
        return this.listTasks.get(index);
    }

    /**
     * Finds tasks that contain the keyword and returns them as
     *     one combined String
     * @param keyword the keyword that tasks should contain
     * @return a string containing all tasks that match the keyword
     */
    public String findMatchingTasks(String keyword) {
        StringBuilder message = new StringBuilder("Here are the matching tasks in your list:\n");

        int index = 1;
        for (Task task : this.listTasks) {
            if (task.contains(keyword)) {
                message.append(index + ". " + task + "\n");
                index++;
            }
        }

        return message.toString();
    }

    /**
     * Checks if the TaskList contains the specified task
     * @param inputTask the task to be checked
     * @return <code>true</code> if the TaskList contains the Task specified,
     *     and otherwise <code>false</code>
     */
    public boolean contains(Task inputTask) {
        for (Task task: this.listTasks) {
            if (task.equals(inputTask)) {
                return true;
            }
        }

        return false;
    }

    public ArrayList<Task> toList() {
        return this.listTasks;
    }
}
