package james;

/**
 * Handles user interactions, including displaying messages and reading input.
 */
class Ui {

    /**
     * Returns a greeting message to the user.
     *
     * @return A greeting string introducing the assistant and asking how to assist.
     */
    public String showGreeting() {
        return "Hello Big Boy! I'm James\nHow can I assist you today?";
    }

    /**
     * Returns an exit message to the user.
     *
     * @return A farewell string encouraging the user to come back anytime.
     */
    public String showExitMessage() {
        return "Goodbye. Come back anytime!";
    }

}
