import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class King {
    private static final String FILE_PATH = "./data/King.txt";

    public static void main(String[] args) {
        String logo = " _  __ _             \n" +
                "| |/ /(_)_ __   __ _ \n" +
                "| ' / | | '_ \\ / _` |\n" +
                "| . \\ | | | | | (_| |\n" +
                "|_|\\_\\|_|_| |_|\\__, |\n" +
                "               |___/ ";

        System.out.println("Behold the wrath of the\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("What do you have for me?");
        System.out.println("____________________________________________________________");

        // Ensure the directory exists
        ensureDirectoryExists();

        // ArrayList to store tasks
        ArrayList<Task> tasks = new ArrayList<>();

        // Load tasks from file
        loadTasks(tasks);

        // Take in user input
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                break;
            } else if (input.equalsIgnoreCase("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(" " + (i + 1) + ". " + tasks.get(i).toString());
                }
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("delete")) {
                try {
                    String[] parts = input.split(" ");
                    int num = Integer.parseInt(parts[1]);
                    System.out.println("I have relieved your suffering of:\n" + tasks.get(num - 1).toString());
                    tasks.remove(num - 1);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Invalid list number entered!");
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number.");
                }
            } else if (input.startsWith("mark")) {
                try {
                    String[] parts = input.split(" ");
                    int num = Integer.parseInt(parts[1]);
                    tasks.get(num - 1).markAsDone();
                    System.out.println("Good job on the completion my minion!\n" + tasks.get(num - 1).toString());
                    System.out.println("____________________________________________________________");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Invalid list number entered!");
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number.");
                }
            } else if (input.startsWith("unmark")) {
                try {
                    String[] parts = input.split(" ");
                    int num = Integer.parseInt(parts[1]);
                    tasks.get(num - 1).markAsUndone();
                    System.out.println("Better get to work before I execute you!\n" + tasks.get(num - 1).toString());
                    System.out.println("____________________________________________________________");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Invalid list number entered!");
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number.");
                }
            } else if (input.startsWith("todo") && input.length() >= 6) {
                String description = input.substring(5).trim();
                tasks.add(new Todo(description));
                System.out.println("____________________________________________________________");
                System.out.println("Approved. I've added this to your list of duties:\n" + tasks.get(tasks.size() - 1).toString());
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("deadline")) {
                String[] parts = input.split(" /by ");
                if (parts.length == 2 && parts[0].length() >= 10) {
                    String description = parts[0].substring(9).trim();
                    String by = parts[1].trim();
                    tasks.add(new Deadline(description, by));
                    System.out.println("____________________________________________________________");
                    System.out.println("Approved. I've added this to your list of duties:\n" + tasks.get(tasks.size() - 1).toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else {
                    System.out.println("Invalid deadline format.");
                }
            } else if (input.startsWith("event")) {
                String[] parts = input.split(" /from | /to ");
                if (parts.length == 3 && parts[0].length() >= 7) {
                    String description = parts[0].substring(6).trim();
                    String from = parts[1].trim();
                    String to = parts[2].trim();
                    tasks.add(new Event(description, from, to));
                    System.out.println("____________________________________________________________");
                    System.out.println("Approved. I've added this to your list of duties:\n" + tasks.get(tasks.size() - 1).toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else {
                    System.out.println("Invalid event format.");
                }
            } else {
                System.out.println("Invalid Command");
                System.out.println("____________________________________________________________");
            }

            // Save tasks to file after each change
            saveTasks(tasks);
        }

        System.out.println("You are dismissed my humble servant.");
        System.out.println("____________________________________________________________");
        scanner.close();
    }

    private static void ensureDirectoryExists() {
        File directory = new File("./data");
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    private static void saveTasks(ArrayList<Task> tasks) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            for (Task task : tasks) {
                writer.write(task.toFileString());
                writer.write(System.lineSeparator()); // Adds a newline after each task
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks.");
            e.printStackTrace();
        }
    }

    private static void loadTasks(ArrayList<Task> tasks) {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return; // File does not exist; nothing to load
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = Task.fromFileString(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while loading tasks.");
            e.printStackTrace();
        }
    }
}
