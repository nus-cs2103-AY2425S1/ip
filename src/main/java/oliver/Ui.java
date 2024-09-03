package oliver;

import java.util.Scanner;

/**
 * Deals with interactions with the user
 */
public class Ui {
    private Scanner sc = new Scanner(System.in);

    /**
     * Shows the welcome message to the user.
     *
     * @return string representation of the welcome message
     */
    public String showWelcome() {
        return "Hello! I'm Oliver.\nWhat can I do for you?";
    }

    /**
     * Shows the farewell message to the user.
     *
     * @return string representation of the farewell message
     */
    public String showBye() {
        return "\tBye. Hope to see you again soon!";
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
     * @param addedTask the task that has been added
     * @param numTasks the number of tasks in the list
     * @return string representation of the confirmation message
     */
    public String showAdd(Task addedTask, int numTasks) {
        return String.format("\tGot it. I've added this task:\n\t%s\n" +
                "\tNow you have %d tasks in the list.", addedTask, numTasks);
    }

    /**
     * Shows a confirmation message when a task is deleted from the list.
     *
     * @param removedTask the task that has been deleted
     * @param numTasks the number of tasks in the list
     * @return string representation of the confirmation message
     */
    public String showDelete(Task removedTask, int numTasks) {
        return String.format("\tOk. I've removed this task:\n\t%s\n" +
                "\tNow you have %d tasks in the list.", removedTask, numTasks);
    }

    /**
     * Shows the list of tasks.
     *
     * @param list the list of tasks to be shown
     * @return string representation of the list of tasks
     */
    public String showList(TaskList list) {
        if (list.isEmpty()) {
            return "\tThere are no tasks in your list.";
        } else {
            StringBuilder builder = new StringBuilder();
            for (int i = 1; i <= list.getSize(); i++) {
                builder.append(String.format("\t%d. %s\n", i, list.get(i - 1)));
            }
            // Remove the last newline char
            builder.deleteCharAt(builder.length() - 1);
            return "\tHere are the tasks in your list:\n" + builder;
        }
    }

    /**
     * Shows the confirmation message when user marks a task as done.
     *
     * @param list the list of tasks
     * @param index the index of the task user marks as done
     * @return string representation of the confirmation message
     */
    public String showMarked(TaskList list, int index) {
        list.get(index).markAsDone();
        return "\tNice! I've marked this task as done:\n\t" + list.get(index);
    }

    /**
     * Shows the confirmation message when user marks a task as undone.
     *
     * @param list the list of tasks
     * @param index the index of the task user marks as undone
     * @return string representation of the confirmation message
     */
    public String showUnmarked(TaskList list, int index) {
        list.get(index).markAsUndone();
        return "\tOk, I've marked this task as not done yet:\n\t" + list.get(index);
    }

    /**
     * Shows the matching results of user's search.
     *
     * @param list the list of tasks
     * @param keyword the word to be searched for in the list of tasks
     * @return string representation of the results
     */
    public String showSearch(TaskList list, String keyword) {
        if (list.isEmpty()) {
            return "\tThere are no tasks in your list.";
        } else {
            TaskList filteredList = list.filter(keyword);
            if (filteredList.isEmpty()) {
                return "\tThere are no matching tasks in your list.";
            } else {
                StringBuilder builder = new StringBuilder();
                for (int i = 1; i <= filteredList.getSize(); i++) {
                    builder.append(String.format("\t%d. %s\n", i, filteredList.get(i - 1)));
                }
                // Remove the last newline char
                builder.deleteCharAt(builder.length() - 1);
                return "\tHere are the matching tasks in your list:\n" + builder;
            }
        }
    }

    /**
     * Shows the error message when arguments are missing for a command.
     *
     * @return string representation of the error message
     */
    public String showMissingArgsError() {
        return "\tMissing arguments for this command.";
    }

    /**
     * Shows the error message when invalid arguments are provided for a command.
     *
     * @return string representation of the error message
     */
    public String showInvalidArgsError() {
        return "\tInvalid arguments provided for this command.";
    }

    /**
     * Shows the error message when arguments provided for a command are out of range.
     *
     * @return string representation of the error message
     */
    public String showOutOfRangeError() {
        return "\tNo such task exists. Task number out of range.";
    }
}
