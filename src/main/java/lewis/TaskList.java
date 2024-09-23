package lewis;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * This class implements a container for tasks, using Java ArrayList.
 * It supports the following methods:
 * (i) add
 * (ii) delete
 * (iii) save
 * (iv) load
 */
public class TaskList {
    /**
     * Private fields that keep track of the length of the tasklist
     * (equivalently, the number of elements in the tasklist)
     */
    private static int length = 0;
    /**
     * Container for all tasks entered in to the tasklist
     */
    private static final ArrayList<Task> TASKS = new ArrayList<>(1024);

    /**
     * Container for all tasks that the user is currently viewing.
     * By default, it will refer to all tasks.
     */
    private static ArrayList<Task> currentTasks = TASKS;
    /** The singular instance of TaskList */
    private static final TaskList TASKLIST = new TaskList();

    /** Private constructor for a tasklist */
    private TaskList() {
    }

    /**
     * Factory method for instantiating a tasklist.
     * Given an array of valid strings representing tasks in csv format,
     * load the tasks into tasklist, and return the filled tasklist.
     * @return a TaskList containing the read tasks
     */
    public static TaskList of(ArrayList<String> tasksToRead) {
        TASKS.clear();
        TaskList.load(tasksToRead);
        return TASKLIST;
    }

    /**
     * Adds a task to the end of the tasklist.
     * @param task the task to be added
     */
    static void add(Task task) {
        TASKS.add(task);
        length++;
        sortChronological();
    }

    /**
     * Gets the task at the specfied index of the tasklist
     * @param index the index of the task to retrive
     * @return a task
     */
    static Task getTask(int index) {
        return currentTasks.get(index);
    }


    /**
     * Updates the status of the task from the tasklist given its index (1-indexed input)
     * @param index the 1-indexed task to be updated.
     */
    static void update(int index, Task.Status status) throws LewisException {
        if (length == 0) {
            throw new LewisException("Hey, you can't update an empty list. Try"
                    + "entering some tasks first!");
        }
        if (index > length || index < 1) {
            throw new LewisException(String.format("Hey, enter a valid index "
                    + "between 1 and %d!", length));
        }
        Task task = currentTasks.get(index - 1);
        task.updateStatus(status);
        sortChronological();
    }

    /**
     * Removes the task from the tasklist given its index (1-indexed input)
     * @param index the 1-indexed task to be removed.
     */
    static void delete(int index) {
        currentTasks.remove(index);
        length--;
        sortChronological();
    }

    /**
     * Returns the current tasklist in a format ready to be written to disk
     * using Java streams.
     * @return An array of strings in csv format.
     */
    public static ArrayList<String> toSaveFile() {
        sortChronological();
        return TASKS.stream()
                .map(Task::toCsv)
                .collect(Collectors.toCollection(ArrayList::new));
    }


    /**
     * Parses the given array of strings as tasks, and inputs them into the current working
     * tasklist using streams.
     * @param tasksToRead an array of strings representing tasks in csv format.
     */
    @SuppressWarnings("checkstyle:MissingSwitchDefault")//Class names are effectively enum
    protected static void load(ArrayList<String> tasksToRead) {
        tasksToRead.stream()
                .map(taskToParse -> taskToParse.split(","))
                .forEach(tokens -> {
                    switch (tokens[0]) {
                    case "Event" -> TaskList.add(Event.of(tokens[1], tokens[2],
                            LocalDateTime.parse(tokens[3]),
                            LocalDateTime.parse(tokens[4])));
                    case "Deadline" -> TaskList.add(Deadline.of(tokens[1], tokens[2], LocalDateTime.parse(tokens[3])));
                    case "Todo" -> TaskList.add(new Todo(tokens[1], tokens[2]));
                    }
                });
        sortChronological();
    }


    /**
     * Returns a string representation of this tasklist
     * @return a string
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < length; i++) {
            str.append(String.format("%d. ", i + 1));
            str.append(TASKS.get(i).toString());
            str.append("\n");
        }
        return str.toString();
    }


    /**
     * Formats the tasks in the tasklist to be printed to the user
     * Uses Java streams to process the list.
     * @return An array of formatted strings.
     */
    public static ArrayList<String> allTasksToString() {
        return IntStream.range(0, currentTasks.size())
                .mapToObj(i -> String.format("%d. %s", i + 1, TaskList.getTask(i).toString()))
                .collect(Collectors.toCollection(ArrayList::new));
    }


    /**
     * Resets the current tasklist to the entire tasklist.
     * This usage should be called when calling the 'list' command.
     */
    public static void showAllTasks() {
        currentTasks = TASKS;
        length = TASKS.size();
    }

    /**
     * Sets the current tasklist to a subset of all tasks
     * containing the keyword in its description or date and time fields.
     * Uses Java streams to do so without manually iterating over the whole list
     * This method can be called repeatedly to narrow the list of tasks.
     * @param keyword the keyword to look for
     */
    public static void allMatchingTasks(String keyword) {
        currentTasks = currentTasks.stream()
                .filter(task -> task.toString().contains(keyword))
                .collect(Collectors.toCollection(ArrayList::new));
        length = currentTasks.size();
    }

    /**
     * Gets the current length of the tasklist (i.e. the number of tasks
     * in the tasklist)
     * @return the length of the tasklist
     */
    public static int getLength() {
        return length;
    }

    /**
     * Sorts the whole tasklist based on these conditions (in order):
     * 1. Tasks marked as done have the lowest priority
     * 2. Todos will have the highest priority
     * 3. The earlier deadline of a deadline or the earlier
     *    "from" date and time of an event takes precedence over
     *    a later one
     * 4. Tasks will be sorted lexicographically if they fall past condition 3.
     */
    private static void sortChronological() {
        TASKS.sort(Comparable::compareTo);
    }
}
