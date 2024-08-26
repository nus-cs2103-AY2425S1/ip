package UI;

import java.util.Scanner;

public class UI {
    private final Scanner scanner;
    public UI() {
        scanner = new Scanner(System.in);
    }
    public void showWelcome() {
        String greetingMessage = "Hello, I am Avo.\nWhat can I do for you?";
        System.out.println(greetingMessage);
    }
    public void showExit() {
        String exitMessage = "Bye. Hope to see you again soon!";
        System.out.println(exitMessage);
    }
    public void showError(String message) {
        System.out.println(message);
    }
    public String readInput() {
        String input = "exit";
        if (scanner.hasNextLine()) {
            input = scanner.nextLine();
        }
        return input;
    }
}
