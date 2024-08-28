import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Morgana {
    private static final String NAME = "Morgana";
    private static final String HORIZONTAL_LINE = "============================================================\n";

    private Storage storage;
    private List<Task> tasks;

    public Morgana(String filePath) {
        try {
            storage = new Storage(filePath);
            tasks = storage.loadTasks();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            tasks = new ArrayList<>();
        }
    }

    public void run() {
        System.out.print(HORIZONTAL_LINE);
        System.out.printf("Hello! I'm %s.%n", NAME);
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);

        Scanner sc = new Scanner(System.in);
        String line;
        while (!(line = sc.nextLine()).startsWith("bye")) {
            String[] input = line.trim().split(" ", 2);
            try {
                switch (input[0]) {
                    case "list" -> {
                        listTasks();
                        continue;
                    }
                    case "mark" -> updateTaskStatus(parseTaskIndex(input),
                            "Nice! I've marked this task as done:", true);
                    case "unmark" -> updateTaskStatus(parseTaskIndex(input),
                            "OK, I've marked this task as not done yet:", false);
                    case "delete" -> deleteTask(parseTaskIndex(input));
                    default -> addTask(input);
                }
                storage.saveTasks(tasks);
            } catch (MorganaException | IOException e) {
                System.out.print(HORIZONTAL_LINE);
                System.out.println(e.getMessage());
                System.out.println(HORIZONTAL_LINE);
            }
        }

        System.out.print(HORIZONTAL_LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.print(HORIZONTAL_LINE);
    }

    public static void main(String[] args) {
        new Morgana("./data/morgana.txt").run();
    }

    private void listTasks() {
        System.out.print(HORIZONTAL_LINE);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, tasks.get(i));
        }
        System.out.println(HORIZONTAL_LINE);
    }

    private int parseTaskIndex(String[] input) throws MorganaException {
        if (input.length < 2) {
            throw new MorganaException("Please specify a task number.");
        }
        try {
            int index = Integer.parseInt(input[1].trim()) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new IndexOutOfBoundsException();
            }
            return index;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new MorganaException("Please specify a valid task number.");
        }
    }

    private void updateTaskStatus(int index, String message, boolean isDone) {
        Task task = tasks.get(index);
        task.markAsDone(isDone);

        System.out.print(HORIZONTAL_LINE);
        System.out.println(message);
        System.out.printf("%d. %s%n", index + 1, task);
        System.out.println(HORIZONTAL_LINE);
    }

    private void deleteTask(int index) {
        System.out.print(HORIZONTAL_LINE);
        System.out.println("Noted. I've removed this task:");
        System.out.printf("%d. %s%n", index + 1, tasks.remove(index));
        System.out.printf("Now you have %d task%s in the list.%n",
                tasks.size(), tasks.size() > 1 ? "s" : "");
        System.out.println(HORIZONTAL_LINE);
    }

    private void addTask(String[] input) throws MorganaException {
        Task task = createTask(input[0], input.length > 1 ? input[1].trim() : "");
        tasks.add(task);

        System.out.print(HORIZONTAL_LINE);
        System.out.println("Got it. I've added this task:");
        System.out.printf("%s%n", task);
        System.out.printf("Now you have %d task%s in the list.%n",
                tasks.size(), tasks.size() > 1 ? "s" : "");
        System.out.println(HORIZONTAL_LINE);
    }

    private Task createTask(String command, String args) throws MorganaException {
        return switch (command) {
            case "todo" -> {
                if (args.isEmpty()) {
                    throw new MorganaException("""
                            Invalid todo format.
                            Usage: todo <description>
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
