import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IpMan {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final List<Task> items = new ArrayList<>();

    private static String[] safeSplit(String input, int noArgs) {
        String[] split = input.split(" ");
        if (split.length < noArgs) {
            throw new IllegalArgumentException(String.format("Expected %d arguments. Found %d.", noArgs, split.length));
        }
        return split;
    }

    public static void main(String[] args) {
        // Hello
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Ip Man.");
        System.out.println("What can I do for you?");

        // Main loop
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            Command command;

            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                command = new ListCommand(items);
            } else if (input.startsWith("mark")) {
                String[] split = safeSplit(input, 2);
                int index = Integer.parseInt(split[1]) - 1;
                command = new MarkCommand(items, index);
            } else if (input.startsWith("unmark")) {
                String[] split = safeSplit(input, 2);
                int index = Integer.parseInt(split[1]) - 1;
                command = new UnmarkCommand(items, index);
            } else {
                command = new CreateTaskCommand(items, input);
            }

            command.execute();
        }

        // Goodbye
        System.out.println(HORIZONTAL_LINE);
        System.out.println("That's enough for today. See you another time.");
        System.out.println(HORIZONTAL_LINE);
    }
}
