package botimusprime.ui;

import java.util.Scanner;

/**
 * The UI class handles all interactions with the user.
 * It provides methods to display messages, read user input,
 * and manage the input scanner.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a new UI instance and initializes the Scanner for reading user input.
     */
    private static final String GREETING_MESSAGE =
            "____________________________________________________________\n"
                    + " Greetings, human. I am BotimusPrime.\n"
                            + " What can I do for you?\n"
                                    + "____________________________________________________________";
    private static final String BYE_MESSAGE =
            "____________________________________________________________\n"
                    + "Autobots, ROLL OUT!!!\n"
                            + "____________________________________________________________\n";
    private static final String LINE =
            "____________________________________________________________\n";

    public Ui() {
        this.scanner = new Scanner(System.in);
    }



    /**
     * Displays the greet message when the chatbot starts.
     */
    public void greet() {
        System.out.println(GREETING_MESSAGE);
    }

    /**
     * Displays the bye message when the user exits the chatbot.
     */
    public void bye() {
        System.out.println(BYE_MESSAGE);
    }

    /**
     * Displays a line separator.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Reads a command entered by the user.
     * This method waits for user input and trims any leading or trailing whitespace.
     *
     * @return The command entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine().
                trim();
    }

    /**
     * Closes the Scanner instance.
     */
    public void closeScanner() {
        scanner.close();
    }

    /**
     * Displays a generic error message.
     */
    public void showError() {
        System.out.println("Error, please try again");
    }
}