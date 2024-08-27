import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Asta {
    private static final String FILE_PATH = "./data/asta.txt";
    private static ArrayList<Task> tasks = new ArrayList<>();
    public static void main(String[] args) {
        loadTasksFromFile();

        Scanner scanner = new Scanner(System.in);

        System.out.println("""
                Hello! I'm Asta
                What can I do for you?
                """);

        while (true) {
            String input = scanner.nextLine();
            try {
                Command command = parseCommand(input);
                switch (command) {
                case BYE -> {
                    System.out.println("Bye. Hope to see you again soon!");
                    scanner.close();
                    return;
                }
                case LIST -> {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                }
                case MARK -> handleMarkCommand(input, tasks);
                case UNMARK -> handleUnmarkCommand(input, tasks);
                case TODO -> handleTodoCommand(input, tasks);
                case DEADLINE -> handleDeadlineCommand(input, tasks);
                case EVENT -> handleEventCommand(input, tasks);
                case DELETE -> handleDeleteCommand(input, tasks);
                case UNKNOWN -> AstaException.handleInvalidCommand();
                }
            } catch (AstaException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void loadTasksFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("No existing data file found. Starting with an empty task list.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                switch (type) {
                case "T" -> tasks.add(new ToDo(description, isDone));
                case "D" -> {
                    String by = parts[3];
                    tasks.add(new Deadline(description, by, isDone));
                }
                case "E" -> {
                    String from = parts[3];
                    String to = parts[4];
                    tasks.add(new Event(description, from, to, isDone));
                }
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
    }

    private static void saveTasksToFile() {
        File file = new File(FILE_PATH);
        file.getParentFile().mkdirs();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    private static Command parseCommand(String input) {
        if (input.equalsIgnoreCase("bye")) {
            return Command.BYE;
        } else if (input.equalsIgnoreCase("list")) {
            return Command.LIST;
        } else if (input.startsWith("mark ")) {
            return Command.MARK;
        } else if (input.startsWith("unmark ")) {
            return Command.UNMARK;
        } else if (input.startsWith("todo ")) {
            return Command.TODO;
        } else if (input.startsWith("deadline ")) {
            return Command.DEADLINE;
        } else if (input.startsWith("event ")) {
            return Command.EVENT;
        } else if (input.startsWith("delete ")) {
            return Command.DELETE;
        } else {
            return Command.UNKNOWN;
        }
    }

    private static void handleMarkCommand(String input, ArrayList<Task> tasks) throws AstaException {
        try {
            int taskNumber = Integer.parseInt(input.substring(5)) - 1;
            if (taskNumber >= 0 && taskNumber < tasks.size()) {
                tasks.get(taskNumber).markAsDone();
                saveTasksToFile();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks.get(taskNumber));
            } else {
                AstaException.handleInvalidMarkTaskNumber();
            }
        } catch (NumberFormatException e) {
            AstaException.handleInvalidTaskNumberFormat("mark");
        }
    }

    private static void handleUnmarkCommand(String input, ArrayList<Task> tasks) throws AstaException {
        try {
            int taskNumber = Integer.parseInt(input.substring(7)) - 1;
            if (taskNumber >= 0 && taskNumber < tasks.size()) {
                tasks.get(taskNumber).markAsNotDone();
                saveTasksToFile();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(tasks.get(taskNumber));
            } else {
                AstaException.handleInvalidUnmarkTaskNumber();
            }
        } catch (NumberFormatException e) {
            AstaException.handleInvalidTaskNumberFormat("unmark");
        }
    }

    private static void handleTodoCommand(String input, ArrayList<Task> tasks) throws AstaException {
        String description = input.substring(5).trim();
        if (description.isEmpty()) {
            AstaException.handleEmptyTodoDescription();
        }
        tasks.add(new ToDo(description));
        saveTasksToFile();
        System.out.println("Got it. I've added this task:");
        System.out.println("[T][ ] " + description);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void handleDeadlineCommand(String input, ArrayList<Task> tasks) throws AstaException {
        String[] parts = input.substring(9).split(" /by ");
        if (parts.length < 2 || parts[0].trim().isEmpty()) {
            AstaException.handleInvalidDeadlineInput();
        }
        tasks.add(new Deadline(parts[0], parts[1]));
        saveTasksToFile();
        System.out.println("Got it. I've added this task:");
        System.out.println("[D][ ] " + parts[0] + " (by: " + parts[1] + ")");
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void handleEventCommand(String input, ArrayList<Task> tasks) throws AstaException {
        String[] parts = input.substring(6).split(" /from | /to ");
        if (parts.length < 3 || parts[0].trim().isEmpty()) {
            AstaException.handleInvalidEventInput();
        }
        tasks.add(new Event(parts[0], parts[1], parts[2]));
        saveTasksToFile();
        System.out.println("Got it. I've added this task:");
        System.out.println("[E][ ] " + parts[0] + " (from: " + parts[1] + " to: " + parts[2] + ")");
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void handleDeleteCommand(String input, ArrayList<Task> tasks) throws AstaException {
        try {
            int taskNumber = Integer.parseInt(input.substring(7)) - 1;
            if (taskNumber >= 0 && taskNumber < tasks.size()) {
                Task removedTask = tasks.remove(taskNumber);
                saveTasksToFile();
                System.out.println("Noted. I've removed this task:");
                System.out.println(removedTask);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else {
                AstaException.handleInvalidDeleteTaskNumber();
            }
        } catch (NumberFormatException e) {
            AstaException.handleInvalidTaskNumberFormat("delete");
        }
    }
}
