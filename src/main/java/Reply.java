/**
 * This class represents a reply from the chatbot.
 * It provides methods to print a reply, such as greeting.
 */
public class Reply {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String INDENT = " ";

    public static void printMessage(String message) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(message);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Print greeting message
     */
    public static void printGreeting() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(INDENT + "Hello! I'm  Carine");
        System.out.println(INDENT + "What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }
    /**
     * Print goodbye message
     */
    public static void printGoodbye() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(INDENT + "Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }

}
