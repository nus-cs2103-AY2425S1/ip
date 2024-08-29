package sora;

/**
 * Ui stands for User Interface.
 * It deals with interactions with the user.
 */
public class Ui {
    /** Separates Different Commands */
    public static final String HORIZONTAL_LINE = "---------------------------------------------------";

    /**
     * Prints Greeting to user.
     */
    public void printGreeting() {
        System.out.println(HORIZONTAL_LINE + "\n" + "\tHello! I'm Sora!\n\tWhat can I do for you?\n" + HORIZONTAL_LINE);
    }

    /**
     * Prints Farewell to user.
     */
    public void printFarewell() {
        System.out.println("\tBye. Hope to see you again soon!");
    }

    /**
     * Prints Invalid Command to user.
     */
    public void printInvalidCommand() {
        System.out.println("\tSora doesn't understand! Please Try Again!");
    }

    /**
     * Prints Unable to Write to File to user.
     */
    public void printUnableToWriteToFile() {
        System.out.println("\tSora is unable to write to file" + Ui.HORIZONTAL_LINE);
    }
}
