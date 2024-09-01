package mummy.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Represents a list of tasks.
 * 
 * @see Task
 * @see TaskListException
 */
public class TaskList {
    private static final String LABEL = "Here are the tasks in your list:";

    private final ArrayList<Task> tasks;

    /**
     * Constructs a TaskList object.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList object.
     * 
     * @param lines the list of strings representing the tasks
     */
    public TaskList(List<String> lines) {
        this.tasks = parseLines(lines);
    }

    /**
     * Retrieves the task at the specified index from the task list.
     *
     * @param index The index of the task to retrieve. Index starts from 1.
     * @return The task at the specified index.
     * @throws TaskListException If the index is out of bounds.
     */
    public Task get(int index) throws TaskListException {
        // index starts from 1
        try {
            return this.tasks.get(index - 1);
        } catch (IndexOutOfBoundsException exception) {
            throw new TaskListException("No such item");
        }
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes the task at the specified index from the task list.
     *
     * @param index the index of the task to be removed
     * @return the removed task
     * @throws TaskListException if the index is out of bounds
     */
    public Task remove(int index) throws TaskListException {
        try {
            return this.tasks.remove(index - 1);
        } catch (IndexOutOfBoundsException exception) {
            throw new TaskListException("No such item");
        }
    }

    /**
     * Marks a task as done in the task list.
     *
     * @param index The index of the task to be marked as done.
     * @throws TaskListException If the index is out of bounds.
     */
    public void markTask(int index) throws TaskListException {
        try {
            this.tasks.get(index - 1).setAsDone();
        } catch (IndexOutOfBoundsException exception) {
            throw new TaskListException("No such item");
        }
    }

    /**
     * Marks the task at the specified index as undone.
     *
     * @param index The index of the task to be unmarked.
     * @throws TaskListException If the index is out of bounds.
     */
    public void unmarkTask(int index) throws TaskListException {
        try {
            this.tasks.get(index - 1).setAsUndone();
        } catch (IndexOutOfBoundsException exception) {
            throw new TaskListException("No such item");
        }
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int getCount() {
        return this.tasks.size();
    }

    @Override
    public String toString() {
        return IntStream.range(0, this.tasks.size())
                .mapToObj(i -> (i + 1) + ". " + this.tasks.get(i))
                .reduce(
                        new StringBuilder(LABEL).append("\n"), (acc, x) -> acc.append(x).append("\n"),
                        StringBuilder::append
                ).toString();
    }

    /**
     * Converts the task list to a list of file records.
     *
     * @return A list of file records representing the tasks in the task list.
     */
    public List<String> toFileRecords() {
        return this.tasks.stream().map(Task::toFileRecord).toList();
    }

    private static ArrayList<Task> parseLines(List<String> lines) {
        return lines.stream()
                .map(line -> line.split("\\s*\\|\\s*"))
                .flatMap(tokens -> {
                    try {
                        switch (tokens[0]) {
                        case "T":
                            return Stream.of(new ToDo(tokens[2], tokens[1].equals("1")));
                        case "D":
                            return Stream.of(new Deadline(tokens[2], tokens[1].equals("1"), tokens[3]));
                        case "E":
                            return Stream.of(new Event(tokens[2], tokens[1].equals("1"), tokens[3], tokens[4]));
                        default:
                            return Stream.of();
                        }
                    } catch (IndexOutOfBoundsException e) {
                        return Stream.of();
                    }
                })
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }
}
