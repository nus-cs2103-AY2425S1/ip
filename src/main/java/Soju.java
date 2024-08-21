import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Soju {
    private static final List<Task> tasks = new ArrayList<>();
    public static void main(String[] args) {
        runWithHorizontalLine(Soju::greet);
        echo();
    }

    public static void greet() {
        System.out.println("-------------------------------------");
        String greetingMessage = "Hello! I'm Soju\nWhat can I do for you?";
        System.out.println(greetingMessage);
    }
    public static void exit() {
        String exitMessage = "Bye. Hope to see you again soon!";
        System.out.println(exitMessage);
    }
    public static void runWithHorizontalLine(Runnable function) {
        function.run();
        System.out.println("-------------------------------------");
    }
    public static void runWithHorizontalLine(String message) {
        System.out.println(message);
        System.out.println("-------------------------------------");
    }
    public static void runWithHorizontalLine() {
        System.out.println("-------------------------------------");
    }
    public static void addToList(Task task) {
        tasks.add(task);
        System.out.println("added: " + task.description);
        runWithHorizontalLine();
    }
    public static void displayList() {
        System.out.println("Here are the tasks in your list:");
        tasks.stream()
                .map(task -> (tasks.indexOf(task) + 1) + "." + task) // Add index to each task
                .forEach(System.out::println); // Print each task
        runWithHorizontalLine();
    }
    public static void echo() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String userInput = scanner.nextLine();
            runWithHorizontalLine();
            if (userInput.equals("bye")) {
                runWithHorizontalLine(Soju::exit);
                break;
            } else if (userInput.equals("list")) {
                displayList();
            } else if (userInput.startsWith("mark")) {
                String[] parts = userInput.split(" ");
                int taskIndex = Integer.parseInt(parts[1]) - 1;
                tasks.get(taskIndex).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + tasks.get(taskIndex));
                runWithHorizontalLine();
            } else if (userInput.startsWith("unmark")) {
                String[] parts = userInput.split(" ");
                int taskIndex = Integer.parseInt(parts[1]) - 1;
                tasks.get(taskIndex).unmark();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + tasks.get(taskIndex));
                runWithHorizontalLine();
            } else if (userInput.startsWith("todo")){
                // Extract the task description by removing the "todo " prefix
                String description = userInput.substring(5).trim();

                // Create a new Todo task with the extracted description
                Todo todoTask = new Todo(description);

                // Add the task to your tasks list
                tasks.add(todoTask);

                // Print the confirmation message
                runWithHorizontalLine(() -> {
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + todoTask);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                });
            } else if (userInput.startsWith("deadline")) {
                // Extract the part after "deadline "
                String[] parts = userInput.substring(9).split(" /by ", 2);

                // The description is before the "/by"
                String description = parts[0].trim();

                // The due date is after the "/by"
                String by = parts[1].trim();

                // Create a new Deadline task
                Deadline deadlineTask = new Deadline(description, by);

                // Add the task to your tasks list
                tasks.add(deadlineTask);

                // Print the confirmation message
                runWithHorizontalLine(() -> {
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + deadlineTask);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                });
            } else if (userInput.startsWith("event")) {
                // Extract the part after "event"
                String[] parts = userInput.substring(6).split(" /from ", 2);
                String descriptionPart = parts[0].trim(); // This is the task description
                String[] timeParts = parts[1].split(" /to ", 2);
                String from = timeParts[0].trim(); // Start time
                String to = timeParts[1].trim(); // End time

                // Create a new Event task
                Event eventTask = new Event(descriptionPart, from, to);

                // Add the task to your tasks list
                tasks.add(eventTask);

                // Print the confirmation message
                runWithHorizontalLine(() -> {
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + eventTask);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                });
                } else {
                runWithHorizontalLine(() -> System.out.println("Invalid command"));
            }
        }
        scanner.close();
    }
}
