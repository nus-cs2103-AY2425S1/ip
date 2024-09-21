package alexer.task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the task manager which handles
 * all actions and operations related to tasks.
 *
 * @author sayomaki
 */
public class TaskManager {
    private final boolean storageEnabled = true; // tasks are stored/loaded automatically
    private final List<Task> taskList;
    private final TaskStorage storage;

    /** The save file location for tasks **/
    public static final String SAVE_FILE = "./data/tasks.txt";

    /**
     * Creates a new task manager, and loads tasks if required
     */
    public TaskManager() {
        if (storageEnabled) {
            storage = new TaskStorage(SAVE_FILE);
            taskList = storage.loadTasks();
        } else {
            taskList = new ArrayList<>();
        }
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

    /**
     * Creates a new To-do task with the given description
     *
     * @param description the description of the task
     * @return a new to-do task as a task object
     */
    public Task createTodo(String description) {
        Task todo = new Todo(description);
        taskList.add(todo);

        if (storageEnabled) {
            storage.saveTasks(taskList);
        }

        return todo;
    }

    /**
     * Creates a new Deadline task with the given description and due datetime
     *
     * @param description the description of the deadline
     * @param by the date time of the deadline
     * @return a new deadline task as a task object
     */
    public Task createDeadline(String description, LocalDateTime by) {
        Task deadline = new Deadline(description, by);
        taskList.add(deadline);

        if (storageEnabled) {
            storage.saveTasks(taskList);
        }

        return deadline;
    }

    /**
     * Creates a new Event task with the given description and starting/ending times
     *
     * @param description the description of the event
     * @param from the starting date/time string of the event
     * @param to the ending date/time string of the event
     * @return a new event task as a task object
     */
    public Task createEvent(String description, String from, String to) {
        Task event = new Event(description, from, to);
        taskList.add(event);

        if (storageEnabled) {
            storage.saveTasks(taskList);
        }

        return event;
    }

    /**
     * Finds and returns a list of tasks with the description
     * containing the keyword given
     *
     * @param keyword the keyword string to search for
     * @return a list of tasks with the keyword string in description
     */
    public List<Task> findTask(String keyword) {
        List<Task> tasks = new ArrayList<>();

        for (Task task : taskList) {
            if (task.getDescription().contains(keyword)) {
                tasks.add(task);
            }
        }

        return tasks;
    }

    /**
     * Saves the tasks using task storage,
     * does nothing if task persistence is disabled
     */
    public void saveTasks() {
        if (storageEnabled) {
            storage.saveTasks(taskList);
        }
    }
}
