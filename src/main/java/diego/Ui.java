package diego;

/**
 * Handles user interface interactions for the Diego application.
 */
public class Ui {

    /**
     * Displays the welcome message to the user.
     */
    public void showWelcomeMessage() {
        System.out.println("""
            ____________________________________________________________
            Hello! I'm Diego
            What can I do for you?
            ____________________________________________________________
            """);
    }

    /**
     * Displays the goodbye message to the user.
     */
    public void showGoodbyeMessage() {
        System.out.println("""
            ____________________________________________________________
            Bye. Hope to see you again soon!
            ____________________________________________________________
            """);
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks The task list to be displayed.
     */
    public void showTaskList(TaskList tasks) {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + ". " + tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a message when a task is marked as done.
     *
     * @param task The task that has been marked as done.
     */
    public void showTaskMarked(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a message when a task is unmarked as not done.
     *
     * @param task The task that has been unmarked.
     */
    public void showTaskUnmarked(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println("Ok! I've marked this task as not done:");
        System.out.println(task);
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a message when a task is added to the task list.
     *
     * @param task      The task that has been added.
     * @param taskCount The current number of tasks in the task list.
     */
    public void showTaskAdded(Task task, int taskCount) {
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list%n", taskCount);
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a message when a task is deleted from the task list.
     *
     * @param task      The task that has been deleted.
     * @param taskCount The current number of tasks in the task list.
     */
    public void showTaskDeleted(Task task, int taskCount) {
        System.out.println("____________________________________________________________");
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list%n", taskCount);
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Displays a loading error message when tasks cannot be loaded.
     */
    public void showLoadingError() {
        System.out.println("Something went wrong while loading tasks.");
    }

    /**
     * Displays the list of tasks that match the search keyword.
     *
     * @param tasks The task list containing the matching tasks.
     */
    public void showFoundTasks(TaskList tasks) {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + ". " + tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }
}
