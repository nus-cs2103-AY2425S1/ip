package gallium.main;

import gallium.task.Deadline;
import gallium.task.Event;
import gallium.task.Task;
import gallium.task.Todo;

import java.io.IOException;
import java.util.Scanner;

/**
 * The Ui class handles the user interface, such as reading user
 * input and displaying messages to the user.
 */
public class Ui {

    private Scanner userInput;
    private static final String DIVIDER = "____________________________________________________________";

    /**
     * Constructs a Ui object and initializes the Scanner for user input.
     * Displays welcome message.
     */
    public Ui() {
        this.userInput = new Scanner(System.in);
        System.out.println("Hello from \n");
        System.out.println("  ____       _ _ _                \r\n" + //
                " / ___| __ _| | (_)_   _ _ __ ___  \r\n" + //
                "| |  _ / _` | | | | | | | '_ ` _ \\ \r\n" + //
                "| |_| | (_| | | | | |_| | | | | | |\r\n" + //
                " \\____|\\__,_|_|_|_|\\__,_|_| |_| |_|");
        System.out.println("Hello! I am Gallium. What can I do for you?");
    }

    /**
     * Reads the next line of input from the user.
     * 
     * @return The next line of input.
     */
    public String readNextLine() {
        return userInput.nextLine();
    }

    /**
     * Closes the Scanner.
     */
    public void closeScanner() {
        userInput.close();
    }

    /**
     * Prints any message wrapped with a divider.
     * 
     * @param message The message to be printed.
     */
    public void printAnyMessage(String message) {
        System.out.println("    " + DIVIDER + "\n    " + message + "\n    " + DIVIDER);
    }

    /**
     * Prints the list of tasks.
     * 
     * @param taskList The TaskList to be printed.
     */
    public void printList(TaskList taskList) {
        String message = "Here are the tasks in your list:";
        System.out.println("    " + DIVIDER + "\n    " + message);
        System.out.println(taskList.toStringIndent());
        System.out.println("\n    " + DIVIDER);
    }

    /**
     * Prints a message to show a task was marked or unmarked.
     * 
     * @param isMark True if the task is marked as done, false if unmarked.
     * @param task   The Task that was marked or unmarked.
     */
    public void printMarkMessage(boolean isMark, Task task) {
        System.out.println("    " + DIVIDER);
        System.out.println("    " + (isMark ? "Nice! I've marked this task as done: "
                : "OK, I've marked this task as not done yet: ") + "\n" + "    "
                + task.toString());
        System.out.println("    " + DIVIDER + "\n    ");
    }

    /**
     * Prints a message to show a Todo task was added.
     * 
     * @param todo The Todo task that was added.
     */
    public void printAddTodo(Todo todo) {
        System.out.println("    " + DIVIDER + "\n    " + "Got it. I've added this task: \n" + "    "
                + todo.toString()
                + "\n    Now you have " + Task.count + " " + Task.taskCount() + " in the list.\n" + "    "
                + DIVIDER
                + "\n");
    }

    /**
     * Prints a message to show a Deadline task was added.
     * 
     * @param deadline The Deadline task that was added.
     */
    public void printAddDeadline(Deadline deadline) {
        System.out
                .println("    " + DIVIDER + "\n    " + "Got it. I've added this task: \n"
                        + "    " + deadline.toString()
                        + "\n    Now you have " + Task.count + " " + Task.taskCount() + " in the list.\n"
                        + "    "
                        + DIVIDER
                        + "\n");
    }

    /**
     * Prints a message to show an Event task was added.
     * 
     * @param event The Event task that was added.
     */
    public void printAddEvent(Event event) {
        System.out.println("    " + DIVIDER + "\n    " + "Got it. I've added this task: \n" + "    "
                + event.toString()
                + "\n    Now you have " + Task.count + " " + Task.taskCount() + " in the list.\n" + "    "
                + DIVIDER
                + "\n");
    }

    /**
     * Prints a message to show a task was deleted.
     * 
     * @param task The Task that was deleted.
     */
    public void printDelete(Task task) {
        System.out.println("    " + DIVIDER);
        System.out.println("    " + "Noted. I've removed this task:" + "\n" + "    "
                + task.toString());
        System.out.println(
                "\n    Now you have " + (Task.count - 1) + " " + Task.taskCountDelete()
                        + " in the list.\n");
        System.out.println("    " + DIVIDER + "\n    ");
    }

    /**
     * Prints a list of tasks that match a date.
     * 
     * @param tasks The tasks that match the date.
     */
    public void printMatchingDate(String tasks) {
        System.out.println("    " + DIVIDER + "\n    Deadlines/Events that match the date: ");
        System.out.println(tasks);
        System.out.println("\n    " + DIVIDER);
    }

    /**
     * Prints a list of tasks that match a keyword.
     * 
     * @param tasks The tasks that match the keyword.
     */
    public void printMatchingFind(String tasks) {
        if (tasks.equals("")) {
            printAnyMessage("Aw man no matching tasks :(");
        } else {
            System.out.println("    " + DIVIDER + "\n    Matching tasks in your list: ");
            System.out.println(tasks);
            System.out.println("\n    " + DIVIDER);
        }
    }

    public void showCreateFileError(IOException e) {
        printAnyMessage("Error creating file:" + e.getMessage());
    }

    /**
     * Prints an error message when there is error loading tasks.
     */
    public void showLoadingError() {
        printAnyMessage("Oh no! Error loading task list :(");
    }

    /**
     * Prints a GalliumException message.
     * 
     * @param e The GalliumException that was thrown.
     */
    public void showGalliumException(GalliumException e) {
        printAnyMessage(e.getMessage());
    }

    /**
     * Prints a message to show that the deadline date is missing.
     */
    public void showIncompleteDeadline() {
        printAnyMessage("Please put the date of the deadline!!");
    }

    /**
     * Prints a message to show that the event details are incomplete.
     */
    public void showIncompleteEvent() {
        printAnyMessage("Please put the from and to of the event!!");
    }

    /**
     * Prints a message to show that the index is out of range.
     * 
     * @param index The index that was out of range.
     */
    public void showWrongIndex() {
        printAnyMessage("Please put a number between 1 and " + (Task.count - 1)
                + "!" + "\n    Now you have " + (Task.count - 1) + " " + Task.taskCountDelete()
                + " in the list.");
    }

    /**
     * Prints an error message when an IOException occurs.
     * 
     * @param e The IOException that was thrown.
     */
    public void showIOException(IOException e) {
        printAnyMessage("Something went wrong: " + e.getMessage());
    }

    /**
     * Prints a message to show invalid date/time format.
     */
    public void showWrongDateTimeFormat() {
        printAnyMessage("Invalid date/time format! Please put in YYYY-MM-DD and 24 hours (HHMM) format!");
    }

    /**
     * Prints a bye message when the program exits.
     */
    public void printByeMessage() {
        printAnyMessage("Bye. Hope to see you again soon!");
    }
}
