package ui;

import java.util.Scanner;

/**
 * Handles interactions with the user by providing input and output methods.
 * The UI class is responsible for displaying messages to the user and reading user input.
 */
public class UI {

    private final Scanner sc;

    /**
     * Constructs a UI object and initializes the Scanner for reading user input.
     * The Scanner is used to read input from the console.
     */
    public UI() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Initializes the user interface by returning a welcome message and a logo.
     * This method is called at the start of the program to greet the user.
     *
     * @return A string containing the ASCII art logo and the welcome message.
     */
    public String init() {
        return """
                ___________        .__     .___               \s
                \\_   _____/_______ |__|  __| _/_____    ___.__.
                 |    __)  \\_  __ \\|  | / __ | \\__  \\  <   |  |
                 |     \\    |  | \\/|  |/ /_/ |  / __ \\_ \\___  |
                 \\___  /    |__|   |__|\\____ | (____  / / ____|
                     \\/                     \\/      \\/  \\/    \s
               \s
                Hello! I'm Friday!
                What would you like to do?               \s
               \s""";
    }

    /**
     * Reads a line of input from the user and splits it into command and arguments.
     * The input is split into two parts: the command and the argument (if any).
     * If the user only provides a command, the second part of the array will be empty.
     * After reading the input, a separator line is printed.
     *
     * @return an array of two Strings: the first is the command and the second is the argument.
     */
    public String[] getInput() {
        String input = this.sc.nextLine();
        System.out.println(printLine());
        return input.split(" ", 2);
    }

    /**
     * Returns a line separator string to visually separate different sections of output.
     * This is primarily used after user input to add clarity to the console display.
     *
     * @return a string containing a line of dashes for separation.
     */
    public static String printLine() {
        return "----------------------------------------------";
    }
}
