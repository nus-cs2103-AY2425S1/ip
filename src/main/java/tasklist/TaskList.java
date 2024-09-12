package tasklist;

import parser.Parser;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import task.Task;
import todo.ToDo;
import deadline.Deadline;
import event.Event;

/**
 * Represents the lists of actions that a user can take.
 * <p>
 * Provides methods to interact with the user through text-based output.
 * </p>
 */
public class TaskList {
    /**
     * Creates a new ToDo Task in the specified task list.
     *
     * @param name The name of the ToDo task.
     * @param taskList The task list to add the new ToDo task to.
     */
    public static void createToDo(String name, ArrayList<Task> taskList) {
        ToDo newToDo = new ToDo(name);
        taskList.add(newToDo);
        speakTaskAdded(newToDo, taskList.size());
    }

    /**
     * Creates a new Deadline task in the specified task list.
     *
     * @param name The name of the Deadline task.
     * @param date The date to which the task is due by.
     * @param taskList The task list to add the new Deadline task to.
     */
    public static void createDeadline(String name, String date, ArrayList<Task> taskList) {
        Deadline newDeadline = new Deadline(name, date);
        taskList.add(newDeadline);
        speakTaskAdded(newDeadline, taskList.size());
    }

    /**
     * Creates a new Event task in the specified task list.
     *
     * @param eventName The name of the Event task.
     * @param startTime The start date and time of the Event task.
     * @param endTime The end date and time of the Event task.
     * @param taskList The task list to add the new Event task to.
     */
    public static void createEvent(String eventName, String startTime, String endTime, ArrayList<Task> taskList) {
        Event newEvent = new Event(eventName, startTime, endTime);
        taskList.add(newEvent);
        speakTaskAdded(newEvent, taskList.size());
    }

    /**
     * Deletes a specified task in the task list.
     *
     * @param deletionNumber The index of the task to be deleted.
     * @param taskList The task list which the task will be deleted from.
     */
    public static void deleteEvent(int deletionNumber, ArrayList<Task> taskList) {
        String deletedDescription = taskList.get(deletionNumber).toString();
        taskList.remove(deletionNumber);
        speakTaskRemoved(deletedDescription, taskList.size());
    }

    /**
     * Updates a specified task in the task list.
     *
     * @param updates The input string containing the task number and updates.
     * @param taskList The task list which the task will be updated.
     * @return A message indicating the result of the update operation.
     */
    public static String updateTask(int taskNum, String updates, ArrayList<Task> taskList) {
            if (taskNum >= 0 && taskNum < taskList.size()) {
                Task task = taskList.get(taskNum);
                task.setName(updates);
                speakTaskUpdated(task, taskList.size());
                return "Task updated successfully:\n" + task;
            } else {
                return "Task number is out of range.";
            }
    }

    /**
     * Prints a message to the console to notify a user when a task has been updated successfully.
     *
     * @param task The task that was updated.
     * @param size The size of the task list.
     */
    public static void speakTaskUpdated(Task task, int size) {
        String msg = "Got it. I've updated this task:\n" + task.toString();
        msg += size == 1 ? "Now you have 1 task in the list.\n" : "Now you have " + size + " tasks in the list.\n";
        Parser.speak(msg);
    }


    /**
     * Prints a message to the console to notify a user when a task has been added successfully.
     *
     * @param task The task to be added.
     * @param size the size of the task list.
     */
    public static void speakTaskAdded(Task task, int size) {
        String msg = "Got it. I've added this task:\n" + task.toString();
        msg += size == 1 ? "Now you have 1 task in the list.\n" : "Now you have " + size + " tasks in the list.\n";
        Parser.speak(msg);
    }

    /**
     * Prints a message to the console to notify a user when a task has been deleted successfully.
     *
     * @param name The name of the task to be deleted.
     * @param size The size of the task list.
     */
    public static void speakTaskRemoved(String name, int size) {
        String msg = "Noted. I've removed this task:\n" + name;
        msg += size == 1 ? "Now you have 1 task in the list.\n" : "Now you have " + size + " tasks in the list.\n";
        Parser.speak(msg);
    }
}