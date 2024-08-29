package charlotte.ui;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void printLine() {
        System.out.println("__________________________________________________________________");
    }

    public void printWelcome() {
        printLine();
        System.out.println("Hello! I'm charlotte.Charlotte!\nWhat can I do for you?");
        printLine();
    }

    public void printExit() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    public void showLoadingError() {
        System.out.println("No existing data file found");
    }

    public void showError(String message) {
        printLine();
        System.out.println(message);
        printLine();
    }

    public void showMessage(String message) {
        printLine();
        System.out.println(message);
        printLine();
    }

}
