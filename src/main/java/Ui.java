/**
 * Ui represents the user interface that the user interacts with the chatbot
 */
public class Ui {

    /**
     * Helper function to print dividers
     */
    public void showDivider() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Helper function to print text output from commands
     */
    public void showText(String text) {
        System.out.println(text);
    }

    /**
     * Helper function to print errors from commands
     */
    public void showError(String error) {
        System.out.printf("Error | %s%n", error);
    }

    /**
     * Helper function to print welcome message
     */
    public void showWelcome() {
        showDivider();
        System.out.println("Hello! I'm Tohru");
        System.out.println("What can I do for you?");
        showDivider();
    }

}
