public class Ui {
    private static final String BORDER = "--------------------------------------";

    public Ui() {
        // Greet user first
        printer("Hello! I'm Michael.\n" + "What can I do for you?");
    }

    public void showLoadingError(String error) {
        System.out.println(error);
    }

    public void feedback(String feedback) {
        printer(feedback);
    }

    private void printer(String text) { // Function to help print each interaction
        System.out.println(BORDER);
        System.out.println(text);
        System.out.println(BORDER);
    }
}
