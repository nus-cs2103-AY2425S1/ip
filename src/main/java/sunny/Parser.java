package sunny;
/**
 * Break downm user messages for other classes
 */
public class Parser {
    private String command;
    private String message;

    public Parser(String message) {
        String[] words = message.trim().split("\\s+", 2);
        command = words[0];
        if (words.length > 1) {
            message = words[1];
        }
    }

    public String getCommand() {
        return command;
    }

    public String getMessage() {
        return message;
    }
}
