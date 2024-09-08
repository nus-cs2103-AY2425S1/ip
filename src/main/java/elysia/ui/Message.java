package elysia.ui;

/**
 * Provides utility methods for displaying formatted messages in the Elysia application.
 * Each message is enclosed within a divider for clear visual separation in the console output.
 */
public class Message {
    static String DIVIDER = "________________________________________________________________________________________\n";

    /**
     * Prints a formatted message to the console.
     * The message is enclosed between two dividers for clarity.
     *
     * @param string The message to be printed.
     */
    public static void print(String string) {
        System.out.println(DIVIDER + string + "\n" + DIVIDER);
    }
}
