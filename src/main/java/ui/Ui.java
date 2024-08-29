package ui;

import java.util.Scanner;

public class Ui {
    // Deals with interactions with the user.
    private final Scanner scanner = new Scanner(System.in);
    public void showWelcome() {
        System.out.println("""
                El Primo:
                Hello! I'm El Primo!!
                What can I do for you?""");
    }

    public void showLoadingError() {
        System.out.println("Error loading data!");
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public String readCommand() {
        System.out.println("\nMe:");
        return scanner.nextLine();
    }

    public void showLine() {
        System.out.println("____________________");
    }
}
