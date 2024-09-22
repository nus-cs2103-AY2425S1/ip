package panorama;

import java.util.Scanner;

/**
 * Handles user interaction by printing various messages to the console.
 */
public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public String getWelcomeMessage() {
        return "Hello! I'm Panorama\nWhat can I do for you?";
    }

    public void showError(String errMessage) {
        System.out.println("Error: " + errMessage);
    }
}
