package chatbot;

public class Format {
    public static String multiLineIndent(String multilineString) {
        // Split the string by the newline character
        String[] lines = multilineString.split("\n");

        // Create a StringBuilder to build the new string
        StringBuilder modifiedString = new StringBuilder();

        // Add 8 characters in front of each line
        String indent = "    ";
        for (String line : lines) {
            modifiedString.append(indent).append(line).append("\n");
        }

        // Return the modified string
        return modifiedString.toString();
    }
    //Format standard output
    public static String printBtnLines(String content) {
        return "    _________________________________________\n" +
                multiLineIndent(content) +
                "    _________________________________________\n";
    }
}
