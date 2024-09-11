package topaz.ui;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import topaz.exception.InvalidStateException;
import topaz.main.TaskList;
import topaz.task.Task;

/**
 * Handles user interactions and displays information to the user.
 * Provides methods to read user input, display messages, and show errors.
 */
public class Ui {
    /**
     * Initializes the Ui with a new {@link Scanner} for reading user input.
     */
    public Ui() {
    }

    public String welcome() {
        return "Hello! I'm Topaz - I work in the IPC's Strategic Investment Department. "
                + "Long time no see! How have you been? Clocking in a lot of overtime?"
                + " What can I do for you?";
    }

    public String showUserIobError() {
        return "Please enter valid information after the keyword!\n"
                + "Try again!";
    }

    public String showMarkIobError(int index) {
        return "Invalid index number: " + index + " Please refer to your list.";
    }
    public String showException(Exception e) {
        return e + "Try agian!";
    }

    public String goodbye() {
        return "Ugh, why do they have to call me for such a trivial matter... "
                + "Sorry, something just came up at work, I've got to get going!\n"
                + "Bye. Hope to see you again soon!";
    }

    public String showHelp() {
        String guide = """
                    1. Adding Tasks
                    1) ToDos: Tasks without specific dates/times.
                    Command: todo <description>
                    Example: todo borrow book
                    Response: Confirms the addition and displays the task.
                    
                    2) Deadlines: Tasks that must be completed by a specific date/time.         
                    Command: deadline <description> /by <date/time>
                    Example: deadline return book /by Sunday
                    Response: Confirms the addition and shows the deadline.
                    
                    3) Events: Tasks that start and end at specific date/times.         
                    Command: event <description> /from <start date/time> /to <end date/time>
                    Example: event project meeting /from Mon 2pm /to 4pm
                    Response: Confirms the addition and shows the event details.
                    
                    2. Viewing All Tasks
                    Command: list
                    Response: Displays all tasks in your list with their types and statuses.
                    
                    3. Mark task status
                    1) Mark task as done: mark <index of task in list>
                    2) Mark task as undo: unmark <index of task in list>""";
        return guide;
    }

    public String showTimeParseException(DateTimeParseException dateTimeParseException) {
        return "Invalid input date time: " + dateTimeParseException;
    }
    public String showFileIobError(IndexOutOfBoundsException e) {
        return "Invalid input task file format: " + e
                + "Remove the file and try again!";
    }
    public String showInvalidStateException(InvalidStateException e) {
        return "Invalid input task file format: " + e
                + "Remove the file and try again!";
    }
    public String showInitializeIoeException(IOException e) {
        return "Error in initializing lists: " + e + " Try again!";
    }

    public String showSaveIoeException(IOException e) {
        return "Error in creating file for current list: " + e;
    }


    public String showTaskList() {
        return "Who works for money these days? Money is a means, "
                + "not an end. Work should make you happy... That's the most fundamental principle.\n"
                + "Don't forget your work~\n";
    }

    public String showDoneTaskStatus(Task task) {
        return "Another project over the finish line! I've marked this task as done:\n"
                + "    " + task.getStatus();
    }

    public String showUndoneTaskStatus(Task task, int size) {
        return "Noted. I've marked this task as undone:\n"
                + "    " + task.getStatus()
                + "Now you have " + size + " tasks in the list.";
    }

    public String showAddTask(Task task, int size) {
        return "Got it. I've added this task:\n"
                + "    " + task.getStatus() + "\n"
                + "Hard work pays off~\n"
                + "Now you have " + size + " tasks in the list.";
    }

    public String showDeleteTask(Task task, int size) {
        return "Noted. I've removed this task:\n"
                + "    " + task.getStatus()
                + "Now you have " + size + " tasks in the list.";
    }

    public String showTargetTask(TaskList taskList) {
        if (taskList.getSize() == 0) {
            return "No matching task found. Enter \"list\" to see current tasks.";
        } else {
            return "Here are matching tasks in your list:\n"
                    + taskList;
        }
    }
}
