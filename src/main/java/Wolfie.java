import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Wolfie {
    private static Storage storage;
    private static List<Task> tasks;

    public static void main(String[] args) {
        String FILE_PATH = "./data/tasks.txt";
        storage = new Storage(FILE_PATH);
        try {
            tasks = storage.load();
        } catch (IOException e) {
            System.out.println("____________________________________________________________");
            System.out.println(" OOPS!!! Error loading tasks: " + e.getMessage());
            System.out.println("____________________________________________________________");
            tasks = new ArrayList<>();
        }

        Scanner scanner = new Scanner(System.in);

        String wolfieArt =
                """
                         __        __   _ _  __ _\s
                         \\ \\      / /__| | |/ _| |
                          \\ \\ /\\ / / _ \\ | | |_| |
                           \\ V  V /  __/ | |  _|_|
                            \\_/\\_/ \\___|_|_|_| (_)
                        """;

        System.out.println("____________________________________________________________");
        System.out.println(wolfieArt);
        System.out.println(" Hello Dean's Lister! I'm Wolfie");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String input = scanner.nextLine();
            try {
                if (input.equals("bye")) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" Bye Dean's Lister! Hope to see you again soon :-)");
                    System.out.println("____________________________________________________________");
                    break; // exit the loop
                } else if (input.equals("list")) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(" " + (i + 1) + ". " + tasks.get(i)); // print all tasks
                    }
                    System.out.println("____________________________________________________________");
                } else if (input.startsWith("mark ")) {
                    int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1; // get the task index
                    if (taskIndex >= 0 && taskIndex < tasks.size()) {
                        tasks.get(taskIndex).markAsDone(); // mark the task as done
                        saveTasks();
                        System.out.println("____________________________________________________________");
                        System.out.println(" Nice! I've marked this task as done:");
                        System.out.println("   " + tasks.get(taskIndex)); // print the task
                        System.out.println("____________________________________________________________");
                    } else {
                        throw new WolfieException("Invalid task number. Please use existing numbers and not the description.");
                    }
                } else if (input.startsWith("unmark ")) {
                    int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (taskIndex >= 0 && taskIndex < tasks.size()) {
                        tasks.get(taskIndex).markAsUndone(); // mark the task as not done
                        saveTasks();
                        System.out.println("____________________________________________________________");
                        System.out.println(" OK! I've marked this task as not done yet:");
                        System.out.println("   " + tasks.get(taskIndex)); // print the task
                        System.out.println("____________________________________________________________");
                    } else {
                        throw new WolfieException("Invalid task number. Please use existing numbers and not the description.");
                    }
                } else if (input.startsWith("todo ")) {
                    String description = input.substring(5).trim();
                    if (description.isEmpty()) {
                        throw new WolfieException("The description of a todo cannot be empty.");
                    }
                    tasks.add(new Todo(description, false)); // create a new task and add it to the list
                    saveTasks();
                    System.out.println("____________________________________________________________");
                    System.out.println(" Got it! I've added this task:");
                    System.out.println("   " + tasks.get(tasks.size() - 1)); // print the task
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    // print the number of tasks
                    System.out.println("____________________________________________________________");
                } else if (input.startsWith("deadline ")) {
                    String[] parts = input.substring(9).split(" /by "); // split by " /by "
                    if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                        throw new WolfieException("The description and deadline of a deadline task cannot be empty.");
                    }
                    String description = parts[0].trim(); // description is before " /by "
                    String by = parts[1].trim(); // by is after " /by "
                    tasks.add(new Deadline(description, by, false)); // create a new deadline task and add it to the list
                    saveTasks();
                    System.out.println("____________________________________________________________");
                    System.out.println(" Got it! I've added this task:");
                    System.out.println("   " + tasks.get(tasks.size() - 1)); // print the task
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    // print the number of tasks
                    System.out.println("____________________________________________________________");
                } else if (input.startsWith("event ")) {
                    String[] parts = input.substring(6).split(" /from | /to ");
                    // split by " /from " or " /to "
                    if (parts.length < 3 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
                        throw new WolfieException("The description, start time, and end time of an event cannot be empty.");
                    }
                    String description = parts[0].trim(); // description is before " /from "
                    String from = parts[1].trim(); // from is after " /from " and before " /to "
                    String to = parts[2].trim(); // to is after " /to "
                    tasks.add(new Event(description, from, to, false)); // create a new event and add it to the list
                    saveTasks();
                    System.out.println("____________________________________________________________");
                    System.out.println(" Got it! I've added this task:");
                    System.out.println("   " + tasks.get(tasks.size() - 1)); // print the task
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    // print the number of tasks
                    System.out.println("____________________________________________________________");
                } else if (input.startsWith("delete ")) {
                    int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1; // get the task index
                    if (taskIndex >= 0 && taskIndex < tasks.size()) {
                        Task removedTask = tasks.remove(taskIndex); // remove the task
                        saveTasks();
                        System.out.println("____________________________________________________________");
                        System.out.println(" Noted! I've removed this task:");
                        System.out.println("   " + removedTask); // print the removed task
                        System.out.println(" Now you have " + tasks.size() + " remaining tasks in the list.");
                        // print the number of tasks
                        System.out.println("____________________________________________________________");
                    } else {
                        throw new WolfieException("Invalid task number. Please use existing numbers and not the description.");
                    }
                } else {
                    throw new WolfieException("I'm sorry Dean's Lister, but I don't know what that means :-(");
                }
            } catch (WolfieException e) { // catch and handle WolfieExceptions
                System.out.println("____________________________________________________________");
                System.out.println(" OOPS!!! " + e.getMessage()); // print the error message
                System.out.println("____________________________________________________________");
            } catch (Exception e) {
                System.out.println("____________________________________________________________");
                System.out.println(" OOPS!!! Something went wrong Dean's Lister :-("); // print a generic error message
                System.out.println("____________________________________________________________");
            }
        }
        scanner.close(); // close scanner and exit the program :)
    }

    private static void saveTasks() {
        try {
            storage.save(tasks);
        } catch (IOException e) {
            System.out.println("____________________________________________________________");
            System.out.println(" OOPS!!! Error saving tasks: " + e.getMessage());
            System.out.println("____________________________________________________________");
        }
    }
}