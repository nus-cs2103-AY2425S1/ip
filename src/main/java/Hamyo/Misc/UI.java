package Hamyo.Misc;

import Hamyo.Tasks.Task;

/**
 * Deals with interactions with the user. Pushes texts to terminal.
 *
 * @author Han Yu
 */
public class UI {

    /**
     * Pushes greeting text to users' terminal.
     */
    public void greet() {
        printLine();
        printLogo();
        System.out.println("\nAnnyeonghaseyo! Hamyo here!\n" +
                "How may I assist you today?");
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
        System.out.println("___________________________________________" +
                "_________________________________________________");
    }

    /**
     * Pushes Hamyo logo to users' terminal.
     */
    private static void printLogo() {
        System.out.println("$$   $$   $$$$    $$$$ $$$$   $$   $$  $$$$$$\n" +
                "$$   $$  $$  $$  $$  $$$  $$  $$   $$  $$  $$\n" +
                "$$$$$$$  $$$$$$  $$  $$$  $$  $$$$$$$  $$  $$\n" +
                "$$   $$  $$  $$  $$  $$$  $$       $$  $$  $$\n" +
                "$$   $$  $$  $$  $$  $$$  $$  $$$$$$   $$$$$$");
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
        UI.printLine();
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
        UI.printLine();
    }

    /**
     * Pushes text to users' terminal after successful listing of task.
     *
     * @param tasksList The list of the users' tasks in String format.
     */
    public static void printListTasks(String tasksList) {
        System.out.println("These are your tasks:");
        System.out.println((tasksList.isEmpty() ? "No tasks found!" : tasksList));
        UI.printLine();
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
        UI.printLine();
    }

    /**
     * Pushes text to users' terminal after successful marking of task.
     *
     * @param task The marked task.
     */
    public static void markTask(Task task) {
        System.out.println("Yay! This task has been marked as completed.");
        System.out.println(task.toString());
        UI.printLine();
    }

    /**
     * Pushes text to users' terminal after successful unmarking of task.
     *
     * @param task The unmarked task.
     */
    public static void unmarkTask(Task task) {
        System.out.println("Oki! This task has been marked as incomplete.");
        System.out.println(task.toString());
        UI.printLine();
    }

    /**
     * Pushes text to users' terminal if when Exception is encountered.
     *
     * @param e The Exception encountered.
     */
    public static void printException(Exception e) {
        System.out.println(e.toString());
        UI.printLine();
    }
    
}
