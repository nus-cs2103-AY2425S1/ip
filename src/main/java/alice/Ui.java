package alice;

/**
 * Represents the user interface for the application.
 * Provides methods to display messages to the user.
 */
public class Ui {
    private static final String name = "Alice";
    private static final String line =
            "____________________________________________________________";

    /**
     * Lists all tasks in the given task list.
     *
     * @param list The task list to be displayed.
     */
    public void listTasks(TaskList list) {
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
        System.out.println(list.toString());
        System.out.println(line);
    }

    /**
     * Greets the user.
     */
    public void greet() {
        System.out.println(line);
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

    /**
     * Displays a message when the user exits the application.
     */
    public void exit() {
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }

    /**
     * Displays the command format for adding a todo item.
     */
    public void todoMsg() {
        System.out.println(line);
        System.out.println("Command Format: todo [description]");
        System.out.println(line);
    }

    /**
     * Displays the command format for adding an event.
     */
    public void eventMsg() {
        System.out.println(line);
        System.out.println("Command Format: event [description] /from [dd-MM-yyyy HHmm] /to [dd-MM-yyyy HHmm]");
        System.out.println(line);
    }

    /**
     * Displays the command format for adding a deadline.
     */
    public void deadlineMsg() {
        System.out.println(line);
        System.out.println("Command Format: deadline [description] /by [dd-MM-yyyy HHmm]");
        System.out.println(line);
    }

    /**
     * Displays an error message for invalid commands.
     */
    public void invalidMsg() {
        System.out.println(line);
        System.out.println("Invalid command, use command words: list, todo, deadline, event, mark, unmark & delete");
        System.out.println(line);
    }

    /**
     * Displays a message when a task is added to the list.
     *
     * @param task The task that was added.
     * @param size The new size of the task list.
     */
    public void addToListMsg(Task task, int size) {
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(line);
    }

    /**
     * Displays an error message when adding a task fails.
     */
    public void addFailMsg() {
        System.out.println("Fail to add the task: wrong format");
    }

    /**
     * Displays a message when a task is marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void markMsg(Task task) {
        System.out.println(line);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
        System.out.println(line);
    }

    /**
     * Displays an error message for an invalid number.
     */
    public void invalidNumMsg() {
        System.out.println(line);
        System.out.println("Invalid number");
        System.out.println(line);
    }

    /**
     * Displays a message when a task is unmarked.
     *
     * @param task The task that was unmarked.
     */
    public void unMarkMsg(Task task) {
        System.out.println(line);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.toString());
        System.out.println(line);
    }

    /**
     * Displays a message when a task is deleted from the list.
     *
     * @param task The task that was deleted.
     * @param size The new size of the task list.
     */
    public void deleteMsg(Task task, int size) {
        System.out.println(line);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println(" Now you have " + size + " tasks in the list.");
        System.out.println(line);
    }

    /**
     * Displays a list of tasks that match a search query.
     *
     * @param list The list of matching tasks.
     */
    public void findMsg(TaskList list) {
        System.out.println(line);
        System.out.println("Here are the matching tasks in your list:");
        System.out.println(list.toString());
        System.out.println(line);
    }

    /**
     * Displays a message when no tasks match the search query.
     */
    public void noFindMsg() {
        System.out.println(line);
        System.out.println("No matching tasks in your list");
        System.out.println(line);
    }
}
