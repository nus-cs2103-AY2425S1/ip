package rei;

/**
 * Controls the output of the bot
 */
public class Ui {
    private static final String LOGO = "  ____  _____ ___ \n"
            + " |  _ \\| ____|_ _|\n"
            + " | |_) |  _|  | | \n"
            + " |  _ <| |___ | | \n"
            + " |_| \\_\\_____|___|\n";
    
    private static final String HEADER = "-----------REI♥-----------";
    private static final String FOOTER = "-----------YOU------------";

    /**
     * Outputs a welcome message
     */
    public static void printWelcomeMessage() {
        System.out.println("Annyeong! I'm\n" + LOGO);
    }

    /**
     * Outputs a message with REI's format
     * @param message the message
     */
    public static void print(String message) {
        System.out.println(HEADER);
        System.out.println(message);
        System.out.println(FOOTER);
    }
}