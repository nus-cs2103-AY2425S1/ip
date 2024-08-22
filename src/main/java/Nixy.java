import java.util.Scanner;

public class Nixy {
    static final String HORIZONTAL_LINE = "____________________________________________________________";
    static String[] tasks = new String[100];
    public static void main(String[] args) {
        String chatbotName = "Nixy";
        wrapPrintWithHorizontalLines("Hello! I'm " + chatbotName, "What can I do for you?");
        store();
    }

    private static void echo() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        if (input.equals("bye")) {
            exit();
            return;
        }
        wrapPrintWithHorizontalLines(input);
        echo();
    }

    /**
     * Read and store new task in the list of tasks.
     */
    private static void store() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        if (input.equals("bye")) {
            exit();
            return;
        }
        tasks[tasks.length - 1] = input;
        wrapPrintWithHorizontalLines("added: " + input);
        store();
    }

    private static void exit() {
        wrapPrintWithHorizontalLines("Bye. Hope to see you again soon!");
    }

    private static void wrapPrintWithHorizontalLines(String... lines) {
        indentPrint(HORIZONTAL_LINE);
        indentPrint(lines);
        indentPrint(HORIZONTAL_LINE);
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
