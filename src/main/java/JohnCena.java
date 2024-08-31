import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class JohnCena {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner;
        if (args.length > 0) {
            try {
                scanner = new Scanner(new File(args[0]));
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
                return;
            }
        } else {
            scanner = new Scanner(System.in);
        }

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm John Cena");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            try {
                handleInput(input);
            } catch (CenaException e) {
                System.out.println("____________________________________________________________");
                System.out.println(" OOPS!!! " + e.getMessage());
                System.out.println("____________________________________________________________");
            }
        }

        scanner.close();
    }


    private static void handleInput(String input) throws CenaException {
        if (input.equals("bye")) {
            System.out.println("____________________________________________________________");
            System.out.println(" Bye. Hope to see you again soon!");
            System.out.println("____________________________________________________________");
            System.exit(0);
        } else if (input.equals("list")) {
            System.out.println("____________________________________________________________");
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + "." + tasks.get(i));
            }
            System.out.println("____________________________________________________________");
        } else if (input.startsWith("mark ")) {
            try {
                int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                if (taskIndex < 0 || taskIndex >= tasks.size()) {
                    throw new CenaInvalidTaskIndexException("The task index is invalid.");
                }
                tasks.get(taskIndex).markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println(" Nice! I've marked this task as done:");
                System.out.println("   " + tasks.get(taskIndex));
                System.out.println("____________________________________________________________");
            } catch (NumberFormatException e) {
                throw new CenaInvalidTaskIndexException("The task index must be a number.");
            }
        } else if (input.startsWith("unmark ")) {
            try {
                int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                if (taskIndex < 0 || taskIndex >= tasks.size()) {
                    throw new CenaInvalidTaskIndexException("The task index is invalid.");
                }
                tasks.get(taskIndex).markAsNotDone();
                System.out.println("____________________________________________________________");
                System.out.println(" OK, I've marked this task as not done yet:");
                System.out.println("   " + tasks.get(taskIndex));
                System.out.println("____________________________________________________________");
            } catch (NumberFormatException e) {
                throw new CenaInvalidTaskIndexException("The task index must be a number.");
            }
        } else if (input.startsWith("todo ")) {
            String description = input.substring(5).trim();
            if (description.isEmpty()) {
                throw new CenaEmptyDescriptionException("The description of a todo cannot be empty.");
            }
            if (description.contains("/by") || description.contains("/from") || description.contains("/to")) {
                throw new CenaInvalidTodoException("A todo should not contain /by, /from, or /to.");
            }
            Task task = new Todo(description);
            tasks.add(task);
            System.out.println("____________________________________________________________");
            System.out.println(" Got it. I've added this task:");
            System.out.println("   " + task);
            System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
            System.out.println("____________________________________________________________");
        } else if (input.startsWith("deadline ")) {
            String[] parts = input.substring(9).split(" /by ");
            if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                throw new CenaInvalidDeadlineException("Incorrect description for deadline. It should contain only /by.");
            }
            Task task = new Deadline(parts[0], parts[1]);
            tasks.add(task);
            System.out.println("____________________________________________________________");
            System.out.println(" Got it. I've added this task:");
            System.out.println("   " + task);
            System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
            System.out.println("____________________________________________________________");
        } else if (input.startsWith("event ")) {
            String[] parts = input.substring(6).split(" /from | /to ");
            if (parts.length < 3 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
                throw new CenaInvalidEventException("Incorrect description for event. It should contain /from and /to.");
            }
            Task task = new Event(parts[0], parts[1], parts[2]);
            tasks.add(task);
            System.out.println("____________________________________________________________");
            System.out.println(" Got it. I've added this task:");
            System.out.println("   " + task);
            System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
            System.out.println("____________________________________________________________");
        } else if (input.startsWith("delete ")) {
            try {
                int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                if (taskIndex < 0 || taskIndex >= tasks.size()) {
                    throw new CenaInvalidTaskIndexException("The task index is invalid.");
                }
                Task removedTask = tasks.remove(taskIndex);
                System.out.println("____________________________________________________________");
                System.out.println(" Noted. I've removed this task:");
                System.out.println("   " + removedTask);
                System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("____________________________________________________________");
            } catch (NumberFormatException e) {
                throw new CenaInvalidTaskIndexException("The task index must be a number.");
            }
        } else {
            throw new CenaUnknownCommandException("I'm sorry, but I don't know what that means :-(\n  Please use a valid command (todo, deadline or event)");
        }
    }
}