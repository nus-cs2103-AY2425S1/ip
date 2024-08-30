package sigmabot;

public class SigmaBot {
    private Dialogue dialogue;

    public SigmaBot() {
        this.dialogue = Dialogue.defaultDialogue();
    }

    public void start() {
        this.dialogue.run();
    }

    public static void main(String[] args) {
        SigmaBot bot = new SigmaBot();
        bot.start();
    }
}
