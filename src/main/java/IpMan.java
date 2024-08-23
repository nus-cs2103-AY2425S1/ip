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

            try {
                if (input.equals("bye")) {
                    break;
                } else if (input.equals("list")) {
                    command = new ListCommand(items);
                } else if (input.startsWith("mark")) {
                    command = new MarkCommand(items, input);
                } else if (input.startsWith("unmark")) {
                    command = new UnmarkCommand(items, input);
                } else if (input.startsWith("todo")) {
                    command = new CreateToDoCommand(items, input);
                } else if (input.startsWith("deadline")) {
                    command = new CreateDeadlineCommand(items, input);
                } else if (input.startsWith("event")) {
                    command = new CreateEventCommand(items, input);
                } else if (input.startsWith("delete")) {
                    command = new DeleteCommand(items, input);
                } else {
                    System.out.println("Sorry, I don't recognise that command. Try again!");
                    continue;
                }

                command.execute();
            } catch (Exception e) {
                System.out.println("Oh no! Something went wrong:");
                System.out.println(e.getMessage());
            }
        }

        // Goodbye
        System.out.println(HORIZONTAL_LINE);
        System.out.println("That's enough for today. See you another time.");
        System.out.println(HORIZONTAL_LINE);
    }
}
