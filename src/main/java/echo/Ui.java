package echo;

import echo.backend.Parser;
import echo.task.TaskList;
import echo.task.TaskType;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
/**
 * The Ui class handles user interaction and command execution.
 * It accepts user input and interacts with the Parser class to process commands and the TaskList class.
 */
public class Ui {
    private Scanner sc;
    private Parser parser;
    private Boolean isAcceptingInput;
    private TaskList taskList;
    /**
     * Constructs a Ui object with the specified TaskList.
     *
     * @param taskList the TaskList object to be referenced by the Ui
     */
    public Ui(TaskList taskList) {
        parser = new Parser(this);
        this.taskList = taskList;
    }
    /**
     * Starts accepting user input and processes commands until the user inputs 'Bye'.
     */
    public void acceptInput() {
        printWelcome();
        sc = new Scanner(System.in);
        isAcceptingInput = true;

        while (isAcceptingInput) { // handles input until user says bye
            if (sc.hasNextLine()) {
                parser.parseInput(sc.nextLine());
            } else {
                stopAcceptingInput();
            }
        }
    }
    /**
     * Stops accepting user input.
     */
    public void stopAcceptingInput() {
        isAcceptingInput = false;
    }
    /**
     * Handles unknown commands by printing an error message.
     */
    public void handleUnknown() {
        printUnknown();
    }
    /**
     * Handles the "bye" command and stops accepting user input.
     */
    public void handleBye() {
        printBye();
        stopAcceptingInput();
    }
    /**
     * Handles the "list" command and prints all tasks in the TaskList.
     */
    public void handleList() {
        printList(taskList.getTasksString());
    }

