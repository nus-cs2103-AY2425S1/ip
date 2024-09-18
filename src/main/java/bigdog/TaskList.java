package bigdog;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * The TaskList class manages a list of tasks.
 * It provides methods to add, delete, mark, unmark, find, and display tasks.
 */
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

        assert (i > 0 && i <= this.list.size()) : "TaskList Error: That's out of your list!\n";

        // Conversion from a 1-based index to a 0 based index, hence i -1.
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
        return new ArrayList<>(this.list);
    }

    /**
     * Marks a task as done based on the specified index.
     *
     * @param i the index of the task to mark as done (1-based).
     * @return a confirmation message indicating that the task has been marked as done.
     * @throws BigdogException if the index is out of the valid range.
     */
    public String mark(int i) {
      
        assert (i > 0 && i <= this.list.size()) : "TaskList Error: That's out of your list!\n";

        // Conversion from a 1-based index to a 0 based index, hence i -1.
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

        assert (i > 0 && i <= this.list.size()) : "TaskList Error: That's out of your list!\n";

        // Conversion from a 1-based index to a 0 based index, hence i -1.
        this.list.get(i - 1).unmark();

        return String.format("OK, I've marked this task as not done yet:\n%s\n", this.list.get(i - 1));
    }

    /**
     * Finds and returns tasks that contain the specified search string.
     *
     * @param str The string to search for in the task descriptions.
     * @return A string representing the tasks that match the search string with their indices.
     */
    public String find(String str) {
        StringBuilder result = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < this.list.size(); i++) {
            if (this.list.get(i).toString().contains(str)) {
                result.append(i + 1).append(". ").append(this.list.get(i).toString()).append("\n");
            }
        }

        assert !result.toString().equals("Here are the tasks in your list:\n")
                : "There are no similar tasks in your list!\n";

        return result.toString();
    }

    public String viewSchedule(String str) {
        int counter = 1;
        StringBuilder schedule = new StringBuilder(String.format("Here is your schedule for %s\n", str));
        String[] dateParts = str.split("/");
        LocalDateTime date = LocalDateTime.parse(String.format("%s-%s-%sT00:00", dateParts[2], dateParts[1], dateParts[0]));
        for (Task task : this.list) {
            if (task.isOnDay(date)) {
                schedule.append(counter).append(". ").append(task).append("\n");
            }
        }

        // String representation of scheduled tasks
        String scheduleString = schedule.toString();

        // Instead of assertion, handle the case where no tasks are found
        if (scheduleString.equals(String.format("Here is your schedule for %s\n", str))) {
            return "You have no tasks on " + str + " or you need to check your format!";
        }
        return scheduleString;
    }

    /**
     * Returns a string representation of the task list.
     * The string includes the index and description of each task in the list.
     *
     * @return a string representation of the tasks in the list.
     */
    @Override
    public String toString() {
        StringBuilder listString = new StringBuilder("Here are the tasks in your list:\n");

        for (int i = 0; i < this.list.size(); i++) {
            listString.append(i + 1).append(".").append(this.list.get(i)).append("\n");
        }

        return listString.toString();
    }
}
