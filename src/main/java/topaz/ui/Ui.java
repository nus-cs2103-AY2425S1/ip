package topaz.ui;

import java.io.IOException;
import java.util.Scanner;

import topaz.main.TaskList;
import topaz.task.Task;

/**
 * Handles user interactions and displays information to the user.
 * Provides methods to read user input, display messages, and show errors.
 */
public class Ui {
    /**
     * Initializes the Ui with a new {@link Scanner} for reading user input.
     * This constructor initializes the user interface without any parameters.
     */
    public Ui() {
    }

    /**
     * Displays the welcome message for the user when the program starts.
     *
     * @return A string containing a welcome message.
     */
    public String welcome() {
        return "Hello! I'm Topaz - I work in the IPC's Strategic Investment Department. "
                + "Long time no see! How have you been? Clocking in a lot of overtime?"
                + " What can I do for you?";
    }

    /**
     * Displays an error message when an invalid index is provided for a task.
     *
     * @param index The index number that was invalid.
     * @return A string containing an error message referring to the invalid index.
     */
    public String showMarkIobError(int index) {
        return "Invalid index number: " + index + " Please refer to your list.";
    }

    /**
     * Displays an error message when an exception is thrown.
     *
     * @param e The exception that occurred.
     * @return A string displaying the exception message and a prompt to try again.
     */
    public String showException(Exception e) {
        return e + "\nTry again!";
    }
    public String showIndexOutRangeException(int i, int size) {
        return String.format("Task list has %d tasks, %d is out of its range.", size, i);
    }

    public String showIntegerParseException(Exception e, String name) {
        return "Please input \"help\" and refer to the correct format of " + name + " command!";
    }
    /**
     * Displays a goodbye message when the user exits the program.
     *
     * @return A string containing a goodbye message.
     */
    public String goodbye() {
        return "Ugh, why do they have to call me for such a trivial matter... "
                + "Sorry, something just came up at work, I've got to get going!\n"
                + "Bye. Hope to see you again soon!";
    }

    /**
     * Provides a help guide explaining how to use different commands in the task management system.
     *
     * @return A string that details various commands and their usage in the system.
     */
    public String showHelp() {
        String guide;
        guide = """
                1. Adding Tasks
                1) ToDos: Tasks without specific dates/times.
                Command: todo <description>
                Example: todo borrow book
                Response: Confirms the addition and displays the task.
                                
                2) Deadlines: Tasks that must be completed by a specific date/time.         
                Command: deadline <description> /by <date/time>, time should be in YYYY-MM-DD HH:mm format
                Example: deadline return book /by 2024-01-01 12:00
                Response: Confirms the addition and shows the deadline.
                                
                3) Events: Tasks that start and end at specific date/times.         
                Command: event <description> /from <start date/time> /to <end date/time>, time should be in YYYY-MM-DD HH:mm format
                Example: event project meeting /from 2024-11-11 11:00 /to 2024-11-11 11:11
                Response: Confirms the addition and shows the event details.
                                
                2. Viewing All Tasks
                Command: list
                Response: Displays all tasks in your list with their types and statuses.
                                
                3. Mark task status
                1) Mark task as done: mark <index of task in list>
                2) Mark task as undo: unmark <index of task in list>
                
                4. Show weekly statistics
                Command: statistic
                Response: Show number of tasks finished this week.
                
                5. Delete tasks
                Command: delete <index>
                Response: delete the index-th task in the task list
                """;
        return guide;
    }

    /**
     * Displays an error message when an IOException occurs during initialization of task lists.
     *
     * @param e The IOException that occurred.
     * @return A string showing the error message and a prompt to try again.
     */
    public String showInitializeIoeException(IOException e) {
        return "Error in initializing lists: " + e + " Try again!";
    }

    /**
     * Displays an error message when an IOException occurs while saving the task list.
     *
     * @param e The IOException that occurred.
     * @return A string showing the error message about the file creation issue.
     */
    public String showSaveIoeException(IOException e) {
        return "Error in creating file for current list: " + e;
    }

    /**
     * Displays a message when listing the current tasks.
     *
     * @return A string with a message about remembering work and tasks.
     */
    public String showTaskList() {
        return "Who works for money these days? Money is a means, "
                + "not an end. Work should make you happy... That's the most fundamental principle.\n"
                + "Don't forget your work~\n";
    }

    /**
     * Displays a message when a task is marked as done.
     *
     * @param task The task that has been marked as done.
     * @return A string confirming the task completion and its status.
     */
    public String showDoneTaskStatus(Task task) {
        return "Another project over the finish line! I've marked this task as done:\n"
                + "    " + task.getStatus();
    }

    /**
     * Displays a message when a task is marked as undone.
     *
     * @param task The task that has been marked as undone.
     * @param size The current size of the task list.
     * @return A string confirming the task's status as undone and the updated list size.
     */
    public String showUndoneTaskStatus(Task task, int size) {
        return "Noted. Numby has marked this task as undone:\n"
                + "    " + task.getStatus()
                + "Now you have " + size + " tasks in the list.";
    }

    /**
     * Displays a message when a new task is added.
     *
     * @param task The task that has been added.
     * @param size The current size of the task list after adding the task.
     * @return A string confirming the task addition and the updated list size.
     */
    public String showAddTask(Task task, int size) {
        return "Got it. I've added this task:\n"
                + "    " + task.getStatus() + "\n"
                + "Hard work pays off~\n"
                + "Now you have " + size + " tasks in the list.";
    }

    /**
     * Displays a message when a task is deleted.
     *
     * @param task The task that has been removed.
     * @param size The current size of the task list after deleting the task.
     * @return A string confirming the task deletion and the updated list size.
     */
    public String showDeleteTask(Task task, int size) {
        return "Noted. I've removed this task:\n"
                + "    " + task.getStatus()
                + "Now you have " + size + " tasks in the list.";
    }

    /**
     * Displays the tasks that match a search criteria.
     *
     * @param taskList The task list that contains matching tasks.
     * @return A string showing the matching tasks or a message if no matches are found.
     */
    public String showTargetTask(TaskList taskList) {
        if (taskList.getSize() == 0) {
            return "Numby told me there's no matching task found. Enter \"list\" to see current tasks.";
        } else {
            return "Here are matching tasks in your list:\n"
                    + taskList;
        }
    }

    /**
     * Displays a feedback message when the user has completed tasks.
     *
     * @param count The number of tasks completed.
     * @return A string congratulating the user for completing tasks.
     */
    public String showGoodFeedback(int count) {
        return String.format("Good job! You have finished "
                + "%d task(s) this week, keep on working!", count);
    }

    /**
     * Displays a feedback message when the user has not completed any tasks.
     *
     * @return A string encouraging the user to complete tasks.
     */
    public String showBadFeedback() {
        return "Looks like you haven't done anything this week. Type \"list\" to see your tasks.";
    }

    /**
     * Prompts the user to add a task if no task is currently added.
     *
     * @return A string message asking the user to add tasks.
     */
    public String callAddTask() {
        return "You haven't added any task now, type \"help\" to see how to add one.";
    }
}
