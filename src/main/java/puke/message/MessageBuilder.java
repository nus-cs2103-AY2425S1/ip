package puke.message;

/**
 * Handles the construction and display of messages to the user.
 */
public class MessageBuilder {

    /**
     * Constructs and formats a message with a standard format.
     *
     * @param message the message to be displayed.
     * @return the formatted message with additional decorations.
     */
    private String buildMessage(String message) {
        System.out.println("______________________________________________________________________");
        System.out.println(message);
        System.out.println("*uwaeh*"); // Puke will always vomit after saying something
        System.out.println("______________________________________________________________________");
        return message + " *uwaeh*";
    }

    /**
     * Sends a greeting message to the user.
     *
     * @return the greeting message with standard formatting.
     */
    public String sendGreetingMessage() {
        return buildMessage("HELLO???!!! I'm Puke and I'm Puk *uwaeh* ing... .-.");
    }

    /**
     * Sends a goodbye message to the user.
     *
     * @return the goodbye message with standard formatting.
     */
    public String sendGoodbyeMessage() {
        return buildMessage("BYE!!! *UWAGHhhHH* !!! see Ya *uWahghgh*");
    }

    /**
     * Sends a generic message to the user.
     *
     * @param message the message to be sent.
     * @return the formatted message with standard decorations.
     */
    public String sendMessage(String message) {
        return buildMessage(message);
    }
}
