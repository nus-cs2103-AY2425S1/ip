package Gary;

import java.util.ArrayList;
import java.util.Scanner;
import Gary.task.Task;
import Gary.task.ToDo;
import Gary.task.Deadline;
import Gary.task.Event;

/**
 * The {@code TaskList} class manages a list of tasks, providing functionalities
 * to add, remove, and retrieve tasks. It also supports initializing tasks from a file.
 */
public class TaskList {
    private ArrayList<Task> taskLists;  // List to store tasks

    /**
     * Constructs an empty {@code TaskList}.
     */
    public TaskList() {
        this.taskLists = new ArrayList<>();
    }

    /**
     * Constructs a {@code TaskList} by loading tasks from a {@code Scanner} object.
     * This constructor reads each line from the scanner to initialize tasks.
     *
     * @param sc The {@code Scanner} object containing task data.
     */
    public TaskList(Scanner sc) {
        this.taskLists = new ArrayList<>();
        while (sc.hasNextLine()) {
            String nextLine = sc.nextLine();
            String[] split = nextLine.split(" \\| ");
            String taskType = split[0].trim();
            boolean isDone = split[1].equals("1");

            Task task;
            if (taskType.equals("T")) {
                task = new ToDo(split[2]);
            } else if (taskType.equals("D")) {
                task = new Deadline(split[2], split[3]);
            } else if (taskType.equals("E")) {
                task = new Event(split[2], split[3], split[4]);
            } else {
                continue; // Skip if the task type is unrecognized
            }

            taskLists.add(task);
            if (isDone) {
                task.editStatus();
            }
        }
        sc.close();
    }

    /**
     * Adds a task to the {@code TaskList}.
     *
     * @param task The {@code Task} object to add.
     */
    public void addTask(Task task) {
        this.taskLists.add(task);
    }

    /**
     * Removes a task from the {@code TaskList} at the specified index.
     *
     * @param index The index of the task to remove.
     * @return The removed {@code Task} object.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public Task removeTask(int index) {
        return this.taskLists.remove(index);
    }

    /**
     * Retrieves the task at the specified index from the {@code TaskList}.
     *
     * @param index The index of the task to retrieve.
     * @return The {@code Task} object at the specified index.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public Task getTask(int index) {
        return this.taskLists.get(index);
    }

    /**
     * Returns the number of tasks in the {@code TaskList}.
     *
     * @return The size of the {@code TaskList}.
     */
    public int size() {
        return this.taskLists.size();
    }

    /**
     * Returns a string representation of the {@code TaskList}.
     *
     * @return A string representation of the {@code TaskList}.
     */
    @Override
    public String toString() {
        return this.taskLists.toString();
    }
}


