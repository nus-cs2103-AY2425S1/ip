package bob.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import bob.tasks.Deadline;
import bob.tasks.Event;
import bob.tasks.Task;
import bob.tasks.Todo;
import bob.exception.BobException;

/**
 * Handles loading and saving of tasks from a file.
 * The Storage class manages reading from and writing to a specified file path.
 */
public class Storage {
    private String filePath;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Constructs a Storage object with the specified file path.
     * @param filePath the path to the file where tasks are saved and loaded from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Load tasks from the specified file.
     * @return ArrayList of tasks loaded from the file.
     * @throws BobException if an error occurs while loading tasks from the file.
     */
    public ArrayList<Task> load() throws BobException {
        try {
            File file = initializeFile();
            Scanner scanner = new Scanner(file);
            return loadCurrentTasks(scanner);
        } catch (IOException e) {
            throw new BobException("Failed to load Tasks from file");
        }
    }

    /**
     * Initializes the file by creating it if it does not exist.
     *
     * @return the initialized File object.
     * @throws IOException if an error occurs while creating the file.
     */
    private File initializeFile() throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        return file;
    }

    /**
     * Loads current tasks from the file using a Scanner.
     *
     * @param scanner the Scanner object used to read the file.
     * @return an ArrayList of tasks parsed from the file.
     * @throws BobException if an error occurs while parsing tasks.
     */
    private ArrayList<Task> loadCurrentTasks(Scanner scanner) throws BobException {
        ArrayList<Task> tasks = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Task task = parseTask(line);
            tasks.add(task);
        }
        return tasks;
    }

    /**
     * Parses a task from a line of text.
     *
     * @param line the line of text representing a task.
     * @return the parsed Task object.
     * @throws BobException if the task type is unknown or the format is incorrect.
     */
    private Task parseTask(String line) throws BobException {
        String[] parts = line.split(" \\| ");
        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        Task task = createTaskFromParts(taskType, parts);
        if (isDone) {
            task.mark();
        }
        return task;
    }

    /**
     * Creates a Task object based on the task type and task details.
     *
     * @param taskType the type of the task ('T' for Todo, 'D' for Deadline, 'E' for Event).
     * @param parts    the array containing task details.
     * @return the created Task object.
     * @throws BobException if the task type is unknown.
     */
    private Task createTaskFromParts(String taskType, String[] parts) throws BobException {
        switch (taskType) {
        case "T":
            return new Todo(parts[2]);
        case "D":
            LocalDateTime deadlineDateTime = parseDeadline(parts[3]);
            return new Deadline(parts[2], deadlineDateTime);
        case "E":
            return new Event(parts[2], parts[3], parts[4]);
        default:
            throw new BobException("Unknown task type");
        }
    }

    /**
     * Parses a deadline date string into a LocalDateTime object.
     * Attempts to parse the date string with two different formats:
     * 'dd/MM/yyyy HHmm' and 'dd/MM/yyyy'.
     *
     * @param dateString the string representation of the deadline.
     * @return the LocalDateTime object parsed from the string.
     * @throws BobException if the date format is invalid.
     */
    private LocalDateTime parseDeadline(String dateString) throws BobException {
        try {
            return LocalDateTime.parse(dateString, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            try {
                LocalDate deadlineDate = LocalDate.parse(dateString, DATE_FORMATTER);
                return deadlineDate.atStartOfDay();
            } catch (DateTimeParseException ex) {
                throw new BobException("Invalid date format in file!");
            }
        }
    }

    /**
     * Saves the list of tasks to the specified file.
     *
     * @param tasks the ArrayList of tasks to be saved.
     * @throws BobException if an error occurs while writing tasks to the file.
     */
    public void save(ArrayList<Task> tasks) throws BobException {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Task task : tasks) {
                writer.write(task.fileFormat() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new BobException("Failed to save Tasks to file");
        }
    }
}
