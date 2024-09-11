package edith;

/**
 * This Ui class handles all interactions with the user.
 * It is responsible for displaying messages, prompts, errors, and other user interface elements.
 */
public class Ui {

    /**
     * Displays a greeting message when the chatbot starts.
     */
    public String showGreeting() {
        return "Hello! I am EDITH, your personal chatbot companion:)" + "\nHow may I assist you?\n";
    }

    /**
     * Displays an exit message when the chatbot ends.
     */
    public String showExitMessage() {
        return "It has been my pleasure helping you. Hope to see you again soon!";
    }

    /**
     * Returns an error message for invalid date/time formats.
     *
     * @return A string message indicating the correct date/time format.
     */
    public String invalidDateTimeError() {
        return "Invalid date/time format. Please use 'day/month/year HHmm' (e.g '13/9/2024 1800').";
    }
}
