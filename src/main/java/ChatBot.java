import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;
import Exception.MissingDateException;
import Exception.EmptyDescriptionException;

import java.util.ArrayList;
import java.util.Scanner;


import java.util.ArrayList;
import java.util.Scanner;

public class ChatBot {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm chatbot lisWhat can I do for you?");

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            try {
                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (input.startsWith("todo")) {
                    addTodoTask(input);
                } else if (input.startsWith("deadline")) {
                    addDeadlineTask(input);
                } else if (input.startsWith("event")) {
                    addEventTask(input);
                } else if (input.equals("list")) {
                    listTasks();
                } else if (input.startsWith("mark")) {
                    markTask(Integer.parseInt(input.substring(5)) - 1);
                } else if (input.startsWith("unmark")) {
                    unmarkTask(Integer.parseInt(input.substring(7)) - 1);
                } else if (input.equalsIgnoreCase("exit")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else {
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (EmptyDescriptionException | MissingDateException e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }
    private static void listTasks() {
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    private static void markTask(int id) {
        tasks.get(id).markDone();
    }
    private static void unmarkTask(int id) {
        tasks.get(id).unmarkDone();
    }

    private static void addTodoTask(String input) throws EmptyDescriptionException {
        if (input.trim().length() <= 5) {
            throw new EmptyDescriptionException("OPS!!! The description of a todo cannot be empty.");
        }
        String description = input.substring(5).trim();
        tasks.add(new Todo(description));
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + new Todo(description));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void addDeadlineTask(String input) throws EmptyDescriptionException, MissingDateException {
        String[] parts = input.split(" /by ");
        if (parts.length < 2) {
            throw new MissingDateException("OPS!!! The date of a deadline cannot be empty.");
        }
        String description = parts[0].substring(9).trim();
        if (description.isEmpty()) {
            throw new EmptyDescriptionException("OPS!!! The description of a deadline cannot be empty.");
        }
        String by = parts[1].trim();
        tasks.add(new Deadline(description, by));
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + new Deadline(description, by));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void addEventTask(String input) throws EmptyDescriptionException, MissingDateException {
        String[] parts = input.split(" /from | /to ");
        if (parts.length < 3) {
            throw new MissingDateException("OPS!!! The start or end time of an event cannot be missing.");
        }
        String description = parts[0].substring(6).trim();
        if (description.isEmpty()) {
            throw new EmptyDescriptionException("OPS!!! The description of an event cannot be empty.");
        }
        String start = parts[1].trim();
        String end = parts[2].trim();
        tasks.add(new Event(description, start, end));
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + new Event(description, start, end));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }
}
