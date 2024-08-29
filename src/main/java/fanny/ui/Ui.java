package fanny.ui;

import java.util.Scanner;

public class Ui {
    private Scanner scanner = new Scanner(System.in);

    public void printHello() {
        showHorizontalLine();
        System.out.println("Hello! I'm Fanny\nWhat can I do for you?");
        showHorizontalLine();
    }

    public void printBye() {
        showHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        showHorizontalLine();
    }

    public String getUserInput() {
        System.out.print("User:");
        return scanner.nextLine();
    }

    public void showHorizontalLine() {
        System.out.println("_____________________________________________");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void close() {
        scanner.close();
    }
}