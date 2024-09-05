package ui;

import java.util.Scanner;

/**
 * Provides user interface functionalities for interacting with the user.
 * <p>
 * The {@code Ui} class handles the reading of user input, displaying messages, and showing lines or dividers
 * to enhance the console-based user interface.
 * </p>
 */
public class Ui {

    private Scanner scanner = new Scanner(System.in);

    /**
     * Reads the user-input command from the console.
     * <p>
     * This method waits for the user to enter a command and returns it as a {@code String}.
     * </p>
     *
     * @return The user-input command as a {@code String}.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints a horizontal line to the console.
     * <p>
     * This method prints a line of underscores to act as a visual divider in the console output.
     * </p>
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the input string to the console.
     * <p>
     * This method outputs the specified string to the console.
     * </p>
     *
     * @param str The string to be printed.
     */
    public void print(String str) {
        System.out.println(str);
    }

    /**
     * Displays a loading error message to the user.
     * <p>
     * This method prints a message indicating that there was an error loading tasks.
     * </p>
     */
    public void showLoadingError() {
        System.out.println("Tch, looks like there's a problem loading the tasks. I'll handle it, just stay calm.");
    }

    /**
     * Displays a welcome message to the user.
     * <p>
     * This method prints a welcome message and a brief introduction to the user.
     * </p>
     */
    public void showWelcome() {
        this.showLine();
        System.out.println("Hey Traveller! I'm Kuki Shinobu, deputy leader of the Arataki Gang.");
        System.out.println("Just let me know if you ever find yourself in a pinch. I can help you out.");
        this.showLine();
    }

    /**
     * Displays a goodbye message to the user.
     * <p>
     * This method prints a farewell message when the application is exiting.
     * </p>
     */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        this.showLine();
    }

    /**
     * Displays an error message to the user.
     * <p>
     * This method prints an error message with the specified details to the console.
     * </p>
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    /**
     * Displays a fancy greeting message with a logo.
     * <p>
     * This method prints a stylized greeting message and logo to the console.
     * </p>
     */
    public void showFancyGreet() {
        String logo = " ____  __.      __   .__       _________.__    .__             ___.          \n"
                + "|    |/ _|__ __|  | _|__|     /   _____/|  |__ |__| ____   ____\\_ |__  __ __ \n"
                + "|      < |  |  \\  |/ /  |     \\_____  \\ |  |  \\|  |/    \\ /  _ \\| __ \\|  |  \\\n"
                + "|    |  \\|  |  /    <|  |     /        \\|   Y  \\  |   |  (  <_> ) \\_\\ \\  |  /\n"
                + "|____|__ \\____/|__|_ \\__|    /_______  /|___|  /__|___|  /\\____/|___  /____/ \n"
                + "        \\/          \\/               \\/      \\/        \\/           \\/      \n";
        System.out.println("Hello from\n" + logo);
    }
}