package windebot;

import java.util.Scanner;

/**
 * The Ui class handles user interaction, including input and output.
 * It provides methods for displaying messages to the user and reading user input.
 */

public class Ui {
    private static Scanner scanner;
    private String output;

    /**
     * Constructs a Ui object and initializes the scanner for reading user input.
     */

    Ui() {
        this.scanner = new Scanner(System.in);
        this.output = "";
    }

    /**
     * Displays the greeting message when the application starts.
     */

    public static void greet() {
        System.out.println("__        __              _");
        System.out.println("\\ \\      / /(_) _ __   __| | ___ ");
        System.out.println(" \\ \\ /\\ / / | || '_  \\/ _` |/ _ \\ ");
        System.out.println("  \\ V  V /  | || | | || (_ || __/ ");
        System.out.println("   \\_/\\_/   |_||_| |_|\\__,_|\\___| ");
        System.out.println("Hello! I'm Winde\n" + "What can I do for you?");
    }

    /**
     * Reads the next line of user input.
     *
     * @return The trimmed user input string.
     */

    public static String read() {
        return scanner.nextLine().trim();
    }

    /**
     * Checks if there is another line of user input.
     *
     * @return true if there is another line of input, false otherwise.
     */

    public static boolean hasNextCommand() {
        return scanner.hasNextLine();
    }

    /**
     * Displays an error message for loading tasks.
     */

    public static void showLoadingError() {
        System.out.println("Error Loading Tasks to File");
    }

    /**
     * Displays a separator line.
     */

    public static void showLine() {
        System.out.println("---------------------");
    }

    /**
     * Prints the specified output string to the console.
     *
     * @param addWhat The string to print.
     */

    public void print(String addWhat) {
        this.output += addWhat + "\n";
    }

    public String getOutput() {
        return this.output;
    }

    public void invalidDateFormatMessage() {
        this.print("FOLLOW THE CORRECT DATE FORMAT: DD/MM/YYYY HH:MM");
    }

    public void emptyDescriptionMessage() {
        this.print("Either you've missed some parameters or you typed the command wrongly");
    }

    public void indexOutBoundsMessage() {
        this.print("Input a valid task number");
    }

    public void notIntegerMessage() {
        this.print("Input a valid Integer stoopid");
    }

    public void tooManyParametersMessage() {
        this.print("One input at a time!");
    }

    public void unsupportedCommandMessage() {
        this.print("GO TO OUR GITHUB PAGE FOR THE LIST OF COMMANDS STOOPID!");
    }

    public void unsupportedFilePathMessage() {
        this.print("WRONG SAVE FILE!");
    }
}
