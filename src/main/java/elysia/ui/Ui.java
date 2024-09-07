package elysia.ui;

import elysia.task.Task;

import java.util.ArrayList;

/**
 * Text UI of the application.
 */
public class Ui {
    private static String line = "____________________________________________________________";
    private static String welcomeMessage = "Hi there! Did you miss me?\n" +
            "Wherever you are and whenever you need,\n" +
            "Elysia will always meet your expectations.";
    private static String exitMessage = "Alright, this time we really have to say goodbye.\n" +
            "Goodbye, Mei!";


    /**
     * Prints a horizontal line.
     */
    public static void insertLine() {
        System.out.println(line);
    }


    public void showWelcomeMessage() {
        insertLine();
        System.out.println(welcomeMessage);
        insertLine();
    }

    /**
     * Displays added message
     * @param task
     */
    public void showAddedMessage(ArrayList<Task> arrayLists, Task task) {
        insertLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + arrayLists.size() + " tasks in the list.");
        insertLine();
    }

    public void showExitMessage() {
        insertLine();
        System.out.println(exitMessage);
        insertLine();
    }

    public void showFailMessage(String e) {
        insertLine();
        System.out.println(e);
        insertLine();
    }



    public void printList(ArrayList<Task> arrayLists) {
        insertLine();
        int n = arrayLists.size();
        System.out.println("Here are the tasks in your list: ");
        for (int i = 1; i <= n; i++) {
            Task curr = arrayLists.get(i - 1);
            System.out.println(i + "." + curr.toString());
        }
        insertLine();
    }

    public void showMarkAsDoneMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public void showUnmarkAsDoneMessage(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }


    public void showDeleteTaskMessage(ArrayList<Task> arrayLists, Task task) {
        insertLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + arrayLists.size() + " tasks in the list.");
        insertLine();
    }

}
