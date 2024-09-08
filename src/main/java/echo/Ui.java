package echo;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";


    /**
     * Sends a greet message to the user.
     */
    public void greet() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println("Hello! I'm echo.EchoBot");
        System.out.println("What can I do for you?");
    }
}
