package sigmabot;

public class SigmaBot {
    private Dialogue dialogue;
    public SigmaBot() {
        // Initialize the Dialogue object
        this.dialogue = Dialogue.defaultDialogue();
    }
    /**
     * Starts a new dialogue
     **/
    public void start() {
        // Start the dialogue
        this.dialogue.run();
    }
    public static void main(String[] args) {
        // Create an instance of SigmaBot
        SigmaBot bot = new SigmaBot();
        // Start the bot
        bot.start();
    }
}
