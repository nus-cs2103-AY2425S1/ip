package blacknut.ui;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Ui class handles user interaction by displaying messages and reading input.
 */
class Ui {
    private static final String LOGO = " ____  _            _                _   \n"
            + "|  _ \\| |          | |              | |  \n"
            + "| |_) | | __ _  ___| | ___ __  _   _| |_ \n"
            + "|  _ <| |/ _ |/ __| |/ / '_ \\| | | | __|\n"
            + "| |_) | | (_| | (__|   <| | | | |_| | |_ \n"
            + "|____/|_|\\__,_|\\___|_|\\_\\_| |_|\\__,_|\\__|\n";

    public void showWelcome() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Blacknut");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public String readInput() {
        return new java.util.Scanner(System.in).nextLine().trim();
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showGoodbye() {
        System.out.println(" Bye. Hope to see you again soon!");
    }

    public void showTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println(" The list is empty.");
        } else {
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + "." + tasks.get(i));
            }
        }
    }

    public void showMarkedTask(Task task, boolean markAsDone) {
        if (markAsDone) {
            System.out.println(" Nice! I've marked this task as done:");
        } else {
            System.out.println(" OK, I've marked this task as not done yet:");
        }
        System.out.println("   " + task);
    }

    public void showAddedTask(Task task, int totalTasks) {
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + totalTasks + " tasks in the list.");
    }

    public void showDeletedTask(Task task, int totalTasks) {
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + totalTasks + " tasks in the list.");
    }

    public void showMatchingTasks(ArrayList<Task> matchingTasks) {
        if (matchingTasks.isEmpty()) {
            System.out.println(" No tasks match your search.");
        } else {
            System.out.println(" Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println(" " + (i + 1) + "." + matchingTasks.get(i));
            }
        }
    }

    public void showError(String message) {
        System.out.println(" ☹ OOPS!!! " + message);
    }
}
