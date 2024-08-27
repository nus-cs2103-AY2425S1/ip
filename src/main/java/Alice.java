import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;

public class Alice {
    private static final String name = "Alice";
    private static final String line =
            "____________________________________________________________";
    private TaskList list;
    private final Storage storage;
    private static final String path = "./data/alice.txt";

    public enum CommandType {
        BYE, LIST, MARK, UNMARK, DELETE,
        TODO, EVENT, DEADLINE, INVALID
    }

    public static CommandType parseCommand(String input) {
        switch (input) {
            case "bye": return CommandType.BYE;
            case "list": return CommandType.LIST;
            case "mark": return CommandType.MARK;
            case "unmark": return CommandType.UNMARK;
            case "delete": return CommandType.DELETE;
            case "todo": return CommandType.TODO;
            case "event": return CommandType.EVENT;
            case "deadline": return CommandType.DEADLINE;
            default: return CommandType.INVALID;
        }
    }

    public Alice(String path) {
        this.storage = new Storage(path);
        this.list = new TaskList();
        ArrayList<Task> loadedTasks = storage.load();
        for (Task task : loadedTasks) {
            list.addToList(task);
        }
    }

    public static void main(String[] args) {
        Alice alice = new Alice(path);
        alice.greet();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine().trim();
            String[] parts = input.split(" ", 2);
            String verb = parts[0];
            CommandType command = parseCommand(verb);

            switch (command) {
                case BYE:
                    alice.exit(scanner);
                    return;
                case LIST:
                    alice.listTasks();
                    break;
                case MARK:
                    alice.handleMark(input);
                    break;
                case UNMARK:
                   alice.handleUnmark(input);
                    break;
                case DELETE:
                    alice.handleDelete(input);
                    break;
                case TODO:
                    alice.handleTodo(input);
                    break;
                case EVENT:
                    alice.handleEvent(input);
                    break;
                case DEADLINE:
                    alice.handleDeadline(input);
                    break;
                case INVALID:
                    alice.handleInvalid(input);
                    break;
            }
        }
    }

    public void listTasks() {
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
        System.out.println(list.toString());
        System.out.println(line);
    }

    private void greet() {
        System.out.println(line);
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

    private void exit(Scanner scanner) {
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
        storage.save(list);
        scanner.close();
    }

    private void handleMark(String input) {
        int num = Integer.parseInt(input.split(" ")[1]);
        list.markTask(num);
        storage.save(list);
    }

    private void handleUnmark(String input) {
        int num = Integer.parseInt(input.split(" ")[1]);
        list.unmarkTask(num);
        storage.save(list);
    }

    private void handleDelete(String input) {
        int num = Integer.parseInt(input.split(" ")[1]);
        list.delete(num);
        storage.save(list);
    }

    private void handleTodo(String input) {
        try {
            String description = input.split(" ", 2)[1];
            Todo todo = new Todo(description);
            list.addToList(todo);
            storage.save(list);
        } catch (Exception e) {
            System.out.println(line);
            System.out.println("Command Format: todo [description]");
            System.out.println(line);
        }
    }

    private void handleEvent(String input) {
        try {
            String[] detail = input.split(" ", 2)[1].split("/from");
            String description = detail[0].trim();
            String[] time = detail[1].trim().split("/to");
            String start = time[0].trim();
            String end = time[1].trim();
            Event event = new Event(description, start, end);
            list.addToList(event);
            storage.save(list);
        } catch (Exception e) {
            System.out.println(line);
            System.out.println("Command Format: event [description] /from [time] /to [time]");
            System.out.println(line);
        }
    }

    private void handleDeadline(String input) {
        try {
            String[] detail = input.split(" ", 2)[1].split("/by");
            String description = detail[0].trim();
            String time = detail[1].trim();
            Deadline deadline = new Deadline(description, time);
            list.addToList(deadline);
            storage.save(list);
        } catch (Exception e) {
            System.out.println(line);
            System.out.println("Command Format: deadline [description] /by [time]");
            System.out.println(line);
        }
    }

    private void handleInvalid(String input) {
        System.out.println(line);
        System.out.println("Invalid command, use command words: list, todo, deadline, event, mark, unmark & delete");
        System.out.println(line);
    }
}
