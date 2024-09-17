package dumpling.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import dumpling.Pair;

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
        if (this.items.isEmpty()) {
            return "      I'm hungry cause there are no tasks in your list...";
        }

        return "     Hungry? Here are the tasks in your list:\n"
                + IntStream.range(1, this.items.size() + 1)
                    .mapToObj(idx -> String.format("     %d.%s", idx, this.items.get(idx - 1)))
                    .reduce((resultString, currString) -> resultString
                            + ((resultString.isEmpty()) ? "" : "\n") + currString)
                .orElse("");
    }

    /**
     * Marks an item as done
     *
     * @param itemIdx target 1-based index of item to be marked as done
     * @return String message of completion
     * @throws IndexOutOfBoundsException If given index is out of range
     */
    public String mark(int itemIdx) throws IndexOutOfBoundsException {
        String message = "     Slurp! I've marked this task as done:\n";
        if (itemIdx < 1 || itemIdx > this.items.size()) {
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
        String message = "     Slurp! I've marked this task as not done yet:\n";
        if (itemIdx < 1 || itemIdx > this.items.size()) {
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
        if (itemIdx < 1 || itemIdx > this.items.size()) {
            throw new IndexOutOfBoundsException();
        }
        Task deletedTask = this.items.remove(itemIdx - 1);
        return "     Glup-glup. I've removed this task:\n"
                + String.format("       %s\n", deletedTask.toString())
                + String.format("     Now you have %d %s in the list.",
                    this.items.size(), (this.items.size() == 1 ? "task" : "tasks"));
    }

    /**
     * Update notes of a task in the task list
     * @param itemIdx target item index of task to be added
     * @param notes updated notes to be added
     * @return message of successful update
     * @throws IndexOutOfBoundsException thrown if item index given is more than the number of items in the list
     */
    public String updateTaskNotes(int itemIdx, String notes) throws IndexOutOfBoundsException {
        if (itemIdx < 1 || itemIdx > this.items.size()) {
            throw new IndexOutOfBoundsException();
        }
        this.items.get(itemIdx - 1).updateNotes(notes);
        return "     Nom, nom, nom. I've updated the notes for this task:\n"
                + String.format("       %s\n", this.items.get(itemIdx - 1).toString());
    }

    /**
     * Find tasks with description that has teh target substring
     * @param targetSubstring Target substring to search for
     * @return String with the list of matching tasks, or a default message if no tasks found.
     */
    public String find(String targetSubstring) {
        return this.items.stream()
                .filter(task -> task.hasSubstring(targetSubstring))
                .map(task -> new Pair<>(1, task.toString()))
                .reduce((resultPair, currentPair) -> {
                    int currIdx = resultPair.getFirst() + currentPair.getFirst();
                    return new Pair<>(
                            currIdx,
                            resultPair.getSecond() + String.format("\n     %d.%s", currIdx, currentPair.getSecond()));
                })
                .map(pair -> "     Waaa! Here are the matching tasks in your list:"
                        + "\n     1." + pair.getSecond())
                .orElse("     There are no tasks that has the substring provided. I'm hungry...");
    }

    /**
     * Helper function that returns the tasks in the format of loading files
     *
     * @return String representation of tasks in the TaskList
     */
    public String getTasksForSaving() {
        return this.items.stream()
                .map(Task::getTaskForSaving)
                .reduce((resultString, currentString) -> resultString + currentString)
                .orElse("");
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
