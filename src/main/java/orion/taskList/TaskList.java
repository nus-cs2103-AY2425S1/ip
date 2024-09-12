package orion.tasklist;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import orion.orionExceptions.FileInitializationException;
import orion.storage.Storage;
import orion.task.Deadline;
import orion.task.DeadlineDetails;
import orion.task.Event;
import orion.task.EventDetails;
import orion.task.Task;
import orion.task.Todo;

/**
 * Manages a list of tasks, including loading, saving, adding, and modifying
 * tasks.
 *
 * <p>
 * This class interacts with the {@link Storage} class to persist tasks in a
 * file.
 * It provides methods to manage tasks such as adding, deleting, and marking
 * tasks
 * as done or undone.
 * </p>
 */
public class TaskList {
    private static final String DATA_FILE_PATH = "./data/tasks.csv";
    private Storage storage;

    /**
     * Constructs a TaskList instance with the specified storage.
     *
     * @param storage the storage used to read and write tasks.
     * @throws FileInitializationException if there is an issue with file
     *                                     initialization.
     */
    public TaskList(Storage storage) throws FileInitializationException {
        this.storage = storage;
        loadTasksFromFile();
    }

    /**
     * Retrieves the next task ID by finding the maximum existing ID and adding one.
     *
     * @param tasks the list of existing tasks.
     * @return the next available task ID.
     */
    private int getNextTaskId(List<Task> tasks) {
        return tasks.stream()
                .mapToInt(Task::getTaskID)
                .max()
                .orElse(0) + 1;
    }

    /**
     * Checks if the given list position is valid.
     *
     * @param listPosition the position to check.
     * @return true if the position is valid, false otherwise.
     * @throws FileInitializationException if there is an issue with file reading.
     */
    public boolean isValidIndex(int listPosition) throws FileInitializationException {
        List<Task> tasks = loadTasksFromFile();
        if (tasks.isEmpty()) {
            return false;
        }
        return listPosition > 0 && listPosition <= tasks.size();
    }

    /**
     * Loads tasks from the storage file into a list.
     *
     * @return a list of tasks read from the file.
     * @throws FileInitializationException if there is an issue reading from the
     *                                     file.
     */
    public List<Task> loadTasksFromFile() throws FileInitializationException {
        return storage.read();
    }

    /**
     * Prints all tasks to the console.
     *
     * <p>
     * If there are no tasks, a message indicating this is printed. Otherwise, each
     * task is printed with its position in the list.
     * </p>
     *
     * @throws FileInitializationException if there is an issue reading the tasks
     *                                     from the file.
     */
    public void printTasks() throws FileInitializationException {
        List<Task> tasks = loadTasksFromFile();
        if (tasks.isEmpty()) {
            System.out.println("There are no tasks.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i).toString());
            }
        }
    }

