package oyster.tasks;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * TaskList class that holds Tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    /**
     * Inserts a Task into the list.
     *
     * @param task Inserts a Task at the end.
     */
    public void insert(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a Task in the list.
     *
     * @param index Index to remove Task.
     */
    public void delete(int index) {
        tasks.remove(index);
    }

    /**
     * Deletes a Task in the list and returns it.
     *
     * @param index Index to remove Task.
     * @return Removed Task object.
     */
    public Task pop(int index) {
        return tasks.remove(index);
    }

    /**
     * Marks a Task in the list.
     *
     * @param index Index of Task to mark.
     */
    public void mark(int index) {
        tasks.get(index).mark();
    }

    /**
     * Unmarks a Task in the list.
     *
     * @param index Index of Task to unmark.
     */
    public void unmark(int index) {
        tasks.get(index).unmark();
    }

    /**
     * Checks if the list is empty.
     *
     * @return True if the TaskList has no Tasks.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns a Task at the index.
     *
     * @param index Index of the Task in the TaskList.
     * @return Task object at the index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of Tasks in the TaskList.
     *
     * @return Number of Tasks in the TaskList.
     */
    public int length() {
        return tasks.size();
    }

    /**
     * A copy of the ArrayList used to hold all the Tasks.
     *
     * @return A copy of the ArrayList used to hold all the Tasks.
     */
    public ArrayList<Task> getItems() {
        @SuppressWarnings("unchecked")
        ArrayList<Task> copy = (ArrayList<Task>) tasks.clone();
        return copy;
    }


    /**
     * A copy of the ArrayList filtered according to a predicate.
     *
     * @param predicate Filtering function that takes in a Task and returns a boolean
     * @return A copy of the ArrayList after filtering.
     */
    public ArrayList<Task> filter(Predicate<Task> predicate) {
        @SuppressWarnings("unchecked")
        ArrayList<Task> copy = (ArrayList<Task>) tasks.clone();

        // Filter
        copy.removeIf(task -> !predicate.test(task));

        return copy;
    }

    /**
     * Performs a mapping function over the TaskList.
     *
     * @return A copy of the ArrayList after mapping is applied.
     */
    public ArrayList<Task> map(Function<Task, Task> mapper) {
        return getItems().stream()
            .map(mapper)
            .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < tasks.size(); i++) {
            result.append(String.format("%d. %s", i + 1, tasks.get(i)))
                .append(i < tasks.size() - 1 ? "\n" : "");
        }

        return result.toString();
    }
}
