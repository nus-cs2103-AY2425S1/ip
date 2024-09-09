package processes;

/**
 * Parser class receives input from a Ui object and makes sense of it
 * A <code>Parser</code> object outputs useful information from user input
 */
public class Parser {

    /**
     * Returns the PrefixString enum that matches the input
     * Receives input, then calls the checkPrefixString method from the PrefixString enum.
     *
     * @param input The input string to compare with the PrefixString enum
     * @return The enum that matches the input string
     */
    public PrefixString parseCommand(String input) {
        return PrefixString.checkPrefixString(input);
    }


    /**
     * Returns whether the command contains an int that can be used as an index to track tasks.
     *
     * @param input The input string that may or may not be a valid index.
     * @return Whether the command has a valid integer index.
     */
    public boolean checkValidIndex(String input) {
        String trimmed = input.trim();
        try {
            Integer.parseInt(trimmed);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }



}
