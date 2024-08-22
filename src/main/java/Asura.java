import java.util.Scanner;

public class Asura {
    public static String formatResponse(String msg) {
        String startBorder = "---------------------------------------\n";
        String endBorder = "\n---------------------------------------";
        String formattedMsg = startBorder + msg + endBorder;
        return formattedMsg.indent(3);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String introduction = """
                Hello! I'm Asura!
                What can I do for you?""";
        String goodbye = """
                Bye. Hope to see you again soon!""";

        System.out.println(formatResponse(introduction));

        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            System.out.println(formatResponse(input));
            input = scanner.nextLine();
        }
        System.out.println(formatResponse(goodbye));
    }
}
