package bot.tasks;

import java.util.ArrayList;
import java.util.List;

/**
 * List of tasks stored by the bot
 *
 * @author mongj
 */
public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Returns the <code>Task</code> at the specified position in the <code>TaskList</code>.
     *
     * @param index Index of the task in the <code>TaskList</code>.
     * @return <code>Task</code> at the given index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Add a <code>Task</code> to the <code>TaskList</code>.
     * @param task <code>Task</code> to be added.
     * @return Index of the newly added <code>Task</code>.
     */
    public int add(Task task) {
        tasks.add(task);
        return this.size() - 1;
    }

    /**
     * Delete a task in the <code>TaskList</code>.
     *
     * @param index Index of the <code>Task</code> to be deleted.
     * @return The deleted <code>Task</code>.
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns a boolean indicating if the <code>Task</code> at the specified position is marked.
     *
     * @param index Index of the <code>Task</code>.
     * @return boolean indicating if the <code>Task</code> at the specified position is marked.
     */
    public boolean isMarked(int index) {
        return tasks.get(index).isDone();
    }

    /**
     * Mark a <code>Task</code> in the <code>TaskList</code> as done.
     *
     * @param index Index of the <code>Task</code> to be marked.
     * @return <code>Task</code> marked as done.
     */
    public Task mark(int index) {
        tasks.get(index).markAsDone();
        return tasks.get(index);
    }

    /**
     * Mark a <code>Task</code> in the <code>TaskList</code> as incomplete.
     *
     * @param index Index of the <code>Task</code> to be unmarked.
     * @return <code>Task</code> marked as undone.
     */
    public Task unmark(int index) {
        tasks.get(index).markAsIncomplete();
        return tasks.get(index);
    }

    /**
     * Gets the number of <code>Task</code> in the <code>TaskList</code>.
     *
     * @return Number of <code>Task</code> in the <code>TaskList</code>.
     */
    public int size() {
        return tasks.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.size(); i++) {
            String s = (i + 1) + ". " + tasks.get(i) + "\n";
            sb.append(s);
        }
        return sb.toString();
    }

    /**
     * Returns the data representation of the <code>TaskList</code> to store in the data file.
     *
     * @return Data representation of the <code>TaskList</code>.
     */
    public String toData() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.size(); i++) {
            sb.append(tasks.get(i).toData()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Returns a string of all the <code>Task</code> in the <code>TaskList</code>
     * that match the given query string
     *
     * @param queryString Search string to query
     * @return String of all the tasks matching the given query string
     */
    public String search(String queryString) {
        List<Task> filteredList = tasks
                .stream()
                .filter(t -> t.toString().contains(queryString))
                .toList();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < filteredList.size(); i++) {
            sb.append(i + 1).append(". ").append(filteredList.get(i));
            if (i != filteredList.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
