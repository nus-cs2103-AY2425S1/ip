package soju;

import java.util.Scanner;

public class Ui {
    Scanner scanner = new Scanner(System.in);
    public void showLoadingError() {

    }
    public void printHorizontalLine() {
        System.out.println("-------------------------------------");
    }
    public void printError(SojuException e) {
        System.out.println(e.getMessage());
        printHorizontalLine();
    }
    public void greet() {
        printHorizontalLine();
        String greetingMessage = "Hello! I'm soju.Soju\nWhat can I do for you?";
        System.out.println(greetingMessage);
        printHorizontalLine();
    }
    public void exit() {
        String exitMessage = "Bye. Hope to see you again soon!";
        System.out.println(exitMessage);
    }
    public void printString(String string) {
        System.out.println(string);
    }
    public String readCommand() {
        return scanner.nextLine();
    }
}
