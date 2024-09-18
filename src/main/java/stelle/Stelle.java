package stelle;

/**
 * Represents the main chatbot class.
 * @author Lee Ze Hao (A0276123J)
 */

public class Stelle {
    static final String NAME = "Stelle";
    static final String FILE_PATH = "./data/stelle.txt";

    private Ui ui;

    /**
     * Constructor for a chatbot class.
     * Requires name of chatbot and filepath to task storage.
     * @param name
     * @param filePath
     */
    public Stelle(String name, String filePath) {
        ui = new Ui(name, filePath);
    }

    /**
     * Constructor for a chatbot class.
     * Uses default values for the UI constructor parameters.
     */
    public Stelle() {
        ui = new Ui(NAME, FILE_PATH);
    }

    /**
     * Gets the chatbot's response as a string.
     * @param input User input / prompt, as a string.
     * @return String The bot's output string.
     */
    public String getResponse(String input) {
        return ui.getResponse(input);
    }

    /**
     * Gets the chatbot's greeting message.
     * @return String The greeting message.
     */
    public String getGreeting() {
        return ui.getGreeting();
    }

    public static void main(String[] args) {

    }
}
