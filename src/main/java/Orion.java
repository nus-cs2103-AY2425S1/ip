import java.util.Scanner;

import storage.Storage;
import task.DeadlineDetails;
import task.EventDetails;
import task.Task;
import taskList.TaskList;
import parser.Parser;
import orionExceptions.*;
import commands.Command;

public class Orion {
    public static Scanner scanner = new Scanner(System.in);
    public static TaskList manager;
    public static Storage storage;

    static {
        try {
            storage = new Storage();
            manager = new TaskList(storage);
        } catch (FileInitializationException e) {
            System.err.println("Failed to initialize TaskList: " + e.getMessage());
            System.exit(1);  // Exiting the application since TaskList is essential
        }
    }

    public static Parser parser = new Parser();
    private static final String HORIZONTAL_LINE = "────────────────────────────────────────";

    public static void main(String[] args) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Orion");
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);

        while (true) {

            String input = scanner.nextLine();
            String[] parts = input.split(" ", 2);
            Command command = Command.fromString(parts[0]);


            switch (command) {
                case BYE:
                    executeWithFormatting(() -> System.out.println("Bye. Hope to see you again soon!"));
                    scanner.close();
                    return;
                case LIST:
                    handleList(parts);
                    break;
                case MARK:
                    handleMark(parts);
                    break;
                case UNMARK:
                    handleUnmark(parts);
                    break;
                case TODO:
                    handleTodo(parts);
                    break;
                case EVENT:
                    handleEvent(parts);
                    break;
                case DEADLINE:
                    handleDeadline(parts);
                    break;
                case DELETE:
                    handleDelete(parts);
                    break;
                case UNKNOWN:
                default:
                    executeWithFormatting(() -> System.out.println("Unknown command: " + parts[0]));
            }
        }
    }

    private static void handleList(String[] parts) {
        executeWithFormatting(() -> {
            try {
                parser.validateListCommand(parts);
                manager.printTasks();
            } catch (OrionException e) {
                System.out.println(e.getMessage());
            }
        });
    }

    private static void handleEvent(String[] parts) {
        executeWithFormatting(() -> {
            try {
                EventDetails details = parser.validateEventCommand(parts);
                Task temp = manager.addEvent(details);
                int taskLen = manager.getSize();
                System.out.println("     Got it. I've added this task:\n" + temp + "\n" + "Now you have " + taskLen + " tasks in the list");
            } catch (OrionException e) {
                System.out.println(e.getMessage());
            }
        });

    }

    private static void handleDeadline(String[] parts) {
        executeWithFormatting(() -> {
            try {
                DeadlineDetails deadlineDetails = parser.validateDeadlineCommand(parts);
                Task newDeadline = manager.addDeadline(deadlineDetails);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + newDeadline);
                System.out.println("Now you have " + manager.getSize() + " tasks in the list.");
            } catch (OrionException e) {
                System.out.println(e.getMessage());
            }
        });
    }
    private static void handleTodo(String[] parts) {
        executeWithFormatting(() -> {
            try {
                String description = parser.validateTodoCommand(parts);

                Task temp = manager.addTodo(description);
                int taskLen = manager.getSize();
                System.out.println("     Got it. I've added this task:\n" + temp + "\n" + "Now you have " + taskLen + " tasks in the list");
            } catch (OrionException e) {
                System.out.println(e.getMessage());
            }
        });

    }

    private static void handleMark(String[] parts) {
        executeWithFormatting(() -> {
            try {
                int index = parser.validateMarkAndUnMarkCommand(parts, manager);
                Task temp = manager.markAsDone(index);
                System.out.println("Marked task as done: " + temp);
            } catch (OrionException e) {
                System.out.println(e.getMessage());
            }
        });
    }

    private static void handleUnmark(String[] parts) {
        executeWithFormatting(() -> {
            try {
                int index = parser.validateMarkAndUnMarkCommand(parts, manager);
                Task temp = manager.unmarkAsDone(index);
                System.out.println("Unmarked task: " + temp);
            } catch (OrionException e) {
                System.out.println(e.getMessage());
            }
        });
    }

    private static void handleDelete(String[] parts) {
        executeWithFormatting(() -> {
            try {
                int index = parser.validateDeleteCommand(parts, manager);
                Task deletedTask = manager.deleteTask(index);
                System.out.println("Noted. I've removed this task:");
                System.out.println("  " + deletedTask);
                System.out.println("Now you have " + manager.getSize() + " tasks in the list.");
            } catch (OrionException e) {
                System.out.println(e.getMessage());
            }
        });
    }


    private static void executeWithFormatting(Runnable action) {
        System.out.println(HORIZONTAL_LINE);
        action.run();
        System.out.println(HORIZONTAL_LINE);
    }
}