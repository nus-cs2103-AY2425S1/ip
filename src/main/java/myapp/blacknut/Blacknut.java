package myapp.blacknut;

import java.util.ArrayList;
import java.util.Scanner;
import myapp.blacknut.BlacknutExceptions.InvalidCommandException;
import myapp.blacknut.BlacknutExceptions.EmptyDescriptionException;
import myapp.blacknut.BlacknutExceptions.InvalidTaskNumberException;
import myapp.blacknut.BlacknutExceptions.IncorrectFormatException;

public class Blacknut {

    public static void main(String[] args) {
        String logo = " ____  _            _                _   \n"
                + "|  _ \\| |          | |              | |  \n"
                + "| |_) | | __ _  ___| | ___ __  _   _| |_ \n"
                + "|  _ <| |/ _` |/ __| |/ / '_ \\| | | | __|\n"
                + "| |_) | | (_| | (__|   <| | | | |_| | |_ \n"
                + "|____/|_|\\__,_|\\___|_|\\_\\_| |_|\\__,_|\\__|\n";

        System.out.println("Hello from\n" + logo);

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Blacknut");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        while (true) {
            String input = scanner.nextLine().trim();

            System.out.println("____________________________________________________________");

            try {
                if (input.equalsIgnoreCase("bye")) {
                    System.out.println(" Bye. Hope to see you again soon!");
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    listTasks(tasks);
                } else if (input.startsWith("mark ")) {
                    markTask(tasks, input, true);
                } else if (input.startsWith("unmark ")) {
                    markTask(tasks, input, false);
                } else if (input.startsWith("todo ")) {
                    addTodo(tasks, input);
                } else if (input.startsWith("deadline ")) {
                    addDeadline(tasks, input);
                } else if (input.startsWith("event ")) {
                    addEvent(tasks, input);
                } else if (input.startsWith("delete ")) {
                    deleteTask(tasks, input);
                } else {
                    throw new InvalidCommandException("I don't know what that means. Please enter a valid command.");
                }
            } catch (InvalidCommandException | EmptyDescriptionException | InvalidTaskNumberException | IncorrectFormatException e) {
                System.out.println(" ☹ OOPS!!! " + e.getMessage());
            }

            System.out.println("____________________________________________________________");
        }

        scanner.close();
    }

    private static void listTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println(" The list is empty.");
        } else {
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + "." + tasks.get(i));
            }
        }
    }

    private static void markTask(ArrayList<Task> tasks, String input, boolean markAsDone) throws InvalidTaskNumberException {
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            if (index >= 0 && index < tasks.size()) {
                Task task = tasks.get(index);
                if (markAsDone) {
                    task.markAsDone();
                    System.out.println(" Nice! I've marked this task as done:");
                } else {
                    task.markAsNotDone();
                    System.out.println(" OK, I've marked this task as not done yet:");
                }
                System.out.println("   " + task);
            } else {
                throw new InvalidTaskNumberException("Invalid task number. Please provide a valid number from the list.");
            }
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException("Invalid command format. Please enter a valid number after 'mark' or 'unmark'.");
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException("Task number out of range. Please provide a valid number from the list.");
        }
    }

    private static void addTodo(ArrayList<Task> tasks, String input) throws EmptyDescriptionException {
        String description = input.substring(5).trim();
        if (description.isEmpty()) {
            throw new EmptyDescriptionException("The description of a todo cannot be empty.");
        }
        Task newTask = new Todo(description);
        tasks.add(newTask);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + newTask);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void addDeadline(ArrayList<Task> tasks, String input) throws IncorrectFormatException {
        String[] parts = input.substring(9).split(" /by ");
        if (parts.length != 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new IncorrectFormatException("The format for a deadline should be: deadline <description> /by <time>");
        }
        Task newTask = new Deadline(parts[0].trim(), parts[1].trim());
        tasks.add(newTask);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + newTask);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void addEvent(ArrayList<Task> tasks, String input) throws IncorrectFormatException {
        String[] parts = input.substring(6).split(" /from | /to ");
        if (parts.length != 3 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
            throw new IncorrectFormatException("The format for an event should be: event <description> /from <start time> /to <end time>");
        }
        Task newTask = new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());
        tasks.add(newTask);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + newTask);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
    }

    // Method to delete a task
    private static void deleteTask(ArrayList<Task> tasks, String input) throws InvalidTaskNumberException {
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            if (index >= 0 && index < tasks.size()) {
                Task removedTask = tasks.remove(index);
                System.out.println(" Noted. I've removed this task:");
                System.out.println("   " + removedTask);
                System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
            } else {
                throw new InvalidTaskNumberException("Invalid task number. Please provide a valid number from the list.");
            }
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException("Invalid command format. Please enter a valid number after 'delete'.");
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException("Task number out of range. Please provide a valid number from the list.");
        }
    }
}
