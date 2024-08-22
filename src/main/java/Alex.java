import java.util.ArrayList;
import java.util.Scanner;

public class Alex {

    private static final ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        printDividerWithMessage("Hello! I'm Alex, your friendly assistant!\nWhat can I do for you?");

        while (true) {
            String userInput = scanner.nextLine();

            // Command to list tasks
            if (userInput.equalsIgnoreCase("list")) {
                printTaskList();
            }
            // Command to mark a task as done
            else if (userInput.toLowerCase().startsWith("mark ")) {
                int taskIndex = parseTaskIndex(userInput);
                if (taskIndex >= 0 && taskIndex < tasks.size()) {
                    tasks.get(taskIndex).markAsDone();
                    printTaskStatusChange("Nice! I've marked this task as done:", taskIndex);
                } else {
                    printInvalidTaskNumber();
                }
            }
            // Command to unmark a task
            else if (userInput.toLowerCase().startsWith("unmark ")) {
                int taskIndex = parseTaskIndex(userInput);
                if (taskIndex >= 0 && taskIndex < tasks.size()) {
                    tasks.get(taskIndex).markAsNotDone();
                    printTaskStatusChange("OK, I've marked this task as not done yet:", taskIndex);
                } else {
                    printInvalidTaskNumber();
                }
            }
            // Command to tell a joke
            else if (userInput.equalsIgnoreCase("tell me a joke")) {
                printDividerWithMessage("Why did the scarecrow win an award? Because he was outstanding in his field!");
            }
            // Command to exit
            else if (userInput.equalsIgnoreCase("bye")) {
                printDividerWithMessage("Bye. Hope to see you again soon!");
                break;
            }
            // For all other inputs, add the task and display "added: {input}"
            else {
                Task newTask = new Task(userInput);
                tasks.add(newTask);
                printDividerWithMessage("added: " + userInput);
            }
        }

        scanner.close();
    }

    // Method to print the task list
    private static void printTaskList() {
        StringBuilder taskList = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            taskList.append("\n ").append(i + 1).append(". ").append(task.getStatusIcon()).append(" ").append(task.getDescription());
        }
        printDividerWithMessage(taskList.toString());
    }

    // Method to print task status change messages
    private static void printTaskStatusChange(String message, int taskIndex) {
        String statusChangeMessage = message + "\n   " + tasks.get(taskIndex).getStatusIcon() + " " + tasks.get(taskIndex).getDescription();
        printDividerWithMessage(statusChangeMessage);
    }

    // Method to print invalid task number message
    private static void printInvalidTaskNumber() {
        printDividerWithMessage("Invalid task number.");
    }

    // Method to print general responses with a divider
    private static void printDividerWithMessage(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(" " + message);
        System.out.println("____________________________________________________________");
    }

    // Helper method to parse the task index from user input
    private static int parseTaskIndex(String userInput) {
        try {
            return Integer.parseInt(userInput.split(" ")[1]) - 1; // Convert to zero-based index
        } catch (Exception e) {
            return -1; // Return -1 if parsing fails
        }
    }
}