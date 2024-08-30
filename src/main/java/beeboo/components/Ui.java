package beeboo.components;

import beeboo.exception.BeeBooExceptions;
import beeboo.task.Tasks;

import java.util.Scanner;

/**
 * The Ui class handles interactions with the user by displaying messages and processing user input.
 */
public class Ui {
    private Scanner input;

    /**
     * Constructs a Ui object and initializes the Scanner for user input.
     */
    public Ui() {
        input = new Scanner(System.in);
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task the task that has been marked as done
     */
    public void markDoneMessage(Tasks task) {
        chatBox("Nice! I've marked this task as done:\n" + task);
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param task the task that has been marked as not done
     */
    public void unmarkDoneMessage(Tasks task) {
        chatBox("OK, I've marked this task as not done yet:\n" + task);
    }

    /**
     * Displays a message indicating that a task has been deleted and shows the number of remaining tasks.
     *
     * @param task the task that has been removed
     * @param size the number of tasks left in the list
     */
    public void deleteItemMessage(Tasks task, int size) {
        chatBox("Ok I have removed the following item\n" + task
                + "\n" + "You have " + size + " tasks left");
    }

    /**
     * Displays a message indicating that there is no data available and a new TaskList is being created.
     */
    public void loadingError() {
        chatBox("You don't have any data right now. Let me create a new TaskList");
    }

    /**
     * Displays the list of tasks.
     *
     * @param list the string representation of the task list
     */
    public void produceList(String list) {
        chatBox(list);
    }

    /**
     * Formats and prints a message surrounded by a border of dashes.
     *
     * @param str the message to be displayed
     */
    public void chatBox(String str) {
        for (int i = 0; i < 60; i++) {
            System.out.print("-");
        }
        System.out.println();
        System.out.println(str);
        System.out.println();
        for (int i = 0; i < 60; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    /**
     * Displays a message indicating that a task has been added to the list and shows the number of tasks.
     *
     * @param task the task that has been added
     * @param size the number of tasks in the list
     */
    public void addList(Tasks task, int size) {
        chatBox("added: " + task + "\n" + "You have " + size + " tasks in the list");
    }

    /**
     * Displays a welcome message when the application starts.
     */
    public void showWelcomeMessage() {
        chatBox("Hello! I'm BeeBoo\nWhat can I do for you?");
    }

    /**
     * Displays a farewell message when the user exits the application.
     */
    public void byeMessageMessage() {
        chatBox("Bye. Hope to see you again soon!");
    }

    /**
     * Displays an error message based on the provided exception.
     *
     * @param e the exception containing the error message
     */
    public void showError(BeeBooExceptions e) {
        chatBox(e.toString());
    }

    /**
     * Reads and returns the next line of user input, converting it to lowercase and trimming whitespace.
     *
     * @return the user input as a trimmed and lowercase string
     */
    public String handleCommand() {
        if (input == null) {  // Ensure Scanner is initialized
            input = new Scanner(System.in);
        }
        return input.nextLine().trim().toLowerCase();
    }

    /**
     * Closes the Scanner object to release resources.
     */
    public void close() {
        input.close();
    }
}
