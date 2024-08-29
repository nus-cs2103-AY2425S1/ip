package snipe.util;

import java.util.Scanner;

public class Ui {
    /** The name of the application. */
    private static final String NAME = "snipe.core.Snipe";

    /** The ASCII art logo for the application. */
    private static final String LOGO
            = "  _________      .__               \n"
            + " /   _____/ ____ |__|_____   ____  \n"
            + " \\_____  \\ /    \\|  \\____ \\_/ __ \\ \n"
            + " /        \\   |  \\  |  |_> >  ___/ \n"
            + "/_______  /___|  /__|   __/ \\___  >\n"
            + "        \\/     \\/   |__|        \\/ \n";

    /** A horizontal line used for formatting output. */
    private static final String HORIZONTAL_LINE = "_".repeat(60);
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        printWithLines("Hello! I'm\n" + LOGO + "\nWhat can I do for you?");
    }

    public void showLoadingError() {
        printWithLines("Error loading tasks from file.");
    }

    public void showError(String message) {
        printWithLines(message);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void printWithLines(String message) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(message);
        System.out.println(HORIZONTAL_LINE);
    }

    public void close() {
        scanner.close();
    }
}
