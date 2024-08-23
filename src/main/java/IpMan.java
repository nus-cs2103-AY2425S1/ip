import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IpMan {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final List<Task> items = new ArrayList<>();

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
                command = new MarkCommand(items, input);
            } else if (input.startsWith("unmark")) {
                command = new UnmarkCommand(items, input);
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
