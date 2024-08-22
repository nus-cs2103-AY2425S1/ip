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
            running = handleInput(input);
        }
    }

    private static void greetUser() {
        printDivider();
        System.out.println("Hello! I'm Evan!");
        System.out.println("What can I do for you?");
        printDivider();
    }

    private static boolean handleInput(String input) {
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
                // Check if the input matches "mark <integer>"
                if (input.toLowerCase().matches("mark \\d+")) {
                    int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1; // Get index of task
                    markTaskAsDone(TASKS.get(taskIndex));
                } else if (input.toLowerCase().matches("unmark \\d+")) {
                    int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1; // Get index of task
                    markTaskAsUndone(TASKS.get(taskIndex));
                } else {
                    addTask(input);
                }
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

    private static void addTask(String description) {
        TASKS.add(new Task(description));
        System.out.println("added: " + description);
        printDivider();
    }

    private static void markTaskAsDone(Task task) {
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        printDivider();
    }

    private static void markTaskAsUndone(Task task) {
        task.markAsUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
        printDivider();
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
