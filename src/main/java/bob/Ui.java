package bob;

/**
 * Represents the user interface for the chatbot application.
 * This class provides methods to display messages and updates to the user.
 */
public class Ui {

    /**
     * Displays a welcome message to the user, including the chatbot's name.
     *
     * @param botName The name of the chatbot to include in the welcome message.
     */
    public void showWelcomeMessage(String botName) {
        System.out.println("____________________________________________________________");
        System.out.printf("Hello! I'm %s!\n", botName);
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void showGoodbyeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a horizontal line for visual separation in the user interface.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a message indicating that a task has been added to the list.
     *
     * @param task The task that was added.
     * @param size The current size of the task list after addition.
     */
    public void showTaskAdded(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.printf("  %s\n", task);
        System.out.printf("Now you have %d tasks in the list.\n", size);
    }

    /**
     * Displays a message indicating that a task has been deleted from the list.
     *
     * @param deleted The message describing the deleted task.
     * @param size The current size of the task list after deletion.
     */
    public void showTaskDeleted(String deleted, int size) {
        System.out.println(deleted);
        System.out.printf("Now you have %d tasks in the list.\n", size);
    }

    /**
     * Displays an error message to the user.
     *
     * @param errorMessage The error message to be displayed.
     */
    public void showError(String errorMessage) {
        System.out.println(" Error: " + errorMessage);
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param mark The message describing the marked task.
     */
    public void showTaskMarked(String mark) {
        System.out.println(mark);
    }

    /**
     * Displays a message indicating that a task has been unmarked.
     *
     * @param unmark The message describing the unmarked task.
     */
    public void showTaskUnmarked(String unmark) {
        System.out.println(unmark);
    }
}
