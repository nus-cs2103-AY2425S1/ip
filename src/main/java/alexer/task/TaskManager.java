package alexer.task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class represents the task manager which handles
 * all actions and operations related to tasks.
 *
 * @author sayomaki
 */
public class TaskManager {
    private final List<Task> taskList;

    /** The save file location for tasks **/
    public static final String SAVE_FILE = "./data/tasks.txt";

    /**
     * Creates a new task manager
     */
    public TaskManager() {
        taskList = new ArrayList<>();
    }

    /**
     * Returns the number of tasks in the task list
     *
     * @return the count of number of tasks
     */
    public int getTaskCount() {
        return taskList.size();
    }

    /**
     * Obtains a specific task by the index of the task in the list.
     * Does not handle invalid indexes (yet)
     *
     * @param index the index of the task in the list, starting from 0
     * @return the task if found
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Removes a task by its index from the task list.
     * Does not handle invalid index yet.
     *
     * @param index The index of the task to be removed
     * @return the task if removed
     */
    public Task removeTask(int index) {
        return taskList.remove(index);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task the task to be added
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    public Task createTodo(String description) {
        Task todo = new Todo(description);
        taskList.add(todo);
        saveTasks();
        return todo;
    }

    public Task createDeadline(String description, LocalDateTime by) {
        Task deadline = new Deadline(description, by);
        taskList.add(deadline);
        saveTasks();
        return deadline;
    }

    public Task createEvent(String description, String from, String to) {
        Task event = new Event(description, from, to);
        taskList.add(event);
        saveTasks();
        return event;
    }

    public List<Task> findTask(String description) {
        List<Task> tasks = new ArrayList<>();

        for (Task task : taskList) {
            if (task.getDescription().contains(description)) {
                tasks.add(task);
            }
        }

        return tasks;
    }

    /**
     * Loads tasks from the task save file.
     *
     * @return boolean whether tasks loaded successfully
     */
    public boolean loadTasks() {
        File file = new File(SAVE_FILE);
        if (!file.isFile()) {
            System.out.println("Failed to find task save file, not loading any tasks.");
            return false;
        }

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                String taskString = scanner.nextLine();
                Task task;
                if (taskString.startsWith("T")) {
                    task = Todo.fromTaskString(taskString);
                } else if (taskString.startsWith("D")) {
                    task = Deadline.fromTaskString(taskString);
                } else if (taskString.startsWith("E")) {
                    task = Event.fromTaskString(taskString);
                } else {
                    System.out.println("Invalid task type found, skipping: " + taskString);
                    continue;
                }

                if (task != null) {
                    taskList.add(task);
                } else {
                    System.out.println("Failed to load task: " + taskString);
                }
            }

            System.out.println("Loaded saved tasks from disk!");

            return true;
        } catch (IOException e) {
            System.out.println("Error! Failed to load tasks!");
            return false;
        }
    }

    /**
     * Saves all the tasks to the hard disk in a file.
     *
     * @return Whether the save is successful
     */
    public boolean saveTasks() {
        File file = new File(SAVE_FILE);
        // ensure our data directory exists first
        file.getParentFile().mkdir();

        try {
            FileWriter fileWriter = new FileWriter(SAVE_FILE);

            StringBuilder output = new StringBuilder();
            for (Task task : taskList) {
                output.append(task.toTaskString()).append("\n");
            }

            fileWriter.write(output.toString());
            fileWriter.close();
            return true;
        } catch (IOException e) {
            System.out.println("Error! Failed to save tasks!");
            return false;
        }
    }
}
