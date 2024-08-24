package myapp.core;

/**
 * The BingBong class serves as the entry point for the BingBong application.
 * It initializes the necessary components and starts the application.
 */
public class BingBong {

    /**
     * The main method of the BingBong application. It initializes the UI, bot, and storage,
     * then starts the bot to run the application.
     *
     * @param args command-line arguments (not used).
     */
    public static void main(String[] args) {
        BingBongUi ui = new BingBongUi();
        BingBongBot bot = new BingBongBot(ui, new Storage("./data/tasks.txt"));
        bot.run();
    }
}
