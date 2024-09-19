package juno.ui;

/**
 * The {@code JunoUi} class handles user interaction for the Juno chat bot.
 * It provides methods for displaying welcome and farewell messages, as well as responses for invalid user inputs.
 */
public class JunoUi {
    private final String greeting;
    private final String farewell;
    private final String invalidUserInputString;
    private final String invalidFunctionInputString;

    /**
     * Constructs a {@code JunoUi} instance and initialises the logo,
     * greeting message, and farewell message for the chatbot.
     */
    public JunoUi() {
        this.greeting = "🌟 Welcome to the Future! I'm Juno, your digital assistant.\n"
                + "How can I assist you on your journey today?";
        this.farewell = "👋 Farewell for now! Looking forward to our next interaction.\n"
                + "Juno is shutting down now...";
        this.invalidUserInputString = "Well, seems like you did not input anything! Please try again.";
        this.invalidFunctionInputString = "The input you provided is invalid! "
                + "(\uD83D\uDCA1 Tip: You can use the following commands \"add\", \"list\",\"mark\", \"unmark\".)";
    }

    /**
     * Displays the welcome message to the console.
     */
    public String displayWelcomeMessage() {
        return this.greeting;
    }

    /**
     * Displays the farewell message to the console.
     */
    public String displayFarewellMessage() {
        return this.farewell;
    }

    /**
     * Returns a string for empty user input.
     *
     * @return A string indicating that the input was empty.
     */
    public String invalidUserInput() {
        return this.invalidUserInputString;
    }

    /**
     * Returns a string for invalid function input.
     *
     * @return A string indicating that the input was invalid.
     */
    // Could implement a help function to see all possible commands in the future
    public String invalidFunctionInput() {
        return this.invalidFunctionInputString;
    }
}
