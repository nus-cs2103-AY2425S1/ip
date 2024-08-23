import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Morgana {
    private static final String NAME = "Morgana";
    private static final String HORIZONTAL_LINE = "============================================================\n";

    private static final List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        System.out.print(HORIZONTAL_LINE);
        System.out.printf("Hello! I'm %s.%n", NAME);
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);

        Scanner sc = new Scanner(System.in);
        String line;
        while (!(line = sc.nextLine()).equals("bye")) {
            String[] input = line.trim().split(" ", 2);
            switch (input[0]) {
                case "list" -> listTasks();
                case "mark" -> markTaskAsDone(Integer.parseInt(line.split(" ")[1]) - 1);
                case "unmark" -> markTaskAsNotDone(Integer.parseInt(line.split(" ")[1]) - 1);
                default -> addTask(input);
            }
        }

        System.out.print(HORIZONTAL_LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.print(HORIZONTAL_LINE);
    }

    private static void listTasks() {
        System.out.print(HORIZONTAL_LINE);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, tasks.get(i));
        }
        System.out.println(HORIZONTAL_LINE);
    }

    private static void markTaskAsDone(int index) {
        Task task = tasks.get(index);
        task.markAsDone();

        System.out.print(HORIZONTAL_LINE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.printf("%d. %s%n", index + 1, task);
        System.out.println(HORIZONTAL_LINE);
    }

    private static void markTaskAsNotDone(int index) {
        Task task = tasks.get(index);
        task.markAsNotDone();

        System.out.print(HORIZONTAL_LINE);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.printf("%d. %s%n", index + 1, task);
        System.out.println(HORIZONTAL_LINE);
    }

    private static void addTask(String[] input) {
        try {
            Task task = createTask(input[0], input.length > 1 ? input[1].trim() : "");
            tasks.add(task);

            System.out.print(HORIZONTAL_LINE);
            System.out.println("Got it. I've added this task:");
            System.out.printf("%s%n", task);
            System.out.printf("Now you have %d task%s in the list.%n",
                    tasks.size(), tasks.size() > 1 ? "s" : "");
            System.out.println(HORIZONTAL_LINE);
        } catch (MorganaException e) {
            System.out.print(HORIZONTAL_LINE);
            System.out.println(e.getMessage());
            System.out.println(HORIZONTAL_LINE);
        }
    }

    private static Task createTask(String command, String args) throws MorganaException {
        return switch (command) {
            case "todo" -> {
                if (args.isEmpty()) {
                    throw new MorganaException("""
                            Invalid todo format.
                            Usage: todo <description>.
                            Example: todo borrow book""");
                }
                yield new Todo(args);
            }
            case "deadline" -> {
                String[] parts = args.split(" /by ");
                if (parts.length != 2) {
                    throw new MorganaException("""
                            Invalid deadline format.
                            Usage: deadline <description> /by <date/time>
                            Example: deadline return book /by Sunday""");
                }
                yield new Deadline(parts[0], parts[1]);
            }
            case "event" -> {
                String[] parts = args.split(" /from | /to ");
                if (parts.length != 3) {
                    throw new MorganaException("""
                            Invalid event format.
                            Usage: event <description> /from <date/time> /to <date/time>
                            Example: event project meeting /from Mon 2pm /to 4pm""");
                }
                yield new Event(parts[0], parts[1], parts[2]);
            }
            default -> throw new MorganaException("Unknown command: %s".formatted(command));
        };
    }
}
