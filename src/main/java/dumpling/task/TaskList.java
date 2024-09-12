package dumpling.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to handle list of tasks
 */
public class TaskList {

    private List<Task> items;

    /**
     * TaskList that handles the functionalities of items
     */
    public TaskList() {
        this.items = new ArrayList<>();
    }

    public TaskList(List<Task> items) {
        this.items = items;
    }

    /**
     * Add a given task to the list.
     *
     * @param task Target task to be added
     */
    public void add(Task task) {
        this.items.add(task);
    }

    /**
     * Lists all the tasks
     *
     * @return String-representation of the list of items
     */
    public String list() {
        String message = "     Here are the tasks in your list:\n";
        for (int i = 1; i <= this.items.size(); i++) {
            message += String.format(
                "     %d.%s" + (i == this.items.size() ? "" : "\n"),
                i, this.items.get(i - 1)
            );
        }
        return message;
    }

    /**
     * Marks an item as done
     *
     * @param itemIdx target 1-based index of item to be marked as done
     * @return String message of completion
     * @throws IndexOutOfBoundsException If given index is out of range
     */
    public String mark(int itemIdx) throws IndexOutOfBoundsException {
        String message = "     Nice! I've marked this task as done:\n";
        if (itemIdx > this.items.size()) {
            throw new IndexOutOfBoundsException();
        }
        this.items.get(itemIdx - 1).markAsDone();
        message += String.format("       %s", this.items.get(itemIdx - 1));
        return message;
    }

    /**
     * Unmarks an item as done
     *
     * @param itemIdx target 1-based index of item to be unmarked as done
     * @return String message of completion
     * @throws IndexOutOfBoundsException If given index is out of range
     */
    public String unmark(int itemIdx) throws IndexOutOfBoundsException {
        String message = "     OK, I've marked this task as not done yet:\n";
        if (itemIdx > this.items.size()) {
            throw new IndexOutOfBoundsException();
        }
        this.items.get(itemIdx - 1).markAsUndone();
        message += String.format("       %s", this.items.get(itemIdx - 1));
        return message;
    }

    /**
     * Deletes the task at the given index
     *
     * @param itemIdx target 1-based index of item to be unmarked as done
     * @return String message of completion
     * @throws IndexOutOfBoundsException If given index is out of range
     */
    public String delete(int itemIdx) throws IndexOutOfBoundsException {
        if (itemIdx > this.items.size()) {
            throw new IndexOutOfBoundsException();
        }
        Task deletedTask = this.items.remove(itemIdx - 1);
        String message = "     Noted. I've removed this task:\n"
                + String.format("       %s\n", deletedTask.toString())
                + String.format("     Now you have %d %s in the list.",
                    this.items.size(), (this.items.size() == 1 ? "task" : "tasks"));
        return message;
    }

    /**
     * Find tasks with description that has teh target substring
     * @param targetSubstring Target substring to search for
     * @return String with the list of matching tasks, or a default message if no tasks found.
     */
    public String find(String targetSubstring) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : this.items) {
            if (task.hasSubstring(targetSubstring)) {
                matchingTasks.add(task);
            }
        }
        String message;
        if (matchingTasks.isEmpty()) {
            message = "     There are no tasks that has the substring provided.";
        } else {
            message = "     Here are the matching tasks in your list:";
            for (int i = 1; i <= matchingTasks.size(); i++) {
                message += String.format("\n     %d.%s", i, matchingTasks.get(i - 1));
            }
        }
        return message;
    }

    /**
     * Helper function that returns the tasks in the format of loading files
     *
     * @return String representation of tasks in the TaskList
     */
    public String getTasksForSaving() {
        String dataString = "";
        for (Task task : this.items) {
            dataString += task.getTaskForSaving();
        }
        return dataString;
    }

    /**
     * Get the number of items in the TaskList
     *
     * @return number of items in TaskList
     */
    public int getNumItems() {
        return this.items.size();
    }
}
