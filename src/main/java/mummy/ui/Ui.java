package mummy.ui;

import java.util.Scanner;


public class Ui {

    private static final String DIVIDER = "-".repeat(100);

    private final String logo;

    private final Scanner scanner = new Scanner(System.in);

    public Ui(String logo) {
        this.logo = logo;
    }

    public void showWelcome() {
        show("Hello from\n"
                + logo + "\n"
                + "What can I do for you?\n");
    }

    public String readCommand() {
        if (scanner.hasNext()) {
            return scanner.nextLine();
        }
        return "";
    }

    public void closeScanner() {
        scanner.close();
    }

    public void show(String message) {
        System.out.println(message);
    }

    public void showError(String message) {
        System.out.println("OOPS!!!! " + message);
    }

    public void showLine() {
        System.out.println(DIVIDER);
    }
}
