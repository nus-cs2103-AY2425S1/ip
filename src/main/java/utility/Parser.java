package utility;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * The {@code Parser} class provides utility methods to extract different parts of a user's input.
 */
public class Parser {
    
    // Regular expression pattern for yyyy-mm-dd
    private static final Pattern DATE_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
    
    /**
     * Checks if the provided date string matches the yyyy-mm-dd format.
     *
     * @param dateStr the date string to validate
     * @return true if the date string matches the format, false otherwise
     */
    private static boolean isValidDateFormat(String dateStr) {
        Matcher matcher = DATE_PATTERN.matcher(dateStr);
        return matcher.matches();
    }
    
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
        // Split the input by '/', limit to 2 parts to avoid splitting dates that contain '/'
        String[] parts = input.split("/");
        
        String processing = parts[1];
        // Remove 'by' or 'from' prefixes (case-insensitive)
        processing = processing.replaceFirst("(?i)by\\s+", "").replaceFirst("(?i)from\\s+", "").trim();
        
        // Assert that the date is in yyyy-mm-dd format
        assert isValidDateFormat(processing) : "Date format should be yyyy-mm-dd.";
        return processing;
    }
    
    /**
     * Extracts the second date part of the input string, which is after the second '/'.
     *
     * @param input the input string containing a date prefixed by 'to'
     * @return the second date part of the input string
     */
    public static String extractSecondDate(String input) {
        // Split the input by '/', limit to 3 parts
        String[] parts = input.split("/", 3);
        assert parts.length >= 3 : "Input does not contain enough '/' for second date extraction.";
        
        String processing = parts[2];
        // Remove 'to' prefix (case-insensitive)
        processing = processing.replaceFirst("(?i)to\\s+", "").trim();
        
        // Assert that the date is in yyyy-mm-dd format
        assert isValidDateFormat(processing) : "Date format should be yyyy-mm-dd.";
        
        return processing;
    }
    
    /**
     * Extracts the integer involved from the input string.
     * <p>
     * Assumes that the input string is a command followed by an integer argument,
     * and extracts the integer argument from the second word of the input string.
     * This method asserts that the input has at least two words, and that the second word is a valid integer.
     *
     * @param input the input string containing a command followed by an integer
     * @return the integer value of the second word in the input string
     */
    public static Integer extractIntegerInvolved(String input) {
        // Split the input string by spaces
        String[] splitArray = input.split(" ");
        
        // Assert that the array has at least two elements
        assert splitArray.length >= 2 : "Missing Arguments in Input";
        
        // Assert that the second element is a valid integer
        assert isNumeric(splitArray[1]) : "Second argument is not a valid integer";
        
        // Return the integer value of the second element
        return Integer.valueOf(splitArray[1]);
    }
    
    /**
     * Checks if the provided string is a valid integer.
     *
     * @param str the string to check
     * @return true if the string can be parsed as an integer, false otherwise
     */
    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    /**
     * Returns the input string excluding the first word.
     * <p>
     * Assumes that the input string contains at least two words.
     * This method asserts that the input has at least two words.
     *
     * @param input the input string
     * @return a string containing the input without the first word
     */
    public static String excludeFirstWord(String input) {
        String[] splitArray = input.split(" ");
        assert splitArray.length >= 2 : "Missing Arguments in Input";
        return String.join(" ", Arrays.copyOfRange(splitArray, 1, splitArray.length));
    }
    
    public static String excludeDescriptionFromTodo(String input) {
        return String.join(" ", Arrays.copyOfRange(input.split(" "), 1, input.split(" ").length));
    }
}
