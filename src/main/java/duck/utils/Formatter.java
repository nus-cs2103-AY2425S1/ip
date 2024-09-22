package duck.utils;

/**
 * Class that is responsible for formatting responses from the chatbot to the
 * user.
 */
public class Formatter {
    /**
     * Indents the text by a specified number of spaces for each line.
     *
     * @param text        Original text.
     * @param indentLevel Number of spaces to indent by.
     * @return Indented Text.
     */
    public static String indentText(String text, int indentLevel) {
        StringBuilder indentedText = new StringBuilder();
        String[] lines = text.split("\n");

        for (String line : lines) {
            indentedText.append(" ".repeat(indentLevel)).append(line).append("\n");
        }

        // Convert to String and trim the last newline character
        return indentedText.toString().replaceAll("[\n\r]$", "");
    }
}
