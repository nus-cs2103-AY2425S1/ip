package jeff;

import java.util.Scanner;

/**
 * Handles interactions with the user.
 *
 * The Ui class provides methods for displaying messages to the user, reading user input,
 * and other related interactions.
 */
public class Ui {
    // Reset
    public static final String RESET = "\033[0m";  // Text Reset

    // Regular Colors
    public static final String RED = "\033[0;31m";     // RED

    // UI Elements
    private static final String LINE = "--------------------------------------------";
    private static final String LOGO =
            """
                                                                        ____._________________________________|
                      _____ ___.__.   ____ _____    _____   ____       |    |\\_   _____/\\_   _____/\\_   _____/|
                     /     <   |  |  /    \\\\__  \\  /     \\_/ __ \\      |    | |    __)_  |    __)   |    __)  |
                    |  Y Y  \\___  | |   |  \\/ __ \\|  Y Y  \\  ___/  /\\__|    | |        \\ |     \\    |     \\   |
                    |__|_|  / ____| |___|  (____  /__|_|  /\\___  > \\________|/_______  / \\___  /    \\___  /   |
                          \\/\\/           \\/     \\/      \\/     \\/                    \\/      \\/         \\/    |
                    """;

    // Scanner
    private static final Scanner sc = new Scanner(System.in); // Scanner object to detect user input


    public void showWelcome() {
        System.out.println("Hello there!");
        System.out.println(LOGO);
        System.out.println("What can I do for you?");
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public void showMessage(String msg) {System.out.println(msg);}

    public void showError(String error) {
        System.out.println(RED + "ERROR!" + RESET + " " + error);
    }

    public String readCommand() {
        return sc.nextLine().trim();
    }

    public void showExit() {
        System.out.println("Bye for now...");
    }
}
