package bestie;

import java.util.ArrayList;
import java.util.Scanner;

import bestie.task.Task;

/**
 * Deals with interactions with the user.
 * Primarily prints chatbot responses to the console.
 */
public class Ui {

    private Scanner sc;

    /**
     * Creates an instance of the object that displays messages to the user on the console.
     */
    public Ui() {
    }

    /**
     * Reads the next input command by the user.
     *
     * @return Next line of input command from the user.
     */
    public String readNextCommand() {
        return sc.nextLine();
    }

    /**
     * Prints the welcome message when chatbot is first started.
     */
    public String greetUser() {
        // greet user at the start
        return ("Hello! I'm bestie, your personal assistant chatbot.\n"
                + "Let's get ready to have a productive day!\n"
                + "What can I do for you today :)?");
    }

    /**
     * Prints goodbye message when user exits the chatbot.
     */
    public String sayGoodbye() {
        return ("Bye. Hope to see you again soon! :)");
    }

    /**
     * Prints nicely formatted tasks in the user's task list.
     * Each task is preceded by its index in the list, the type of task and whether it has been completed.
     *
     * @param tasks Tasks in the user's list of tasks.
     */
    public String displayTasks(ArrayList<Task> tasks) {
        String output = "Sure! Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            int index = i + 1;
            // string concatenation returns a new string, must reassign
            output = output.concat(index + "." + tasks.get(i).toString() + "\n");
        }
        return output;
    }

    /**
     * Prints message that is displayed to show that a task has been successfully added to user's task list.
     *
     * @param task Task to be added to the user's task list. Includes the type of task and deadline, if applicable.
     * @param size Number of tasks in the task list, after the new task has been added.
     */
    public String showTaskAdded(Task task, int size) {
        return ("added: " + task.toString() + "\n"
                + "Now you have " + size + " tasks in your list.");
    }

    /**
     * Prints message after task has been marked, to show that task has been marked.
     *
     * @param task Task that user wants to mark as completed.
     */
    public String showTaskMarked(Task task) {
        return ("Awesome work! I've marked this task as done.\n"
                + "  " + task.toString());
    }

    /**
     * Prints message to show that user has successfully unmarked a task in the task list.
     *
     * @param task  Task that user wants to mark as undone.
     */
    public String showTaskUnmarked(Task task) {
        return ("Noted! I've marked this task as not done yet:\n"
                + "  " + task.toString());
    }

    /**
     * Prints message to show that task has been successfully deleted.
     * Displays remaining number of tasks in the user's task list.
     *
     * @param size Number of tasks remaining in user's task list.
     */
    public String showTaskDeleted(int size) {
        String output = "Noted! The task has been removed.\n";
        if (size == 1) {
            output = output.concat("You now have 1 task in your list.\n");
        } else {
            output = output.concat("You now have " + size + " tasks in your list.\n");
        }
        return output;
    }

    /**
     * Prints message to show that user has tried to perform a command on a task that is out of bounds.
     *
     * @param taskSize Number of tasks in the list of tasks.
     */
    public String showIndexOutOfBoundsMessage(int taskSize, TaskList tasks) {
        String output = "";
        if (taskSize == 1) {
            output = output.concat("That task does not exist. There is only 1 task in your list!\n");
        } else {
            output = output.concat("That task does not exist. There are only " + tasks.size()
                    + " tasks in your list!\n");
        }
        output = output.concat("Please key in a valid index.\n");
        return output;
    }

    /**
     * Prints message that user has keyed in an invalid command, that is not one of the accepted commands.
     */
    public String invalidCommand() {
        return ("Invalid command! Please remember to start with \"todo\", \"deadline\" "
                + "or \"event\".\nDouble check your spelling for other common commands like \"unmark\" or \"list\".\n");
    }

    /**
     * Prints list of tasks that having description matching the keyword entered by user.
     *
     * @param tasks List of all of user's tasks.
     */
    public String showFoundTasks(ArrayList<Task> tasks) {
        String output = "Here are the matching tasks in your list: \n";
        for (int i = 0; i < tasks.size(); i++) {
            output = output.concat((i + 1) + ". " + tasks.get(i).toString() + "\n");
        }
        return output;
    }

}
