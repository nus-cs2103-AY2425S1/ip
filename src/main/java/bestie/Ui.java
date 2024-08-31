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
     *
     * @param sc Input scanner.
     */
    public Ui(Scanner sc) {
        this.sc = sc;
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
    public void welcome() {
        // greet user at the start
        System.out.println("Hello! I'm bestie.Bestie, your personal assistant chatbot.");
        System.out.println("Let's get ready to have a productive day!");
        System.out.println("What can I do for you today :)?");
    }

    /**
     * Prints goodbye message when user exits the chatbot.
     */
    public void byeBye() {
        System.out.println("Bye. Hope to see you again soon! :)");
    }

    /**
     * Prints nicely formatted tasks in the user's task list.
     * Each task is preceded by its index in the list, the type of task and whether it has been completed.
     *
     * @param tasks Tasks in the user's list of tasks.
     */
    public void displayTasks(ArrayList<Task> tasks) {
        System.out.println("Sure! Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            int index = i + 1;
            System.out.println(index + "." + tasks.get(i).toString());
        }
    }

    /**
     * Prints message that is displayed to show that a task has been successfully added to user's task list.
     *
     * @param task Task to be added to the user's task list. Includes the type of task and deadline, if applicable.
     * @param size Number of tasks in the task list, after the new task has been added.
     */
    public void showTaskAdded(Task task, int size) {
        System.out.println("added: " + task.toString());
        System.out.println("Now you have " + size + " tasks in your list.");
    }

    /**
     * Prints message after task has been marked, to show that task has been marked.
     *
     * @param task Task that user wants to mark as completed.
     */
    public void showTaskMarked(Task task) {
        System.out.println("Awesome work! I've marked this task as done.");
        System.out.println("  " + task.toString());
    }

    /**
     * Prints message to show that user has successfully unmarked a task in the task list.
     *
     * @param task  Task that user wants to mark as undone.
     */
    public void showTaskUnmarked(Task task) {
        System.out.println("Noted! I've marked this task as not done yet:");
        System.out.println("  " + task.toString());
    }

    /**
     * Prints message to show that task has been successfully deleted.
     * Displays remaining number of tasks in the user's task list.
     *
     * @param size Number of tasks remaining in user's task list.
     */
    public void showTaskDeleted(int size) {
        System.out.println("Noted! The task has been removed.");
        if (size == 1) {
            System.out.println("You now have 1 task in your list.");
        } else {
            System.out.println("You now have " + size + " tasks in your list.");
        }
    }

    /**
     * Prints message to show that user has tried to perform a command on a task that is out of bounds.
     *
     * @param taskSize Number of tasks in the list of tasks.
     */
    public void showIndexOutOfBoundsMessage(int taskSize, TaskList tasks) {
        if (taskSize == 1) {
            System.out.println("That task does not exist. There is only 1 task in your list!");
        } else {
            System.out.println("That task does not exist. There are only " + tasks.size()
                    + " tasks in your list!");
        }
        System.out.println("Please key in a valid index.");
    }

    /**
     * Prints message that user has keyed in an invalid command, that is not one of the accepted commands.
     */
    public void invalidCommand() {
        System.out.println("Invalid command! Please remember to start with \"todo\", \"deadline\" "
                + "or \"event\".\nDouble check your spelling for other common commands like \"unmark\" or \"list\".");
    }

    /**
     * Prints list of tasks that having description matching the keyword entered by user.
     *
     * @param tasks List of all of user's tasks.
     */
    public void showFoundTasks(ArrayList<Task> tasks) {
        System.out.println("Here are the matching tasks in your list: ");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
    }

}
