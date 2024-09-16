package hien.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UI {
    private Scanner scanner;
    private String lastMessage;

    public UI() {
        scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        String welcomeMessage = "____________________________________________________________\n" +
                " Hello! I'm Hien\n" +
                " What can I do for you?\n" +
                "____________________________________________________________";
        System.out.println(welcomeMessage);
        lastMessage = welcomeMessage;
    }

    public void showGoodbye() {
        String goodbyeMessage = "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!";
        System.out.println(goodbyeMessage);
        lastMessage = goodbyeMessage; // Store the message
    }

    public void showLine() {
        String lineMessage = "____________________________________________________________";
        System.out.println(lineMessage);
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void showLoadingError() {
        String errorMessage = "Error loading tasks. Starting with an empty task list.";
        System.out.println(errorMessage);
        lastMessage = errorMessage;
    }

    public void showError(String message) {
        System.out.println(message);
        lastMessage = message;// Store the message
    }

    public void showMessage(String message) {
        System.out.println(message);
        lastMessage = message;// Store the message
    }

    // Close scanner when done
    public void closeScanner() {
        scanner.close();
    }

    // New methods for testing
    public String getLastMessage() {
        return lastMessage;
    }

}
