package pixel;

import java.util.Scanner;

/**
 * Class that deals with the interactions with the user
 */
public class Ui {
    public static String LINE = "\t" + "------------------------------------";

    /**
     * Constructor method for Ui
     */
    public Ui() {
    }

    /**
     * Prints the greeting displayed to user when user first runs the program
     */
    public static void printGreeting() {
        System.out.println(LINE);
        System.out.println("\t" + "Hello! I'm Pixel!");
        System.out.println("\t" + "What can I do for you?");
        System.out.println(LINE);
    }

    /**
     * Prints the UI when user inputs 'bye' as command
     */
    public static void printExit() {
        System.out.println(LINE);
        System.out.println("\t" + "Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    /**
     * Prints the list of Tasks when user inputs "list" as a command
     *
     * @param tasks TaskList which stores all tasks
     */
    public static void printList(TaskList tasks) {
        System.out.println(LINE);
        System.out.println("\t" + "Here are the tasks in your list:");
        tasks.printTasks();
        System.out.println(LINE);
    }

    /**
     * Prints the Task that has been added and the total number of items in the list
     * to inform user that items has been added successfully to TaskList
     *
     * @param tasks TaskList which stores all the tasks
     */
    public static void printAddConfirmation(TaskList tasks) {
        System.out.println(LINE);
        System.out.println("\t" + "Got it. I've added this task:");
        System.out.println("\t  " + tasks.getTask(tasks.getSize()-1).toString());
        System.out.println("\t" + "Now you have " + tasks.getSize() + " task(s) in the list.");
        System.out.println(LINE);
    }

    /**
     * Prints the Task that has been marked as done
     * to inform user that items has been marked successfully
     *
     * @param currentTask task which needs to be mark as done
     */
    public static void printMarkConfirmation(Task currentTask) {
        System.out.println(LINE);
        System.out.println("\t" + "Nice! I've marked this task as done:");
        System.out.println("\t  " + currentTask);
        System.out.println(LINE);
    }

    /**
     * Prints the Task that has been marked as undone
     * to inform user that items has been marked successfully
     *
     * @param currentTask task which needs to be mark as undone
     */
    public static void printUnmarkConfirmation(Task currentTask) {
        System.out.println(LINE);
        System.out.println("\t" + "OK, I've marked this task as not done yet:");
        System.out.println("\t  " + currentTask);
        System.out.println(LINE);
    }
    /**
     * Prints the Task that has been deleted and the total number of items in the list left
     * to inform user that tasks have been deleted successfully from TaskList
     *
     * @param removedTask task to be removed
     * @param tasks TaskList which stores all the tasks
     */
    public static void printDeleteConfirmation(Task removedTask, TaskList tasks) {
        System.out.println(LINE);
        System.out.println("\t" + "Noted. I've removed this task:");
        System.out.println("\t  " + removedTask);
        System.out.println("\t" + "Now you have " + tasks.getSize() + " task(s) in the list.");
        System.out.println(LINE);
    }

    /**
     * Prints tasks that contains the keyword provided
     * @param keyword search key
     */
    public static void printMatchingTasks(String keyword, TaskList tasks) {
        System.out.println(LINE);
        System.out.println("\t" + "Here are the matching tasks in your list:");
        tasks.printMatchingTasks(keyword);
        System.out.println(LINE);
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
    public static void handleIndexOutOfBoundsException() {
        System.out.println(LINE);
        System.out.println("\t" + "I'm sorry, but there is no such index that exists");
        System.out.println("\t" + "Type in a valid index!");
        System.out.println(LINE);
    }

    /**
     * Prints error message when InvalidCommandException is thrown
     */
    public static void handleInvalidCommandException() {
        System.out.println(LINE);
        System.out.println("\t" + "I'm sorry, but I don't know what that means");
        System.out.println("\t" + "Type in a valid command!");
        System.out.println(LINE);
    }

    /**
     * Prints error message when EmptyDescriptionException is thrown
     */
    public static void handleEmptyDescriptionException() {
        System.out.println(LINE);
        System.out.println("\t" + "I'm sorry, but I can't add a task if the description is empty!");
        System.out.println("\t" + "Type in a valid description!");
        System.out.println(LINE);
    }

    /**
     * Prints error message when FileNotFoundException is thrown
     */
    public static void handleFileNotFoundException() {
        System.out.println(LINE);
        System.out.println("\t" + "I'm sorry, but I can't find the data for the ToDos!");
        System.out.println("\t" + "Please specify the correct file path.");
        System.out.println(LINE);
    }
}
