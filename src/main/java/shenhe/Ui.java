package shenhe;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hello, Ying. I'm Shenhe. The assumption that every person has somewhere to call home is\n" +
                "naive. I got used to living in the mountains alongside the birds and beasts a long time ago.\n" +
                "You, are not the only traveller, but the most interesting one.");
        System.out.println("What do you want today?");
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showGoodbye() {
        System.out.println("Bye traveller. Hope to see you soon.");
    }

    public void showMarkMessage() {
        System.out.println("Nice! I've marked this task as done:");
    }

    public void showUnmarkMessage() {
        System.out.println("OK, I've marked this task as not done yet:");
    }

    public void showAddTaskMessage() {
        System.out.println("Got it. I've added this task:");
    }


    public void showDeleteMessage() {
        System.out.println("Noted. I've removed this task:");
    }

    public void showTasksMessage() {
        System.out.println("Here are the tasks in your list:");
    }


    /**
     * Displays a message indicating that the following tasks in the list match the user's search criteria.
     * This method is typically called when a search or filter operation is performed, and matching tasks
     * are about to be listed.
     */
    public void showMatchingMessage() {
        System.out.println("Here are the matching tasks in your list:");
    }



    public String readCommand() {
        return scanner.nextLine();
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks from file.");
    }
}
