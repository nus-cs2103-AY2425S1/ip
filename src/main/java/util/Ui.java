package util;

import java.util.Scanner;

/**
 * Handles printing and reading input from user.
 *
 * @author dwsc37
 */
public class Ui {
    private Scanner scanner;

    private void printMessageWithDividers(String message) {
        String line = "____________________________________________________________";
        System.out.println(line);
        System.out.print(message.indent(2));
        System.out.println(line);
    }

    public void start() {
        String greetMessage = "Hello, I'm BotManager, your friendly task assistant!\nWhat can I do for you?";
        printMessageWithDividers(greetMessage);
        scanner = new Scanner(System.in);
    }

    public void exit() {
        String goodbyeMessage = "Goodbye! Hope to see you again soon!";
        printMessageWithDividers(goodbyeMessage);
        scanner.close();
    }

    public void printMessage(String message) {
        printMessageWithDividers(message);
    }

    public String readUserInput() {
        return scanner.nextLine();
    }
}
