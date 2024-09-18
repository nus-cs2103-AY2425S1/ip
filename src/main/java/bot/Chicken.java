package bot;


/**
 * The Chicken class serves as the main entry point for the bot application.
 * It handles user interaction, file management, and task management by using other classes.
 * The class initializes the necessary managers and the parser to process commands.
 */
public class Chicken {
    private final String name = "Bot.Chicken";
    private final ListManager chickenManager = new ListManager();
    private final FileManager chickenFileManager = new FileManager("src/main/java/data");
    private final Parser parser = new Parser(chickenManager, chickenFileManager);


    private void exit() {
        System.out.println("Bye! Hope to see you again.");
    }

    /**
     * Greets the user and starts processing commands in a loop.
     * Reads any existing data from the file first and continues to interact
     * with the user until an exit command is issued.
     */
    public String getResponse(String input) {
        assert chickenFileManager != null : "FileManager is not initialized";
        assert parser != null : "Parser is not initialized";

        return parser.parseCommand(input);
    }


    public static void main(String[] args) {

    }
}
