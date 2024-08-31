package bob;

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
        String separator = Printer.lineIndent + Printer.line;
        System.out.println(separator);
        for (String text: texts) {
            System.out.print(Printer.indentation);
            System.out.println(text);
        }
        System.out.println(separator);
    }
}
