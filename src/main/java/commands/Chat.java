package commands;

/**
 * Deals with interactions with the user.
 */
public class Chat {
    private static final String LOGO = "  ______      _           \n"
            + " |  ____|    | |          \n"
            + " | |__   __ _| | ___  ___ \n"
            + " |  __| / _` | |/ _ \\/ _ \\\n"
            + " | |___| (_| | |  __/  __/\n"
            + " |______\\__,_|_|\\___|\\___|\n";



    /**
     * Sends a greet message to the user.
     */
    public static String greet() {
        return "Hello! I'm EchoBot.\n" + LOGO + "What can I do for you?";
    }

    /** Sends goodbye message to user */
    public static String bye() {
        return "Bye. Hope to see you again soon!";
    }
}
