package tars;

import java.util.Scanner;

public class UI {
    private final Scanner scanner;
    private final String line = "____________________________________";

    /**
     * Constructs a new UI object and initializes the scanner for reading user input.
     */
    public UI() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads a line of input from the user.
     *
     * @return the input string entered by the user.
     */
    public String readInput() {
        return scanner.nextLine();
    }

    /**
     * Prints a formatted response to the console, surrounded by lines.
     *
     * @param response the response message to be printed.
     */
    public void printResponse(String response) {
        System.out.println(line);
        System.out.println(response);
        System.out.println(line);
    }

    /**
     * Prints a response indicating that a task has been marked as done.
     *
     * @param task the task that was marked as done.
     */
    public void taskMarkedResponse(Task task) {
        printResponse("Nice! I've marked this task as done:\n" + "  " + task.toString());
    }

    /**
     * Prints a response indicating that a task has been marked as not done.
     *
     * @param task the task that was marked as not done.
     */
    public void taskUnmarkedResponse(Task task) {
        printResponse("OK, I've marked this task as not done yet:\n" + "  " + task.toString());
    }

    /**
     * Prints a response indicating that the specified task could not be found.
     */
    public void taskNotFoundResponse() {
        printResponse("The task you specified cannot be found. Please try again");
    }

    /**
     * Prints a response indicating that a task has been added to the task list.
     *
     * @param task the task that was added.
     * @param size the current number of tasks in the list.
     */
    public void taskAddedResponse(Task task, int size) {
        printResponse("Got it. I've added this task: \n" + task.toString() + "\n" + "Now you have "
                + size + " tasks in the list\n");
    }

    /**
     * Prints a response indicating that a task has been removed from the task list.
     *
     * @param taskDescription the description of the task that was removed.
     * @param size the current number of tasks in the list.
     */
    public void taskRemovedResponse(String taskDescription, int size) {
        printResponse("Noted. I've removed this task:\n" + taskDescription + "\n" + "Now you have "
                + size + " tasks in the list\n");
    }

    /**
     * Prints a welcome message when the application starts.
     */
    public void printWelcome() {
        System.out.println(line);
        System.out.println("Hello! I'm TARS");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

    /**
     * Prints a goodbye message when the application is exiting.
     */
    public void printGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints an error message if there is an issue loading tasks from storage.
     */
    public void printLoadingError() {
        System.out.println("There was an error loading tasks.");
    }

}
