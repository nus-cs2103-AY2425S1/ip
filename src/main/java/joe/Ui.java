package joe;

import java.util.Scanner;

public class Ui {
    //deals with interactions with the user
    public Ui() {

    }

    public void printWelcome() {
        System.out.println("\t" + "Hello! I'm Joe");
        System.out.println("\t" + "What can I do for you?");
    }

    public void printDivider() {
        System.out.println("\t____________________________________________________________");
    }

    public void printExit() {
        System.out.println("\t" + "Bye. Hope to see you again soon!");
    }

    public void printError(String msg) {
        System.out.println("\t" + msg);
    }

    public void printLoadingError() {
        System.out.println("An error occurred while loading data file");
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void printUnknownCommand() {
        System.out.println("\tOOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
