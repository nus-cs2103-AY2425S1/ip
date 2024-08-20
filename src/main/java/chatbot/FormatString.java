package chatbot;

/**
 * Formats string to standardise chatbot
 * output format
 *
 * @author celeschai
 * @version 1.0 20 Aug 2023
 */
public class FormatString {
    /**
     * Ensure correct indentation for any text
     * Indent single and multiline strings
     *
     * @param multilineString single or multiline string
     * @return formatted string for chatbot output
     */
    public static String multiLineIndent(String multilineString) {
        String[] lines = multilineString.split("\n");

        // Create a StringBuilder for efficient concatenation
        StringBuilder modifiedString = new StringBuilder();

        // Append 4 characters in front of each line
        String indent = "    ";
        for (String line : lines) {
            modifiedString.append(indent).append(line).append("\n");
        }

        return modifiedString.toString();
    }

    //Format standard output to be sandwiched by two lines
    public static String printBtnLines(String content) {
        return "    __________________________________________________\n" +
                multiLineIndent(content) +
                "    __________________________________________________\n";
    }
}
