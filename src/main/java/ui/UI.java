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
     */
    public UI() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Initializes the user interface by displaying a welcome message and a logo.
     * This method is called at the start of the program to greet the user.
     */
    public void init() {
        String logo = """
                ___________        .__     .___               \s
                \\_   _____/_______ |__|  __| _/_____    ___.__.
                 |    __)  \\_  __ \\|  | / __ | \\__  \\  <   |  |
                 |     \\    |  | \\/|  |/ /_/ |  / __ \\_ \\___  |
                 \\___  /    |__|   |__|\\____ | (____  / / ____|
                     \\/                     \\/      \\/  \\/    \s
                """;

        System.out.println("Loading........\n" + logo);
        printLine();
        System.out.println("""
                Hello! I'm Friday!
                What would you like to do?
                """);
    }

    /**
     * Reads input from the user and returns it as an array of Strings.
     * The input is split into two parts: the command and the argument.
     *
     * @return an array containing the command and argument provided by the user
     */
    public String[] getInput() {
        String input = this.sc.nextLine();
        printLine();
        return input.split(" ", 2);
    }

    /**
     * Prints a line separator to the console.
     * This is used to visually separate different sections of the output.
     */
    public static void printLine() {
        System.out.println("----------------------------------------------");
    }

    /**
     * Terminates the user interface by displaying a goodbye message.
     * This method is called when the program is about to exit.
     */
    public void terminate() {
        System.out.println("Friday > Bye! See you soon!");
        printLine();
    }
}
