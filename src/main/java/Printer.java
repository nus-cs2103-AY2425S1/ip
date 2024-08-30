public class Printer {
    private static final String line = "____________________________________________________________";
    private static final String lineIndent = "    ";
    private static final String indentation = "     ";

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
