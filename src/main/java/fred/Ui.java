package fred;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Ui class handles all user interactions, including printing messages
 * to the console and receiving user input. It is responsible for greeting the user,
 * printing task lists, and displaying messages in a consistent format.
 */
public class Ui {
    String line = "____________________________________________________________";

    /**
     * Constructs a new Ui object and initializes the scanner for user input.
     */
    public Ui() {
    }

    /**
     * Displays a message to the user, surrounded by horizontal lines for formatting.
     *
     * @param message The message to be displayed to the user.
     */
    void say(String message) {
        System.out.println(line);
        System.out.println(message);
        System.out.println(line);
    }
}
