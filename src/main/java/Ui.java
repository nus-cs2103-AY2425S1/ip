/**
 * This class deal with user interaction and print out reaction to user input
 * @author Gan Ren Yick (A0276246X)
 */
public class Ui {
    private final static String logo = " _   _                 __\n"
            + "| \\ | |  ___  _ ___   |_  \\ \n"
            + "|  \\| | / _ \\| '__ |    ) |\n"
            + "| |\\  ||  __/| | | |   / /_ \n"
            + "|_| \\_| \\___||_| |_|  |____|\n";
    private static final String separator = "--------------------------------------------";


    /**
     * Greets user by printing out logo and greeting messages
     */
    public void greet() {
        System.out.println(separator);
        System.out.println(logo + "Hello! I'm Nen2 \nWhat can I do for you?");
        System.out.println(separator);
    }

    /**
     * Ends the conversation with ending messages
     */
    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(separator);
    }

    /**
     * Prints separator
     */
    public void separate() {
        System.out.println(separator);
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
