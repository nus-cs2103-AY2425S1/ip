package futureYou;

import java.io.IOException;
import java.util.ArrayList;

import futureYou.task.Deadline;
import futureYou.task.Events;
import futureYou.task.Task;

import futureYou.Storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The TaskList manages a list of tasks.
 */
public class TaskList {

    private static ArrayList<Task> taskList = new ArrayList<>();
    private static Storage storage;

    /**
     * Constructs a new TaskList instance.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Constructs a new TaskList instance using data from file.
     * 
     * @throws IOException
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
        String message = "";
        int count = 1;
        System.out.println("Items in List:");
        for (Task item : taskList) {
            message += (count++ + ". " + item.print() + System.lineSeparator());
        }
        return message;
    }

    /**
     * Adds a new task to the list.
     *
     * @param task The new task to add to the list.
     */
    public static void add(Task newTask) {
        taskList.add(newTask);
    }

    /**
     * Adds a new task to the list and returns the formatted output to print.
     *
     * @param taskName The name of the new task to add.
     * @return message The message to be printed
     */
    public static String addTask(String taskName) {
        Task newTask = new Task(taskName);
        taskList.add(newTask);
        storage.saveTasks();
        String message = "Added this task: \n" + newTask.print() +
                System.lineSeparator() + taskList.size() + " tasks in the list";
        return message;
    }

    /**
     * Adds a new Deadline to the list and returns the formatted output to print.
     *
     * @param taskName The name of the new deadline task to add.
     * @param deadline The deadline of the new deadline task to add.
     * @return message The message to be printed
     */
    public static String addTask(String taskName, LocalDateTime deadline) {
        Deadline newDeadline = new Deadline(taskName, deadline);
        taskList.add(newDeadline);
        storage.saveTasks();
        String message = "Added this task: \n" + newDeadline.print() +
                System.lineSeparator() + taskList.size() + " tasks in the list";
        return message;
    }

    /**
     * Adds a new Event to the list and returns the formatted output to print.
     *
     * @param taskName      The name of the new Event task to add.
     * @param startDateTime The start date time of the new Event task to add.
     * @param endDateTime   The start date time of the new Event task to add.
     * @return message The message to be printed
     */
    public static String addTask(String taskName, String startDateTime, String endDateTime) {
        Events newEvent = new Events(taskName, startDateTime, endDateTime);
        taskList.add(newEvent);
        storage.saveTasks();
        String message = "Added this task: \n" + newEvent.print() +
                System.lineSeparator() + taskList.size() + " tasks in the list";
        return message;
    }

    /**
     * Marks a specified task as done based on the task number input by the user.
     *
     * @param taskNumber The task number to mark as done.
     * @throws Exception If the task number is invalid.
     */
    public static String markTask(int taskNumber) throws Exception {
        try {
            taskList.get(taskNumber).markTask();
            storage.saveTasks();
            String message = "Marked as Done: \n" + taskList.get(taskNumber).print();
            return message;
        } catch (Exception e) {
            return "Please enter a valid Task number";
        }
    }

    /**
     * Deletes a specific task based on the given task number.
     *
     * @param taskNumber The task number to delete.
     * @throws Exception If the task number is invalid.
     */
    public static String deleteTask(int taskNumber) throws Exception {
        try {
            Task removedTask = taskList.remove(taskNumber);
            storage.saveTasks();
            String message = "Task Deleted: \n" + removedTask.print() + System.lineSeparator() + taskList.size()
                    + " tasks left in the list";
            return message;

        } catch (Exception e) {
            return "Please enter a valid Task number";
        }
    }

}
