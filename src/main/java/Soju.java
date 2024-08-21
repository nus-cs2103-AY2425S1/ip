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
        runWithHorizontalLine(() -> {
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + task);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        });
    }
    public static void displayList() {
        System.out.println("Here are the tasks in your list:");
        tasks.stream()
                .map(task -> (tasks.indexOf(task) + 1) + "." + task) // Add index to each task
                .forEach(System.out::println); // Print each task
        runWithHorizontalLine();
    }
    public static void deleteTask(int taskNumber) {
        Task task = tasks.remove(taskNumber - 1);
        runWithHorizontalLine(() -> {
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + task);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        });
    }
    public static void echo() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String userInput = scanner.nextLine();
            runWithHorizontalLine();

            try {
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
                } else if (userInput.startsWith("delete")) {
                    String[] parts = userInput.split(" ");
                    int taskNumber = Integer.parseInt(parts[1]);
                    deleteTask(taskNumber);
                } else if (userInput.startsWith("todo")){
                    if (!userInput.startsWith("todo ")) {
                        throw new SojuException("The description of a todo cannot be empty.");
                    }
                    String description = userInput.substring(5).trim();
                    if (description.isEmpty()) {
                        throw new SojuException("The description of a todo cannot be empty.");
                    }
                    // Create a new Todo task with the extracted description
                    Todo todoTask = new Todo(description);

                    // Add the task to your tasks list
                    addToList(todoTask);
                } else if (userInput.startsWith("deadline")) {
                    if (!userInput.startsWith("deadline ")) {
                        throw new SojuException("Deadlines must have a description!");
                    }

                    // Extract the part after "deadline "
                    String[] parts = userInput.substring(9).split(" /by ", 2);

                    // The description is before the "/by"
                    String description = parts[0].trim();
                    if (description.isEmpty()) {
                        throw new SojuException("The description of a todo cannot be empty.");
                    }

                    // The due date is after the "/by"
                    String by = parts[1].trim();


                    // Create a new Deadline task
                    Deadline deadlineTask = new Deadline(description, by);

                    // Add the task to your tasks list
                    addToList(deadlineTask);
                } else if (userInput.startsWith("event")) {
                    // Extract the part after "event"
                    String[] parts = userInput.substring(6).split(" /from ", 2);
                    String description = parts[0].trim(); // This is the task description
                    if (description.isEmpty()) {
                        throw new SojuException("The description of a todo cannot be empty.");
                    }
                    String[] timeParts = parts[1].split(" /to ", 2);
                    String from = timeParts[0].trim(); // Start time
                    String to = timeParts[1].trim(); // End time

                    // Create a new Event task
                    Event eventTask = new Event(description, from, to);

                    // Add the task to your tasks list
                    addToList(eventTask);
                } else {
                    throw new SojuException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (SojuException sojuException) {
                runWithHorizontalLine(sojuException.getMessage());
            }
        }
        scanner.close();
    }
}
