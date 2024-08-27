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
     * Displays a greeting message and instructions on how to interact with the application.
     */
    public void showGreeting() {
        String greeting = "   *        *        *        __o    *       *\n"
                + "*      *       *        *    /_| _     *\n"
                + "  FF  *    FF      *        O'_)/ \\  *    *\n"
                + "  o')____  o')____    __*   V   \\  ) __  *\n"
                + "   \\ ___ )--\\ ___ )--( (    (___|__)/ /*     *\n"
                + " *  |   |    |   |  * \\ \\____| |___/ /  *\n"
                + "    |*  |    |   |     \\____________/       *\n";
        String festiveMessage =
                "Merry★* 。 • ˚ ˚ ˛ ˚ ˛ •\n" +
                        "•。★Christmas★ 。* 。\n" +
                        "° 。 ° ˛˚˛ * Π__。˚\n" +
                        "˚ ˛ •˛•˚ */__/~＼。˚ ˚ ˛\n" +
                        "˚ ˛ •˛• ˚ ｜ 田田 ｜門｜ ˚\n" +
                        "And a happy new year!";
        System.out.println(greeting + "Hello, I am Rudolf, Santa's trusty red-nose reindeer");
        System.out.println("Christmas is nearing, and I am here to help you with your Christmas preparations.");
        System.out.println("Here's how you can chat with me:");
        System.out.println("1. Add a duke.task.ToDo: todo <description>");
        System.out.println("2. Add a duke.task.Deadline: deadline <description> /by <date/time>");
        System.out.println("3. Add an duke.task.Event: event <description> /from <start date/time> /to <end date/time>");
        System.out.println("4. List all tasks: list");
        System.out.println("5. Mark a task as done: mark <task number>");
        System.out.println("6. Unmark a task: unmark <task number>");
        System.out.println("7. Delete a task: delete <task number>");
        System.out.println("8. Exit: bye");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an error message when there is an issue loading previous tasks.
     */
    public void showLoadingError() {
        System.out.println("Error encountered while loading previous tasks.");
    }

    /**
     * Displays the list of tasks to the user.
     * If the task list is empty, a message indicating no tasks is displayed.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public void showTaskList(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Ho Ho Ho! No tasks in your list yet. Add some tasks to get started.");
        } else {
            System.out.println("Ho Ho Ho! Here are the tasks in your Christmas list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    /**
     * Displays a message indicating a task has been added.
     *
     * @param task The task that was added.
     * @param size The current number of tasks in the list.
     */
    public void showTaskAdded(Task task, int size) {
        System.out.println("Gotcha. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list. Feliz Navidad!");
    }

    /**
     * Displays a message indicating a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void showTaskMarked(Task task) {
        System.out.println("Sleigh! I've marked this task as done:");
        System.out.println("  " + task);
    }

    /**
     * Displays a message indicating a task has been marked as not done.
     *
     * @param task The task that was marked as not done.
     */
    public void showTaskUnmarked(Task task) {
        System.out.println("Alright-o, I've marked this task as not done yet:");
        System.out.println("  " + task);
    }

    /**
     * Displays a message indicating a task has been deleted.
     *
     * @param task The task that was deleted.
     * @param size The current number of tasks in the list.
     */
    public void showTaskDeleted(Task task, int size) {
        System.out.println("Aww okay. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list. Let it snow!");
    }

    /**
     * Displays a farewell message when the user exits the application.
     */
    public void showGoodbyeMessage() {
        String festiveMessage =
                "Merry★* 。 • ˚ ˚ ˛ ˚ ˛ •\n" +
                        "•。★Christmas★ 。* 。\n" +
                        "° 。 ° ˛˚˛ * Π__。˚\n" +
                        "˚ ˛ •˛•˚ */__/~＼。˚ ˚ ˛\n" +
                        "˚ ˛ •˛• ˚ ｜ 田田 ｜門｜ ˚\n" +
                        "And a happy new year!";
        System.out.println("Bye~ Hope to see you again!\n" + festiveMessage);
    }

    /**
     * Displays a message when an unknown command is entered.
     */
    public void showUnknownCommand() {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Displays a suggestion for the correct format of a command when an invalid command is entered.
     *
     * @param message The suggested command format.
     */
    public void showSuggestion(String message) {
        System.out.println("Sorry, I don't understand. Did you mean: " + message);
    }

    /**
     * Displays an error message with a specific message.
     *
     * @param message The error message to be displayed.
     */
    public void showErrorMessage(String message) {
        System.out.println(message);
    }

    /**
     * Displays an error message when there is an issue saving tasks.
     *
     * @param message The error message to be displayed.
     */
    public void showSaveError(String message) {
        System.out.println("Jingling bells! It seems like an error was encountered while saving tasks: " + message);
    }
}

