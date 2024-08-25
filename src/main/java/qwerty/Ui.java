package qwerty;

import java.util.Scanner;

public class Ui {

    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        System.out.println(); // blank line before user input
        return scanner.nextLine();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showError(String message) {
        System.out.println("\nWell done! An error has occurred:\n" + message);
    }

    public void showGreeting() {
        System.out.println("""

                It's your worst buggy nightmare, Qwerty.
                What can I do for you?""");
    }

    public void showGoodbye() {
        System.out.println("""
                
                Goodbye, and I'll see you within 3 business days.""");
    }

}
