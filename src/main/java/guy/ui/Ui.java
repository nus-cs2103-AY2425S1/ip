package guy.ui;

/**
 * The class handling the main UI logic for the ThatOneGuy application.
 * Supports displaying startup and shutdown messages, albeit somewhat rude ones.
 */
public class Ui {
    private final String name = "that one guy";

    /**
     * Displays a startup message, complete with the application name.
     */
    public void greet() {
        System.out.println("I'm " + name + ".");
        System.out.println("Make it quick, I don't have much time.");
    }

    /**
     * Displays a shutdown message.
     */
    public void bye() {
        System.out.println("Whatever. Hope you never come back.");
    }
}
