package guy.ui;

/**
 * The class handling the main UI logic for the ThatOneGuy application.
 * Supports displaying startup and shutdown messages, albeit somewhat rude ones.
 */
public class Ui {
    private final String greeting = "I'm that one guy. Make it quick, I don't have much time.";
    private final String sendoff = "Whatever. Hope you never come back.";


    /**
     * Displays a startup message, complete with the application name.
     */
    public void greet() {
        System.out.println(greeting);
    }

    /**
     * Retrieves the greeting message.
     * @return the greeting message
     */
    public String getGreeting() {
        return greeting;
    }

    /**
     * Displays a shutdown message.
     */
    public void bye() {
        System.out.println(sendoff);
    }
}
