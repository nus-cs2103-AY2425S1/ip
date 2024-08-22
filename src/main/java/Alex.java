import java.util.ArrayList;
import java.util.Scanner;

public class Alex {

    private static final ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        printDividerWithMessage("Hello! I'm Alex, your friendly assistant!\nWhat can I do for you?");

        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("list")) {
                printTaskList();
            } else if (userInput.toLowerCase().startsWith("mark ")) {
                int taskIndex = parseTaskIndex(userInput);
                if (taskIndex >= 0 && taskIndex < tasks.size()) {
                    tasks.get(taskIndex).markAsDone();
                    printTaskStatusChange("Nice! I've marked this task as done:", taskIndex);
                } else {
                    printInvalidTaskNumber();
                }
            } else if (userInput.toLowerCase().startsWith("unmark ")) {
                int taskIndex = parseTaskIndex(userInput);
                if (taskIndex >= 0 && taskIndex < tasks.size()) {
                    tasks.get(taskIndex).markAsNotDone();
                    printTaskStatusChange("OK, I've marked this task as not done yet:", taskIndex);
                } else {
                    printInvalidTaskNumber();
                }
            } else if (userInput.equalsIgnoreCase("tell me a joke")) {
                printDividerWithMessage("Why did the scarecrow win an award? Because he was outstanding in his field!");
            } else if (userInput.equalsIgnoreCase("bye")) {
                printDividerWithMessage("Bye. Hope to see you again soon!");
                break;
            } else if (userInput.toLowerCase().startsWith("todo ")) {
                Task newTask = new Todo(userInput.substring(5));
                tasks.add(newTask);
                printTaskAdded(newTask);
            } else if (userInput.toLowerCase().startsWith("deadline ")) {
                String[] parts = userInput.substring(9).split(" /by ");
                Task newTask = new Deadline(parts[0], parts[1]);
                tasks.add(newTask);
                printTaskAdded(newTask);
            } else if (userInput.toLowerCase().startsWith("event ")) {
                String[] parts = userInput.substring(6).split(" /from | /to ");
                Task newTask = new Event(parts[0], parts[1], parts[2]);
                tasks.add(newTask);
                printTaskAdded(newTask);
            } else {
                Task newTask = new Task(userInput);
                tasks.add(newTask);
                printTaskAdded(newTask);
            }
        }

        scanner.close();
    }

    private static void printTaskList() {
        StringBuilder taskList = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            taskList.append("\n ").append(i + 1).append(". ").append(task);
        }
        printDividerWithMessage(taskList.toString());
    }

    private static void printTaskStatusChange(String message, int taskIndex) {
        String statusChangeMessage = message + "\n   " + tasks.get(taskIndex);
        printDividerWithMessage(statusChangeMessage);
    }

    private static void printInvalidTaskNumber() {
        printDividerWithMessage("Invalid task number.");
    }

    private static void printTaskAdded(Task task) {
        printDividerWithMessage("Got it. I've added this task:\n   " + task + "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    private static void printDividerWithMessage(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(" " + message);
        System.out.println("____________________________________________________________");
    }

    private static int parseTaskIndex(String userInput) {
        try {
            return Integer.parseInt(userInput.split(" ")[1]) - 1; // Convert to zero-based index
        } catch (Exception e) {
            return -1; // Return -1 if parsing fails
        }
    }
}
