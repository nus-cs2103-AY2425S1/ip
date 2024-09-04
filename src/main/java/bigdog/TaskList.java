package bigdog;

import java.util.ArrayList;

public class TaskList {

    /** The list of tasks managed by this TaskList. */
    protected ArrayList<Task> list;

    /**
     * Constructs a TaskList with the specified list of tasks.
     *
     * @param list the list of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Adds a new task to the list.
     *
     * @param task the task to add to the list.
     * @return a confirmation message indicating that the task has been added and showing the updated task count.
     */
    public String add(Task task) {
        this.list.add(task);
        return String.format("Got it. I've added this task:\n%s\nNow you have %s tasks in the list.\n",
                task.toString(), this.list.size());
    }

    /**
     * Deletes a task from the list based on the specified index.
     *
     * @param i the index of the task to delete (1-based).
     * @return a confirmation message indicating that the task has been removed and showing the updated task count.
     * @throws BigdogException if the index is out of the valid range.
     */
    public String delete(int i) {
        if (i <= 0 || i > this.list.size()) {
            throw new BigdogException("TaskList Error: That's out of your list!\n");
        }
        Task temp = this.list.get(i - 1);
        this.list.remove(i - 1);
        return String.format("Noted. I've removed this task:\n%s\nNow you have %s tasks in the list.\n",
                temp, this.list.size());
    }

    /**
     * Returns the list of tasks managed by this TaskList.
     *
     * @return the list of tasks.
     */
    public ArrayList<Task> get() {
        return this.list;
    }

    /**
     * Marks a task as done based on the specified index.
     *
     * @param i the index of the task to mark as done (1-based).
     * @return a confirmation message indicating that the task has been marked as done.
     * @throws BigdogException if the index is out of the valid range.
     */
    public String mark(int i) {
        if (i <= 0 || i > this.list.size()) {
            throw new BigdogException("TaskList Error: That's out of your list!\n");
        }
        this.list.get(i - 1).mark();
        return String.format("Nice! I've marked this task as done:\n%s\n", this.list.get(i - 1));
    }

    /**
     * Unmarks a task as not done based on the specified index.
     *
     * @param i the index of the task to unmark (1-based).
     * @return a confirmation message indicating that the task has been marked as not done.
     * @throws BigdogException if the index is out of the valid range.
     */
    public String unmark(int i) {
        if (i <= 0 || i > this.list.size()) {
            throw new BigdogException("TaskList Error: That's out of your list!\n");
        }
        this.list.get(i - 1).unmark();
        return String.format("OK, I've marked this task as not done yet:\n%s\n", this.list.get(i - 1));
    }

    /**
     * Returns a string representation of the task list.
     * The string includes the index and description of each task in the list.
     *
     * @return a string representation of the tasks in the list.
     */
    @Override
    public String toString() {
        String listString = "Here are the tasks in your list:\n";

        for (int i = 0; i < this.list.size(); i++) {
            listString += (i + 1) + "." + this.list.get(i) + "\n";
        }

        return listString;
    }

    /**
     * Displays the task list by printing it to the console.
     */
    public void show() {
        System.out.println(this);
    }

}
