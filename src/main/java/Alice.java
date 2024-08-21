import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Alice {
    private static final String name = "Alice";
    private static final String line =
            "____________________________________________________________";
    private static TaskList list = new TaskList();
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

    public static void main(String[] args) {
        greet();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine().trim();
            String[] parts = input.split(" ", 2);
            String verb = parts[0];
            CommandType command = parseCommand(verb);

            switch (command) {
                case BYE:
                    exit(scanner);
                    return;
                case LIST:
                    listTasks();
                    break;
                case MARK:
                    handleMark(input);
                    break;
                case UNMARK:
                    handleUnmark(input);
                    break;
                case DELETE:
                    handleDelete(input);
                    break;
                case TODO:
                    handleTodo(input);
                    break;
                case EVENT:
                    handleEvent(input);
                    break;
                case DEADLINE:
                    handleDeadline(input);
                    break;
                case INVALID:
                    handleInvalid(input);
                    break;
            }
        }
    }

    public static void listTasks() {
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
        System.out.println(list.toString());
        System.out.println(line);
    }

    private static void greet() {
        System.out.println(line);
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

    private static void exit(Scanner scanner) {
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
        scanner.close();
    }

    private static void handleMark(String input) {
        int num = Integer.parseInt(input.split(" ")[1]);
        list.markTask(num);
    }

    private static void handleUnmark(String input) {
        int num = Integer.parseInt(input.split(" ")[1]);
        list.unmarkTask(num);
    }

    private static void handleDelete(String input) {
        int num = Integer.parseInt(input.split(" ")[1]);
        list.delete(num);
    }

    private static void handleTodo(String input) {
        try {
            String description = input.split(" ", 2)[1];
            Todo todo = new Todo(description);
            list.addToList(todo);
        } catch (Exception e) {
            System.out.println(line);
            System.out.println("Command Format: todo [description]");
            System.out.println(line);
        }
    }

    private static void handleEvent(String input) {
        try {
            String[] detail = input.split(" ", 2)[1].split("/from");
            String description = detail[0].trim();
            String[] time = detail[1].trim().split("/to");
            String start = time[0].trim();
            String end = time[1].trim();
            Event event = new Event(description, start, end);
            list.addToList(event);
        } catch (Exception e) {
            System.out.println(line);
            System.out.println("Command Format: event [description] /from [time] /to [time]");
            System.out.println(line);
        }
    }

    private static void handleDeadline(String input) {
        try {
            String[] detail = input.split(" ", 2)[1].split("/by");
            String description = detail[0].trim();
            String time = detail[1].trim();
            Deadline deadline = new Deadline(description, time);
            list.addToList(deadline);
        } catch (Exception e) {
            System.out.println(line);
            System.out.println("Command Format: deadline [description] /by [time]");
            System.out.println(line);
        }
    }

    private static void handleInvalid(String input) {
        System.out.println(line);
        System.out.println("Invalid command, use command words: list, todo, deadline, event, mark, unmark & delete");
        System.out.println(line);
    }
}
