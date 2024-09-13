package pixel;

import java.util.Scanner;

/**
 * Class that deals with the interactions with the user
 */
public class Ui {
    /**
     * Constructor method for Ui
     */
    public Ui() {
    }

    /**
     * Prints the greeting displayed to user when user first runs the program
     */
    public static String getGreeting() {
        return "Hello! I'm Pixel!\nWhat can I do for you?";
    }

    /**
     * Prints the UI when user inputs 'bye' as command
     */
    public static String getExit() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints the list of Tasks when user inputs "list" as a command
     *
     * @param tasks TaskList which stores all tasks
     */
    public static String getListAsString(TaskList tasks) {
        return "Here are the tasks in your list:\n" + tasks.getTasksAsString();
    }

    /**
     * Prints the Task that has been added and the total number of items in the list
     * to inform user that items has been added successfully to TaskList
     *
     * @param tasks TaskList which stores all the tasks
     */
    public static String getAddConfirmation(TaskList tasks) {
        String resultingString = "";
        resultingString += "Got it. I've added this task:\n";
        resultingString += tasks.getTask(tasks.getSize()-1).toString() + "\n";
        resultingString += "Now you have " + tasks.getSize() + " task(s) in the list.";
        return resultingString;
    }

    /**
     * Prints the Task that has been marked as done
     * to inform user that items has been marked successfully
     *
     * @param currentTask task which needs to be mark as done
     */
    public static String getMarkConfirmation(Task currentTask) {
        return "Nice! I've marked this task as done:\n" + currentTask;
    }

    /**
     * Prints the Task that has been marked as undone
     * to inform user that items has been marked successfully
     *
     * @param currentTask task which needs to be mark as undone
     */
    public static String getUnmarkConfirmation(Task currentTask) {
        return "OK, I've marked this task as not done yet:\n" + currentTask;
    }
    /**
     * Prints the Task that has been deleted and the total number of items in the list left
     * to inform user that tasks have been deleted successfully from TaskList
     *
     * @param removedTask task to be removed
     * @param tasks TaskList which stores all the tasks
     */
    public static String getDeleteConfirmation(Task removedTask, TaskList tasks) {
        String resultingString = "";
        resultingString += "Noted. I've removed this task:\n";
        resultingString += removedTask + "\n";
        resultingString += "Now you have " + tasks.getSize() + " task(s) in the list.";
        return resultingString;
    }

    /**
     * Prints tasks that contains the keyword provided
     * @param keyword search key
     */
    public static String getMatchingTasks(String keyword, TaskList tasks) {
        return "Here are the matching tasks in your list:\n" + tasks.getMatchingTasksAsString(keyword);
    }

    /**
     * Prints tasks that contains the date
     * @param date date to search
     */
    public static String getMatchingDates(String date, TaskList tasks) {
        return "Here are the matching events and deadlines in your list:\n" + tasks.getMatchingDatesAsString(date);
    }

    /**
     * returns command from user's input
     *
     * @return String of command
     */
    public static String getCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Prints error message when IndexOutOfBoundsException is thrown
     */
    public static String handleIndexOutOfBoundsException() {
        return "I'm sorry, but there is no such index that exists\n" +
                "Type in a valid index!";
    }

    /**
     * Prints error message when InvalidCommandException is thrown
     */
    public static String handleInvalidCommandException() {
        return "I'm sorry, but I don't know what that means\n" +
                "Type in a valid command!";
    }

    /**
     * Prints error message when EmptyDescriptionException is thrown
     */
    public static String handleEmptyDescriptionException() {
        return "I'm sorry, but I can't add a task if the description is empty!\n" +
                "Type in a valid description!";
    }

    /**
     * Prints error message when FileNotFoundException is thrown
     */
    public static String handleFileNotFoundException() {
        return "I'm sorry, but I can't find the data for the ToDos!\n" +
                "Please specify the correct file path.";
    }
}
