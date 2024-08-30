package sunny;
/**
 * Breaks down user messages for other classes
 */
public class Parser {
    private String command;
    private String description;

    public Parser(String message) {
        String[] words = message.split("\\s+", 2);
        command = words[0];
        if (words.length > 1) {
            description = words[1];
        }
    }

    public String getCommand() {
        return command;
    }

    public String getMessage() {
        return description;
    }
}
