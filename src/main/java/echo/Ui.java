package echo;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    private static final String DASHLINE = "____________________________________________________________";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public void showLine() {
        System.out.println(DASHLINE);
    }

    /**
     * Sends a greet message to the user.
     */
    public void greet() {
        System.out.println("Hello from\n" + LOGO);
        showLine();
        System.out.println("Hello! I'm EchoBot");
        System.out.println("What can I do for you?");
        showLine();
    }
}
