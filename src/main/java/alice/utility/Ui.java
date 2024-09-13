package alice.utility;

import alice.task.Task;

/**
 * Represents the user interface for the application.
 * Provides methods to display messages to the user.
 */
public class Ui {
    private static final String name = "Alice";
    private static final String line =
            "__________________________________________________";

    /**
     * Lists all tasks in the given task list.
     *
     * @param list The task list to be displayed.
     */
    public String listTasks(TaskList list) {
        String string = line + '\n'
                + "Here are the tasks in your list:" + '\n'
                + list.toString() + '\n'
                + line;
        return string;
    }

    /**
     * Greets the user.
     */
    public String greetMsg() {
        String string = line + '\n'
                + "Hello! I'm " + name + '\n'
                + "What can I do for you?" + '\n'
                + line;
        return string;
    }

    /**
     * Displays a message when the user exits the application.
     */
    public String exitMsg() {
        String string = line + '\n'
                + "Bye. Hope to see you again soon!" + '\n'
                + line;
        return string;
    }

    /**
     * Displays the command format for adding a todo item.
     */
    public String todoMsg() {
        String string = line + '\n'
                + "Command Format: todo [description]" + '\n'
                + line;
        return string;
    }

    /**
     * Displays the command format for adding an event.
     */
    public String eventMsg() {
        String string = line + '\n'
                + "Command Format: event [description] /from [dd-MM-yyyy HHmm] /to [dd-MM-yyyy HHmm]" + '\n'
                + line;
        return string;
    }

    /**
     * Displays the command format for adding a deadline.
     */
    public String deadlineMsg() {
        String string = line + '\n'
                + "Command Format: deadline [description] /by [dd-MM-yyyy HHmm]" + '\n'
                + line;
        return string;
    }

    /**
     * Displays an error message for invalid commands.
     */
    public String invalidMsg() {
        String string = line + '\n'
                + "Invalid command, use command words: list, todo, deadline, event, mark, unmark & delete" + '\n'
                + line;
        return string;
    }

    /**
     * Displays a message when a task is added to the list.
     *
     * @param task The task that was added.
     * @param size The new size of the task list.
     */
    public String addToListMsg(Task task, int size) {
        String string = line + '\n'
                + "Got it. I've added this task:" + '\n'
                + task.toString() + '\n'
                + "Now you have " + size + " tasks in the list." + '\n'
                + line;
        return string;
    }

    /**
     * Returns a formatted message indicating that the task already exists in the list.
     *
     * @return A string with a message about the duplicate task.
     */
    public String addDuplicateMsg() {
        String string = line + '\n'
                + "Task already existed in the list" + '\n'
                + line;
        return string;
    }

    /**
     * Displays an error message when adding a task fails.
     */
    public String addFailMsg() {
        String string = line + '\n'
                + "Fail to add the task: wrong format" + '\n'
                + line;
        return string;
    }

    /**
     * Displays a message when a task is marked as done.
     *
     * @param task The task that was marked as done.
     */
    public String markMsg(Task task) {
        String string = line + '\n'
                + "Nice! I've marked this task as done:" + '\n'
                + task.toString() + '\n'
                + line;
        return string;
    }

    /**
     * Displays an error message for an invalid number.
     */
    public String invalidNumMsg() {
        String string = line + '\n'
                + "Invalid number" + '\n'
                + line;
        return string;
    }

    /**
     * Displays a message when a task is unmarked.
     *
     * @param task The task that was unmarked.
     */
    public String unMarkMsg(Task task) {
        String string = line + '\n'
                + "OK, I've marked this task as not done yet:" + '\n'
                + task.toString() + '\n'
                + line;
        return string;
    }

    /**
     * Displays a message when a task is deleted from the list.
     *
     * @param task The task that was deleted.
     * @param size The new size of the task list.
     */
    public String deleteMsg(Task task, int size) {
        String string = line + '\n'
                + "Noted. I've removed this task:" + '\n'
                + task.toString() + '\n'
                + " Now you have " + size + " tasks in the list." + '\n'
                + line;
        return string;
    }

    /**
     * Displays a list of tasks that match a search query.
     *
     * @param list The list of matching tasks.
     */
    public String findMsg(TaskList list) {
        String string = line + '\n'
                + "Here are the matching tasks in your list:" + '\n'
                + list.toString() + '\n'
                + line;
        return string;
    }

    /**
     * Displays a message when no tasks match the search query.
     */
    public String noFindMsg() {
        String string = line + '\n'
                + "No matching tasks in your list" + '\n'
                + line;
        return string;
    }
}
