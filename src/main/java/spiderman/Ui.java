package spiderman;

import java.util.Scanner;

public class Ui {
    private final Scanner scan;  // Declare Scanner as a class-level field

    public Ui() {
        this.scan = new Scanner(System.in);  // Initialize Scanner in the constructor
    }

    public void showWelcome() {
        System.out.println("Hello! This is your friendly neighbourhood Spiderman.");
        System.out.println("What can I do for you?");
    }

    public void showLoadingError() {
        System.out.println("Error loading data from file. Starting with an empty task list.");
    }

    public String readCommand() {
        return scan.nextLine();
    }

    public void showDividerLine() {
        System.out.println("=======================================================================================");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void close() {
        scan.close();
    }
}
