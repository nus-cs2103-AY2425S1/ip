package hannah;

import java.util.List;
import java.util.Scanner;

import hannah.task.Task;
import hannah.task.TaskList;

/**
 * The Ui class handles all interactions with the user, including displaying messages and reading input.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Initializes a new Ui object and sets up the scanner for reading user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Returns a welcome message when the program starts.
     */
    public String showWelcomeMessage() {
        return "Hello! I'm Hannah.\n"
                + " What can I do for you?\n";
    }

    /**
     * Returns a goodbye message when the program ends.
     */
    public String showGoodbyeMessage() {
        return " Bye. Hope to see you again soon!\n";
    }

    /**
     * Returns all tasks currently in the list as a string.
     *
     * @param tasks The TaskList containing the tasks to be displayed.
     */
    public String showTasks(TaskList tasks) {
        StringBuilder sb = new StringBuilder("Tasks on your list!\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.getTasks().get(i).toString()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Returns a message indicating that a task has been added.
     *
     * @param task      The task that was added.
     * @param taskCount The current number of tasks in the list.
     */
    public String showTaskAdded(Task task, int taskCount) {
        return "Added task!\n"
                + task.toString() + "\n"
                + "You now have " + taskCount + " tasks in your list.";
    }

    /**
     * Returns a message indicating that a task has been deleted.
     *
     * @param taskCount The current number of tasks in the list.
     * @param task      The task that was deleted.
     */
    public String showTaskDeleted(int taskCount, Task task) {
        return "Okay, will remove the task:\n"
                + task.toString() + "\n"
                + "You now have " + taskCount + " tasks in your list.";
    }

    /**
     * Returns a message indicating that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public String showTaskMarked(Task task) {
        return "Good job! Task marked as done!\n" + task.toString();
    }

    /**
     * Returns a message indicating that a task has been unmarked (marked as not done).
     *
     * @param task The task that was unmarked.
     */
    public String showTaskUnmarked(Task task) {
        return "Okay, task unmarked!\n" + task.toString();
    }

    /**
     * Returns an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public String showErrorMessage(String message) {
        return "____________________________________________________________\n"
                + message + "\n"
                + "____________________________________________________________";
    }

    /**
     * Reads the user's command from the input.
     *
     * @return The user's input as a String.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Extracts the task number from the user's input.
     *
     * @param userInput The user's input string.
     * @return The task number extracted from the input.
     */
    public int getTaskNumber(String userInput) {
        String[] parts = userInput.split(" ");
        int taskNumber = 0;
        if (parts.length > 1) {
            taskNumber = Integer.parseInt(parts[1]);
        }
        return taskNumber;
    }

    /**
     * Extracts and returns the keyword from the user input.
     *
     * This method splits the user input by spaces and returns the second part
     * which is assumed to be the keyword for searching tasks.
     *
     * @param userInput The full user input string entered by the user.
     * @return The keyword extracted from the input string.
     */
    public String getKeyword(String userInput) {
        String[] parts = userInput.split(" ");
        return parts[1];
    }

    /**
     * Returns the tasks that match the specified keyword as a string.
     *
     * This method searches through the given list of tasks using the specified keyword and
     * returns all matching tasks with their respective task numbers.
     *
     * @param tasks   The TaskList object containing the list of tasks to search through.
     * @param keyword The keyword to search for within the task names.
     */
    public String showFindResults(TaskList tasks, String keyword) {
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        List<Task> matchingTasks = tasks.findTasks(keyword);
        for (int i = 0; i < matchingTasks.size(); i++) {
            sb.append((i + 1)).append(". ").append(matchingTasks.get(i).toString()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Returns a string containing the help message with all the available commands.
     *
     * The help message includes details on how to use the following commands:
     * - list: Lists all tasks.
     * - todo: Adds a new todo task.
     * - deadline: Adds a new deadline task with a specified date.
     * - event: Adds a new event task with a specified date.
     * - mark/unmark: Marks or unmarks a task as done.
     * - delete: Deletes a task based on its task number.
     * - help: Displays the help message.
     * - bye: Exits the application.
     *
     * @return A string that contains the help message with command descriptions.
     */
    public String getHelpMessage() {
        StringBuilder helpMessage = new StringBuilder();
        helpMessage.append("Here are the available commands:\n\n");
        helpMessage.append("list - Lists all tasks\n");
        helpMessage.append("todo [task] - Adds a new todo\n");
        helpMessage.append("deadline [task] /by [date] - Adds a new deadline task\n");
        helpMessage.append("event [task] /at [date] - Adds a new event task\n");
        helpMessage.append("mark/unmark [task number] - Marks/unmarks a task as done\n");
        helpMessage.append("delete [task number] - Deletes a task\n");
        helpMessage.append("help - Shows this help message\n");
        helpMessage.append("bye - Exits the application\n");

        return helpMessage.toString();
    }


}
