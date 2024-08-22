import java.util.ArrayList;
import java.util.Scanner;

public class Alex {
    private static final ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Alex, your friendly assistant!");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String userInput = scanner.nextLine();
            try {
                if (userInput.equalsIgnoreCase("list")) {
                    printTaskList();
                } else if (userInput.toLowerCase().startsWith("mark ")) {
                    int taskIndex = parseTaskIndex(userInput);
                    if (taskIndex >= 0 && taskIndex < tasks.size()) {
                        tasks.get(taskIndex).markAsDone();
                        printTaskStatusChange("Nice! I've marked this task as done:", taskIndex);
                    } else {
                        throw new AlexException("Invalid task number.");
                    }
                } else if (userInput.toLowerCase().startsWith("unmark ")) {
                    int taskIndex = parseTaskIndex(userInput);
                    if (taskIndex >= 0 && taskIndex < tasks.size()) {
                        tasks.get(taskIndex).markAsNotDone();
                        printTaskStatusChange("OK, I've marked this task as not done yet:", taskIndex);
                    } else {
                        throw new AlexException("Invalid task number.");
                    }
                } else if (userInput.equalsIgnoreCase("tell me a joke")) {
                    printDividerWithMessage("Why did the scarecrow win an award? Because he was outstanding in his field!");
                } else if (userInput.equalsIgnoreCase("bye")) {
                    printDividerWithMessage("Bye. Hope to see you again soon!");
                    break;
                } else if (userInput.toLowerCase().startsWith("todo")) {
                    if (userInput.trim().equals("todo")) {
                        throw new AlexException("The description of a todo cannot be empty.");
                    }
                    Task newTask = new Todo(userInput.substring(5).trim());
                    tasks.add(newTask);
                    printTaskAdded(newTask);
                } else if (userInput.toLowerCase().startsWith("deadline ")) {
                    String[] parts = userInput.substring(9).split(" /by ");
                    if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                        throw new AlexException("Invalid format for deadline. Use: deadline <description> /by <time>");
                    }
                    Task newTask = new Deadline(parts[0].trim(), parts[1].trim());
                    tasks.add(newTask);
                    printTaskAdded(newTask);
                } else if (userInput.toLowerCase().startsWith("event ")) {
                    String[] parts = userInput.substring(6).split(" /from | /to ");
                    if (parts.length < 3 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
                        throw new AlexException("Invalid format for event. Use: event <description> /from <start time> /to <end time>");
                    }
                    Task newTask = new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());
                    tasks.add(newTask);
                    printTaskAdded(newTask);
                } else {
                    throw new AlexException("I'm sorry, what do you mean?");
                }
            } catch (AlexException e) {
                printDividerWithMessage("Oops! " + e.getMessage());
            }
        }
    }

    private static void printTaskList() {
        printDivider();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        printDivider();
    }

    private static int parseTaskIndex(String userInput) throws AlexException {
        try {
            return Integer.parseInt(userInput.split(" ")[1]) - 1;
        } catch (NumberFormatException e) {
            throw new AlexException("Please provide a valid task number.");
        }
    }

    private static void printTaskStatusChange(String message, int taskIndex) {
        printDivider();
        System.out.println(message);
        System.out.println("  " + tasks.get(taskIndex));
        printDivider();
    }

    private static void printTaskAdded(Task task) {
        printDivider();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " task" + (tasks.size() > 1 ? "s" : "") + " in the list.");
        printDivider();
    }

    private static void printDividerWithMessage(String message) {
        printDivider();
        System.out.println(message);
        printDivider();
    }

    private static void printDivider() {
        System.out.println("____________________________________________________________");
    }
}

