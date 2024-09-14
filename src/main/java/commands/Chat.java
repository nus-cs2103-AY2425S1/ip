package commands;

/**
 * Deals with interactions with the user.
 */
public class Chat {
    /**
     * Sends a greet message to the user.
     */
    public static String greet() {
        return "Hello! I'm EchoBot.\n" + "What can I do for you?";
    }

    /** Sends goodbye message to user */
    public static String bye() {
        return "Bye. Hope to see you again soon!";
    }
}
