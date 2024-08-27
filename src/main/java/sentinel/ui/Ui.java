package sentinel.ui;

/**
 * Ui for Sentinel chatbot.
 */
public class Ui {
    public static String MESSAGE_LINE = "---------------------------------------";

    /**
     * Outputs a message on the screen
     *
     * @param message Message to be outputted.
     */
    public void output(String message) {
        System.out.println(MESSAGE_LINE);
        System.out.println(message);
        System.out.println(MESSAGE_LINE);
    }

    /**
     * Shows an error message on the screen.
     *
     * @param errorMessage Error message to be shown.
     */
    public void showError(String errorMessage) {
        output("Oh no! : " + errorMessage);
    }
}
