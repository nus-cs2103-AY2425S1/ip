package devon;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Represents a list of tasks in the Devon application.
 * Provides methods to manage and manipulate the list of tasks, including adding, removing, and marking tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks in the list.
     */
    public int getNumberOfTasks() {
        return tasks.size();
    }

    /**
     * Initializes the task list with tasks loaded from a database.
     *
     * @param stringListOfTasks A list of strings representing tasks in a database format.
     * @throws DevonReadDatabaseException If there is an error reading or parsing the task data from the database.
     */
    public void initialiseLoadTasks(ArrayList<String> stringListOfTasks) throws DevonReadDatabaseException {
        for (String line : stringListOfTasks) {
            String[] fields = line.split(Storage.DB_DELIMITER);
            Task newTask;

            switch (fields[0]) {
            case "Deadline":
                newTask = new Deadline(
                        fields[2],
                        LocalDateTime.parse(fields[3], Storage.DATE_TIME_FORMATTER_FOR_DB)
                );
                break;
            case "Event":
                newTask = new Event(
                        fields[2],
                        LocalDateTime.parse(fields[3], Storage.DATE_TIME_FORMATTER_FOR_DB),
                        LocalDateTime.parse(fields[4], Storage.DATE_TIME_FORMATTER_FOR_DB)
                );
                break;
            case "Todo":
                newTask = new Todo(fields[2]);
                break;
            default:
                throw new DevonReadDatabaseException();
            }

            boolean toBeMarkedAsDone = Integer.parseInt(fields[1]) == 1;
            if (toBeMarkedAsDone) {
                newTask.markAsDoneSilently();
            }

            this.addTask(newTask);
        }
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    protected void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Retrieves a task from the task list based on the task number.
     *
     * @param taskNumber The index of the task to retrieve (0-based index).
     * @return The task at the specified index.
     */
    protected Task getTask(int taskNumber) {
        return this.tasks.get(taskNumber);
    }

    /**
     * Removes a task from the task list based on the task number.
     *
     * @param taskNumber The index of the task to be removed (0-based index).
     * @return A message indicating the task that was removed.
     */
    protected String removeTask(int taskNumber) {
        String textResponse = getTask(taskNumber).announceDeletion();
        this.tasks.remove(taskNumber);
        return textResponse;
    }

    /**
     * Marks a task as done based on the task number.
     *
     * @param taskNumber The index of the task to be marked as done (0-based index).
     * @return A message indicating the task that was marked as done.
     */
    protected String markAsDone(int taskNumber) {
        return getTask(taskNumber).markAsDone();
    }

    /**
     * Marks a task as undone based on the task number.
     *
     * @param taskNumber The index of the task to be marked as undone (0-based index).
     * @return A message indicating the task that was marked as not done.
     */
    protected String markAsUndone(int taskNumber) {
        return getTask(taskNumber).markAsUndone();
    }

    /**
     * Returns a string representation of all tasks in the list.
     *
     * @return A string listing all tasks with their indices and details.
     */
    protected String getListAsString() {
        StringBuilder s = new StringBuilder("\t" + "Here are the tasks in your list:");

        for (int i = 0; i < this.getNumberOfTasks(); i++) {
            Task current = this.getTask(i);
            String formattedEntry = String.format("\n\t" + "%d. %s", i + 1, current);
            s.append(formattedEntry);
        }

        return s.toString();
    }

    /**
     * Searches for tasks that contain a specific keyword and returns the matching tasks as a formatted string.
     * <p>
     * This method iterates through the list of tasks, checks if each task's description contains the specified
     * keyword (case-insensitive), and collects the matching tasks. The tasks are then formatted and returned as
     * a string with each task on a new line, prefixed by its position in the list.
     *
     * @param query The keyword to search for within the task descriptions.
     * @return A formatted string of matching tasks, each task on a new line prefixed by its position in the list.
     */
    protected String search(String query) {
        ArrayList<String> matchingTasks = new ArrayList<>();

        for (int i = 0; i < getNumberOfTasks(); i++) {
            Task task = getTask(i);
            if (task.getDescription().toLowerCase().contains(query)) {
                matchingTasks.add((i + 1) + ". " + task);
            }
        }

        return String.join("\n\t", matchingTasks);
    }
}
