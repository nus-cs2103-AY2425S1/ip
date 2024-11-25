package commands;

/**
 * Deals with interactions with the user.
 */
public class ChatCommand {

    /** Sends a greet message to the user. */
    public static String greet() {
        return "Hello! I'm EchoBot.\n" + "What can I do for you?";
    }

    /** Sends goodbye message to user */
    public static String bye() {
        return "Bye. Hope to see you again soon!";
    }

    /** Sends help information of command 'hi' and 'bye' to user */
    public static String getHelpMessage() {
        return "Get greet and bye message from EchoBot.\n"
                + "\t hi \t\t send greet message to users \n"
                + "\t bye \t send goodbye message to users and exit\n\n"
                + "Format:\n"
                + "\t hi\n"
                + "\t bye\n"
                + "with no whitespace.";
    }
}
