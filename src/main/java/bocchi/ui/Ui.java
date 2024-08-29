package bocchi.ui;

import bocchi.command.Command;
import bocchi.command.Parser;

import java.util.Scanner;

public class Ui {
    /**
     * The name of this chatbot.
     */
    static private final String NAME = "Bocchi";

    /**
     * Prints a greeting with an ASCII art.
     */
    public void printLogo() {
        String logo = """
                 ____                 _     _\s
                |  _ \\               | |   (_)
                | |_) | ___   ___ ___| |__  _\s
                |  _ < / _ \\ / __/ __| '_ \\| |
                | |_) | (_) | (_| (__| | | | |
                |____/ \\___/ \\___\\___|_| |_|_|
                                             \s
                                             \s""";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Prints a horizontal line.
     */
    public void printSeparator() {
        System.out.println("_____________________________________________________________");
    }

    /**
     * Greets the user.
     */
    public void greet() {
        printSeparator();
        System.out.println("Hi! I'm " + NAME + "! Nice to see you!");
        System.out.println("Wha..what can I do for you today? o(*//▽//*)q");
        printSeparator();
    }

    /**
     * Reads a bocchi.command.
     *
     * @param scanner The scanner to use.
     * @return The bocchi.command.
     */
    public Command readCommand(Scanner scanner) {
        System.out.print(">> ");
        return Parser.parse(scanner.nextLine());
    }

    /**
     * Prints a message.
     *
     * @param message The message to print.
     * @param <T>     The type of the message.
     */
    public <T> void printMessage(T message) {
        System.out.println(message);
    }
}
