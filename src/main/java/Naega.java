import java.util.ArrayList;
import java.util.Scanner;

public class Naega {
    public static void main(String[] args) {

        // Greeting the user
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Naega");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        // Initialize a list to store user tasks
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String userInput;

        // Main loop
        while (true) {
            userInput = scanner.nextLine();

            try {
                if (userInput.equals("bye")) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    break;
                } else if (userInput.equals("list")) {
                    handleList(tasks);
                } else if (userInput.startsWith("mark ")) {
                    handleMark(tasks, userInput);
                } else if (userInput.startsWith("unmark ")) {
                    handleUnmark(tasks, userInput);
                } else if (userInput.startsWith("todo ")) {
                    handleTodo(tasks, userInput);
                } else if (userInput.startsWith("deadline ")) {
                    handleDeadline(tasks, userInput);
                } else if (userInput.startsWith("event ")) {
                    handleEvent(tasks, userInput);
                } else if (userInput.startsWith("delete ")) {
                    handleDelete(tasks, userInput);
                } else {
                    throw new NaegaException("I don't know how to handle that command.");
                }
            } catch (NaegaException e) {
                System.out.println("____________________________________________________________");
                System.out.println(" Oops! " + e.getMessage());
                System.out.println("____________________________________________________________");
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("____________________________________________________________");
                System.out.println(" Oops! Invalid task number.");
                System.out.println("____________________________________________________________");
            }
        }

        scanner.close();
    }

    private static void handleList(ArrayList<Task> tasks) {
        System.out.println("____________________________________________________________");
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    private static void handleMark(ArrayList<Task> tasks, String userInput) throws NaegaException {
        int taskIndex = parseTaskIndex(userInput, "mark ");
        tasks.get(taskIndex).markAsDone();
        System.out.println("____________________________________________________________");
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + tasks.get(taskIndex));
        System.out.println("____________________________________________________________");
    }

    private static void handleUnmark(ArrayList<Task> tasks, String userInput) throws NaegaException {
        int taskIndex = parseTaskIndex(userInput, "unmark ");
        tasks.get(taskIndex).markAsNotDone();
        System.out.println("____________________________________________________________");
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + tasks.get(taskIndex));
        System.out.println("____________________________________________________________");
    }

    private static void handleTodo(ArrayList<Task> tasks, String userInput) throws NaegaException {
        String description = parseDescription(userInput, "todo ");
        Task newTask = new Todo(description);
        tasks.add(newTask);
        printTaskAdded(tasks, newTask);
    }

    private static void handleDeadline(ArrayList<Task> tasks, String userInput) throws NaegaException {
        String[] parts = parseDescription(userInput, "deadline ").split(" /by ");
        if (parts.length < 2) {
            throw new NaegaException("The deadline must include a '/by' keyword.");
        }
        Task newTask = new Deadline(parts[0], parts[1]);
        tasks.add(newTask);
        printTaskAdded(tasks, newTask);
    }

    private static void handleEvent(ArrayList<Task> tasks, String userInput) throws NaegaException {
        String[] parts = parseDescription(userInput, "event ").split(" /from ");
        if (parts.length < 2) {
            throw new NaegaException("The event must include a '/from' keyword.");
        }
        String description = parts[0];
        String[] timeParts = parts[1].split(" /to ");
        if (timeParts.length < 2) {
            throw new NaegaException("The event must include a '/to' keyword.");
        }
        Task newTask = new Event(description, timeParts[0], timeParts[1]);
        tasks.add(newTask);
        printTaskAdded(tasks, newTask);
    }
    private static void handleDelete(ArrayList<Task> tasks, String userInput) throws NaegaException {
        int taskIndex = parseTaskIndex(userInput, "delete ");
        Task removedTask = tasks.remove(taskIndex);
        System.out.println("____________________________________________________________");
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + removedTask);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    private static String parseDescription(String userInput, String command) throws NaegaException {
        if (userInput.length() <= command.length()) {
            throw new NaegaException("The description of a " + command.trim() + " cannot be empty.");
        }
        return userInput.substring(command.length()).trim();
    }


    private static int parseTaskIndex(String userInput, String command) throws NaegaException {
        try {
            return Integer.parseInt(userInput.substring(command.length()).trim()) - 1;
        } catch (NumberFormatException e) {
            throw new NaegaException("Invalid task number format.");
        }
    }

    private static void printTaskAdded(ArrayList<Task> tasks, Task newTask) {
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + newTask);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }
}