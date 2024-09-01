import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

            for (Task task : storedTasks) {
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
                Task task = switch (parts[0]) {
                    case "T" -> new Todo(parts[2]);
                    case "D" -> new Deadline(parts[2], parts[3]);
                    case "E" -> new Event(parts[2], parts[3], parts[4]);
                    default -> null;
                };
                if (task != null) {
                    if (parts[1].equals("1")) {
                        task.markAsDone();
                    }
                    storedTasks.add(task);
                }
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("Error: Unable to load tasks from file.");
        } catch (Exception e) {
            System.out.println("Error: Data file is corrupted.");
        }
    }

    private static final ArrayList<Task> storedTasks = new ArrayList<>();

    public static void parseCommand(String input) throws TaskManagerException {
        if (input.startsWith("todo")) {
            if (input.length() <= 5) {
                throw new TaskManagerException("Error: The description of a 'todo' cannot be empty.");
            }
            String description = input.substring(5).trim();
            storedTasks.add(new Todo(description));
            System.out.println("Got it. I've added this task:");
            System.out.println("\t" + storedTasks.get(storedTasks.size() -1).toString());
            System.out.println("Now you have " + storedTasks.size() + " tasks in the list");
        } else if (input.startsWith("deadline")) {
            if (input.length() <= 9) {
                throw new TaskManagerException("Error: The description of a 'deadline' cannot be empty");
            }
            String[] parts = input.substring(9).split(" /by ");
            if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                throw new TaskManagerException("Error: The deadline command requires both a task description and a '/by' date.");
            }
            storedTasks.add(new Deadline(parts[0].trim(), parts[1].trim()));
            System.out.println("Got it. I've added this task:");
            System.out.println("\t" + storedTasks.get(storedTasks.size() -1).toString());
            System.out.println("Now you have " + storedTasks.size() + " tasks in the list");
        } else if (input.startsWith("event")) {
            if(input.length() <= 6) {
                throw new TaskManagerException("Error: The event command requires a description, start time, and end time.");
            }
            String[] parts = input.substring(6).split(" /from ");
            if (parts.length < 2 || parts[0].trim().isEmpty() || !parts[1].contains(" /to ")) {
                throw new TaskManagerException("Error: The event command requires a description, start time, and end time.");
            }
            String description = parts[0].trim();
            String[] timeParts = parts[1].split(" /to ");
            if (timeParts[1].isEmpty()) {
                throw new TaskManagerException("Error: The event command requires a description, start time, and end time.");
            }
            storedTasks.add(new Event(description, timeParts[0].trim(), timeParts[1].trim()));
            System.out.println("Got it. I've added this task:");
            System.out.println("\t" + storedTasks.get(storedTasks.size() -1).toString());
            System.out.println("Now you have " + storedTasks.size() + " tasks in the list");
        } else if (input.startsWith("list")) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < storedTasks.size(); i ++) {
                System.out.println((i+1) + "." + storedTasks.get(i));
            }
        } else if (input.startsWith("mark")) {
            int position = Integer.parseInt(input.substring(5)) - 1;
            if (position < 0 || position >= storedTasks.size()) {
                throw new TaskManagerException("Error: Invalid task number.");
            }
            System.out.println("Nice! I've marked this task as done:");
            storedTasks.get(position).markAsDone();
            System.out.println(storedTasks.get(position));
        } else if (input.startsWith("unmark")) {
            int position = Integer.parseInt(input.substring(7)) - 1;
            if (position < 0 || position >= storedTasks.size()) {
                throw new TaskManagerException("Error: Invalid task number.");
            }
            System.out.println("OK, I've marked this task as not done yet:");
            storedTasks.get(position).markAsUndone();
            System.out.println(storedTasks.get(position));
        } else if (input.startsWith("delete")) {
            int position = Integer.parseInt(input.substring(7)) - 1;
            if (position < 0 || position >= storedTasks.size()) {
                throw new TaskManagerException("Error: Invalid task number.");
            }
            System.out.println("Noted. I've removed this task:");
            System.out.println(storedTasks.get(position));
            storedTasks.remove(position);
            System.out.println("Now you have " + storedTasks.size() + " tasks in the list.");
        } else if (input.startsWith("on")) {
            String date = input.substring(3).trim() + " 1234";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime queryDate;
            try {
                queryDate = LocalDateTime.parse(date, formatter);
            } catch (Exception e) {
                throw new TaskManagerException("Error: Please enter the date in the correct format (yyyy-MM-dd).");
            }
            System.out.println("Tasks on " + queryDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ":");
            for (Task task : storedTasks) {
                if ((task instanceof Deadline && ((Deadline) task).by.toLocalDate().equals(queryDate.toLocalDate()))) {
                    System.out.println(task);
                } else if (task instanceof Event event) {
                    if (!queryDate.isBefore(event.from) && !queryDate.isAfter(event.to)) {
                        System.out.println(task);
                    }
                }
            }
        } else {
            throw new TaskManagerException("Error: Unrecognized command. Please enter a valid task command.");
        }
        if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")
                || input.startsWith("mark") || input.startsWith("unmark") || input.startsWith("delete")) {
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
