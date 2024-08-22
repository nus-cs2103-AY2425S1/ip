import java.util.Scanner;

public class Nixy {
    static final String HORIZONTAL_LINE = "____________________________________________________________";
    public static void main(String[] args) {
        String chatbotName = "Nixy";
        indentPrint(wrapWithHorizontalLines("Hello! I'm " + chatbotName, "What can I do for you?"));
        echo();
    }

    private static void echo() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        if (input.equals("bye")) {
            exit();
            return;
        }
        indentPrint(wrapWithHorizontalLines(input));
        echo();
    }

    private static void exit() {
        indentPrint(wrapWithHorizontalLines("Bye. Hope to see you again soon!"));
    }

    private static String[] wrapWithHorizontalLines(String... lines) {
        String[] wrappedLines = new String[lines.length + 2];
        wrappedLines[0] = HORIZONTAL_LINE;
        System.arraycopy(lines, 0, wrappedLines, 1, lines.length);
        wrappedLines[wrappedLines.length - 1] = HORIZONTAL_LINE;
        return wrappedLines;
    }

    private static void indentPrint(String... lines) {
        for (String line : lines) {
            System.out.println("    " + line);
        }
    }

    private static void normalPrint(String... lines) {
        for (String line : lines) {
            System.out.println(line);
        }
    }
}
