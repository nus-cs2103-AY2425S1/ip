package donna;

import donna.task.Task;

import java.util.List;

/**
 * Contains user interface methods for interacting with the application.
 * Handles displaying messages, task lists, and error information to the user.
 */
public class Ui {

    static private void printDashedLine() {
        System.out.println("____________________________________________________________________");
    }
    static private void printDonnaLogo() {
        System.out.println(" ____      ");
        System.out.println("|  _ \\  ___  _ __  _ __   __ _ ");
        System.out.println("| | | |/ _ \\| '_ \\| '_ \\ / _` |");
        System.out.println("| |_| | (_) | | | | | | | (_| |");
        System.out.println("|____/ \\___/|_| |_|_| |_|\\__,_|");
        System.out.println();
    }

    /**
     * Prints a greeting message to the user.
     *
     * @param dataWasLoaded True if any previously saved tasks were loaded; false otherwise.
     */
    public void printGreeting(boolean dataWasLoaded) {
        printDashedLine();
        printDonnaLogo();
        System.out.println("Hello! I'm Donna");
        if (dataWasLoaded) {
            System.out.println("We have had a chat before! Let's resume :)");
        } else {
            System.out.println("What can I do for you?");
        }
        printDashedLine();
    }

    /**
     * Prints a goodbye message to the user.
     */
    public void printGoodbyeMessage() {
        printDashedLine();
        System.out.println("Bye. Hope to see you again soon!");
        printDashedLine();
    }

    /**
     * Prints a message confirming that a task has been added.
     *
     * @param task The task that was added.
     * @param taskCount The current number of tasks in the list.
     */
    public void printTaskAddedMessage(Task task, int taskCount) {
        printDashedLine();
        System.out.println("Got it. I've added this task: " );
        System.out.println("    " + task);
        if (taskCount == 1) {
            System.out.println("This is the first task in the list. ");
        } else {
            System.out.println("You now have " + taskCount + " tasks in the list. ");
        }
        printDashedLine();
    }

    /**
     * Prints a message confirming that a task has been deleted.
     *
     * @param task The task that was deleted.
     * @param taskCount The current number of tasks in the list.
     */
    public void printTaskDeletedMessage(Task task, int taskCount) {
        printDashedLine();
        System.out.println("Alright. The following task has been deleted: ");
        System.out.println("    " + task);
        if (taskCount != 1){
            System.out.println("You now have " + taskCount + " tasks in the list.");
        } else {
            System.out.println("You now have 1 task left in the list.");
        }
        printDashedLine();
    }

    /**
     * Prints a message confirming the status of a task.
     *
     * @param task The task that was marked.
     * @param isMarked True if the task is marked as done; false otherwise.
     */
    public void printTaskMarkedMessage(Task task, boolean isMarked) {
        printDashedLine();
        if (isMarked) {
            System.out.println("Nice! I've marked this task as done: ");
        } else {
            System.out.println("OK, I have marked this task as not done yet: ");
        }
        System.out.println("    " + task);
        printDashedLine();
    }

    /**
     * Prints the list of all tasks.
     *
     * @param tasks The TaskList containing tasks to be displayed.
     */
    public void printTaskList(TaskList tasks) {
        printDashedLine();
        if (tasks.isEmpty()) {
            System.out.println("No tasks added to the list yet."+ "\n"
                    + "use todo / deadline / event to add tasks to the list!");

        } else {
            System.out.println("There are " + tasks.size() + " task(s) in the list: ");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
        printDashedLine();
    }

    /**
     * Prints an error message.
     *
     * @param message The error message to be printed.
     */
    public void printErrorMessage(String message) {
        printDashedLine();
        System.out.println(message);
        printDashedLine();
    }

    /**
     * Prints the results of a search query.
     *
     * @param tasks The list of tasks that match the search.
     */
    public void printFindResults(List<Task> tasks) {
        printDashedLine();
        if (tasks.isEmpty()) {
            System.out.println("No tasks found matching the search criteria :(");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        }
        printDashedLine();
    }
}
