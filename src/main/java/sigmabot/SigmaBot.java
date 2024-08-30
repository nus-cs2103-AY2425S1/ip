package sigmabot;

/**
 * The {@code SigmaBot} class serves as the main entry point for the application.
 * It initializes and starts a dialogue session, allowing the user to interact with the bot.
 */
public class SigmaBot {
    private Dialogue dialogue;

    /**
     * Constructs a new {@code SigmaBot} instance.
     * Initializes the dialogue component that manages user interactions.
     */
    public SigmaBot() {
        this.dialogue = Dialogue.defaultDialogue();
    }

    /**
     * Starts the dialogue session, initiating interaction with the user.
     */
    public void start() {
        this.dialogue.run();
    }

    /**
     * The main method that serves as the entry point of the application.
     * It creates an instance of {@code SigmaBot} and starts the dialogue session.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        SigmaBot bot = new SigmaBot();
        bot.start();
    }
}
