package wenjiebot;

import java.util.Scanner;

import javafx.application.Platform;

/**
 * The Ui class handles interactions with the user, including displaying messages and reading input.
 * It provides methods to show welcome and farewell messages, display lines for formatting,
 * show error messages, and read user commands.
 */
public class Ui {

    private String output;

    /**
     * Displays a welcome message to the user.
     * This message is shown when the bot is first started.
     */
    public void showWelcome() {
        String greeting = "Eh wassup la bro, my name is Wen Jie.\n What you want? \nI got these commands for you, try"
                + " them out my guy:\n"
                + "1. todo <task>\n"
                + "2. event <task> /from <dd/mm/yyyy HHmm> /to <dd/mm/yyyy HHmm>\n"
                + "3. deadline <task> /by <dd/mm/yyyy HHmm>\n"
                + "4. list\n"
                + "5. mark <task index>\n"
                + "6. unmark <task index>\n"
                + "7. delete <task index>\n"
                + "8. find <keyword>\n"
                + "9. snooze <task no> /from <dd/mm/yyyy HHmm> /to <dd/mm/yyyy HHmm> OR "
                + "snooze <task no> /by <dd/mm/yyyy HHmm>\n"
                + "10. bye\n";
        showLine();
        System.out.println(greeting);
        showLine();
        this.output = greeting;
    }

    /**
     * Displays a farewell message to the user.
     * This message is shown when the bot is about to exit.
     */
    public void showFarewell() {
        String farewell = "Paiseh bro I zao liao, see you around ah bro.";
        showLine();
        System.out.println(farewell);
        showLine();

        this.output = farewell;
    }

    /**
     * Displays a line for formatting output.
     * This line is used to separate different sections of output for clarity.
     */
    public void showLine() {
        String line = "____________________________________________________________";
        System.out.println(line);
    }

    /**
     * Displays an error message to the user.
     * This method is used to inform the user of any errors that occur during execution.
     *
     * @param errorMessage the error message to be displayed.
     */
    public void showError(String errorMessage) {
        showLine();
        System.out.println(errorMessage);
        showLine();

        this.output = errorMessage;
    }

    /**
     * Reads a command input from the user.
     * This method waits for the user to enter a command and returns it as a string.
     *
     * @param scanner the Scanner object used to read input from the user.
     * @return the command entered by the user as a string.
     */
    public String readCommand(Scanner scanner) {
        return scanner.nextLine();
    }
    public String getOutput() {
        return output;
    }
    public void setOutput(String message) {
        this.output = message;
    }

    public void exitApp() {
        Platform.exit();
    }

}
