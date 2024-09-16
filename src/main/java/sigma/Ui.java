package sigma;

/**
 * Class for handling interactions with the user
 *
 * @author Qiao Yi
 */
public class Ui {
    /**
     * Greets the user upon running the program
     *
     * @return The welcome message
     */
    public String welcome() {
        return "Hello! I'm Sigma \nWhat do you gyat to do today?\n";
    }

    /**
     * Alerts the user that there is no recognised command
     */
    public String dontRecognise() {
        return "erm, what the sigma? i don't recognise that command.";
    }

}
