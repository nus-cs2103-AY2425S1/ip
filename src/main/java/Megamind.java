import java.util.Scanner;

public class Megamind {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final List taskList = new List();

    public static void main(String[] args) {
        String logo = """
                  _____ \s
                 /     \\\s
                | () () |
                 \\  ^  /\s
                  ||||| \s
                  ||||| \s
                """;
        Scanner scanner = new Scanner(System.in);

        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello from\n" + logo);

        greet();

        // Start a loop to read commands from the user
        while (true) {
            System.out.println(HORIZONTAL_LINE);
            System.out.print("> ");
            String command = scanner.nextLine().trim();

            // Exit the loop if the user types "bye"
            if (command.equalsIgnoreCase("bye")) {
                exit();
                break;
            }

            // Print the list of tasks
            if (command.equalsIgnoreCase("list")) {
                System.out.println(taskList);
                continue;
            }

            // Mark a task as done
            if (command.contains("unmark")) {
                unmark(command);
                continue;
            }

            // Mark a task as done
            if (command.contains("mark")) {
                mark(command);
                continue;
            }

            // Add the command to the list
            taskList.add(command);

            // Echo the command
            System.out.println("Added " + command + " to list." + "\n");
        }
    }

    /**
     * Greets the user.
     */
    public static void greet() {
        System.out.println("What's good, I'm Megamind.\nWhat can I do for you? \n");
    }

    /**
     * Exits the program.
     */
    public static void exit() {
        System.out.println("See you around! \n");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Marks a task as not done.
     * If the task number is invalid, print an error message.
     *
     * @param command Command entered by the user.
     */
    public static void unmark(String command) {
        String[] words = command.split(" ");
        int index = Integer.parseInt(words[1]) - 1;
        boolean success = taskList.markTaskAsNotDone(index);

        // Depending on success of marking task as not done, print the appropriate message
        if (!success) {
            System.out.println("Invalid task number. Please try again.\n");
        } else {
            System.out.println("Alright, I've marked it as not done:\n" + taskList.get(index) + "\n");
        }
    }

    /**
     * Marks a task as done.
     * If the task number is invalid, print an error message.
     *
     * @param command Command entered by the user.
     */
    public static void mark(String command) {
        String[] words = command.split(" ");
        int index = Integer.parseInt(words[1]) - 1;
        boolean success = taskList.markTaskAsDone(index);

        // Depending on success of marking task as not done, print the appropriate message
        if (!success) {
            System.out.println("Invalid task number. Please try again.\n");
        } else {
            System.out.println("Good job! You've completed a task:\n" + taskList.get(index) + "\n");
        }
    }
}
