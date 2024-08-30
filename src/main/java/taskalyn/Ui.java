package taskalyn;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void printLines(String input) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    " + input);
        System.out.println("    ____________________________________________________________\n");
    }

    public void printWelcome() {
        printLines("Hey! I'm Taskalyn, your personal Task Manager :)\n" +
                "    What can I do for you?");
    }

    public String readCommand() {
        if (scanner.hasNextLine()) {
            return scanner.nextLine().trim();
        } else {
            return "unknown command";
        }
    }

    public void close() {
        scanner.close();
    }

    public String getLastCommand() {
        return readCommand();
    }
}
