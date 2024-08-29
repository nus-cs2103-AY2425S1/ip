import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class JEFF {
    private static final String LINE = "--------------------------------------------";
    private static final Scanner sc = new Scanner(System.in); // Scanner object to detect user input
    private static ArrayList<Task> taskList = new ArrayList<>();
    private static final String DIR_PATH = "./data";
    private static final String FILE_PATH = DIR_PATH + "/JEFF.txt";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    public static void main(String[] args) {
        // Init sequence
        String logo =
                """
                                                                            ____._________________________________|
                          _____ ___.__.   ____ _____    _____   ____       |    |\\_   _____/\\_   _____/\\_   _____/|
                         /     <   |  |  /    \\\\__  \\  /     \\_/ __ \\      |    | |    __)_  |    __)   |    __)  |
                        |  Y Y  \\___  | |   |  \\/ __ \\|  Y Y  \\  ___/  /\\__|    | |        \\ |     \\    |     \\   |
                        |__|_|  / ____| |___|  (____  /__|_|  /\\___  > \\________|/_______  / \\___  /    \\___  /   |
                              \\/\\/           \\/     \\/      \\/     \\/                    \\/      \\/         \\/    |
                        """;

        System.out.println("Hello there\n" + logo);
        // Load saved files (if any)
        loadData();

        // Chat loop
        beginChat();
    }

    public static void loadData() {
        // Check if the directory exists
        Path file = checkDirectory(DIR_PATH, FILE_PATH);
        // Read the file
        readFile(file);
    }

    private static void readFile(Path file) {
        // Read file line by line
        try {
            List<String> lines = Files.readAllLines(file);
            for (String line : lines) {
                try {
                    processLine(line);
                } catch (LineCorruptedException e) {
                    System.out.println("Error, " + e.getMessage() + ": " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Something went wrong with reading the file!");
        }
    }

    private static void processLine(String line) throws LineCorruptedException {
        // Split the line by ","
        String[] parts = line.split(",");
        
        // Load task into the current tasklist
        switch (parts[0]) {
        case "T":
            if (parts.length != 3) throw new LineCorruptedException("Incorrect Format");
            taskList.add(new ToDo(parts[2]));
            break;
        case "D":
            if (parts.length != 4) throw new LineCorruptedException("Incorrect Format");
            try {
                taskList.add(
                        new Deadline(parts[2],
                            LocalDateTime.parse(parts[3].trim(), FORMATTER)
                        )
                );
            } catch (DateTimeParseException e) {
                throw new LineCorruptedException("Incorrect date format at the file!");
            }
            break;
        case "E":
            if (parts.length != 5) throw new LineCorruptedException("Incorrect Format");
            try {
                taskList.add(
                        new Event(parts[2],
                                LocalDateTime.parse(parts[3].trim(), FORMATTER),
                                LocalDateTime.parse(parts[4].trim(), FORMATTER)
                        )
                );
            } catch (DateTimeParseException e) {
                throw new LineCorruptedException("Incorrect date format at the file!");
            }
            break;
        default:
            throw new LineCorruptedException("Unsupported task");
        }
        // check if the task is already completed
        if (Objects.equals(parts[1], "X")) {
            taskList.get(taskList.size() - 1).markDone();
        }
    }

    private static Path checkDirectory(String DIR_PATH, String FILE_PATH) {
        // Check if directory does not exist
        Path directory = Paths.get(DIR_PATH);
        if (!Files.exists(directory)) {
            try {
                Files.createDirectories(directory);
            } catch (IOException e) {
                System.out.println("Failed to create directory");
            }
        }

        // Check if file exists
        Path file = Paths.get(FILE_PATH);
        if (!Files.exists(file)) {
            try {
                Files.createFile(file);
            } catch (IOException e) {
                System.out.println("Failed to create directory");
            }
        }

        return file;
    }

    public static void beginChat() {
        System.out.println("What can I do for you?");
        boolean chatCont = true;
        while (chatCont) {
            chatCont = chatEvent();
        }
        exitChat();
    }

    private static boolean chatEvent() {
        System.out.println(LINE);
        String input = sc.nextLine().trim();
        String[] parts = input.split(" ", 2);
        try {
            if (handleCommand(parts)) return false;
        } catch (JEFFException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    private static boolean handleCommand(String[] parts) throws JEFFException {
        switch (parts[0].toLowerCase()) {
        case "bye":
            return true;
        case "list":
            printList();
            break;
        case "print":
            if (parts.length == 2) {
                printTasksOnDate(parts[1]);
            } else {
                throw new JEFFException("You must provide a date after the command!");
            }
            break;
        case "mark":
            if (parts.length == 2 && isNumeric(parts[1])) {
                markDone(Integer.parseInt(parts[1]));
            } else {
                throw new JEFFException("You must provide only one number after mark!");
            }
            break;
        case "unmark":
            if (parts.length == 2 && isNumeric(parts[1])) {
                markNotDone(Integer.parseInt(parts[1]));
            } else {
                throw new JEFFException("You must provide only one number after unmark!");
            }
            break;
        case "delete":
            if (parts.length == 2 && isNumeric(parts[1])) {
                deleteTask(Integer.parseInt(parts[1]));
            } else {
                throw new JEFFException("You must provide only one number after unmark!");
            }
            break;
        case "todo":
            if (parts.length != 2) {
                throw new JEFFException("You must provide a valid task to do!");
            } else {
                addTask(parts[1], "todo");
            }
            break;
        case "deadline":
            if (parts.length != 2) {
                throw new JEFFException("You must provide a valid deadline task!");
            } else {
                addTask(parts[1], "deadline");
            }
            break;
        case "event":
            if (parts.length != 2) {
                throw new JEFFException("You must provide a valid event task!");
            } else {
                addTask(parts[1], "event");
            }
            break;
        default:
            throw new JEFFException("That is not a valid command!");
        }
        return false;
    }

    private static void printTasksOnDate(String dateString) throws JEFFException {
        try {
            DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.parse(dateString, DATE_FORMATTER);
            System.out.println("Tasks due on " + date.format(DATE_FORMATTER) + ":");
            boolean found = false;
            for (Task task : taskList) {
                if (task instanceof Deadline) {
                    Deadline deadlineTask = (Deadline) task;
                    if (deadlineTask.getDueDate().toLocalDate().equals(date)) {
                        System.out.println(deadlineTask);
                        found = true;
                    }
                }
            }

            if (!found) {
                System.out.println("No tasks due on this date.");
            }
        } catch (DateTimeParseException e) {
            throw new JEFFException("Invalid date format! Please use 'DD/MM/YYYY'.");
        }
    }

    private static void deleteTask(int i) throws JEFFException {
        if (i <= 0 || i > taskList.size()) {
            throw new JEFFException("The number is outside the range!");
        }
        System.out.println("Ok, I will delete this task:");
        System.out.printf("%s\n", taskList.get(i - 1));
        taskList.remove(i - 1);
    }

    private static void markNotDone(int i) throws JEFFException {
        if (i <= 0 || i > taskList.size()) {
            throw new JEFFException("The number is outside the range!");
        }
        taskList.get(i - 1).markNotDone();
        updateSave(i - 1);
        System.out.println("Ok, I marked this as undone:");
        System.out.printf("%s\n", taskList.get(i - 1));
    }

    private static void markDone(int i) throws JEFFException {
        if (i <= 0 || i > taskList.size()) {
            throw new JEFFException("The number is outside the range!");
        }
        taskList.get(i - 1).markDone();
        updateSave(i - 1);
        System.out.println("Alrighty, I marked this as done:");
        System.out.printf("%s\n", taskList.get(i - 1));
    }

    // Overloaded `addTask` to have more functionality
    private static void addTask(String input, String task) throws JEFFException {

        switch (task.toLowerCase()) {
        case "todo":
            taskList.add(new ToDo(input));
            break;
        case "deadline":
            String[] deadline_parts = input.split("/by", 2);
            if (deadline_parts.length < 2) {
                throw new JEFFException("You did not follow the format for providing a deadline!" +
                        "\nIt should be: task /by date");
            }
            try {
                taskList.add(
                        new Deadline(deadline_parts[0].trim(),
                                LocalDateTime.parse(deadline_parts[1].trim(), FORMATTER)
                        )
                );
            } catch (DateTimeParseException e) {
                throw new JEFFException("You need to format your dates as follows: DD/MM/YYYY HHMM");
            }
            break;
        case "event":
            String[] event_parts = input.split("/from | /to ", 3);
            if (event_parts.length < 3) {
                throw new JEFFException("You did not follow the format for providing a deadline!" +
                        "\nIt should be: task /from date /to date");
            }
            try {
                taskList.add(
                        new Event(event_parts[0].trim(),
                                LocalDateTime.parse(event_parts[1].trim(), FORMATTER),
                                LocalDateTime.parse(event_parts[2].trim(), FORMATTER)
                        )
                );
            } catch (DateTimeParseException e) {
                throw new JEFFException("You need to format your dates as follows: DD/MM/YYYY HHMM");
            }
            break;
        default:
            throw new JEFFException("Unsupported Task!");
        }
        // Save the task
        saveTask();
        System.out.printf("added: %s\n", taskList.get(taskList.size() - 1));
    }

    private static void saveTask() {
        // write to the file the newly added task
        String taskAsCSV = taskList.get(taskList.size() - 1).saveAsCSV();
        // try to append string to end of data file
        try {
            Path file = Paths.get(FILE_PATH);
            // Check if the file already has content
            boolean fileHasContent = Files.size(file) > 0;
            if (fileHasContent) {
                Files.writeString(file, "\n" + taskAsCSV);
            } else {
                Files.writeString(file, taskAsCSV);
            }
        } catch (IOException e) {
            System.out.println("An error occurred saving the file");
        }
    }

    private static void updateSave(int lineNumber) {
        Path file = Paths.get(FILE_PATH);

        try {
            // Read all lines from the file
            List<String> lines = Files.readAllLines(file);

            // Update the specific line (lineNumber is zero-based)
            if (lineNumber >= 0 && lineNumber < lines.size()) {
                lines.set(lineNumber, taskList.get(lineNumber).saveAsCSV());
            } else {
                System.out.println("Invalid line number.");
                return;
            }

            // Write the updated lines back to the file
            Files.write(file, lines);
        } catch (IOException e) {
            System.out.println("Something went wrong updating the file");
        }
    }

    private static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal
    }

    private static void printList() {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, taskList.get(i));
        }
    }

    public static void exitChat() {
        System.out.println(LINE);
        System.out.println("Bye for now!");
        sc.close();
    }
}