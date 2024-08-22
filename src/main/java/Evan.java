import java.util.ArrayList;
import java.util.Scanner;

public class Evan {
    private static final String DIVIDER = "_".repeat(50);
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final ArrayList<Task> TASKS = new ArrayList<>();

    public static void main(String[] args) {
        greetUser();

        boolean running = true; // true if we want to continue prompting the user for input

        while (running) {
            String input = SCANNER.nextLine();
            printDivider();
            try {
                running = handleInput(input);
            } catch (EvanException e) {
                System.out.println(e.getMessage());
                System.out.println("Here is a list of valid commands:");
                System.out.println("- list");
                System.out.println("- todo <description>");
                System.out.println("- deadline <description> /by <end>");
                System.out.println("- event <description> /from <start> /to <end>");
                System.out.println("- mark <task_number>");
                System.out.println("- unmark <task_number>");
                System.out.println("- bye");
                System.out.println("Please enter another command.");
                printDivider();
            }
        }
    }

    private static void greetUser() {
        printDivider();
        System.out.println("Hello! I'm Evan!");
        System.out.println("What can I do for you?");
        printDivider();
    }

    private static boolean handleInput(String input) throws EvanException {
        // Continue prompting the user for input
        // Exit the program
        return switch (input.toLowerCase()) {
            case "list" -> {
                listTasks();
                yield true;
            }
            case "bye" -> {
                exitProgram();
                yield false;
            }
            default -> {
                // Check for "mark <task_number>"
                if (input.matches("(?i)mark \\d+")) {
                    int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                    markTaskAsDone(TASKS.get(taskIndex));
                }
                // Check for "unmark <task_number>"
                else if (input.matches("(?i)unmark \\d+")) {
                    int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                    markTaskAsUndone(TASKS.get(taskIndex));
                }
                // Check for "todo <description>"
                else if (input.matches("(?i)todo .+")) {
                    String description = input.substring(5); // Extract everything after "todo "
                    if (description.isBlank()) {
                        throw new EvanException("The <description> of a todo cannot be empty.");
                    }
                    addToDo(description);
                    System.out.printf("Now you have %d tasks in the list.\n", TASKS.size());
                }
                // Check for "deadline <description> /by <end>"
                else if (input.matches("(?i)deadline .+ /by.+")) {
                    String[] parts = input.substring(9).split(" /by ", 2); // Split into task_name and by_when
                    String description = parts[0];
                    String end = parts[1];
                    if (description.isBlank() || end.isBlank()) {
                        throw new EvanException("The <description> and <end> of a deadline cannot be empty.");
                    }
                    addDeadline(description, end);
                    System.out.printf("Now you have %d tasks in the list.\n", TASKS.size());
                } // Check for "event <description> /from <start> /to <end>"
                else if (input.matches("(?i)event .+ /from .+ /to .+")) {
                    String[] parts = input.substring(6).split(" /from | /to ", 3); // Split into task_name, start, and end
                    String taskName = parts[0];
                    String start = parts[1];
                    String end = parts[2];
                    if (taskName.isBlank() || start.isBlank() || end.isBlank()) {
                        throw new EvanException("The <description>, <start>, and <end> of an event cannot be empty.");
                    }
                    addEvent(taskName, start, end);
                    System.out.printf("Now you have %d tasks in the list.\n", TASKS.size());
                } else {
                    throw new EvanException("You have entered an invalid command: " + input);
                }
                printDivider();
                yield true;
            }
        };
    }

    private static void listTasks() {
        for (int i = 0; i < TASKS.size(); i++) {
            System.out.printf("%d.%s\n", i + 1, TASKS.get(i));
        }
        printDivider();
    }

    private static void addToDo(String description) {
        ToDo todo = new ToDo(description);
        TASKS.add(todo);
        System.out.println("Got it. I've added this task:");
        System.out.println(todo);
    }

    private static void addDeadline(String description, String by) {
        Deadline deadline = new Deadline(description, by);
        TASKS.add(deadline);
        System.out.println("Got it. I've added this task:");
        System.out.println(deadline);
    }

    private static void addEvent(String description, String from, String to) {
        Event event = new Event(description, from, to);
        TASKS.add(event);
        System.out.println("Got it. I've added this task:");
        System.out.println(event);
    }

    private static void markTaskAsDone(Task task) {
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    private static void markTaskAsUndone(Task task) {
        task.markAsUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    private static void exitProgram() {
        System.out.println("Bye. Hope to see you again soon!");
        printDivider();
        SCANNER.close();
    }

    private static void printDivider() {
        System.out.println(DIVIDER);
    }
}
