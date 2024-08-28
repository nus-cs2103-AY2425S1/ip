package joeduck.ui;

import java.util.Scanner;

/**
 * Handles user input/output.
 */
public class Ui {
    private static final String LINE_DIVIDER = "---";
    private static final String MOTD = "Welcome to Joe Duck";
    private static final String EXIT_MESSAGE = "Goodbye from Joe Duck";

    /**
     * Scanner to be used with Singleton Ui instance in JoeDuck.
     */
    public final Scanner scanner = new Scanner(System.in);

    public void printResponse(String res) {
        System.out.println(LINE_DIVIDER);
        System.out.println(res);
        System.out.println(LINE_DIVIDER);
    }

    public void printError(String msg) {
        printResponse("Error: " + msg);
    }

    public boolean scannerHasNextLine() {
        return scanner.hasNextLine();
    }

    public String scannerNextLine() {
        return scanner.nextLine();
    }

    public void onStart() {
        printResponse(MOTD);
    }

    public void onExit() {
        printResponse(EXIT_MESSAGE);
    }
}
