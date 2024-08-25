package oliver;

import java.util.Scanner;

/**
 * Deals with interactions with the user
 */
public class Ui {
    private Scanner sc = new Scanner(System.in);

    /**
     * Shows the welcome message to the user.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Oliver.");
        System.out.println("What can I do for you?");
    }

    /**
     * Shows the farewell message to the user.
     */
    public void showBye() {
        System.out.println("\tBye. Hope to see you again soon!");
    }

    /**
     * Reads the input from the user.
     *
     * @return string representation of user's input
     */
    public String readInput() {
        return this.sc.nextLine();
    }

    /**
     * Closes the scanner.
     */
    public void close() {
        this.sc.close();
    }

    /**
     * Shows a confirmation message when a task is added to the list.
     *
     * @param t the task that has been added
     * @param numTasks the number of tasks in the list
     */
    public void showAdd(Task t, int numTasks) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t" + t);
        System.out.println("\tNow you have " + numTasks + " tasks in the list.");
    }

    /**
     * Shows a confirmation message when a task is deleted from the list.
     *
     * @param removedTask the task that has been deleted
     * @param numTasks the number of tasks in the list
     */
    public void showDelete(Task removedTask, int numTasks) {
        System.out.println("\tOk, I've removed this task:");
        System.out.println("\t" + removedTask);
        System.out.println("\tNow you have " + numTasks + " tasks in the list.");
    }

    /**
     * Shows the list of tasks by printing each task out.
     *
     * @param list the list of tasks to be shown
     */
    public void showList(TaskList list) {
        if (list.isEmpty()) {
            System.out.println("\tThere are no tasks in your list.");
        } else {
            System.out.println("\tHere are the tasks in your list:");
            for (int i = 0; i < list.getSize(); i++) {
                System.out.println("\t" + (i+1) + "." + list.get(i));
            }
        }
    }

    /**
     * Shows the confirmation message when user marks a task as done.
     *
     * @param list the list of tasks
     * @param index the index of the task user marks as done
     */
    public void showMarked(TaskList list, int index) {
        list.get(index).markAsDone();
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t" + list.get(index));
    }

    /**
     * Shows the confirmation message when user marks a task as undone.
     *
     * @param list the list of tasks
     * @param index the index of the task user marks as undone
     */
    public void showUnmarked(TaskList list, int index) {
        list.get(index).markAsUndone();
        System.out.println("\tOk, I've marked this task as not done yet:");
        System.out.println("\t" + list.get(index));
    }

    /**
     * Shows the matching results of user's search.
     *
     * @param list the list of tasks
     * @param keyword the word to be searched for in the list of tasks
     */
    public void showSearch(TaskList list, String keyword) {
        if (list.isEmpty()) {
            System.out.println("\tThere are no tasks in your list.");
        } else {
            TaskList filteredList = list.filter(keyword);
            if (filteredList.isEmpty()) {
                System.out.println("\tThere are no matching tasks in your list.");
            } else {
                System.out.println("\tHere are the matching tasks in your list:");
                for (int i = 0; i < filteredList.getSize(); i++) {
                    System.out.println("\t" + (i+1) + "." + filteredList.get(i));
                }
            }
        }
    }

    /**
     * Shows the error message when arguments are missing for a command.
     */
    public void showMissingArgsError() {
        System.out.println("\tMissing arguments for this command.");
    }

    /**
     * Shows the error message when invalid arguments are provided for a command.
     */
    public void showInvalidArgsError() {
        System.out.println("\tInvalid arguments provided for this command.");
    }

    /**
     * Shows the error message when arguments provided for a command are out of range.
     */
    public void showOutOfRangeError() {
        System.out.println("\tNo such task exists. Task number out of range.");
    }
}
