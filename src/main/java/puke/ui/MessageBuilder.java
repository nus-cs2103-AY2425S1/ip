package puke.ui;

/**
 * Handles the construction and display of messages to the user.
 */
public class MessageBuilder {

    /**
     * Constructs and prints a message with a standard format.
     *
     * @param message the message to be displayed
     */
    private void buildMessage(String message) {
        System.out.println("______________________________________________________________________");
        System.out.println(message);
        System.out.println("*uwaeh*"); // Puke will always vomit after saying something
        System.out.println("______________________________________________________________________");
    }

    /**
     * Sends a greeting message to the user.
     */
    public void sendGreetingMessage() {
        buildMessage("HELLO???!!! I'm Puke and I'm Puk *uwaeh* ing... .-.");
    }

    /**
     * Sends a goodbye message to the user.
     */
    public void sendGoodbyeMessage() {
        buildMessage("BYE!!! *UWAGHhhHH* !!! see Ya *uWahghgh*");
    }

    /**
     * Sends a generic message to the user.
     *
     * @param message the message to be sent
     */
    public void sendMessage(String message) {
        buildMessage(message);
    }
}
