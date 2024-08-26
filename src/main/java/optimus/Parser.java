package optimus;

// Let ChatGPT check and suggest comments and JavaDocs according to CS2103T style guide
/**
 * Handles the parsing of user commands into executable formats.
 */
public class Parser {
    /**
     * Parses the user input and splits it into command words.
     *
     * @param userInput The raw input from the user.
     * @return An array of command words from the input.
     */
    public static String[] parseCommand(String userInput) {
        return userInput.split(" ");
    }
}
