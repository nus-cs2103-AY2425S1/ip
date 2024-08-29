import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArsenBot {

    public static void saveTasksToFile() {
        try {
            File directory = new File("./data");
            if (!directory.exists()) {
                directory.mkdir();
            }

            File file = new File("./data/history.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            for (Task task : mem) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error: Unable to save tasks to file.");
        }
    }

    public static void loadTasksFromFile() {
        try {
            File file = new File("./data/history.txt");
            if (!file.exists()) {
                return; // No tasks to load
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                Task task = null;
                switch (parts[0]) {
                    case "T":
                        task = new Todo(parts[2]);
                        break;
                    case "D":
                        task = new Deadline(parts[2], parts[3]);
                        break;
                    case "E":
                        task = new Event(parts[2], parts[3], parts[4]);
                        break;
                }
                if (task != null) {
                    if (parts[1].equals("1")) {
                        task.markAsDone();
                    }
                    mem.add(task);
                }
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("Error: Unable to load tasks from file.");
        } catch (Exception e) {
            System.out.println("Error: Data file is corrupted.");
        }
    }

    private static final ArrayList<Task> mem = new ArrayList<>();

    public static void parseCommand(String input) throws TaskManagerException {
        if (input.startsWith("todo")) {
            if (input.length() <= 5) {
                throw new TaskManagerException("Error: The description of a 'todo' cannot be empty.");
            }
            String description = input.substring(5).trim();
            mem.add(new Todo(description));
            System.out.println("Got it. I've added this task:");
            System.out.println("\t" + mem.get(mem.size() -1).toString());
            System.out.println("Now you have " + mem.size() + " tasks in the list");
        } else if (input.startsWith("deadline")) {
            if (input.length() <= 9) {
                throw new TaskManagerException("Error: The deadline command requires both a task description and a '/by' date.");
            }
            String[] parts = input.substring(9).split(" /by ");
            if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                throw new TaskManagerException("Error: The deadline command requires both a task description and a '/by' date.");
            }
            mem.add(new Deadline(parts[0], parts[1]));
            System.out.println("Got it. I've added this task:");
            System.out.println("\t" + mem.get(mem.size() -1).toString());
            System.out.println("Now you have " + mem.size() + " tasks in the list");
        } else if (input.startsWith("event")) {
            if(input.length() <= 6) {
                throw new TaskManagerException("Error: The event command requires a description, start time, and end time.");
            }
            String[] parts = input.substring(6).split(" /from ");
            if (parts.length < 2 || parts[0].trim().isEmpty() || !parts[1].contains(" /to ")) {
                throw new TaskManagerException("Error: The event command requires a description, start time, and end time.");
            }
            String description = parts[0];
            String[] timeParts = parts[1].split(" /to ");
            if (timeParts[1].isEmpty()) {
                throw new TaskManagerException("Error: The event command requires a description, start time, and end time.");
            }
            mem.add(new Event(description, timeParts[0], timeParts[1]));
            System.out.println("Got it. I've added this task:");
            System.out.println("\t" + mem.get(mem.size() -1).toString());
            System.out.println("Now you have " + mem.size() + " tasks in the list");
        } else if (input.startsWith("list")) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < mem.size(); i ++) {
                System.out.println((i+1) + "." + mem.get(i));
            }
        } else if (input.startsWith("mark")) {
            int pos = Integer.parseInt(input.substring(5)) - 1;
            if (pos < 0 || pos >= mem.size()) {
                throw new TaskManagerException("Error: Invalid task number.");
            }
            System.out.println("Nice! I've marked this task as done:");
            mem.get(pos).markAsDone();
            System.out.println(mem.get(pos));
        } else if (input.startsWith("unmark")) {
            int pos = Integer.parseInt(input.substring(7)) - 1;
            if (pos < 0 || pos >= mem.size()) {
                throw new TaskManagerException("Error: Invalid task number.");
            }
            System.out.println("OK, I've marked this task as not done yet:");
            mem.get(pos).markAsUndone();
            System.out.println(mem.get(pos));
        } else if (input.startsWith("delete")) {
            int pos = Integer.parseInt(input.substring(7)) - 1;
            if (pos < 0 || pos >= mem.size()) {
                throw new TaskManagerException("Error: Invalid task number.");
            }
            System.out.println("Noted. I've removed this task:");
            System.out.println(mem.get(pos));
            mem.remove(pos);
            System.out.println("Now you have " + mem.size() + " tasks in the list.");
        } else {
            throw new TaskManagerException("Error: Unrecognized command. Please enter a valid task command.");
        }

        if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
            saveTasksToFile();
        } else if (input.startsWith("mark") || input.startsWith("unmark") || input.startsWith("delete")) {
            saveTasksToFile();
        }
    }

    public static void greeting() {
        System.out.println("Hello! I'm ArsenBot");
        System.out.println("What can I do for you?\n");
    }

    public static void main(String[] args) {
        greeting();
        loadTasksFromFile();

        Scanner reader = new Scanner(System.in);
        while (true) {
            String input = reader.nextLine();

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n");
                break;
            }

            try {
                parseCommand(input);
            } catch (TaskManagerException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
