package ui;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }
    public String readCommand() {
        return scanner.nextLine();
    }
    public String showLine() {
        return "___________________________________________________________________________";
    }
    public void welcomeUser() {
        System.out.println("Hello! I'm Handsome\nWhat can I do for you?\n");
    }
    public void showBye() {
        System.out.println("Bye, it was an amazing conversation!");
    }
    public static void show(String message) {
        System.out.println(message);
    }
    public void closeScanner() {
        scanner.close();
    }
}
