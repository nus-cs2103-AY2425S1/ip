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
    private StringBuilder output;
    /**
     * Constructs a Ui object and initializes the Scanner for user input.
     * Displays welcome message.
     */
    public Ui() {
        this.userInput = new Scanner(System.in);
        this.output = new StringBuilder();
        printHelloMessage();
    }
    
    void resetOutput() {
        this.output.setLength(0);
    }

    String getOutput() {
        return this.output.toString();
    }

    private void append(String str) {
        this.output.append(str);
    }

    public static String printHelloMessage() {
        return "Hellooooooooo! I am Gallium!! :> \nWhat can I help you?";
    }

    public String printInputAgainMessage() {
        this.append("\nWhat can I help you?");
        return this.getOutput();
    }

    /**
     * Closes the Scanner.
     */
    public void closeScanner() {
        userInput.close();
    }

    /**
     * Prints the list of tasks.
     * 
     * @param taskList The TaskList to be printed.
     */
    public void printList(TaskList taskList) {
        String message = "Here are the tasks in your list: \n";
        append(message);
        append(taskList.toStringIndent());
    }

    /**
     * Prints a message to show a task was marked or unmarked.
     * 
     * @param isMark True if the task is marked as done, false if unmarked.
     * @param task   The Task that was marked or unmarked.
     */
    public void printMarkMessage(boolean isMark, Task task) {
        append((isMark ? "Yayyyy! I've marked this task as done: "
                : "Aw man :( I've marked this task as not done yet: ") + "\n" + "    "
                + task.toString());
    }

    public void printEditMessage(Task task) {
        append("Yippee! You have edited this task: " + task.toString());
    }

    public void printShowTask(Task task) {
        append("This is your task: " + task.toString() + "\n");
    }

    public void printAskEditField(Task task) {
        if (task instanceof Todo) {
            append("Enter the new description of your todo: ");
        } else if (task instanceof Deadline) {
            append("Enter the number representing the fields + the description that you want to change to: \n");
            append("1. description \n 2. date \n");
            append("Eg: 1. cs2103t assignment");
        } else if (task instanceof Event) {
            append("Enter the number representing the fields + the description that you want to change to: \n");
            append("1. description \n2. from date \n3. to date\n");
            append("Eg: 1. cs2103t assignment");
        }
    }

    /**
     * Prints a message to show a Todo task was added.
     * 
     * @param todo The Todo task that was added.
     */
    public void printAddTodo(Todo todo) {
        append("Yayyy! :> I've added this todo: \n"
                + todo.toString()
                + "\nNow you have " + Task.count + " " + Task.taskCount() + " in the list."
                + "\n");
    }

    /**
     * Prints a message to show a Deadline task was added.
     * 
     * @param deadline The Deadline task that was added.
     */
    public void printAddDeadline(Deadline deadline) {
        append("Yayyy! :> I've added this deadline: \n"
                        + deadline.toString()
                        + "\nNow you have " + Task.count + " " + Task.taskCount() + " in the list."
                        + "\n");
    }

    /**
     * Prints a message to show an Event task was added.
     * 
     * @param event The Event task that was added.
     */
    public void printAddEvent(Event event) {
        append("Yayyy! :> I've added this event: \n"
                + event.toString()
                + "\nNow you have " + Task.count + " " + Task.taskCount() + " in the list.\n");
    }

    /**
     * Prints a message to show a task was deleted.
     * 
     * @param task The Task that was deleted.
     */
    public void printDelete(Task task) {
        append("Okieee! I've removed this task:" + "\n"
                + task.toString());
        append("\nNow you have " + (Task.count - 1) + " " + Task.taskCountDelete()
                        + " in the list.\n");
    }

    /**
     * Prints a list of tasks that match a date.
     * 
     * @param tasks The tasks that match the date.
     */
    public void printMatchingDate(String tasks) {
        if (tasks.equals("")) {
            append("Aw man no matching dates :(");
        } else { append("Deadlines/Events that match the date: ");
            append(tasks);
        }
    }

    /**
     * Prints a list of tasks that match a keyword.
     * 
     * @param tasks The tasks that match the keyword.
     */
    public void printMatchingFind(String tasks) {
        if (tasks.equals("")) {
            append("Aw man no matching tasks :(");
        } else {
            append("Matching tasks in your list: ");
            append(tasks);
        }
    }

    public void showCreateFileError(IOException e) {
        append("Oh no! Error creating file:" + e.getMessage());
    }

    /**
     * Prints an error message when there is error loading tasks.
     */
    public void showLoadingError() {
        append("Oh no! Error loading task list :(");
    }

    /**
     * Prints a GalliumException message.
     * 
     * @param e The GalliumException that was thrown.
     */
    public void showGalliumException(GalliumException e) {
        append(e.getMessage());
    }

    /**
     * Prints a message to show that the deadline date is missing.
     */
    public void showIncompleteDeadline() {
        append("3:( Incomplete information!! Please input your date & time in YYYY-MM-DD HHMM (24 hour) format!");
        append("\nExample: deadline submit tp /by 2024-09-22 2359");
    }

    /**
     * Prints a message to show that the event details are incomplete.
     */
    public void showIncompleteEvent() {
        append("3:( Incomplete information!! Please put the from and to of the event!!");
        append("\nPlease input your date & time in YYYY-MM-DD HHMM (24 hour) format!");
        append("\nExample: event CS2103T meeting /from 2024-09-21 1530 /to 2024-09-21 1630");
    }

    /**
     * Prints a message to show that the index is out of range.
     * 
     * @param index The index that was out of range.
     */
    public void showWrongIndex() {
        append("3:( Please put a number between 1 and " + (Task.count - 1)
                + "!" + "\nNow you have " + (Task.count - 1) + " " + Task.taskCountDelete()
                + " in the list.");
    }

    /**
     * Prints an error message when an IOException occurs.
     * 
     * @param e The IOException that was thrown.
     */
    public void showIOException(IOException e) {
        append("Oh no! Something went wrong: " + e.getMessage());
    }

    /**
     * Prints a message to show invalid date/time format.
     */
    public void showWrongDateTimeFormat() {
        append("3:( Invalid date/time format! Please put in YYYY-MM-DD and 24 hours (HHMM) format!");
    }

    /**
     * Prints a bye message when the program exits.
     */
    public void printByeMessage() {
        append("Thanks!! :> Bye, hope to see you again soon!");
    }
}
