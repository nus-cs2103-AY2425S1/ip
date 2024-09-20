package ratchet.ui;

/**
 * Class to handle interaction with the user.
 */
public class Ui {
    /**
     * Returns a greeting to the user.
     *
     * @return Greeting to the user.
     */
    public String greet() {
        return "Hello! I'm Ratchet, What can I do for you?";
    }

    /**
     * Returns a farewell to the user.
     *
     * @return Farewell to the user.
     */
    public String exit() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns a string with a line separator
     *
     * @param message The message to prepend a line separator to.
     * @return String with a line separator.
     */
    public String printWithSeparator(String message) {
        return System.lineSeparator() + message;
    }
}
