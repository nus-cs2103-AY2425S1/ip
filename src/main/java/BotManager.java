/**
 * Entry point for the BotManager Chatbot
 *
 * @author dwsc37
 */
public class BotManager {
    private final Ui ui;

    public BotManager() {
        ui = new Ui();
    }

    public void run() {
       ui.printWelcomeMessage();
       ui.printGoodbyeMessage();
    }

    public static void main(String[] args) {
        new BotManager().run();
    }
}
