/**
 * Break downm user messages for other classes
 */
public class Parser {
    private String command;
    private String message;

    public Parser(String message) {
        String[] words = message.trim().split("\\s+");
        command = words[0];
    }

    public String getCommand() {
        return this.command;
    }
}
