package sentinel.ui;

/**
 * Ui for Sentinel chatbot.
 */
public class Ui {
    public static final String MESSAGE_LINE = "---------------------------------------";
    private String currentMessage;
    /**
     * Outputs a message on the screen
     *
     * @param message Message to be outputted.
     */
    public void output(String message) {
        System.out.println(MESSAGE_LINE);
        System.out.println(message);
        System.out.println(MESSAGE_LINE);

        currentMessage = message;
    }

    /**
     * Shows an error message on the screen.
     *
     * @param errorMessage Error message to be shown.
     */
    public void showError(String errorMessage) {
        output("Oh no! : " + errorMessage);
        currentMessage = errorMessage;
    }

    /**
     * Gets the current message outputted.
     *
     * @return Current message outputted.
     */
    public String getCurrentMessage() {
        return currentMessage;
    }
}
