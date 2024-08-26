import java.util.ArrayList;
import java.util.Scanner;

public class SKD {
    private static final String LINE = "    ____________________________________________________________";
    private final ArrayList<Task> tasks;

    public SKD() {
        tasks = new ArrayList<>();
    }

    public void run() {
        greet();
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            String input = scanner.nextLine();
            CommandType commandType = parseCommand(input);
            try {
                switch (commandType) {
                    case BYE:
                        bye();
                        isRunning = false;
                        break;
                    case LIST:
                        System.out.println(input);
                        returnList();
                        break;
                    case MARK:
                        System.out.println(input);
                        executeMark(input);
                        break;
                    case UNMARK:
                        System.out.println(input);
                        executeUnmark(input);
                        break;
                    case TODO:
                        System.out.println(input);
                        executeToDo(input);
                        break;
                    case DEADLINE:
                        System.out.println(input);
                        executeDeadline(input);
                        break;
                    case EVENT:
                        System.out.println(input);
                        executeEvent(input);
                        break;
                    case DELETE:
                        System.out.println(input);
                        executeDelete(input);
                        break;
                    case ETC:
                    default:
                        System.out.println(input);
                        throw new IllegalArgumentException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(LINE);
                System.out.println("     " + e.getMessage());
                System.out.println(LINE);
                System.out.println();
            }
        }
        scanner.close();
    }

    private void greet() {
        System.out.println(LINE);
        System.out.println("     Hello! I'm SKD");
        System.out.println("     What can I do for you?");
        System.out.println(LINE);
        System.out.println();
    }

    private void bye() {
        System.out.println("bye");
        System.out.println(LINE);
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    private void returnList() {
        System.out.println(LINE);
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("     " + (i + 1) + "." + tasks.get(i));
        }
        System.out.println(LINE);
        System.out.println();
    }

    private void executeMark(String input) {
        System.out.println(LINE);
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new IllegalArgumentException("OOPS!!! The task number is invalid.");
            }
            tasks.get(index).markAsDone();
        } catch (IllegalArgumentException e) {
            System.out.println("     " + e.getMessage());
        }
        System.out.println(LINE);
        System.out.println();
    }

    private void executeUnmark(String input) {
        System.out.println(LINE);
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new IllegalArgumentException("OOPS!!! The task number is invalid.");
            }
            tasks.get(index).unmark();
        } catch (IllegalArgumentException e) {
            System.out.println("     " + e.getMessage());
        }
        System.out.println(LINE);
        System.out.println();
    }

    private void executeToDo(String input) {
        System.out.println(LINE);
        try {
            String description = input.length() > 5 ? input.substring(5).trim() : "";
            if (description.isEmpty()) {
                throw new IllegalArgumentException("OOPS!!! The description of a todo cannot be empty.");
            }
            ToDo task = new ToDo(description);
            tasks.add(task);
            printAddedMessage(task);
        } catch (IllegalArgumentException e) {
            System.out.println("     " + e.getMessage());
        }
        System.out.println(LINE);
        System.out.println();
    }

    private void executeDeadline(String input) {
        System.out.println(LINE);
        try {
            String[] parts = input.length() > 9 ? input.substring(9).split(" /by ") : new String[]{""};
            if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                throw new IllegalArgumentException("OOPS!!! The description or deadline cannot be empty.");
            }
            Deadline task = new Deadline(parts[0].trim(), parts[1].trim());
            tasks.add(task);
            printAddedMessage(task);
        } catch (IllegalArgumentException e) {
            System.out.println("     " + e.getMessage());
        }
        System.out.println(LINE);
        System.out.println();
    }

    private void executeEvent(String input) {
        System.out.println(LINE);
        try {
            String[] parts = input.length() > 6 ? input.substring(6).split(" /from | /to ") : new String[]{"", "", ""};
            if (parts.length < 3 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
                throw new IllegalArgumentException("OOPS!!! The description, start time, or end time cannot be empty.");
            }
            Event task = new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());
            tasks.add(task);
            printAddedMessage(task);
        } catch (IllegalArgumentException e) {
            System.out.println("     " + e.getMessage());
        }
        System.out.println(LINE);
        System.out.println();
    }

    private void executeDelete(String input) {
        System.out.println(LINE);
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new IllegalArgumentException("OOPS!!! The task number is invalid.");
            }
            Task removedTask = tasks.remove(index);
            removedTask.printTaskRemovedMessage(tasks.size());
        } catch (IllegalArgumentException e) {
            System.out.println("     " + e.getMessage());
        }
        System.out.println(LINE);
        System.out.println();
    }

    private void printAddedMessage(Task task) {
        task.printTaskAddedMessage(tasks.size());
    }

    private CommandType parseCommand(String input) {
        if (input.startsWith("todo")) {
            return CommandType.TODO;
        } else if (input.startsWith("deadline")) {
            return CommandType.DEADLINE;
        } else if (input.startsWith("event")) {
            return CommandType.EVENT;
        } else if (input.equals("list")) {
            return CommandType.LIST;
        } else if (input.startsWith("mark")) {
            return CommandType.MARK;
        } else if (input.startsWith("unmark")) {
            return CommandType.UNMARK;
        } else if (input.startsWith("delete")) {
            return CommandType.DELETE;
        } else if (input.equals("bye")) {
            return CommandType.BYE;
        } else {
            return CommandType.ETC;
        }
    }


    public static void main(String[] args) {
        new SKD().run();
    }
}