package hana.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import hana.HanaException;
import hana.task.Deadline;
import hana.task.Event;
import hana.task.Task;
import hana.task.ToDo;

/**
 * Handles reading and writing of task to a file.
 */
public class Storage {
    private static final String FILE_PATH = "./data/hana.txt";
    private static final Path FILE = Paths.get(FILE_PATH);
    private static final Path DIR = Paths.get("./data");
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static ArrayList<Task> tasks;

    /**
     * Constructs a new Storage with tasks.
     *
     * @param tasks The list of tasks.
     */
    public Storage(ArrayList<Task> tasks) {
        Storage.tasks = tasks;
    }

    /**
     * Loads all task in file to tasks.
     *
     * @throws HanaException If error occurs when loading.
     */
    public void load() throws HanaException {
        if (!Files.exists(DIR)) {
            try {
                Files.createDirectories(DIR);
            } catch (IOException e) {
                throw new HanaException("Failed to create the directory for saving task");
            }
        }

        if (!Files.exists(FILE)) {
            try {
                Files.createFile(FILE);
            } catch (IOException e) {
                throw new HanaException("Failed to create the directory for saving task");
            }
        }

        // Assert that the file is created and directory exists
        assert Files.exists(DIR) : "Directory does not exist after creation attempt";
        assert Files.exists(FILE) : "File does not exist after creation attempt";

        try (BufferedReader br = Files.newBufferedReader(FILE)) {
            String line = br.readLine();
            while (line != null) {
                String[] parts = line.split(" \\| ");
                Task task;
                switch (parts[0]) {
                case "T":
                    task = new ToDo(parts[2]);
                    break;
                case "D":
                    task = new Deadline(parts[2], LocalDateTime.parse(parts[3], FORMATTER));
                    break;
                case "E":
                    task = new Event(parts[2], LocalDateTime.parse(parts[3], FORMATTER),
                            LocalDateTime.parse(parts[3], FORMATTER));
                    break;
                default:
                    System.out.println("Failed to read saved task. File may be corrupted. Skipping line");
                    continue;
                    // Fallthrough
                }
                task.setDone(parts[1].equals("1"));
                tasks.add(task);
                line = br.readLine();
            }
        } catch (IOException | DateTimeParseException e) {
            System.out.println("Failed to read saved tasks. File may be corrupted.");
        }
    }

    /**
     * Saves all task in file to tasks.
     *
     * @throws HanaException If error occurs when saving.
     */
    public void save() throws HanaException {
        try (BufferedWriter bw = Files.newBufferedWriter(FILE)) {
            for (Task task : tasks) {
                String taskString = task.fileString();
                // Assert that the task string is not null or empty
                assert taskString != null && !taskString.isEmpty() : "Task string is null or empty";
                bw.write(taskString);
                bw.newLine();
            }
        } catch (IOException e) {
            throw new HanaException("Failed to save tasks.");
        }
    }
}
