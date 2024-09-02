package michael;

public class Ui {
    private final String BORDER = "--------------------------------------";

    public Ui() {
        // Greet user first
        printer("Hello! I'm Michael.\n" + "What can I do for you?");
    }

    public void showLoadingError(String error) {
        System.out.println(error);
    }

    /**
     * Prints feedback to user in formatted manner.
     *
     * @param feedback Feedback to user about the operations carried out.
     */
    public void giveFeedback(String feedback) {
        printer(feedback);
    }

    private void printer(String text) {
        System.out.println(BORDER);
        System.out.println(text);
        System.out.println(BORDER);
    }
}