    /**
     * Saves a list of tasks to the storage file.
     *
     * @param tasks the list of tasks to be saved.
     */
    public void saveTasksToFile(List<Task> tasks) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DATA_FILE_PATH))) {
            for (Task task : tasks) {
                StringBuilder taskLine = new StringBuilder();
                taskLine.append(task.getTaskID()).append(",");
                taskLine.append(getTaskType(task)).append(",");
                taskLine.append(task.getDescription()).append(",");
                taskLine.append(task.isCompleted());

                if (task instanceof Deadline) {
                    taskLine.append(",").append(((Deadline) task).getBy()); // ISO-8601 format
                } else if (task instanceof Event) {
                    taskLine.append(",").append(((Event) task).getFrom())
                            .append("|").append(((Event) task).getTo()); // ISO-8601 format
                }

                bw.write(taskLine.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Gets the type of a task as a string.
     *
     * @param task the task for which the type is to be determined.
     * @return a string representing the type of the task (e.g., "TODO", "DEADLINE",
     *         "EVENT").
     * @throws IllegalArgumentException if the task type is unknown.
     */
    private String getTaskType(Task task) {
        if (task instanceof Todo) {
            return "TODO";
        } else if (task instanceof Deadline) {
            return "DEADLINE";
        } else if (task instanceof Event) {
            return "EVENT";
        } else {
            throw new IllegalArgumentException("Unknown task type: " + task.getClass().getName());
        }
    }

    /**
     * Adds a TODO task to the task list.
     *
     * @param description the description of the new TODO task.
     * @return the newly created TODO task.
     * @throws FileInitializationException if there is an issue with file reading or
     *                                     writing.
     */
    public Task addTodo(String description) throws FileInitializationException {
        List<Task> tasks = loadTasksFromFile();
        int newTaskId = getNextTaskId(tasks);
        Task task = new Todo(newTaskId, description);
        tasks.add(task);
        saveTasksToFile(tasks);
        return task;
    }

    /**
     * Adds a DEADLINE task to the task list.
     *
     * @param temp an object containing details of the new DEADLINE task.
     * @return the newly created DEADLINE task.
     * @throws FileInitializationException if there is an issue with file reading or
     *                                     writing.
     */
    public Task addDeadline(DeadlineDetails temp) throws FileInitializationException {
        List<Task> tasks = loadTasksFromFile();
        int newTaskId = getNextTaskId(tasks);
        Task task = new Deadline(newTaskId, temp.getDescription(), temp.getBy());
        tasks.add(task);
        saveTasksToFile(tasks);
        return task;
    }

    /**
     * Adds an EVENT task to the task list.
     *
     * @param temp an object containing details of the new EVENT task.
     * @return the newly created EVENT task.
     * @throws FileInitializationException if there is an issue with file reading or
     *                                     writing.
     */
    public Task addEvent(EventDetails temp) throws FileInitializationException {
        List<Task> tasks = loadTasksFromFile();
        int newTaskId = getNextTaskId(tasks);
        Task task = new Event(newTaskId, temp.getDescription(), temp.getFrom(), temp.getTo());
        tasks.add(task);
        saveTasksToFile(tasks);
        return task;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the number of tasks.
     * @throws FileInitializationException if there is an issue with file reading.
     */
    public int getSize() throws FileInitializationException {
        List<Task> tasks = loadTasksFromFile();
        return tasks.size();
    }

    /**
     * Marks a task as done based on its list position.
     *
     * @param listPosition the position of the task to mark as done.
     * @return the task that was marked as done, or null if the position is invalid.
     * @throws FileInitializationException if there is an issue with file reading or
     *                                     writing.
     */
    public Task markAsDone(int listPosition) throws FileInitializationException {
        List<Task> tasks = loadTasksFromFile();
        int index = listPosition - 1;
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            task.setCompleted(true);
            saveTasksToFile(tasks);
            return task;
        }
        return null;
    }

    /**
     * Unmarks a task as done based on its list position.
     *
     * @param listPosition the position of the task to unmark as done.
     * @return the task that was unmarked as done, or null if the position is
     *         invalid.
     * @throws FileInitializationException if there is an issue with file reading or
     *                                     writing.
     */
    public Task unmarkAsDone(int listPosition) throws FileInitializationException {
        List<Task> tasks = loadTasksFromFile();
        int index = listPosition - 1;
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            task.setCompleted(false);
            saveTasksToFile(tasks);
            return task;
        }
        return null;
    }

    /**
     * Deletes a task based on its list position.
     *
     * @param listPosition the position of the task to delete.
     * @return the task that was deleted, or null if the position is invalid.
     * @throws FileInitializationException if there is an issue with file reading or
     *                                     writing.
     */
    public Task deleteTask(int listPosition) throws FileInitializationException {
        List<Task> tasks = loadTasksFromFile();
        int index = listPosition - 1;
        if (index >= 0 && index < tasks.size()) {
            Task removedTask = tasks.remove(index);
            saveTasksToFile(tasks);
            return removedTask;
        }
        return null;
    }

    public List<Task> findTasks(String keyword) throws FileInitializationException {
        List<Task> tasks = loadTasksFromFile();

        // Use stream to filter tasks
        List<Task> matchingTasks = tasks.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .toList();

        return matchingTasks;
    }

}
