package puke;

import java.util.ArrayList;
import java.util.stream.Collectors;

import puke.exceptions.TaskNumberOutOfBoundsException;
import puke.task.Deadline;
import puke.task.Event;
import puke.task.Task;
import puke.task.Todo;

/**
 * Manages a list of tasks, providing methods to add, delete, and manipulate tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList with tasks loaded from storage.
     *
     * @param loadedFromStorage the tasks loaded from storage
     */
    public TaskList(ArrayList<Task> loadedFromStorage) {
        assert loadedFromStorage != null : "Task list loaded from storage should not be null";
        this.tasks = loadedFromStorage;
    }

    /**
     * Adds a new task to the task list.
     *
     * @param type the type of the task (todo, deadline, event)
     * @param description the description of the task
     * @param timeInfo optional time information for deadline and event tasks
     * @return a message indicating the task has been added
     */
    public String addTask(String type, String description, String... timeInfo) {
        assert description != null && !description.isEmpty() : "Task description should not be null or empty";

        Task task = null;
        switch (type) {
        case "todo":
            task = new Todo(description, false);
            break;
        case "deadline":
            task = new Deadline(description, false, timeInfo[0]);
            break;
        case "event":
            task = new Event(description, false, timeInfo[0], timeInfo[1]);
            break;
        default:
            return "Invalid task type!";
        }
        tasks.add(task);
        Storage.saveTasks(tasks);

        return "Roger that! I've added in this task:\n  " + task.toString()
                + "\nNow you have " + tasks.size() + " tasks in the list ~ !";
    }

    /**
     * Deletes a task from the task list.
     *
     * @param taskNumber the index of the task to delete
     * @return a message indicating the task has been removed
     */
    public String deleteTask(int taskNumber) {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            return "OOPS!!! The task number " + taskNumber + " is out of bounds.";
        }
        Task removedTask = tasks.remove(taskNumber - 1);
        Storage.saveTasks(tasks);

        return "Noted. I've removed this task:\n  " + removedTask.toString()
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Lists all tasks currently in the task list.
     *
     * @return a string representation of all tasks
     */
    public String listTasks() {
        if (tasks.isEmpty()) {
            return "You haven't added any task yet! :L";
        }
        StringBuilder list = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            list.append(i + 1).append(". ").append(tasks.get(i).toString()).append("\n");
        }
        return list.toString();
    }

    /**
     * Marks a task as done or not done.
     *
     * @param taskNumber the index of the task to mark
     * @param isDone true to mark as done, false to unmark
     * @return a message indicating the task's new status
     */
    public String markTask(int taskNumber, boolean isDone) {
        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            Task task = tasks.get(taskNumber - 1);

            if (isDone) {
                task.markAsDone();
                Storage.saveTasks(tasks);
                return "Yippee~ *uweah* I've marked this task as done:\n  " + task;
            } else {
                task.unmarkAsDone();
                Storage.saveTasks(tasks);
                return "LOL I've marked this task as not done yet:\n  " + task;
            }
        }
        return "Invalid task number!!!!";
    }

    /**
     * Returns the total number of tasks in the list.
     *
     * @return the task count
     */
    public int getTaskCount() {
        return tasks.size();
    }

    /**
     * Retrieves a task by its index.
     *
     * @param taskNum the index of the task to retrieve
     * @return the task at the specified index
     * @throws TaskNumberOutOfBoundsException if the task index is invalid
     */
    public Task getTask(int taskNum) throws TaskNumberOutOfBoundsException {
        if (isInvalidTaskNum(taskNum)) {
            throw new TaskNumberOutOfBoundsException(taskNum);
        } else {
            return tasks.get(taskNum);
        }
    }

    /**
     * Returns the list of tasks.
     *
     * @return an ArrayList of Task objects.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Finds and returns a list of all tasks containing the specified keyword in their description.
     *
     * @param keyword the keyword to search for in the task descriptions
     * @return an ArrayList of tasks that contain the keyword
     */
    public ArrayList<Task> findTasks(String keyword) {
        return tasks.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Checks if a task number is invalid.
     *
     * @param taskNum the task number to check
     * @return true if the task number is invalid, false otherwise
     */
    private boolean isInvalidTaskNum(int taskNum) {
        return taskNum < 0 || taskNum >= tasks.size();
    }
}
