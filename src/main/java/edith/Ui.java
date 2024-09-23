package edith;

/**
 * This Ui class handles all interactions with the user.
 * It is responsible for displaying messages including greetings and error messages.
 */
public class Ui {

    /**
     * Returns a greeting message when the chatbot starts.
     * This message is displayed to the user when the application is first launched,
     * welcoming them and prompting for further interaction.
     *
     * @return A string containing the chatbot's greeting message.
     */
    public String showGreeting() {
        return "Hello! I am EDITH, your personal chatbot companion:)" + "\nHow may I assist you?\n";
    }

    /**
     * Returns an exit message when the chatbot session ends.
     * This message is shown when the user decides to terminate the session,
     * providing a friendly sign-off.
     *
     * @return A string containing the chatbot's exit message.
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
