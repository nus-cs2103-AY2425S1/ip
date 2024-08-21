import java.util.ArrayList;
import java.util.Scanner;

public class Chatsy {
    static final String name = "Chatsy";
    static final String line = "\t____________________________________________________________";
    static final Scanner scanner = new Scanner(System.in);

    static final ArrayList<Task> tasks = new ArrayList<>();

    public static void greet() {
        System.out.println(line);
        System.out.println("\tHello! I'm " + name + "\n\tWhat can I do for you?");
        System.out.println(line);
    }

    public static void output(String output) {
        System.out.println(line);
        System.out.println(output);
        System.out.println(line);
    }

    public static void exit() {
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(line);
        scanner.close(); // Close the scanner to prevent resource leaks
    }

    public static void addTask(String taskDescription) {
        tasks.add(new Task(taskDescription));
        output("\tadded: " + taskDescription);
    }

    public static void listTasks() {
        if (tasks.isEmpty()) {
            output("\tNo tasks to list.");
        } else {
            StringBuilder listOutput = new StringBuilder("\tHere are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                listOutput.append("\t").append(i + 1).append(". ").append(tasks.get(i)).append("\n");
            }
            output(listOutput.toString());
        }
    }

    public static void markTask(int index) {
        if (index >= 1 && index <= tasks.size()) {
            tasks.get(index - 1).markAsDone();
            output("\tNice! I've marked this task as done:\n\t  " + tasks.get(index - 1));
        } else {
            output("\tInvalid task number.");
        }
    }

    public static void unmarkTask(int index) {
        if (index >= 1 && index <= tasks.size()) {
            tasks.get(index - 1).markAsNotDone();
            output("\tOK, I've marked this task as not done yet:\n\t  " + tasks.get(index - 1));
        } else {
            output("\tInvalid task number.");
        }
    }

    public static void nextCommand() {
        String command = scanner.nextLine();
        String[] parts = command.split(" ", 2);
        String action = parts[0];

        switch (action) {
            case "bye":
                exit();
                break;
            case "list":
                listTasks();
                nextCommand();
                break;
            case "mark":
                if (parts.length > 1) {
                    int taskNumber = Integer.parseInt(parts[1]);
                    markTask(taskNumber);
                } else {
                    output("\tPlease specify the task number to mark.");
                }
                nextCommand();
                break;
            case "unmark":
                if (parts.length > 1) {
                    int taskNumber = Integer.parseInt(parts[1]);
                    unmarkTask(taskNumber);
                } else {
                    output("\tPlease specify the task number to unmark.");
                }
                nextCommand();
                break;
            default:
                addTask(command);
                nextCommand();
                break;
        }
    }

    public static void main(String[] args) {
        greet();
        nextCommand();
    }
}
