package Joseph;

import java.util.ArrayList;
import java.util.Scanner;

import Joseph.Tasks.Task;

/**
 * Handles all interactions with the user.
 */
public class UI {
    private final Scanner scanner;

    public UI() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints a line separator.
     */
    public void printLine() {
        final String line = "----------------------------------";
        System.out.println(line);
    }

    /**
     * Prints the welcome message when the chatbot is started.
     * @param name The name of the chatbot.
     */
    public void printWelcomeMessage(String name) {
        printLine();
        System.out.println("Hello, I'm " + name + "!");
        System.out.println("How can I help you today? (type help for all commands)");
        printLine();
    }

    /**
     * Prints the exit message when the chatbot is closed i.e. the "bye" command.
     */
    public void printExitMessage() {
        printLine();
        System.out.println("Bye! Have a nice day :)");
        printLine();
    }

    /**
     * Prints the current list of tasks.
     * @param list The list of tasks within ./data/joseph.txt.
     */
    public void printListMessage(ArrayList<Task> list) {
        printLine();
        for (int i = 0; i < list.size(); i++) {
            String done = "[" + list.get(i).getDone() + "] ";
            System.out.println(i + 1 + ". " + done + list.get(i).getDetails());
        }
        printLine();
    }

    /**
     * Prints a help message showing all available commands. Command is "help".
     */
    public void printHelpMessage() {
        printLine();
        System.out.println("help: prints all available commands");
        System.out.println("list: prints the current list");
        System.out.println("mark X, where X is any number: "
                + "marks task X on the list as completed");
        System.out.println("unmark X, where X is any number: "
                + "unmarks task X on the list as uncompleted.");
        System.out.println("The below todo, deadline and event commands "
                + "accepts desc as any string");
        System.out.println("The below deadline and event commands "
                + "accepts a due or start and end "
                + "in the following format: DD/MM/YYYY HHmm");
        System.out.println("todo desc, "
                + "adds a todo to the list");
        System.out.println("deadline desc /due, "
                + "adds a deadline to the list with its due date");
        System.out.println("event desc /start /end, "
                + "adds an event to the list with its start and end");
        System.out.println("bye: closes the chatbot");
        printLine();
    }

    /**
     * Prints a message to indicate that the task as been marked as done. Command is "mark X".
     * @param taskDetails The details of the task being marked.
     */
    public void printMarkMessage(String taskDetails) {
        System.out.println("Great, I've marked " + taskDetails + " as done!");
    }

    /**
     * Prints a message to indicate that the task has been unmarked. Command is "unmark X".
     * @param taskDetails The details of the task being unmarked.
     */
    public void printUnmarkMessage(String taskDetails) {
        System.out.println("Okay, I've unmarked " + taskDetails + " as not done!");
    }

    /**
     * Prints a message to indicate that the ToDo task has been added. Command is "todo XXX".
     * @param taskDetails The details of the ToDo task.
     */
    public void printTodoMessage(String taskDetails) {
        System.out.println("I've added the todo: " + taskDetails);
    }

    /**
     * Prints a message to indicate that the Deadline task has been added. Command is "deadline XXX".
     * @param taskDetails The details of the Deadline task.
     */
    public void printDeadlineMessage(String taskDetails) {
        System.out.println("I've added the deadline: " + taskDetails);
    }

    /**
     * Prints a message to indicate that the Event task has been added. Command is "event XXX".
     * @param taskDetails The details of the Event task.
     */
    public void printJEventMessage(String taskDetails) {
        System.out.println("I've added the event: " + taskDetails);
    }

    /**
     * Prints a message to indicate that the task has been deleted. Command is "delete X".
     * @param taskDetails The details of the task being deleted.
     */
    public void printDeleteMessage(String taskDetails) {
        System.out.println("Alright, I've deleted " + taskDetails);
    }

    /**
     * Prints the tasks that match the keyword.
     * @param tasks The list of matching tasks.
     * @param keyword The search keyword.
     */
    public void printFindMessage(ArrayList<Task> tasks, String keyword) {
        if (tasks.isEmpty()) {
            System.out.println("I couldn't find any tasks with the keyword: " + keyword);
        } else {
            System.out.println("I've found the below tasks with the keyword: " + keyword);
            printLine();
            for (int i = 0; i < tasks.size(); i++) {
                String done = "[" + tasks.get(i).getDone() + "] ";
                System.out.println(i + 1 + ". " + done + tasks.get(i).getDetails());
            }
            printLine();
        }
    }
    /**
     * Prints a message to indicate that the command was not recognised.
     */
    public void printUnrecognisedMessage() {
        System.out.println("I don't know what that means :(");
    }

    /**
     * Prints a message to indicate the error details.
     * @param message The error message.
     */
    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    /**
     * Reads user input.
     * @return The user input as a string.
     */
    public String readUserInput() {
        return scanner.nextLine();
    }

    /**
     * Closes the scanner.
     */
    public void close() {
        scanner.close();
    }
}