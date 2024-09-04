import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Crack {
    private static final String FILE_PATH = "./data/crack.txt";
    public static void main(String[] args) {
        String divider = "____________________________________________________________\n";
        String greeting = divider
                + " Hello! I'm Crack\n"
                + " What can I do for you?\n"
                + divider;
        System.out.println(greeting);

        Scanner scanner = new Scanner(System.in);
        String input;
        ArrayList<Task> tasks = new ArrayList<>();
        loadTasksFromFile(tasks);

        while (true) {
            System.out.print("You: ");
            input = scanner.nextLine().trim();

            if (input.equals("bye")) {
                System.out.println("Goodbye!");
                break;
            } else if (input.equals("list")) {
                if (tasks.isEmpty()) {
                    System.out.println(divider + " Your task list is empty.\n" + divider);
                } else {
                    System.out.println(divider + " Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + "." + tasks.get(i));
                    }
                    System.out.println(divider);
                }
            } else if (input.startsWith("mark ")) {
                try {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (index >= 0 && index < tasks.size()) {
                        tasks.get(index).markAsDone();
                        System.out.println(divider + " Nice! I've marked this task as done:\n   " + tasks.get(index) + "\n" + divider);
                        saveTasksToFile(tasks);
                    } else {
                        System.out.println(divider + " Error: Task number out of range.\n" + divider);
                    }
                } catch (Exception e) {
                    System.out.println(divider + " Error: Please provide a valid task number.\n" + divider);
                }
            } else if (input.startsWith("unmark ")) {
                try {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (index >= 0 && index < tasks.size()) {
                        tasks.get(index).unmark();
                        System.out.println(divider + " OK, I've marked this task as not done yet:\n   " + tasks.get(index) + "\n" + divider);
                        saveTasksToFile(tasks);
                    } else {
                        System.out.println(divider + " Error: Task number out of range.\n" + divider);
                    }
                } catch (Exception e) {
                    System.out.println(divider + " Error: Please provide a valid task number.\n" + divider);
                }
            } else if (input.startsWith("todo ")) {
                String description = input.substring(5).trim();
                if (description.isEmpty()) {
                    System.out.println(divider + " Error: The description of a todo cannot be empty.\n" + divider);
                } else {
                    tasks.add(new Todo(description));
                    System.out.println(divider + " Got it. I've added this task:\n   [T][ ] " + description + "\n"
                            + " Now you have " + tasks.size() + " tasks in the list.\n" + divider);
                    saveTasksToFile(tasks);
                }
            } else if (input.startsWith("deadline ")) {
                try {
                    String[] parts = input.substring(9).split(" /by ");
                    if (parts.length < 2 || parts[0].trim().isEmpty()) {
                        throw new IllegalArgumentException();
                    }
                    String description = parts[0].trim();
                    String by = parts[1].trim();
                    tasks.add(new Deadline(description, by));
                    System.out.println(divider + " Got it. I've added this task:\n   [D][ ] " + description + " (by: " + by + ")\n"
                            + " Now you have " + tasks.size() + " tasks in the list.\n" + divider);
                    saveTasksToFile(tasks);
                } catch (Exception e) {
                    System.out.println(divider + " Error: Invalid format for deadline. Use: deadline <description> /by <time>.\n" + divider);
                }
            } else if (input.startsWith("event ")) {
                try {
                    String[] parts = input.substring(6).split(" /from | /to ");
                    if (parts.length < 3 || parts[0].trim().isEmpty()) {
                        throw new IllegalArgumentException();
                    }
                    String description = parts[0].trim();
                    String from = parts[1].trim();
                    String to = parts[2].trim();
                    tasks.add(new Event(description, from, to));
                    System.out.println(divider + " Got it. I've added this task:\n   [E][ ] " + description + " (from: " + from + " to: " + to + ")\n"
                            + " Now you have " + tasks.size() + " tasks in the list.\n" + divider);
                    saveTasksToFile(tasks);
                } catch (Exception e) {
                    System.out.println(divider + " Error: Invalid format for event. Use: event <description> /from <start> /to <end>.\n" + divider);
                }
            } else if (input.startsWith("delete ")) {
                try {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (index >= 0 && index < tasks.size()) {
                        Task removedTask = tasks.remove(index);
                        System.out.println(divider + " Noted. I've removed this task:\n   " + removedTask + "\n"
                                + " Now you have " + tasks.size() + " tasks in the list.\n" + divider);
                        saveTasksToFile(tasks);
                    } else {
                        System.out.println(divider + " Error: Task number out of range.\n" + divider);
                    }
                } catch (Exception e) {
                    System.out.println(divider + " Error: Please provide a valid task number.\n" + divider);
                }
            } else {
                System.out.println(divider + " Error: Invalid Command.\n" + divider);
            }
        }

        scanner.close();
    }

    private static void saveTasksToFile(ArrayList<Task> tasks) {
        try {
            File dir = new File("./data");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            

            File file = new File(FILE_PATH);
            System.out.println("File path: " + file.getAbsolutePath());
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter writer = new FileWriter(FILE_PATH);
            for (Task task : tasks) {
                writer.write(task.toSaveString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("Error: Unable to save tasks.");
        }
    }

    private static void loadTasksFromFile(ArrayList<Task> tasks) {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                return; // No file yet, nothing to load
            }
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(" \\| ");
                switch (parts[0]) {
                    case "T":
                        Todo todo = new Todo(parts[2]);
                        if (parts[1].equals("1")) {
                            todo.markAsDone();
                        }
                        tasks.add(todo);
                        break;
                    case "D":
                        Deadline deadline = new Deadline(parts[2], parts[3]);
                        if (parts[1].equals("1")) {
                            deadline.markAsDone();
                        }
                        tasks.add(deadline);
                        break;
                    case "E":
                        Event event = new Event(parts[2], parts[3], parts[4]);
                        if (parts[1].equals("1")) {
                            event.markAsDone();
                        }
                        tasks.add(event);
                        break;
                    default:
                        throw new IOException("Corrupted task format");
                }
            }
            fileScanner.close();
        } catch (IOException e) {
            System.out.println("Error: Unable to load tasks or file is corrupted.");
        }
    }
}
