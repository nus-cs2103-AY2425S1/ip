import java.util.ArrayList;
import java.util.List;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;

public class Vinegar {
    private static List<Task> tasks = new ArrayList<>();
    private static final String FILE_PATH = "./data/vinegar.txt";

    public static void main(String[] args) {
        loadTasksFromFile();

        Scanner scanner = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Vinegar\n" +
                "What Can I do for you?");
        System.out.println("____________________________________________________________");

        chatLoop:
        while (true) {
            try {
                String userInput = scanner.nextLine();
                String[] inputParts = userInput.split(" ", 2);
                String instruction = inputParts[0].toLowerCase();

                switch (instruction) {
                    case "bye":
                        System.out.println("____________________________________________________________");
                        System.out.println("Bye. Hope to see you again soon!");
                        System.out.println("____________________________________________________________");
                        saveTasksToFile();
                        break chatLoop;

                    case "list":
                        System.out.println("____________________________________________________________");
                        System.out.println(" Here are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + "." + tasks.get(i));
                        }
                        System.out.println("____________________________________________________________");
                        break;

                    case "mark":
                        if (inputParts.length < 2) throw new VinegarException("Please specify which task to mark.");
                        int markIndex = Integer.parseInt(inputParts[1]) - 1;
                        tasks.get(markIndex).markAsDone();
                        System.out.println("____________________________________________________________");
                        System.out.println(" Nice! I've marked this task as done:");
                        System.out.println("   " + tasks.get(markIndex));
                        System.out.println("____________________________________________________________");
                        saveTasksToFile();
                        break;

                    case "unmark":
                        if (inputParts.length < 2) throw new VinegarException("Please specify which task to unmark.");
                        int unmarkIndex = Integer.parseInt(inputParts[1]) - 1;
                        tasks.get(unmarkIndex).markAsNotDone();
                        System.out.println("____________________________________________________________");
                        System.out.println(" OK, I've marked this task as not done yet:");
                        System.out.println("   " + tasks.get(unmarkIndex));
                        System.out.println("____________________________________________________________");
                        saveTasksToFile();
                        break;

                    case "todo":
                        if (inputParts.length < 2) throw new VinegarException("Please specify the description.");
                        Task todoTask = new Todo(inputParts[1]);
                        tasks.add(todoTask);
                        printTaskAdded(todoTask);
                        saveTasksToFile();
                        break;

                    case "deadline":
                        if (inputParts.length < 2) throw new VinegarException("Please specify the description and deadline.");
                        String[] deadlineParts = inputParts[1].split(" /by ");
                        if (deadlineParts.length < 2) throw new VinegarException("Please specify the deadline using /by.");
                        Task deadlineTask = new Deadline(deadlineParts[0], deadlineParts[1]);
                        tasks.add(deadlineTask);
                        printTaskAdded(deadlineTask);
                        saveTasksToFile();
                        break;

                    case "event":
                        if (inputParts.length < 2) throw new VinegarException("Please specify the description and event time.");
                        String[] eventParts = inputParts[1].split("/from | /to ");
                        if (eventParts.length < 3) throw new VinegarException("Please specify the event time using /from and /to.");
                        Task eventTask = new Event(eventParts[0], eventParts[1], eventParts[2]);
                        tasks.add(eventTask);
                        printTaskAdded(eventTask);
                        saveTasksToFile();
                        break;

                    case "delete":
                        handleDelete(inputParts);
                        saveTasksToFile();
                        break;

                    default:
                        throw new VinegarException("""
                                Please use these commands
                                 todo\s
                                 deadline\s
                                 event\s
                                 list\s
                                 mark\s
                                 unmark\s
                                 bye""");
                }
            } catch (VinegarException e) {
                System.out.println("____________________________________________________________");
                System.out.println(" OOPS!!! " + e.getMessage());
                System.out.println("____________________________________________________________");
            } catch (NumberFormatException e) {
                System.out.println("____________________________________________________________");
                System.out.println(" OOPS!!! The task number must be an integer.");
                System.out.println("____________________________________________________________");
            } catch (Exception e) {
                System.out.println("____________________________________________________________");
                System.out.println(" OOPS!!! An unexpected error occurred: " + e.getMessage());
                System.out.println("____________________________________________________________");
            }
        }

        scanner.close();
    }

    private static void handleDelete(String[] inputParts) throws VinegarException {
        if (inputParts.length < 2) throw new VinegarException("Please specify which task to delete.");
        int taskIndex = Integer.parseInt(inputParts[1]) - 1;

        Task taskToRemove = tasks.remove(taskIndex);
        System.out.println("____________________________________________________________");
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + taskToRemove);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    private static void printTaskAdded(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    // Save tasks to file
    private static void saveTasksToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat() + "\n");
            }
        } catch (IOException e) {
            System.out.println(" OOPS!!! Unable to save tasks to file.");
        }
    }

    // Load tasks from file
    private static void loadTasksFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];
                Task task = null;

                switch (type) {
                    case "T":
                        task = new Todo(description);
                        break;
                    case "D":
                        task = new Deadline(description, parts[3]);
                        break;
                    case "E":
                        task = new Event(description, parts[3], parts[4]);
                        break;
                }
                if (task != null && isDone) {
                    task.markAsDone();
                }
                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println(" OOPS!!! Unable to load tasks from file.");
        }
    }
}
