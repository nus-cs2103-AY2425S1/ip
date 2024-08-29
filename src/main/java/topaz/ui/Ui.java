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

    private Scanner scanner;
    /**
     * Initializes the Ui with a new {@link Scanner} for reading user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }


    /**
     * Reads the next line of user input.
     *
     * @return The line of user input as a {@link String}.
     */
    public String readCommand() {
        return scanner.nextLine();
    }
    public void welcome() {
        showLine();
        System.out.println(" Hello! I'm Topaz - I work in the IPC's Strategic Investment Department. "
                + "Long time no see! How have you been? Clocking in a lot of overtime?");
        System.out.println(" What can I do for you?");
        showLine();
    }

    public void showUserIobError() {
        System.out.println("Please enter valid information after the keyword!");
        System.out.println("Try again!");
    }

    public void showMarkIobError(int index) {
        System.out.println("Invalid index number: " + index + " Please refer to your list.");
    }
    public void showException(Exception e) {
        System.out.println(e);
        System.out.println("Try again!");
    }

    public void goodbye() {
        System.out.println(" Ugh, why do they have to call me for such a trivial matter... Sorry, something just came up at work, I've got to get going!");
        System.out.println(" Bye. Hope to see you again soon!");
        scanner.close();
    }

    public void showHelp() {
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
        System.out.println(guide);
    }

    public void showTimeParseException(DateTimeParseException dateTimeParseException) {
        System.out.println("Invalid input date time: " + dateTimeParseException);
    }
    public void showFileIobError(IndexOutOfBoundsException e) {
        System.out.println("Invalid input task file format: " + e
                + " Remove the file and try again!");
    }
    public void showInvalidStateException(InvalidStateException e) {
        System.out.println("Invalid input task file format: " + e
                + " Remove the file and try again!");
    }
    public void showInitializeIoeException(IOException e) {
        System.out.println("Error in ininializing lists: " + e + " Try again!");
    }

    public void showSaveIoeException(IOException e) {
        System.out.println("Error in creating file for current list: " + e);
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showTaskList() {
        System.out.println(" Who works for money these days? Money is a means, "
                           + "not an end. Work should make you happy... That's the most fundamental principle.");
        System.out.println(" Don't forget your work~");
    }

    public void showDoneTaskStatus(Task task) {
        System.out.println(" Another project over the finish line! I've marked this task as done:");
        System.out.println("    " + task.getStatus());
    }

    public void showUndoneTaskStatus(Task task, int size) {
        System.out.println(" Noted. I've removed this task:");
        System.out.println("    " + task.getStatus());
        System.out.println(" Now you have " + size + " tasks in the list.");
    }

    public void showAddTask(Task task, int size) {
        System.out.println(" Got it. I've added this task:");
        System.out.println("    " + task.getStatus());
        System.out.println(" Hard work pays off~");
        System.out.println(" Now you have " + size + " tasks in the list.");
    }

    public void showDeleteTask(Task task, int size) {
        System.out.println(" Noted. I've removed this task:");
        System.out.println("    " + task.getStatus());
        System.out.println(" Now you have " + size + " tasks in the list.");
    }

    public void showTargetTask(TaskList taskList) {
        if (taskList.getSize() == 0) {
            System.out.println("No matching task found. Enter \"list\" to see current tasks.");
        } else {
            System.out.println("Here are matching tasks in your list:");
            System.out.println(taskList);
        }
    }
}
