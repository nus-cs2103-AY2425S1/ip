package hamyo.misc;

import hamyo.tasks.Task;

/**
 * Deals with interactions with the user. Pushes texts to terminal.
 *
 * @author Han Yu
 */
public class Ui {

    public static final String line = "___________________________________________"
        + "_________________________________________________";
    public static final String logo = "$$   $$   $$$$    $$$$ $$$$   $$   $$  $$$$$$\n"
        + "$$   $$  $$  $$  $$  $$$  $$  $$   $$  $$  $$\n"
        + "$$$$$$$  $$$$$$  $$  $$$  $$  $$$$$$$  $$  $$\n"
        + "$$   $$  $$  $$  $$  $$$  $$       $$  $$  $$\n"
        + "$$   $$  $$  $$  $$  $$$  $$  $$$$$$   $$$$$$";

    /**
     * Pushes greeting text to users' terminal.
     */
    public void greet() {
        printLine();
        printLogo();
        System.out.println("\nAnnyeonghaseyo! Hamyo here!\n"
                + "How may I assist you today?");
        printLine();
    }

    /**
     * Pushes goodbye text to users' terminal.
     */
    public void terminate() {
        System.out.println("Annyeong! Till we meet again. <3");
        printLine();
    }

    /**
     * Pushes a break line to users' terminal, to segment different commands.
     */
    public static void printLine() {
        System.out.println(line);
    }

    /**
     * Pushes Hamyo logo to users' terminal.
     */
    private static void printLogo() {
        System.out.println(logo);
    }

    /**
     * Pushes text to users' terminal after successful adding of task.
     *
     * @param task The added task.
     * @param size The size of the users' list of tasks after task addition.
     */
    public static void printAddTask(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.printf("There are %d tasks in the list now.\n", size);
        Ui.printLine();
    }

    /**
     * Pushes text to users' terminal after successful deletion of task.
     *
     * @param task The deleted task.
     * @param size The size of the users' list of tasks after task deletion.
     */
    public static void printDeleteTask(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.printf("There are %d tasks in the list now.\n", size);
        Ui.printLine();
    }

    /**
     * Pushes text to users' terminal after successful listing of task.
     *
     * @param tasksList The list of the users' tasks in String format.
     */
    public static void printListTasks(String tasksList) {
        System.out.println("These are your tasks:");
        System.out.println((tasksList.isEmpty() ? "No tasks found!" : tasksList));
        Ui.printLine();
    }

    /**
     * Pushes text to users' terminal after successful listing of task on the
     * specified date.
     *
     * @param tasksList The list of the users' tasks in String format.
     * @param formattedDate The specified date in String format.
     */
    public static void printListTasksByDate(String tasksList, String formattedDate) {
        System.out.println("These are your tasks on " + formattedDate + ".");
        System.out.println(tasksList);
        Ui.printLine();
    }

    /**
     * Pushes text to users' terminal after successful listing of task with the
     * specified keyword.
     *
     * @param tasksList The list of the users' tasks in String format.
     * @param keyword The keyword to filter users' tasks by.
     */
    public static void printListTasksByKeyword(String tasksList, String keyword) {
        System.out.println("Here are the matching tasks in your list for " + keyword.toUpperCase() + ".");
        System.out.println(tasksList);
        Ui.printLine();
    }

    /**
     * Pushes text to users' terminal after successful marking of task.
     *
     * @param task The marked task.
     */
    public static void markTask(Task task) {
        System.out.println("Yay! This task has been marked as completed.");
        System.out.println(task.toString());
        Ui.printLine();
    }

    /**
     * Pushes text to users' terminal after successful unmarking of task.
     *
     * @param task The unmarked task.
     */
    public static void unmarkTask(Task task) {
        System.out.println("Oki! This task has been marked as incomplete.");
        System.out.println(task.toString());
        Ui.printLine();
    }

    /**
     * Pushes text to users' terminal if when Exception is encountered.
     *
     * @param e The Exception encountered.
     */
    public static void printException(Exception e) {
        System.out.println(e.toString());
        Ui.printLine();
    }
}
