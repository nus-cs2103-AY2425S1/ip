package dipsy.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dipsy.parser.DateParser;
import dipsy.task.Deadline;
import dipsy.task.Event;
import dipsy.task.Task;
import dipsy.task.ToDo;
import dipsy.tasklist.TaskList;

/**
 * The Storage class handles reading from and writing to a local file that stores tasks, allowing for tasks
 * to persist when the program is restarted.
 * It supports saving the task list to a CSV file and loading the tasks from the file on startup.
 */
public class Storage {

    /** The file path where tasks are stored. */
    private static final String TASK_FILE_DIRECTORY = "./data/taskTable.csv";

    /** The File object representing the task file. */
    private static final File TASK_FILE = new File(TASK_FILE_DIRECTORY);

    /**
     * Saves the list of tasks to a local file in CSV format.
     * If an error occurs during the save, an error message is printed.
     *
     * @param tasks The list of tasks to save.
     */
    public static void save(ArrayList<Task> tasks) {
        try {
            writeToTaskFile(formatTasks(tasks));
        } catch (IOException e) {
            System.out.println("Failed to save the list of tasks to local disk.");
        }
    }

    /**
     * Loads the list of tasks from the local file.
     * If the file does not exist, an empty TaskList is returned.
     * If an error occurs during loading, an error message is printed.
     *
     * @return A TaskList containing the loaded tasks, or an empty TaskList if the file does not
     *     exist or an error occurs.
     */
    public static TaskList load() {
        if (!TASK_FILE.isFile()) {
            return new TaskList();
        }

        ArrayList<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(TASK_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while loading tasks from file.");
        }
        return new TaskList(tasks);
    }

    /**
     * Formats the list of tasks into a CSV format suitable for saving to a file.
     *
     * @param tasks The list of tasks to format.
     * @return A string representing the tasks in CSV format.
     */
    private static String formatTasks(ArrayList<Task> tasks) {
        StringBuilder res = new StringBuilder();
        for (Task t: tasks) {
            res.append(t.formatToCsv());
            res.append("\n");
        }
        return res.toString();
    }

    /**
     * Creates the task file and its parent directories if they do not exist.
     * If the file is created successfully, a success message is printed.
     * If an error occurs, an error message is printed.
     */
    private static void createTaskFile() {
        try {
            File parentDir = TASK_FILE.getParentFile();
            if (!parentDir.exists()) {
                boolean success = parentDir.mkdirs();
            }
            if (TASK_FILE.createNewFile()) {
                System.out.println("A task file has been created locally.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred when creating the task file.");
        }
    }

    /**
     * Writes the formatted tasks to the task file. If the file does not exist, it is created first.
     *
     * @param formattedTasks The string representing the formatted tasks to write to the file.
     * @throws IOException If an I/O error occurs during writing.
     */
    private static void writeToTaskFile(String formattedTasks) throws IOException {
        if (TASK_FILE.isFile()) {
            try (FileWriter writer = new FileWriter(TASK_FILE_DIRECTORY)) {
                writer.write(formattedTasks);
            } catch (IOException e) {
                System.out.println("An error occurred while writing to the task file.");
            }
        } else {
            createTaskFile();
            writeToTaskFile(formattedTasks);
        }
    }

    /**
     * Parses a line from the task file into a Task object.
     * The method identifies the task type (e.g., ToDo, Deadline, Event) and parses the task accordingly.
     * If the line is in an invalid format or the task type is unknown, the method returns null.
     *
     * @param line The line from the task file to parse.
     * @return The parsed Task object, or null if the line format is invalid or the task type is unknown.
     */
    private static Task parseTask(String line) {
        // Regex to match quoted strings and handle | as a delimiter outside of quotes
        Pattern pattern = Pattern.compile("\"([^\"]*)\"\\s*\\|?");
        Matcher matcher = pattern.matcher(line);

        ArrayList<String> parts = new ArrayList<>();

        while (matcher.find()) {
            parts.add(matcher.group(1).trim());
        }

        if (parts.size() < 3) {
            System.out.println("Invalid task format: " + line);
            return null; // Invalid format, do not add to list
        }

        String statusIcon = parts.get(0);
        boolean isDone = statusIcon.equals("X");
        String taskType = parts.get(1);
        String description = parts.get(2);

        switch (taskType) {
        case "ToDo":
            return new ToDo(description, isDone);
        case "Deadline":
            if (parts.size() < 4) {
                System.out.println("Invalid Deadline task format: " + line);
                return null;
            }
            String by = parts.get(3);
            LocalDate parsedBy = DateParser.parseDate(by);
            return new Deadline(description, isDone, parsedBy);
        case "Event":
            if (parts.size() < 5) {
                System.out.println("Invalid Event task format: " + line);
                return null;
            }
            String start = parts.get(3);
            String end = parts.get(4);
            LocalDate parsedStart = DateParser.parseDate(start);
            LocalDate parsedEnd = DateParser.parseDate(end);
            return new Event(description, isDone, parsedStart, parsedEnd);
        default:
            System.out.println("Unknown task type: " + taskType);
            return null; // Unknown task type, do not add to list
        }
    }
}
