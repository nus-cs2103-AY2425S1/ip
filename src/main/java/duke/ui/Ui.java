package duke.ui;

import duke.task.Task;

import java.util.List;

/**
 * Handles the user interface of the application.
 * This class is responsible for displaying various messages to the user,
 * including greetings, task lists, error messages, and more.
 */
public class Ui {
    /**
     * Generates a greeting message and instructions on how to interact with the application.
     *
     * @return The greeting and instructions as a string.
     */
    public String showGreeting() {
        String greeting = "   *        *        *        __o    *       *\n"
                + "*      *       *        *    /_| _     *\n"
                + "  FF  *    FF      *        O'_)/ \\  *    *\n"
                + "  o')____  o')____    __*   V   \\  ) __  *\n"
                + "   \\ ___ )--\\ ___ )--( (    (___|__)/ /*     *\n"
                + " *  |   |    |   |  * \\ \\____| |___/ /  *\n"
                + "    |*  |    |   |     \\____________/       *\n";
        return "Hello, I am Rudolf, Santa's trusty red-nose reindeer. Christmas is nearing, so I am here to help you with your Christmas preparations.\n"
                + "Here's how you can chat with me:\n"
                + "1. Add a duke.task.ToDo: todo <description>\n"
                + "2. Add a duke.task.Deadline: deadline <description> /by <date/time>\n"
                + "3. Add an duke.task.Event: event <description> /from <start date/time> /to <end date/time>\n"
                + "4. List all tasks: list\n"
                + "5. Mark a task as done: mark <task number>\n"
                + "6. Unmark a task: unmark <task number>\n"
                + "7. Delete a task: delete <task number>\n"
                + "8. Find tasks by keyword: find <keyword>\n"
                + "9. Sort tasks by date: list sorted by date\n"
                + "10. Sort tasks by description: list sorted by description\n"
                + "11. Exit: bye\n" + greeting;
    }

    /**
     * Generates the list of tasks to the user.
     * If the task list is empty, a message indicating no tasks is displayed.
     *
     * @param tasks The list of tasks to be displayed.
     * @return The list of tasks or no tasks message as a string.
     */
    public String showTaskList(List<Task> tasks) {
        if (tasks.isEmpty()) {
            return "Ho Ho Ho! No corresponding tasks in your list yet. Add some tasks to get started.\n";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Ho Ho Ho! Here are the tasks in your Christmas list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                sb.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
            }
            return sb.toString();
        }
    }

    /**
     * Generates a message indicating a task has been added.
     *
     * @param task The task that was added.
     * @param size The current number of tasks in the list.
     * @return The task added message as a string.
     */
    public String showTaskAdded(Task task, int size) {
        return "Gotcha. I've added this task:\n"
                + "  " + task + "\n"
                + "Now you have " + size + " tasks in the list. Feliz Navidad!\n";
    }

    /**
     * Generates a message indicating a task has been marked as done.
     *
     * @param task The task that was marked as done.
     * @return The task marked message as a string.
     */
    public String showTaskMarked(Task task) {
        return "Sleigh! I've marked this task as done:\n"
                + "  " + task + "\n";
    }

    /**
     * Generates a message indicating a task has been marked as not done.
     *
     * @param task The task that was marked as not done.
     * @return The task unmarked message as a string.
     */
    public String showTaskUnmarked(Task task) {
        return "Alright-o, I've marked this task as not done yet:\n"
                + "  " + task + "\n";
    }

    /**
     * Generates a message indicating a task has been deleted.
     *
     * @param task The task that was deleted.
     * @param size The current number of tasks in the list.
     * @return The task deleted message as a string.
     */
    public String showTaskDeleted(Task task, int size) {
        return "Aww okay. I've removed this task:\n"
                + "  " + task + "\n"
                + "Now you have " + size + " tasks in the list. Let it snow!\n";
    }

    /**
     * Generates a farewell message when the user exits the application.
     *
     * @return The farewell message as a string.
     */
    public String showGoodbyeMessage() {
        String festiveMessage =
                "Merry★* 。 • ˚ ˚ ˛ ˚ ˛ •\n" +
                        "•。★Christmas★ 。* 。\n" +
                        "° 。 ° ˛˚˛ * Π__。˚\n" +
                        "˚ ˛ •˛•˚ */__/~＼。˚ ˚ ˛\n" +
                        "˚ ˛ •˛• ˚ ｜ 田田 ｜門｜ ˚\n" +
                        "And a happy new year!";
        return "Bye~ Hope to see you again!\n" + festiveMessage + "\n";
    }

    /**
     * Generates a message when an unknown command is entered.
     *
     * @return The unknown command message as a string.
     */
    public String showUnknownCommand() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(\n";
    }

    /**
     * Generates a suggestion for the correct format of a command when an invalid command is entered.
     *
     * @param message The suggested command format.
     * @return The suggestion message as a string.
     */
    public String showSuggestion(String message) {
        return "Sorry, I don't understand. Did you mean: " + message + "\n";
    }

    /**
     * Generates an error message with a specific message.
     *
     * @param message The error message to be displayed.
     * @return The error message as a string.
     */
    public String showErrorMessage(String message) {
        return message + "\n";
    }

    /**
     * Generates an error message when there is an issue saving tasks.
     *
     * @param message The error message to be displayed.
     * @return The save error message as a string.
     */
    public String showSaveError(String message) {
        return "Jingling bells! It seems like an error was encountered while saving tasks: " + message + "\n";
    }

    /**
     * Generates a message when tasks matching a keyword are found.
     *
     * @param tasks The list of tasks found.
     * @return The found tasks message as a string.
     */
    public String showFoundTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            return "Aww popsicles! No matching tasks found.\n";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Hooray! The elves found these matching tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                sb.append((i + 1)).append(".").append(tasks.get(i)).append("\n");
            }
            return sb.toString();
        }
    }
}