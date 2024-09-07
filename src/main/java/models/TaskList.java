package models;

import java.util.ArrayList;
import lib.ActiveRecord;
import lib.DbDriverInterface;

/**
 * Represents a list of tasks in the task management system.
 * The {@code TaskList} class manages tasks and handles interactions
 * with the database, including adding, removing, marking, and unmarking tasks.
 * It also handles serialization and deserialization of tasks for persistent storage.
 *
 * <p>This class extends the {@code ActiveRecord} and provides core functionality for
 * managing tasks in the system. It uses a {@code DbDriverInterface} to interact with
 * the underlying database (e.g., file-based).</p>
 *
 * @see ActiveRecord
 */
public class TaskList extends ActiveRecord {

    private ArrayList<Task> tasks;

    /**
     * Constructs a {@code TaskList} with the specified database driver.
     * Initializes the task list and attempts to load tasks from the database.
     * If loading fails, the database is reset.
     *
     * @param dbDriver The database driver used to interact with the database.
     */
    public TaskList(DbDriverInterface dbDriver) {
        super(dbDriver);
        init();
    }

    /**
     * Initializes the task list by deserializing raw data from the database.
     * If deserialization fails, it resets the database.
     */
    private void init() {
        this.tasks = new ArrayList<>();
        try {
            deserialiseRawData();
        } catch (Exception e) {
            resetDB();
        }
    }

    /**
     * Resets the database by saving an empty string to the database.
     */
    public void resetDB() {
        this.dbDriver.save("");
    }

    /**
     * Adds a task to the task list and saves the updated list to the database.
     *
     * @param task The task to be added to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
        saveToDb();
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The size of the task list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Marks a task as completed at the specified index and saves the updated list to the database.
     *
     * @param index The index of the task to mark as completed.
     */
    public void markTask(int index) {
        tasks.get(index).mark();
        saveToDb();
    }

    /**
     * Unmarks a task as not completed at the specified index and saves the updated list to the database.
     *
     * @param index The index of the task to unmark.
     */
    public void unmarkTask(int index) {
        tasks.get(index).unmark();
        saveToDb();
    }

    /**
     * Returns the list of tasks.
     *
     * @return The {@code ArrayList} of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Retrieves a task from the list at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Removes a task from the list at the specified index and saves the updated list to the database.
     *
     * @param index The index of the task to remove.
     * @return The task that was removed.
     */
    public Task removeTask(int index) {
        Task returnTask = this.tasks.remove(index - 1);
        saveToDb();
        return returnTask;
    }

    /**
     * Saves the task list to the database by serializing it into a string format.
     */
    public void saveToDb() {
        String serializedString = this.serialiseTaskList();
        this.dbDriver.save(serializedString);
    }

    /**
     * Serializes the task list into a string format for storage in the database.
     * Each task is serialized on a new line.
     *
     * @return A string representing the serialized task list.
     */
    public String serialiseTaskList() {
        if (tasks.isEmpty()) {
            return "";
        }
        StringBuilder list = new StringBuilder();
        for (Task task : tasks) {
            list.append(task.serialize()).append("\n");
        }
        return list.toString();
    }

    /**
     * Deserializes the raw data from the database and populates the task list.
     * Supports deserialization of {@code Todo}, {@code Event}, and {@code Deadline} tasks.
     *
     * @throws Exception If deserialization fails.
     */
    public void deserialiseRawData() throws Exception {
        String rawData = this.dbDriver.read();
        String[] tasks = rawData.split("\n");

        for (String taskLine : tasks) {
            String[] sections = taskLine.split("\\|");

            String taskType = sections[0];

            if (taskType.equals("T")) {
                Todo task = new Todo(sections[2], sections[1].equals("1"));
                this.tasks.add(task);
            } else if (taskType.equals("E")) {
                Event event = new Event(sections[2], sections[1].equals("1"), sections[3], sections[4]);
                this.tasks.add(event);
            } else if (taskType.equals("D")) {
                Deadline deadline = new Deadline(sections[2], sections[1].equals("1"), sections[3]);
                this.tasks.add(deadline);
            }
        }
    }

    /**
     * Returns a string representation of the tasks in the task list.
     * The format is a numbered list of tasks, with each task on a new line.
     *
     * @return A string representing the list of tasks.
     */
    public String listTasks() {
        if (tasks.isEmpty()) {
            return "List is empty!";
        }
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            list.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return list.toString();
    }
}
