package UI;

import java.util.Scanner;

public class UI {
    private final Scanner input;

    public UI() {
        input = new Scanner(System.in);
    }

    public void showWelcomeMessage() {
        System.out.println("Hello! I'm Bob\nWhat can I do for you?");
        System.out.println();
    }

    public void showAddTaskMsg() {
        System.out.println("Sure I'll add that in for you!");
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }

    public void showSeparator() {
        System.out.println("--------------------------------------------------");
    }

    public void showErrorMessage(String message) {
        System.out.println(message);
    }

    public void showNewLineSeparator() {
        System.out.println("--------------------------------------------------\n");
    }

    public String getUserInput() {
        return input.nextLine();
    }
}

