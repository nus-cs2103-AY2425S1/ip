package jackbean.command;

/**
 * Represents the user interface of the JackBean chatbot.
 */
public class Ui {
    public static String horizontalLine = "____________________________________________________________";
    public static String greeting = "Hello homie! I'm jackbean.command.JackBean, a chatbot designed to help you with your daily tasks!\nHow may I help you today my homie?";
    public static String exitMessage = "Bye homie! Come back if you need anything else. jackbean.command.JackBean, signing off!";

    /**
     * Greets the user with a welcome message.
     * This JavaDoc was written by GitHub Copilot.
     */
    public static void greet() {
        System.out.println(horizontalLine);
        System.out.println(greeting);
        System.out.println(horizontalLine);
    }

    /**
     * Says goodbye to the user.
     * This JavaDoc was written by GitHub Copilot.
     */
    public static void sayGoodBye() {
        System.out.println(horizontalLine);
        System.out.println(exitMessage);
        System.out.println(horizontalLine);
    }

    /**
     * Shows a message to the user.
     * This JavaDoc was written by GitHub Copilot.
     *
     * @param message The message to be shown.
     */
    public static void showMessage(String message) {
        System.out.println(horizontalLine);
        System.out.println(message);
        System.out.println(horizontalLine);
    }
}
