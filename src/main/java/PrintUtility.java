/**
 * Utility class for printing messages to the console.
 * This class is used to print messages with horizontal lines and indentation.
 */
public final class PrintUtility {
    public static final String HORIZONTAL_LINE = "____________________________________________________________";

    /**
     * Prevents instantiation of the PrintUtility class.
     */
    private PrintUtility() {
        throw new AssertionError("Cannot instantiate PrintUtilityClass");
    }

    /**
     * Prints the specified lines with horizontal lines wrapped around them.
     *
     * @param lines The lines to print.
     */
    public static void wrapPrintWithHorizontalLines(String... lines) {
        indentPrint(HORIZONTAL_LINE);
        indentPrint(lines);
        indentPrint(HORIZONTAL_LINE);
    }

    /**
     * Prints the specified lines with indentation.
     *
     * @param lines The lines to print.
     */
    public static void indentPrint(String... lines) {
        for (String line : lines) {
            System.out.println("    " + line);
        }
    }

    /**
     * Prints the specified lines without any special formatting.
     *
     * @param lines The lines to print.
     */
    public static void normalPrint(String... lines) {
        for (String line : lines) {
            System.out.println(line);
        }
    }
}
