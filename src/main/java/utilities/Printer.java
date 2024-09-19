package utilities;

import java.lang.StringBuilder;

/**
 * The {@code Printer} class provides printing methods for printing formatted text to the console.
 */
public class Printer {
    private static final String line = "____________________________________________________________";
    private static final String lineIndent = "    ";
    private static final String indentation = "     ";

    /**
     * Prints the provided array of strings in a formatted manner, with each string indented and separated by ann
     * indented horizontal line.
     *
     * @param texts an array of strings to be printed, each on a new line.
     */
    public static void prettyPrint(String[] texts) {
        System.out.println(Printer.format(texts));
    }

    public static String format(String[] texts) {
        StringBuilder result = new StringBuilder();
        for (String text: texts) {
            result.append(text);
            result.append('\n');
        }
        return result.toString();
    }
}
