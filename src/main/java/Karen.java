import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Karen {
    public static void main(String[] args) {
        final String LINE = "_______________________\n";
        List<String> tasks = new ArrayList<String>();

        String output = LINE +
                        "Hi! I'm Karen\n" +
                        "What can I do for you?\n" +
                        LINE;

        System.out.print(output);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            output = "";

            if (input.equals("bye")) {
                System.out.print(
                        LINE +
                        "Bye! Hope to see you again!\n" +
                        LINE
                );
                break;
            } else if (input.equals("list")) {
                output += LINE;
                for (int i = 0; i < tasks.size(); i++) {
                    String s = tasks.get(i);
                    if (s.isEmpty()) {
                        break;
                    } else {
                        output += String.format("%d. %s\n",i + 1, s);
                    }
                }
                output += LINE;
            }
            else {
                tasks.add(input);
                output = LINE +
                        "Added: " + input + "\n" +
                        LINE;
            }

            System.out.print(output);
        }

    }
}
