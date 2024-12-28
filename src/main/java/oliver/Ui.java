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
        return "Meow! I'm Oliver, your purr-sonal task tracker!\nWhat can I do for you today?\n" +
                "Need help with your list, or maybe a reminder to chase your dreams... or a mouse?";
    }

    /**
     * Shows the farewell message to the user.
     *
     * @return string representation of the farewell message
     */
    public String showBye() {
        return "Bye for now! Hope to see you again soon for more tasks and chats!";
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
    public String showAdded(Task addedTask, int numTasks) {
        return String.format("Got it. I've added this task:\n%s\n" +
                "Now you have %d tasks to keep you busy.", addedTask, numTasks);
    }

    /**
     * Shows a confirmation message when a task is deleted from the list.
     *
     * @param removedTask the task that has been deleted
     * @param numTasks the number of tasks in the list
     * @return string representation of the confirmation message
     */
    public String showDeleted(Task removedTask, int numTasks) {
        return String.format("All done. I've removed this task:\n%s\n" +
                "You are down to %d tasks now.", removedTask, numTasks);
    }

    /**
     * Shows the list of tasks.
     *
     * @param list the list of tasks to be shown
     * @return string representation of the list of tasks
     */
    public String showList(TaskList list) {
        if (list.isEmpty()) {
            return "Meow? I could not find any tasks in your list.";
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= list.getSize(); i++) {
            builder.append(String.format("%d. %s\n", i, list.get(i - 1)));
        }
        assert builder.charAt(builder.length() - 1) == '\n' : "Last char should be a newline char";
        // Remove the last newline char
        builder.deleteCharAt(builder.length() - 1);
        return "Here's your list of tasks to tackle:\n" + builder;
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
        return "Pawsome work! I've marked this task as done:\n" + list.get(index);
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
        return "Alright, I've marked this task as still pending:\n" + list.get(index);
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
            return "Meow? I could not find any tasks in your list.";
        }

        TaskList filteredList = list.filter(keyword);
        if (filteredList.isEmpty()) {
            return "Meow? I could not find any matching tasks in your list.";
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= filteredList.getSize(); i++) {
            builder.append(String.format("%d. %s\n", i, filteredList.get(i - 1)));
        }
        assert builder.charAt(builder.length() - 1) == '\n' : "Last char should be a newline char";
        // Remove the last newline char
        builder.deleteCharAt(builder.length() - 1);
        return "Here are the matching tasks in your list I managed to dig up:\n" + builder;
    }

    /**
     * Shows the list of tasks that are due soon.
     *
     * @param list the list of tasks
     * @return string representation of the list of tasks
     */
    public String showReminders(TaskList list) {
        if (list.isEmpty()) {
            return "Meow? I could not find any tasks in your list.";
        }

        TaskList reminderList = list.getUpcomingTasks();
        if (reminderList.isEmpty()) {
            return "Don't worry, there are no tasks due soon.";
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= reminderList.getSize(); i++) {
            builder.append(String.format("%d. %s\n", i, reminderList.get(i - 1)));
        }
        assert builder.charAt(builder.length() - 1) == '\n' : "Last char should be a newline char";
        // Remove the last newline char
        builder.deleteCharAt(builder.length() - 1);
        return "Here are the upcoming tasks in your list:\n" + builder;
    }

    /**
     * Shows the error message when arguments are missing for a command.
     *
     * @return string representation of the error message
     */
    public String showMissingArgsError() {
        return "Meow??? Missing arguments for this command.";
    }

    /**
     * Shows the error message when invalid arguments are provided for a command.
     *
     * @return string representation of the error message
     */
    public String showInvalidArgsError() {
        return "Meow??? Invalid arguments provided for this command.";
    }

    /**
     * Shows the error message when arguments provided for a command are out of range.
     *
     * @return string representation of the error message
     */
    public String showOutOfRangeError() {
        return "Meow??? I could not find such a task. Task number out of range.";
    }

    /**
     * Shows the error message when the date or time provided are invalid.
     *
     * @return string representation of the error message
     */
    public String showInvalidDateTimeError(boolean isTimeOptional) {
        if (isTimeOptional) {
            return "Invalid date or time. Please enter the date and time in the following format: " +
                    "YYYY-MM-DD HHmm\nNote that date is required but time is optional.";
        }
        return "Invalid date or time. Please enter the date and time in the following format: " +
                "YYYY-MM-DD HHmm";
    }
}
