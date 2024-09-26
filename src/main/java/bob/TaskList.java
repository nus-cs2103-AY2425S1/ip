package bob;

import bob.task.Task;

import java.util.*;

/**
 * Represents a list of tasks.
 */
public class TaskList implements Iterable<Task> {
    private final List<Task> tasks;
    private final Set<String> tags = new HashSet<>();

    /**
     * Constructs an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a task list populated by the given list of tasks.
     *
     * @param tasks the tasks that are in the initial task list
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
        for (Task task : tasks) {
            this.tags.addAll(List.of(task.getTags()));
        }
    }

    /**
     * Checks if this list is empty.
     *
     * @return true if this list is empty, false otherwise
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Adds the given task to this list.
     *
     * @param task the task to be added
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes the task at the given index in this list.
     *
     * @param index index of the task to be removed
     * @return the removed task
     * @throws IndexOutOfBoundsException if the index is out of range (index &lt; 0 || index &gt;= size())
     */
    public Task remove(int index) {
        String[] taskTags = tasks.get(index).getTags();
        Task t = tasks.remove(index);

        for (String tag : taskTags) {
            if (getIndicesTaggedWith(tag).isEmpty()) {
                tags.remove(tag);
            }
        }

        return t;
    }

    /**
     * Returns the task at the given index in this list.
     *
     * @param index index of the task to return
     * @return the task at the given index in this list
     * @throws IndexOutOfBoundsException if the index is out of range (index &lt; 0 || index &gt;= size())
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in this list.
     *
     * @return the number of tasks in this list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Removes all tasks in this list.
     */
    public void reset() {
        tasks.clear();
        tags.clear();
    }

    /**
     * Returns this task list as a <code>List&lt;Task&gt;</code>.
     *
     * @return a list of tasks
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Tags the task at index {@code i} in this task list.
     *
     * @param i the index of the task to be tagged
     * @param tagName the name of the tag
     * @return true if the specified task is not already tagged with the given tag name
     */
    public boolean tag(int i, String tagName) {
        this.tags.add(tagName);
        return tasks.get(i).tag(tagName);
    }

    /**
     * Remove a specific tag name from the task at index {@code i} in this list.
     *
     * @param i the index of the task to be untagged
     * @param tagName the name of the tag to be removed
     * @return true if the specified task was previously tagged with the given tag name
     */
    public boolean unTag(int i, String tagName) {
        boolean b = tasks.get(i).unTag(tagName);
        if (getIndicesTaggedWith(tagName).isEmpty()) {
            tags.remove(tagName);
        }
        return b;
    }

    /**
     * Removes all tags from the task at index {@code i} in this list.
     *
     * @param i the index of the task
     */
    public void unTag(int i) {
        Task task = tasks.get(i);
        String[] taskTags = task.getTags();
        task.clearTags();

        for (String tag : taskTags) {
            if (getIndicesTaggedWith(tag).isEmpty()) {
                tags.remove(tag);
            }
        }
    }

    /**
     * Returns all the tags in this task list.
     *
     * @return the set of all the tags in this list
     */
    public Set<String> getAllTags() {
        return this.tags;
    }

    /**
     * Returns the indices of tasks in this list that is tagged with {@code tagName}
     *
     * @param tagName the name of the tag
     * @return a list of indices of tasks tagged with the given tag name
     */
    public List<Integer> getIndicesTaggedWith(String tagName) {
        if (!tags.contains(tagName)) {
            return List.of();
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < tasks.size(); ++i) {
            if (Arrays.asList(tasks.get(i).getTags()).contains(tagName)) {
                list.add(i);
            }
        }

        return list;
    }

    /**
     * Returns the string representation of each task in this list, prefixed by its index (starting from 1)
     * and followed by a linebreak.
     */
    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();

        int i;
        for (i = 0; i < tasks.size() - 1; ++i) {
            text.append(i + 1).append(".").append(tasks.get(i)).append("\n");
        }
        text.append(i + 1).append(".").append(tasks.get(i));

        return text.toString();
    }

    /**
     * Returns an iterator over the tasks in this list.
     *
     * @return an Iterator
     */
    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }
}
