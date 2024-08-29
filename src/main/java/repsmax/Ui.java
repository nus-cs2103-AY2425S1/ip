package repsmax;

import java.util.Scanner;
/**
 * Handles all user interaction
 */
public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showWelcome() {
        System.out.println("  ____________________________________________________________");
        System.out.println("   Hello! I'm Repsmax");
        System.out.println("   What can I do for you?");
        System.out.println("  ____________________________________________________________");
    }

    public void showGoodbye() {
        System.out.println("  ____________________________________________________________");
        System.out.println("   Bye. Hope to see you again soon!");
        System.out.println("  ____________________________________________________________");
    }

    public void showLoadingError() {
        System.out.println("An error occurred while loading tasks.");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showLine() {
        System.out.println("____________________________");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}