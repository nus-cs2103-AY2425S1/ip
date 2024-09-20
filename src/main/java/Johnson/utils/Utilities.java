package Johnson.utils;

/**
 * Utility class providing common methods used across the application.
 */
public class Utilities {

    /**
     * A constant string representing a line separator.
     */
    public static final String line = "____________________________________________________________\n";

    /**
     * Prints a message surrounded by line separators to the console.
     *
     * @param msg the message to be printed.
     */
    public static void OutlineMessage(String msg) {
        System.out.println(line + msg + "\n" + line);
    }
}
