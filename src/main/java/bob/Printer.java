package bob;

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

    /*public static String format(String[] texts) {
        String separator = Printer.lineIndent + Printer.line;
        StringBuilder result = new StringBuilder(separator);
        result.append('\n');
        for (String text: texts) {
            result.append(Printer.indentation);
            result.append(text);
            result.append('\n');
        }
        result.append(separator);
        return result.toString();
    }*/

    public static String format(String[] texts) {
        StringBuilder result = new StringBuilder();
        for (String text: texts) {
            result.append(text);
            result.append('\n');
        }
        return result.toString();
    }
}
