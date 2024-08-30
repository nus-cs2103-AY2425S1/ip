package Nen2;

/**
 * This class deal with user interaction and print out reaction to user input
 * @author Gan Ren Yick (A0276246X)
 */
public class Ui {
    private final static String LOGO = " _   _                 __\n"
            + "| \\ | |  ___  _ ___   |_  \\ \n"
            + "|  \\| | / _ \\| '__ |    ) |\n"
            + "| |\\  ||  __/| | | |   / /_ \n"
            + "|_| \\_| \\___||_| |_|  |____|\n";
    private static final String SEPARATOR = "--------------------------------------------";


    /**
     * Greets user by printing out LOGO and greeting messages
     */
    public void greet() {
        System.out.println(SEPARATOR);
        System.out.println(LOGO + "Hello! I'm Nen2 \nWhat can I do for you?");
        System.out.println(SEPARATOR);
    }

    /**
     * Ends the conversation with ending messages
     */
    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(SEPARATOR);
    }

    /**
     * Prints separator
     */
    public void separate() {
        System.out.println(SEPARATOR);
    }

    /**
     * Prints out message with separators
     * @param message to be printed
     */
    public void print(String message) {
        separate();
        System.out.print(message);
        separate();
    }

}
