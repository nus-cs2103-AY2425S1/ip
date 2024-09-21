package mummy.task;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Represents a list of tasks.
 * @see Task
 * @see TaskListException
 */
public class TaskList {

    private static final String DEFAULT_LABEL = "Here are the tasks in your list:";

    private final String label;

    private final ArrayList<Task> tasks;

    /**
     * Constructs a TaskList object.
     */
    public TaskList() {
        this.label = DEFAULT_LABEL;
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList object with the file records.
     *
     * @param fileRecords the lines representing the tasks
     */
    public TaskList(List<String> fileRecords) {
        this.label = DEFAULT_LABEL;
        this.tasks = parseLines(fileRecords);
    }

    private TaskList(String label, ArrayList<Task> tasks) {
        this.label = label;
        this.tasks = tasks;
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
    public int count() {
        return this.tasks.size();
    }

    @Override
    public String toString() {
        return IntStream.range(0, this.tasks.size())
                .mapToObj(i -> (i + 1) + ". " + this.tasks.get(i))
                .reduce(
                        new StringBuilder(label).append("\n"), (acc, x) -> acc.append(x).append("\n"),
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

    /**
     * Filters the tasks in the task list based on the given predicate.
     *
     * @param predicate the predicate used to filter the tasks
     * @return a new TaskList containing the matching tasks
     */
    public TaskList filter(Predicate<Task> predicate) {
        ArrayList<Task> filteredTasks = this.tasks.stream()
                .filter(predicate)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        return new TaskList("Here are the matching tasks in your list:", filteredTasks);
    }

    private static ArrayList<Task> parseLines(List<String> fileRecords) {
        return fileRecords.stream()
                .map(fileRecord -> fileRecord.split("\\s*\\|\\s*"))
                .flatMap(tokens -> {
                    try {
                        return switch (tokens[0]) {
                        case "T" -> Stream.of(new ToDo(tokens[2], tokens[1].equals("1")));
                        case "D" -> Stream.of(new Deadline(tokens[2], tokens[1].equals("1"), tokens[3]));
                        case "E" -> Stream.of(new Event(tokens[2], tokens[1].equals("1"), tokens[3], tokens[4]));
                        default -> Stream.of();
                        };
                    } catch (IndexOutOfBoundsException e) {
                        return Stream.of();
                    }
                })
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }
}
