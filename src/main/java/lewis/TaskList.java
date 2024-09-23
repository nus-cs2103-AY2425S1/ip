package lewis;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
        if (index > length) {
            throw new LewisException(String.format("Hey, enter a valid index "
                    + "between 1 and %d!", length));
        }
        Task task = TASKS.get(index - 1);
        task.updateStatus(status);
    }

    /**
     * Removes the task from the tasklist given its index (1-indexed input)
     * @param index the 1-indexed task to be removed.
     */
    static void delete(int index) {
        TASKS.remove(index);
        length--;
    }

    /**
     * Returns the current tasklist in a format ready to be written to disk.
     * @return An array of strings in csv format.
     */
    public static ArrayList<String> toSaveFile() {
        ArrayList<String> saveFiles = new ArrayList<>();
        for (Task task : TASKS) {
            saveFiles.add(task.toCsv());
        }
        return saveFiles;
    }

    /**
     * Parses the given array of strings as tasks, and inputs them into the current working
     * tasklist
     * @param tasksToRead an array of strings representing tasks in csv format.
     */
    @SuppressWarnings("checkstyle:MissingSwitchDefault")//Class names are effectively enum
    protected static void load(ArrayList<String> tasksToRead) {
        for (String taskToParse : tasksToRead) {
            String[] tokens = taskToParse.split(",");
            //To handle the different types of tasks separately.
            switch (tokens[0]) {
            case "Event" -> TaskList.add(Event.of(tokens[1], tokens[2],
                    LocalDateTime.parse(tokens[3]),
                    LocalDateTime.parse(tokens[4])));
            case "Deadline" -> TaskList.add(Deadline.of(tokens[1], tokens[2], LocalDateTime.parse(tokens[3])));
            case "Todo" -> TaskList.add(new Todo(tokens[1], tokens[2]));
            }
        }
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
     * @return An array of formatted strings.
     */
    public static ArrayList<String> allTasksToString() {
        ArrayList<String> tasksToString = new ArrayList<>();
        for (int i = 0; i < currentTasks.size(); i++) {
            Task taskToAdd = TaskList.getTask(i);
            String taskString = String.format("%d. %s", i + 1, taskToAdd.toString());
            tasksToString.add(taskString);
        }
        return tasksToString;
    }

    /**
     * Resets the current tasklist to the entire tasklist.
     * This usage should be called when calling the 'list' command.
     */
    public static void showAllTasks() {
        currentTasks = TASKS;
    }

    /**
     * Sets the current tasklist to a subset of all tasks
     * containing the keyword in its description or date and time fields.
     * This method can be called repeatedly to narrow the list of tasks.
     * @param keyword the keyword to look for
     */
    public static void allMatchingTasks(String keyword) {
        ArrayList<Task> filteredTasks = new ArrayList<>();
        for (int i = 0; i < currentTasks.size(); i++) {
            Task taskToCheck = TaskList.getTask(i);
            if (taskToCheck.toString().contains(keyword)) {
                filteredTasks.add(taskToCheck);
            }
        }
        currentTasks = filteredTasks;
    }

    /**
     * Gets the current length of the tasklist (i.e. the number of tasks
     * in the tasklist)
     * @return the length of the tasklist
     */
    public static int getLength() {
        return length;
    }
}
