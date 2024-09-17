package stelle;

/**
 * Represents the UI handler of the chatbot.
 * @author Lee Ze Hao (A0276123J)
 */

public class Ui {
    private String name;
    private Parser parser;

    /**
     * Creates a stelle.Ui handler instance.
     * @param name Name of the chatbot.
     */
    public Ui(String name, String filePath) {
        this.name = name;
        this.parser = new Parser(filePath);
    }

    public String getResponse(String input) {
        try {
            return parser.processInput(input);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Returns a greeting message.
     * @return String The greeting message.
     */
    public String getGreeting() {
        return "Hello! I'm " + name + " (simulated).\nWhat brings you here today?";
    }
}
