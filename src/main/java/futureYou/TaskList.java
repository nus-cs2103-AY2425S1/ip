package futureYou;

import java.util.ArrayList;

import futureYou.task.Deadline;
import futureYou.task.Events;
import futureYou.task.Task;

/**
 * The TaskList manages a list of tasks.
 */
public class TaskList {

    static ArrayList<Task> taskList;

    /**
     * Constructs a new TaskList instance.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Returns the task list.
     *
     * @return The task list.
     */
    public ArrayList<Task> getTaskList() {
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
            message += (count++ + ". " + item.print() + "\n");
        }
        return message;
    }

    /**
     * Adds a new task to the list.
     *
     * @param task The new task to add to the list.
     */
    public void add(Task newTask) {
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
        String message = "Added this task: \n" + newTask.print() +
                "\n" + taskList.size() + " tasks in the list";
        return message;
    }

    /**
     * Adds a new Deadline to the list and returns the formatted output to print.
     *
     * @param taskName The name of the new deadline task to add.
     * @param deadline The deadline of the new deadline task to add.
     * @return message The message to be printed
     */
    public static String addTask(String taskName, String deadline) {
        Deadline newDeadline = new Deadline(taskName, deadline);
        taskList.add(newDeadline);
        String message = "Added this task: \n" + newDeadline.print() +
                "\n" + taskList.size() + " tasks in the list";
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
        String message = "Added this task: \n" + newEvent.print() +
                "\n" + taskList.size() + " tasks in the list";
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
            String message = "Marked as Done: + \n" + taskList.get(taskNumber).print();
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
            String message = "Task Deleted: \n" + removedTask.print() + "\n" + taskList.size()
                    + " tasks left in the list";
            return message;

        } catch (Exception e) {
            return "Please enter a valid Task number";
        }
    }

}
