package dudu.utils;

/**
 * Represents a class to wrap strings in a line rule
 */
public class LineWrapper {
    private static String lineRule = "--------------------------------------------";

    /**
     * Wraps the provided content with a horizontal line above and below.
     *
     * @param content The content to be wrapped with lines.
     * @return The wrapped content with lines separating it from above and below.
     */
    public static String wrap(String content) {
        return String.format("%s\n%s\n%s", lineRule, content, lineRule);
    }
}
