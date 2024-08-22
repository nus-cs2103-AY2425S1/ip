import java.util.ArrayList;
import java.util.List;
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
        List<String> userInput = new ArrayList<>();

        System.out.println(formatResponse(introduction));
        String input = scanner.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                StringBuilder output = new StringBuilder();
                for (int i=0; i<userInput.size(); i++) {
                    output.append(i + 1).append(". ").append(userInput.get(i)).append("\n");
                }
                System.out.println(formatResponse(output.toString()));
            }
            else {
                userInput.add(input);
                System.out.println(formatResponse("added: " + input));
            }
            input = scanner.nextLine();
        }
        System.out.println(formatResponse(goodbye));
    }
}