    /**
     * Handles the "mark" command to mark a task as done.
     *
     * @param arg the argument provided with the "mark" command (task index)
     */
    public void handleMark(String arg) {
        // Error handling
        if (arg.length() != 1) { // Arg of incorrect length
            System.out.println("Please input 'mark [index]'");
            return;
        }

        int index;
        try {
            index = Integer.valueOf(arg);
        } catch (NumberFormatException e) { // Index is not an integer
            System.out.println("Please input 'mark [index]'");
            return;
        }
        if (index > taskList.getNumTasks()) { // Index is not within tasks length
            System.out.println("Invalid index.");
            return;
        }

        // Mark task
        taskList.markTask(index);

        // Print success message
        printMarkedTask(taskList.getTaskString(index));
    }
    /**
     * Handles the "unmark" command to unmark a task as not done.
     *
     * @param arg the argument provided with the "unmark" command (task index)
     */
    public void handleUnmark(String arg) {
        if (arg.length() != 1) { // Incorrect argument length
            System.out.println("Please input 'unmark [index]'");
            return;
        }
        int index;
        try {
            index = Integer.valueOf(arg);
        } catch (NumberFormatException e) { // Not a number input
            System.out.println("Please input 'unmark [index]'");
            return;
        }
        if (index > taskList.getNumTasks()) { // Index exceeds tasks length
            System.out.println("Invalid index.");
            return;
        }

        // Unmark task
        taskList.unmarkTask(index);

        // Print success msg
        printUnmarkedTask(taskList.getTaskString(index));
    }
    /**
     * Handles the "todo" command to add a new todo task.
     *
     * @param task the task description
     */
    public void handleTodo(String task) {
        while (task.isEmpty()) {
            System.out.println("Enter task description: ");
            task = sc.nextLine();
        }
        taskList.addTask(task.trim(), TaskType.TODO, "");
        handleAddedTask();
    }
    /**
     * Handles the "event" command to add a new event task.
     *
     * @param description the event description
     * @param startDate   the event start date
     */
    public void handleEvent(String description, String startDate) {
        String endDate = "";
        if (description.contains("/to")) { // No start date, end date provided
            String[] temp = parser.parseEventTo(description);
            endDate = temp[1];
            description = temp[0];
        }

        while (description.isEmpty()) { // No echo.task description, start date provided
            System.out.println("Enter task description: ");
            description = sc.nextLine().trim();
        }

        while (startDate.isEmpty()) { // No start date provided
            System.out.println("Start: ");
            startDate = sc.nextLine();
        }

        if (startDate.contains("/to")) {
            String[] temp = parser.parseEventTo(startDate);
            endDate = temp[1];
            startDate = temp[0];
        }

        while (endDate.isEmpty()) {
            System.out.println("End: ");
            endDate = sc.nextLine();
        }

        taskList.addTask(description, TaskType.EVENT, startDate + "->" + endDate);
        handleAddedTask();
    }
    /**
     * Handles the "deadline" command to add a new deadline task.
     *
     * @param description     the task description
     * @param deadlineToParse the deadline date string to be parsed
     */
    public void handleDeadline(String description, String deadlineToParse) {
        while (description.isEmpty()) {
            System.out.println("Enter task description: ");
            description = sc.nextLine().trim();
        }

        LocalDate deadline = null;
        while (deadline == null) {
            if (deadlineToParse.isEmpty()) {
                System.out.println("Deadline:");
                deadlineToParse = sc.nextLine();
            }
            try {
                deadline = parser.parseDateTime(deadlineToParse);
            } catch (DateTimeParseException e) {
                System.out.println("No matching date formats");
                deadlineToParse = null;
            }
        }

        taskList.addDeadline(description, deadline);
        handleAddedTask();
    }
    /**
     * Prints a message indicating that a task was successfully added.
     */
    public void handleAddedTask() {
        printAddedTask(taskList.getTaskString(taskList.getNumTasks()), taskList.getNumTasks());
    }
    /**
     * Handles the "delete" command to remove a task from the list.
     *
     * @param arg the argument provided with the "delete" command (task index)
     */
    public void handleDelete(String arg) {
        if (arg.isEmpty()) {
            System.out.println("Please input 'delete [item index]'");
            return;
        }
        int index;

        try {
            index = Integer.valueOf(arg);
        } catch (NumberFormatException e) {
            System.out.println("Please input 'delete [item index]'");
            return;
        }

        if (index > taskList.getNumTasks()) {
            System.out.println("Invalid index.");
            return;
        }

        printDelete(taskList.getTaskString(index));
        taskList.deleteTask(index);
    }
    private void printWelcome() {
        String welcomeMsg =
                "____________________________________________________________\n" +
                "Hello! I'm Echo!\n" +
                "What can I do for you?\n" +
                "____________________________________________________________\n";
        System.out.print(welcomeMsg);
    }
    private void printMarkedTask(String task) {
        System.out.println(
            "____________________________________________________________\n" +
            "Nice! I've marked this task as done:\n" +
            task +
            "____________________________________________________________"
        );
    }
    private void printUnmarkedTask(String task) {
        System.out.println(
        "____________________________________________________________\n" +
        "Ok, I've marked this task as not done yet:\n"+
        task +
        "____________________________________________________________");
    }
    private void printAddedTask(String task, int numTasks) {
        System.out.printf(
            "____________________________________________________________\n" +
            "Got it. I've added this task:\n" +
            task +
            "Now you have %d task" +
            (numTasks == 1 ? "" : "s") +
            " in the list.\n" +
            "____________________________________________________________\n",
            numTasks
        );
    }
    private void printUnknown() {
        System.out.println(
            "____________________________________________________________\n" +
            "OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
            "____________________________________________________________");
    }
    private void printList(String tasks) {
        System.out.println(
            "____________________________________________________________\n" +
            "Here are the tasks in your list:\n" +
            tasks +
            "____________________________________________________________");
    }
    private void printDelete(String task) {
        System.out.println(
            "____________________________________________________________\n" +
            "Noted. I've removed this etask:\n" +
            task +
            "____________________________________________________________"
        );
    }
    private void printBye() {
        System.out.println(
            "____________________________________________________________\n" +
            "Bye. Hope to see you again soon!\n" +
            "____________________________________________________________");
    }
    /**
     * Prints an error message indicating that there was an issue loading from the file.
     */
    public void showLoadingError() {
        System.out.println(
            "____________________________________________________________\n" +
            "Oh no! Error loading from file!\n" +
            "____________________________________________________________"
        );
    }
}
