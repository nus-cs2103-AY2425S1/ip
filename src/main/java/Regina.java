import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Regina {
    public static void main(String[] args) {
        // Name
        final String NAME = "Regina";
        final String INDENT = "    ";
        final String LINE = INDENT + "-------------------------------";

        // Greet
        System.out.printf(LINE + "\n" + INDENT + "Hello! I'm %s \n" +
                INDENT + "What can I do for you?\n" + LINE + "\n", NAME);

        // Create a Scanner object for user input
        Scanner scanner = new Scanner(System.in);
        String userInput;

        List<String> list = new ArrayList<>();

        while (true) {
            userInput = scanner.nextLine();   // Read user input
            if (userInput.equals("bye")) {
                break;
            }

            // Print out the list
            int length = list.size();
            if (userInput.equals("list")) {
                StringBuilder inputList = new StringBuilder();
                for (int i = 1; i <= length; i++) {
                    inputList.append(INDENT)
                            .append(i)
                            .append(". ")
                            .append(list.get(i - 1))
                            .append("\n");
                }
                System.out.println(LINE + "\n" + inputList + LINE);
            } else {
                // Add input to list
                list.add(userInput);
                System.out.println(LINE + "\n" + INDENT + "added: "
                        + userInput + "\n" + LINE); // show that input was added
            }
        }

        // Exit
        System.out.println(LINE + "\n" + INDENT +
                "Bye. Hope to see you again soon!\n" + LINE);

        scanner.close();
    }
}
