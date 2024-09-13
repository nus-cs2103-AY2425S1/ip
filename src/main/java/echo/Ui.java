package echo;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    private static final String LOGO = "  ______      _           \n"
            + " |  ____|    | |          \n"
            + " | |__   __ _| | ___  ___ \n"
            + " |  __| / _` | |/ _ \\/ _ \\\n"
            + " | |___| (_| | |  __/  __/\n"
            + " |______\\__,_|_|\\___|\\___|\n";



    /**
     * Sends a greet message to the user.
     */
    public void greet() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println("Hello! I'm EchoBot");
        System.out.println("What can I do for you?");
    }
}
