package ui;

/**
 * The Launcher class serves as the entry point for starting the ChatBot application.
 * It calls the main method of the {@link ChatBotApp} to initiate the application.
 */
public class Launcher {

    /**
     * The main method that launches the ChatBot application.
     * It passes the command-line arguments to the {@link ChatBotApp}'s main method.
     *
     * @param args the command-line arguments
     */
    public static void main(final String[] args) {
        ChatBotApp.main(args);
    }
}
