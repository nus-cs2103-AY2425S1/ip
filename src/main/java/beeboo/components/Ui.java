package beeboo.components;

import java.util.Scanner;

import beeboo.task.Tasks;

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
     * Returns a message indicating that a task has been marked as done.
     *
     * @param task the task that has been marked as done
     * @return a message indicating the task is marked as done
     */
    public String markDoneMessage(Tasks task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Returns a message indicating that a task has been marked as not done.
     *
     * @param task the task that has been marked as not done
     * @return a message indicating the task is marked as not done
     */
    public String unmarkDoneMessage(Tasks task) {
        return "OK, I've marked this task as not done yet:\n" + task;
    }

    /**
     * Returns a message indicating that a task has been deleted and shows the number of remaining tasks.
     *
     * @param task the task that has been removed
     * @param size the number of tasks left in the list
     * @return a message indicating the task has been deleted
     */
    public String deleteItemMessage(Tasks task, int size) {
        return "Ok I have removed the following item\n" + task
                + "\n" + "You have " + size + " tasks left";
    }

    /**
     * Returns a message indicating that there is no data available and a new TaskList is being created.
     *
     * @return a message indicating there is no data available
     */
    public String loadingError() {
        return "You don't have any data right now. Let me create a new TaskList";
    }

    /**
     * Returns the list of tasks as a string.
     *
     * @param list the string representation of the task list
     * @return the list of tasks
     */
    public String produceList(String list) {
        return list;
    }

    /**
     * Returns a message indicating that a task has been added to the list and shows the number of tasks.
     *
     * @param task the task that has been added
     * @param size the number of tasks in the list
     * @return a message indicating the task has been added
     */
    public String addList(Tasks task, int size) {
        assert size >= 0 : "Size of list should be non-negative";
        return "added: " + task + "\n" + "You have " + size + " tasks in the list";
    }

    /**
     * Returns a welcome message when the application starts.
     *
     * @return a welcome message
     */
    public String showWelcomeMessage() {
        return "Hello! I'm BeeBoo\nWhat can I do for you?";
    }

    /**
     * Returns a farewell message when the user exits the application.
     *
     * @return a farewell message
     */
    public String byeMessageMessage() {
        return "Bye. Hope to see you again soon!";
    }


    /**
     * Reads and returns the next line of user input, converting it to lowercase and trimming whitespace.
     *
     * @return the user input as a trimmed and lowercase string
     */
    public String handleCommand() {
        if (input == null) { // Ensure Scanner is initialized
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
