package bao.main;

import java.util.Scanner;

/**
 * The Ui class handles the display and interactions with the user.
 * Contains methods to display messages and read user input.
 */
public class Ui {
    private Scanner scanner;
    private static String baoHappy =
            "     ___\n"
                    + "   /     \\\n"
                    + "  /       \\\n"
                    + " |  ^   ^  |\n"
                    + " |    3    |\n"
                    + " \\________/\n";
    private static String baoSad =
            "     ___\n"
                    + "   /     \\\n"
                    + "  /       \\\n"
                    + " |  T   T  |\n"
                    + " |    ^    |\n"
                    + " \\________/\n";

    /**
     * Constructs a new Ui object that initializes a scanner to read user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads a line of user input from the console.
     *
     * @return Next line of user input.
     */
    public String command() {
        return scanner.nextLine();
    }

    /**
     * Displays the welcome message and Bao's happy face at the start of the application.
     */
    public void showWelcomeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println(baoHappy);
        System.out.println("Bao says hello! Bao's name is Bao but you can call me Bao");
        System.out.println("Bao is ready for instructions");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays the exit message and Bao's sad face at the termination of the application.
     */
    public void showExitMessage() {
        System.out.println(baoSad);
        System.out.println("Bye :( Come back soon!");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a generic message to the user.
     *
     * @param message Message to be displayed.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }
}
