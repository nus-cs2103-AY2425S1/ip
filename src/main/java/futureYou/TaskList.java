package futureyou;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import futureyou.task.Deadline;
import futureyou.task.Events;
import futureyou.task.Task;

/**
 * The TaskList manages a list of tasks.
 */
public class TaskList {

    private static final ArrayList<Task> taskList = new ArrayList<>();
    private static Storage storage;

    /**
     * Constructs a new TaskList instance using data from file.
     *
     * @throws IOException when the file does not exist
     */
    public TaskList(String filePath) throws IOException {
        storage = new Storage(filePath);
        storage.loadTasks();
    }

    /**
     * Returns the task list.
     *
     * @return The task list.
     */
    public static ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Returns all the tasks in the user's tasklist.
     *
     * @return message All tasks in tasklist neatly formatted
     */
    public static String listTasks() {
        StringBuilder message = new StringBuilder();
        int count = 1;
        System.out.println("Items in List:");
        for (Task item : taskList) {
            message.append(count++).append(". ").append(item.print()).append(System.lineSeparator());
        }
        return message.toString();
    }

    /**
     * Adds a new task to the list.
     *
     * @param newTask The new task to add to the list.
     */
    public static void add(Task newTask) {
        taskList.add(newTask);
    }

    /**
     * Adds a new task to the list and returns the formatted output to print.
     *
     * @param taskName The name of the new task to add.
     * @return The message to be printed
     */
    public static String addTask(String taskName) {
        Task newTask = new Task(taskName);
        taskList.add(newTask);
        storage.saveTasks();
        return "Added this task: \n" + newTask.print() + System.lineSeparator()
                + taskList.size() + " tasks in the list";
    }

    /**
     * Adds a new Deadline to the list and returns the formatted output to print.
     *
     * @param taskName The name of the new deadline task to add.
     * @param deadline The deadline of the new deadline task to add.
     * @return The message to be printed
     */
    public static String addTask(String taskName, LocalDateTime deadline) {
        Deadline newDeadline = new Deadline(taskName, deadline);
        taskList.add(newDeadline);
        storage.saveTasks();
        return "Added this task: \n" + newDeadline.print() + System.lineSeparator()
                + taskList.size() + " tasks in the list";
    }

    /**
     * Adds a new Event to the list and returns the formatted output to print.
     *
     * @param taskName      The name of the new Event task to add.
     * @param startDateTime The start date time of the new Event task to add.
     * @param endDateTime   The start date time of the new Event task to add.
     * @return The message to be printed
     */
    public static String addTask(String taskName, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        Events newEvent = new Events(taskName, startDateTime, endDateTime);
        taskList.add(newEvent);
        storage.saveTasks();
        return "Added this task: \n" + newEvent.print() + System.lineSeparator() + taskList.size()
                + " tasks in the list";
    }

    /**
     * Marks a specified task as done based on the task number input by the user.
     *
     * @param taskNumber The task number to mark as done.
     * @return message to be printed after task is marked
     */
    public static String markTask(int taskNumber) {
        try {
            taskList.get(taskNumber).markTask();
            storage.saveTasks();
            return "Marked as Done: \n" + taskList.get(taskNumber).print();
        } catch (Exception e) {
            return "Please enter a valid Task number";
        }
    }

    /**
     * Deletes a specific task based on the given task number.
     *
     * @param taskNumber The task number to delete.
     * @return message to be printed after task is deleted
     */
    public static String deleteTask(int taskNumber) {
        try {
            Task removedTask = taskList.remove(taskNumber);
            storage.saveTasks();
            return "Task Deleted: \n" + removedTask.print() + System.lineSeparator() + taskList.size()
                    + " tasks left in the list";

        } catch (Exception e) {
            return "Please enter a valid Task number";
        }
    }

    /**
     * Finds specified tasks using text input by the user.
     *
     * @param text The text to search.
     * @throws Exception If the task number is invalid.
     */
    public static String findTask(String text) throws Exception {
        try {

            StringBuilder message = new StringBuilder(" Here are the matching tasks in your list: \n");
            int count = 1;
            for (Task task : TaskList.getTaskList()) {
                if (task.getTaskName().contains(text)) {
                    message.append("[").append(count++).append("]").append(task.print()).append(System.lineSeparator());
                }
            }
            if (count == 1) {
                return "No task mataching that text was found";
            }
            return message.toString();
        } catch (Exception e) {
            return "Please enter a valid Task number";
        }
    }
}
