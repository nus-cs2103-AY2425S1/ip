import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Asta {
    private static final String FILE_PATH = "./data/asta.txt";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private static final ArrayList<Task> tasks = new ArrayList<>();

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
                case BYE:
                    System.out.println("Bye. Hope to see you again soon!");
                    scanner.close();
                    return;
                case LIST:
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                    break;
                case MARK:
                    handleMarkCommand(input);
                    break;
                case UNMARK:
                    handleUnmarkCommand(input);
                    break;
                case TODO:
                    handleTodoCommand(input);
                    break;
                case DEADLINE:
                    handleDeadlineCommand(input);
                    break;
                case EVENT:
                    handleEventCommand(input);
                    break;
                case DELETE:
                    handleDeleteCommand(input);
                    break;
                case UNKNOWN:
                    AstaException.handleInvalidCommand();
                    break;
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
                try {
                    String[] parts = line.split(" \\| ");
                    if (parts.length < 3) {
                        throw new IllegalArgumentException("Invalid task format: " + line);
                    }

                    String type = parts[0];
                    boolean isDone = parts[1].equals("1");
                    String description = parts[2];

                    switch (type) {
                    case "T":
                        tasks.add(new ToDo(description, isDone));
                        break;
                    case "D":
                        if (parts.length < 4) {
                            throw new IllegalArgumentException("Invalid deadline format: " + line);
                        }
                        LocalDateTime by = LocalDateTime.parse(parts[3], DATE_TIME_FORMATTER);
                        tasks.add(new Deadline(description, by, isDone));
                        break;
                    case "E":
                        if (parts.length < 5) {
                            throw new IllegalArgumentException("Invalid event format: " + line);
                        }
                        LocalDateTime from = LocalDateTime.parse(parts[3], DATE_TIME_FORMATTER);
                        LocalDateTime to = LocalDateTime.parse(parts[4], DATE_TIME_FORMATTER);
                        tasks.add(new Event(description, from, to, isDone));
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown task type: " + type);
                    }
                } catch (IllegalArgumentException | DateTimeParseException e) {
                    System.out.println("Skipping corrupted line: " + e.getMessage());
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

    private static void handleMarkCommand(String input) throws AstaException {
        processTaskCommand(input, "mark", true);
    }

    private static void handleUnmarkCommand(String input) throws AstaException {
        processTaskCommand(input, "unmark", false);
    }

    private static void processTaskCommand(String input, String action, boolean markAsDone) throws AstaException {
        int taskNumber = getTaskNumber(input, action);
        if (taskNumber >= 0 && taskNumber < tasks.size()) {
            if (markAsDone) {
                tasks.get(taskNumber).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
            } else {
                tasks.get(taskNumber).markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
            }
            System.out.println(tasks.get(taskNumber));
            saveTasksToFile();
        } else {
            if (markAsDone) {
                AstaException.handleInvalidMarkTaskNumber();
            } else {
                AstaException.handleInvalidUnmarkTaskNumber();
            }
        }
    }

    private static int getTaskNumber(String input, String action) throws AstaException {
        try {
            return Integer.parseInt(input.substring(action.length() + 1).trim()) - 1;
        } catch (NumberFormatException e) {
            AstaException.handleInvalidTaskNumberFormat(action);
            return -1;
        }
    }

    private static void handleTodoCommand(String input) throws AstaException {
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

    private static void handleDeadlineCommand(String input) throws AstaException {
        String[] parts = input.substring(9).split(" /by ");
        if (parts.length < 2 || parts[0].trim().isEmpty()) {
            AstaException.handleInvalidDeadlineInput();
        }
        try {
            LocalDateTime by = LocalDateTime.parse(parts[1].trim(), DATE_TIME_FORMATTER);
            Task newTask = new Deadline(parts[0].trim(), by);
            tasks.add(newTask);
            saveTasksToFile();
            System.out.println("Got it. I've added this task:");
            System.out.println(newTask);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use dd/MM/yyyy HHmm format.");
        }
    }

    private static void handleEventCommand(String input) throws AstaException {
        // Split the input based on the command and delimiters
        String[] parts = input.substring(6).split(" /from | /to ");

        // Check if we have the correct number of parts and validate the description
        if (parts.length < 3 || parts[0].trim().isEmpty()) {
            AstaException.handleInvalidEventInput();
            return;
        }

        String description = parts[0].trim();
        String fromDateString = parts[1].trim();
        String toDateString = parts[2].trim();

        try {
            LocalDateTime from = LocalDateTime.parse(fromDateString, DATE_TIME_FORMATTER);
            LocalDateTime to = LocalDateTime.parse(toDateString, DATE_TIME_FORMATTER);

            Task newTask = new Event(description, from, to);
            tasks.add(newTask);
            saveTasksToFile();

            System.out.println("Got it. I've added this task:");
            System.out.println(newTask);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use dd/MM/yyyy HHmm format.");
        }
    }

    private static void handleDeleteCommand(String input) throws AstaException {
        int taskNumber = getTaskNumber(input, "delete");
        if (taskNumber >= 0 && taskNumber < tasks.size()) {
            Task removedTask = tasks.remove(taskNumber);
            saveTasksToFile();
            System.out.println("Noted. I've removed this task:");
            System.out.println(removedTask);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } else {
            AstaException.handleInvalidDeleteTaskNumber();
        }
    }
}
