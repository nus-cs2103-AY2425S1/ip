/**
 * Provides the Parsing functionality that allows Delphi to process different types of input
 *
 * @author jordanchan
 */
public class Parser {
    /**
     * Checks if the first part of the input matches a certain string up to a given number of characters
     * @param original
     * @param index
     * @param comparison
     * @return
     */
    public static boolean checkStringPrefix(String original, int index, String comparison) {
        // Ensure the index is within the bounds of the original string
        if (index > original.length()) {
            index = original.length();
        }

        // Get the substring from the original string up to the specified index
        String prefix = original.substring(0, index);

        // Compare the prefix with the comparison string
        return prefix.equals(comparison);
    }
}
