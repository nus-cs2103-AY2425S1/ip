package lutodo.ui;

import java.util.Scanner;

/**
 * Contains Ui operations.
 */
public class Ui {

    // A long line to divide the messages sent to users.
    public static final String LINE = "________________________________________________";

    /**
     * Shows a line.
     */
    public static void showLine() {
        System.out.println(LINE);
    }

    /**
     * Shows greeting to user.
     */
    public static void showWelcome() {
        showLine();
        System.out.println(" Hello! I'm LuToDo. What can I do for you?");
        showLine();
    }

    /**
     * Shows bye to the user.
     */
    public static void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Shows the error occurred to the user.
     *
     * @param errorMessage The error message.
     */
    public static void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Scans a whole line imputed by the user and returns the string of that line.
     *
     * @return The command message line read.
     */
    public static String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }



}
