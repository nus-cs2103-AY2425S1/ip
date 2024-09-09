package utility;

/**
 * The {@code Parser} class provides utility methods to extract different parts of a user's input.
 */
public class Parser {
    
    /**
     * Extracts the command part of the input string.
     *
     * @param input the input string containing a command followed by other details
     * @return the command part of the input string
     */
    public static String extractCommand(String input) {
        return input.split(" ")[0];
    }
    
    /**
     * Extracts the description part of the input string, which is before the first '/'.
     *
     * @param input the input string containing a description and date details
     * @return the description part of the input string
     */
    public static String extractDescription(String input) {
        return input.split("/")[0].trim();
    }
    
    /**
     * Extracts the first date part of the input string, which is after the first '/'.
     *
     * @param input the input string containing a description and date details
     *              the date is prefixed by 'by' or 'from'
     * @return the first date part of the input string
     */
    public static String extractFirstDate(String input) {
        String processing = input.split("/")[1];
        processing = processing.replace("by ", "").trim();
        return processing.replace("from ", "").trim();
    }
    
    /**
     * Extracts the second date part of the input string, which is after the second '/'.
     *
     * @param input the input string containing a date prefixed by 'to'
     * @return the second date part of the input string
     */
    public static String extractSecondDate(String input) {
        String processing = input.split("/")[2];
        return processing.replace("to", "").trim();
    }
}
