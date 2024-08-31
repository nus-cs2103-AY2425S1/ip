package gopher.ui;

import gopher.exception.UnknownCommandException;
import gopher.task.Task;
import gopher.task.TaskList;

/**
 * Represents the UI that handles the user interactions and messages.
 */
public class UI {
    /**
     * Prints the Gopher Logo.
     */
    public static void printGopherLogo() {
        System.out.println("""
                  ____             _
                 / ___| ___  _ __ | |__   ___ _ __
                | |  _ / _ \\| '_ \\| '_ \\ / _ \\ '__|
                | |_| | (_) | |_) | | | |  __/ |
                 \\____|\\___/| .__/|_| |_|\\___|_|
                            |_|
                """);
    }

    /**
     * Prints the horizontal separator.
     */
    public static void printHorizontalSeparator() {
        System.out.println("==================================================");
    }

    /**
     * Prints the greeting message.
     */
    public static void printGreetMessage() {
        printGopherLogo();
        System.out.println("Hello! I am Gopher.\nWhat can I do for you?");
    }

    /**
     * Prints the given TaskList for task visualization.
     *
     * @param tasks TaskList object to be printed
     */
    public static void printTaskList(TaskList tasks) {
        System.out.println(String.format("You currently have %d %s in the list\n",
                tasks.getSize(),
                tasks.getSize() == 1 ? "task" : "tasks"));
        System.out.println(tasks);
    }

    /**
     * Prints the message when TaskList successfully add in a new task.
     *
     * @param taskList TaskList object that accepts the new task
     * @param task task to be added
     */
    public static void printAddTaskMessage(TaskList taskList, Task task) {
        System.out.println("Got it! I have added this task:\n" + task);
    }

    /**
     * Prints the message when a given task is successfully mark as done.
     *
     * @param task task to be marked as done
     */
    public static void printMarkAsDoneMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    /**
     * Prints the message when a given task is successfully mark as not done.
     *
     * @param task task to be marked as not done
     */
    public static void printMarkAsUndoneMessage(Task task) {
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println(task);
    }

    /**
     * Prints the message when TaskList successfully delete a task.
     *
     * @param taskList TaskList in which the task needs to be deleted
     * @param task task to be deleted
     */
    public static void printDeleteTaskMessage(TaskList taskList, Task task) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
    }

    /**
     * Prints the exit message.
     */
    public static void printExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the helping message when user encounters an UnknownCommandException.
     *
     * @param e UnknownCommandException thrown by the system
     */
    public static void printUnknownCommandWarning(UnknownCommandException e) {
        System.out.println(e.getMessage());
        System.out.println("""
                Current Supported Commands:
                    1. todo - Create a ToDo Task
                    2. deadline - Create a Deadline Task
                    3. event - Create an Event Task
                    4. mark - Mark a task as done
                    5. unmark - Mark a task as not done
                    6. bye - Exit the chatbot
                
                Note: The command is case-insensitive, as long as the
                input characters match, the chatbot would be able to
                respond to the given command""");
    }

    /**
     * Prints the helping message when user inputs invalid date.
     */
    public static void printInvalidDateWarning() {
        System.out.println("Oops...Seems like you have passed in invalid date...\n"
                + "Please check if your date is of the correct value and format\n"
                + "Current accepted date format: YYYY-MM-DD or YYYY-MM-DD hh:mm");
    }
}
