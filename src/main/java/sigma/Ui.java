package sigma;

import java.util.Scanner;

import sigma.exception.SigmaException;
import sigma.task.Task;
import sigma.task.TaskList;

/**
 * The {@code Ui} class handles all interactions with the user.
 * It is responsible for displaying messages, receiving user input, and
 * showing information related to tasks.
 */
public class Ui {
    private static final String LOGO =
            "   _____ _                       \n"
                    + "  / ____(_)                      \n"
                    + " | (___  _  __ _ _ __ ___   __ _ \n"
                    + "  \\___ \\| |/ _` | '_ ` _ \\ / _` |\n"
                    + "  ____) | | (_| | | | | | | (_| |\n"
                    + " |_____/|_|\\__, |_| |_| |_|\\__,_|\n"
                    + "            __/ |                \n"
                    + "           |___/                 \n";
    private Scanner scanner;


    /**
     * Constructs a {@code Ui} object and initializes the {@code Scanner} for reading user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads and returns the user's input after trimming any leading or trailing whitespace.
     *
     * @return The trimmed input from the user.
     */
    public String getInput() {
        return scanner.nextLine().trim();
    }

    /**
     * Closes the {@code Scanner} to free up resources.
     */
    public void closeScanner() {
        this.scanner.close();
    }

    /**
     * Displays the welcome message when the application starts.
     */
    public String showWelcome() {
        return "Skibidi bop bop! It's the legend27 himself, Sigma!\n"
                + LOGO + "\nReady to hit the grind and go sigma mode?";
    }

    /**
     * Displays an error message when a {@code SigmaException} is thrown.
     *
     * @param e The exception containing the error message.
     */
    public String showErrorMessage(SigmaException e) {
        return e.toString();
    }

    /**
     * Displays a goodbye message when the application is about to exit.
     */
    public String showGoodbye() {
        return "Skedaddling outta here, my dude! See you in the Matrix or when baby Gronk rizzes up Livvy Dunne!"
                + "\nExiting in 3...2...1...";
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks The list of tasks to display.
     */
    public String showList(TaskList tasks) {
        if (tasks.getSize() == 0) {
            return "Ayo, ain't no tasks on your sigma grindset, dawg. You're moving like a sussy imposter!";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.getSize(); i++) {
            sb.append(String.format("%d. %s\n", i + 1, tasks.getTask(i + 1)));
        }
        return "Yo, here's the list of your goon cave missions, king:\n" + sb + "Get that grind on, fam!";
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     */
    public String showMarkedTask(Task task) {
        return "Rizzed up and ready to go! Task has been marked as done like a true sigma:\n"
                + String.format("  %s", task);
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param task The task that has been marked as not done.
     */
    public String showUnmarkedTask(Task task) {
        return "L, blud. I've marked this task as not done yet:\n" + String.format("  %s", task);
    }

    /**
     * Displays a message indicating that a task has been added to the list.
     *
     * @param task The task that has been added.
     * @param numberOfTasks The current number of tasks in the list.
     */
    public String showAddedTask(Task task, int numberOfTasks) {
        return "Ayy, task added to the grindset! Check it out:\n" + String.format("  %s\n", task)
                + "You are now dripping with rizz: " + numberOfTasks + " tasks in your epic collection.";
    }

    /**
     * Displays a message indicating that a task has been removed from the list.
     *
     * @param task The task that has been removed.
     * @param numberOfTasks The current number of tasks in the list.
     */
    public String showDeletedTask(Task task, int numberOfTasks) {
        return "Alright, I’ve yeeted this task out of your list:\n" + String.format("  %s\n", task)
                + "You’re down to " + numberOfTasks + " tasks now — keep hustling, champ!";
    }

    /**
     * Updates the specified task and shows the updated details.
     *
     * @param task The task that has been updated.
     * @param taskNumber The number assigned to the updated task.
     * @return A message confirming the task update with its number and details.
     */
    public String showUpdatedTask(Task task, int taskNumber) {
        return String.format("Ayo, task number %d just got a major glow-up:\n  %s\n",
                taskNumber, task);
    }

    /**
     * Displays the tasks that match a search query.
     *
     * @param tasks The list of matching tasks.
     */
    public String showSearchedTasks(TaskList tasks) {
        if (tasks.getSize() == 0) {
            return "Ayo, no matching tasks found, fam. Looks like the search was a total flop!";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.getSize(); i++) {
            sb.append(String.format("%d. %s\n", i + 1, tasks.getTask(i + 1)));
        }

        return "Boom! Check out these epic tasks that match your search quest:\n" + sb;
    }

    /**
     * Returns a help message containing a list of all available commands with brief
     * descriptions of their usage. This method provides guidance on how to use the
     * commands supported by the application.
     *
     * @return A string listing all commands and their descriptions.
     */
    public String showHelp() {
        return "List of Commands:\n"
               + "1. bye: Exits the program. Use this command when you want to end your session.\n\n"
               + "2. list: Displays all tasks currently in your task list, including their status "
               + "(completed or not completed).\n\n"
               + "3. mark <task number>: Marks the specified task as completed. Replace <task number> with the number "
               + "of the task you wish to mark.\n\n"
               + "4. unmark <task number>: Unmarks the specified task, setting it back to not completed. Replace "
               + "<task number> with the number of the task you wish to unmark.\n\n"
               + "5. todo <description>: Creates a new todo task with the given description. This command is used to "
               + "add simple tasks without deadlines.\n\n"
               + "6. deadline <description> /by <date>: Creates a deadline task with the specified description and a "
               + "due date. Use /by followed by the date to set when the task should be completed (e.g., deadline "
               + "Finish report /by 2024-08-31).\n\n"
               + "7. event <description> /from <date> /to <date>: Creates an event task with the given description "
               + "and a time frame. Use /from to indicate the start date and /to to indicate the end date (e.g., event "
               + "Team meeting /from 2024-09-01 /to 2024-09-02).\n\n"
               + "8. delete <task number>: Removes the specified task from your list. Replace <task number> with the "
               + "number of the task you want to delete.\n\n"
               + "9. find <keyword>: Searches your task list for tasks that contain the specified keyword and displays "
               + "matching tasks.\n\n"
               + "10. help: Displays a list of all available commands with brief descriptions. Use this command "
               + "whenever you need a quick reminder of what commands are available.\n\n";
    }
}
