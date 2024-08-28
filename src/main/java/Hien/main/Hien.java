package Hien.main;

import Hien.task.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Hien {
    private static final ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Hien");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String input = scanner.nextLine().trim();

            System.out.println("____________________________________________________________");

            if (input.equalsIgnoreCase("bye")) {
                System.out.println(" Bye. Hope to see you again soon!");
                break;
            } else if (input.equalsIgnoreCase("list")) {
                listTasks();
            } else if (input.startsWith("mark ")) {
                markTask(input, true);
            } else if (input.startsWith("unmark ")) {
                markTask(input, false);
            } else if (input.startsWith("todo ")) {
                addTodo(input);
            } else if (input.startsWith("deadline ")) {
                addDeadline(input);
            } else if (input.startsWith("event ")) {
                addEvent(input);
            } else {
                System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

            System.out.println("____________________________________________________________");
        }

        scanner.close();
    }

    private static void addTodo(String input) {
        String description = input.substring(5).trim();
        Todo todo = new Todo(description);
        tasks.add(todo);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + todo);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void addDeadline(String input) {
        String[] parts = input.substring(9).split(" /by ");
        if (parts.length == 2) {
            Deadline deadline = new Deadline(parts[0].trim(), parts[1].trim());
            tasks.add(deadline);
            System.out.println(" Got it. I've added this task:");
            System.out.println("   " + deadline);
            System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        } else {
            System.out.println(" ☹ OOPS!!! The deadline format is incorrect. Please use: deadline <description> /by <date>");
        }
    }

    private static void addEvent(String input) {
        String[] parts = input.substring(6).split(" /from | /to ");
        if (parts.length == 3) {
            Event event = new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());
            tasks.add(event);
            System.out.println(" Got it. I've added this task:");
            System.out.println("   " + event);
            System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        } else {
            System.out.println(" ☹ OOPS!!! The event format is incorrect. Please use: event <description> /from <start-date> /to <end-date>");
        }
    }

    private static void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println(" There are no tasks in your list.");
        } else {
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + "." + tasks.get(i));
            }
        }
    }

    private static void markTask(String input, boolean isDone) {
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            if (index >= 0 && index < tasks.size()) {
                Task task = tasks.get(index);
                if (isDone) {
                    task.markAsDone();
                    System.out.println(" Nice! I've marked this task as done:");
                } else {
                    task.markAsUndone();
                    System.out.println(" OK, I've marked this task as not done yet:");
                }
                System.out.println("   " + task);
            } else {
                System.out.println(" ☹ OOPS!!! The task number is invalid.");
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println(" ☹ OOPS!!! The command format is incorrect. Please use 'mark <task number>' or 'unmark <task number>'.");
        }
    }
}