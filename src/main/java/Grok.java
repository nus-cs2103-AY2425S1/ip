import java.util.Scanner;

public class Grok {

    private static String padHorizontalLines(String msg) {
        String horizontalLine = "_".repeat(80);
        return String.join(
                "\n",
                horizontalLine, msg, horizontalLine
        );
    }

    private static String indent(String msg) {
        String indentSpaces = " ".repeat(4);
        return indentSpaces.concat(msg.replace("\n", "\n".concat(indentSpaces)));
    }

    private static String padMessage(String msg) {
        return indent(padHorizontalLines(msg));
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(padMessage("Hello! I'm Grok\nWhat ya wanna do to grok your way to success?"));
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                break;
            }
            System.out.println(padMessage(userInput));
        }


        System.out.println(padMessage("Bye. Hope to see you again soon!"));
    }
}