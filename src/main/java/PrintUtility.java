public final class PrintUtility {
    public static final String HORIZONTAL_LINE = "____________________________________________________________";

    private PrintUtility() {
        throw new AssertionError("Cannot instantiate PrintUtilityClass");
    }

    public static void wrapPrintWithHorizontalLines(String... lines) {
        indentPrint(HORIZONTAL_LINE);
        indentPrint(lines);
        indentPrint(HORIZONTAL_LINE);
    }

    public static void indentPrint(String... lines) {
        for (String line : lines) {
            System.out.println("    " + line);
        }
    }

    public static void normalPrint(String... lines) {
        for (String line : lines) {
            System.out.println(line);
        }
    }
}
